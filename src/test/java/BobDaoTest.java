
import bob.dao.SQLBobDao;
import bob.domain.*;
import java.io.File;
import java.sql.*;
import java.time.*;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.*;

public class BobDaoTest {

    private SQLBobDao bobDao;
    private Reminder testReminder;
    private LocalDate today;
    private LocalTime now;

    @Before
    public void setUp() {
        today = LocalDate.now();
        now = LocalTime.now();
        this.bobDao = new SQLBobDao("jdbc:sqlite:testData.db");
        this.testReminder = new Reminder(today, ":)");
    }

    @After
    public void tearDown() throws SQLException {
        File file = new File("testData.db");
        file.delete();
    }

    @Test
    public void bobDaoObjectIsNotNull() {
        assertThat(bobDao, is(notNullValue()));
    }

    @Test
    public void addEventToDatabase() {
        Event event = new Event(today, now, ":D");
        assertThat(bobDao.addEventToDatabase(event), is(true));
    }
    
    @Test
    public void addEventWithNullTimeToDatabase(){
        Event event = new Event(today, null, ":D");
        assertThat(bobDao.addEventToDatabase(event), is(true));
    }

    @Test
    public void addReminderToDatabase() {
        Reminder reminder = new Reminder(today, ":)");
        assertThat(bobDao.addReminderToDatabase(reminder), is(true));
    }

    @Test
    public void removeOldRemoves() {
        bobDao.addReminderToDatabase(new Reminder(today.minusDays(1), ":)"));
        bobDao.removeOld(today);
        assertThat(bobDao.getTodaysReminders(today.minusDays(1)).isEmpty(), is(true));
    }

    @Test
    public void reminderIsFoundInDatabase() {
        bobDao.addReminderToDatabase(testReminder);
        List<CalendarItem> reminders = bobDao.getTodaysReminders(today);
        assertThat(reminders.get(0).toString(), equalTo(":)"));
    }

    @Test
    public void eventIsFoundInDatabaseReturnsSorted() {
        bobDao.addEventToDatabase(new Event(today, null, ":D"));
        bobDao.addEventToDatabase(new Event(today, now, ":D"));
        List<CalendarItem> events = bobDao.getTodaysEventsSorted(today);
        assertThat(events.get(0).toString(), equalTo(":D"));
        assertThat(events.get(1).toString(), equalTo("klo " + now + ": :D"));
    }
    
    @Test
    public void firstGetWorktimeInsertsEmptyTimeData(){
        assertThat(bobDao.getWorkTime(today), equalTo(LocalTime.parse("00:00:00")));
    }
    
    @Test 
    public void worktimeUpdatesInDatabase(){
        bobDao.getWorkTime(today);
        bobDao.updateWorkTime(LocalTime.parse("12:34:56"), today);
        assertThat(bobDao.getWorkTime(today), equalTo(LocalTime.parse("12:34:56")));
    }
}
