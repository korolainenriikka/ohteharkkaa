
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
    public void bobServiceExists() {
        assertThat(bobService, is(notNullValue()));
    }

    @Test
    public void addRemiderToDatabaseWorks() {
        assertThat(bobService.createNewReminder(today, ":)"), equalTo("uusi muistutus lisätty:\n" + today + "\n:)"));
    }

    @Test
    public void addEventToDatabaseWorks() {
        bobService.createNewEvent(today, now, ":D");
        assertThat(bobService.createNewEvent(today, now, ":D"), equalTo("uusi tapahtuma lisätty:\n" + today + "\n" + now + "\n:D"));
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
        assertThat(bobService.getDaysItemsAsString(Reminder.class, today), hasItem(":)"));
    }
}
