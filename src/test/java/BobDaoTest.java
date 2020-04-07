
import bob.dao.SQLBobDao;
import bob.domain.Event;
import bob.domain.Reminder;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;

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
        this.testReminder = new Reminder(today, "tämä on testimuistutus!");
    }

    @After
    public void tearDown() throws SQLException {
        Connection c = bobDao.getConnection();
        Statement s = c.createStatement();
        s.execute("DROP TABLE IF EXISTS Muistutukset");
        s.execute("DROP TABLE IF EXISTS Tapahtumat");
    }

    @Test
    public void bobDaoObjectIsNotNull() {
        assertThat(bobDao, is(notNullValue()));
    }

    @Test
    public void addReminderToDatabase() {
        Reminder reminder = new Reminder(today, "tämä on testimuistutus!");
        assertThat(bobDao.addReminderToDatabase(reminder), equalTo("uusi muistutus lisätty:\n" + today + "\n" + "tämä on testimuistutus!"));
    }

    @Test
    public void reminderIsFoundInDatabase() {
        bobDao.addReminderToDatabase(testReminder);
        List<Reminder> reminders = bobDao.getTodaysReminders(today);
        assertThat(reminders.get(0).getDescription(), equalTo("tämä on testimuistutus!"));
    }

    @Test
    public void removeOldRemoves() {
        bobDao.addReminderToDatabase(new Reminder(today.minusDays(1), "tämä on testimuistutus!"));
        bobDao.removeOld(today);
        assertThat(bobDao.getTodaysEvents(today.minusDays(1)).isEmpty(), is(true));
    }

    @Test
    public void addEventToDatabase() {
        Event event = new Event(today, now, "tämä on testitapahtuma!");
        assertThat(bobDao.addEventToDatabase(event), equalTo("uusi tapahtuma lisätty:\n" + today + "\n" + now + "\n" + "tämä on testitapahtuma!"));
    }

    @Test
    public void eventIsFoundInDatabase() {
        bobDao.addEventToDatabase(new Event(today, now, "tämä on testitapahtuma!"));
        List<Event> events = bobDao.getTodaysEvents(today);
        assertThat(events.get(0).getDescription(), equalTo("tämä on testitapahtuma!"));
    }

}
