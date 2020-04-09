
import bob.dao.SQLBobDao;
import bob.domain.*;
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
        Connection c = bobDao.getConnection();
        Statement s = c.createStatement();
        s.execute("DROP TABLE IF EXISTS Reminders");
        s.execute("DROP TABLE IF EXISTS Events");
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
    public void addReminderToDatabase() {
        Reminder reminder = new Reminder(today, ":)");
        assertThat(bobDao.addReminderToDatabase(reminder), is(true));
    }

    //tässä tesstissä mitäkummaaaa????
    @Test
    public void removeOldRemoves() {
        bobDao.addReminderToDatabase(new Reminder(today.minusDays(1), ":)"));
        assertThat(bobDao.removeOld(today), is(true));
    }

    @Test
    public void reminderIsFoundInDatabase() {
        bobDao.addReminderToDatabase(testReminder);
        List<CalendarItem> reminders = bobDao.getTodaysReminders(today);
        assertThat(reminders.get(0).toString(), equalTo(":)"));
    }

    @Test
    public void eventIsFoundInDatabase() {
        bobDao.addEventToDatabase(new Event(today, now, ":D"));
        List<CalendarItem> events = bobDao.getTodaysEvents(today);
        assertThat(events.get(0).toString(), equalTo("klo " + now + ": :D"));
    }
}
