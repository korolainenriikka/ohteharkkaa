
import bob.dao.*;
import bob.domain.*;
import java.time.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.*;

public class BobServiceTest {

    private BobService bobService;
    private BobDao bobDao;
    private LocalDate today;
    private LocalTime now;

    @Before
    public void setUp() {
        this.today = LocalDate.now();
        this.now = LocalTime.now();
        this.bobDao = new FakeBobDao();
        this.bobService = new BobService(bobDao);
    }

    @Test
    public void addRemiderToDatabaseWorks() {
        assertThat(bobService.createNewReminder(today, ":)"), equalTo("uusi muistutus lisätty:\n" + today + "\n:)"));
    }

    @Test
    public void addFalseReminderToDatabaseReturnsError() {
        assertThat(bobService.createNewReminder(null, null), equalTo("virhe!"));
    }

    @Test
    public void addEventToDatabaseWorks() {
        assertThat(bobService.createNewEvent(today, now, ":D"), equalTo("uusi tapahtuma lisätty:\n" + today + "\n" + now + "\n:D"));
    }

    @Test
    public void addEventWtihNoTimeToDatabase() {
        assertThat(bobService.createNewEvent(today, null, ":D"), equalTo("uusi tapahtuma lisätty:\n" + today + "\n:D"));
    }

    @Test
    public void addFalseEventToDatabaseReturnsError() {
        assertThat(bobService.createNewEvent(null, null, null), equalTo("virhe!"));
    }

    @Test
    public void removesOld() {
        assertTrue(bobService.removeOld(today));
    }

    @Test
    public void findsTodaysEventsToStrings() {
        bobService.createNewEvent(today, now, ":D");
        assertThat(bobService.getDaysItemsAsString(Event.class, today).get(0), equalTo("klo " + now + ": :D"));
    }

    @Test
    public void findsTodaysRemindersToStrings() {
        bobService.createNewReminder(today, ":)");
        assertThat(bobService.getDaysItemsAsString(Reminder.class, today).get(0), equalTo(":)"));
    }

    @Test
    public void reminderMovesToNextDay() {
        bobService.moveReminderToNextDay(":)", today);
        today = today.plusDays(1);
        assertThat(bobService.getDaysItemsAsString(Reminder.class, today), hasItem("! :)"));
    }

    @Test
    public void workTimeUpdates() {
        bobService.saveWorkedTime(LocalTime.parse("00:00:01"), today);
        assertThat(bobService.getWorkTime(today), equalTo(LocalTime.parse("00:00:01")));
    }

    @Test
    public void reminderIsDeleted() {
        bobService.createNewReminder(today, ":)");
        bobService.deleteReminder(":)", today);
        assertTrue(bobService.getDaysItemsAsString(Reminder.class, today).isEmpty());
    }
}
