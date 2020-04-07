
import bob.dao.BobDao;
import bob.dao.FakeBobDao;
import bob.domain.BobService;
import bob.domain.Event;
import bob.domain.Reminder;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

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
        this.bobService = new BobService(bobDao, today);
    }

    @Test
    public void bobServiceExists() {
        assertThat(bobService, is(notNullValue()));
    }

    @Test
    public void addRemiderToDatabaseWorks() {
        assertThat(bobService.createReminder(today, "tämä on testimuistutus!"), equalTo("uusi muistutus lisätty:\n" + today + "\ntämä on testimuistutus!"));
    }

    @Test
    public void findsTodaysReminders() {
        bobService.createReminder(today, "lisätään testimuistutus!");
        assertThat(bobService.getTodaysReminders().get(0).getDescription(), equalTo("lisätään testimuistutus!"));
    }

    @Test
    public void addEventToDatabaseWorks() {
        assertThat(bobService.createEvent(today, now, "tämä on testitapahtuma!"), equalTo("uusi tapahtuma lisätty:\n" + today + "\n" + now + "\ntämä on testitapahtuma!"));
    }

    @Test
    public void findsTodaysEvents() {
        bobService.createEvent(today, now, "lisätään testitapahtuma!");
        assertThat(bobService.getTodaysEvents().get(0).getDescription(), equalTo("lisätään testitapahtuma!"));
    }

    @Test
    public void removesOld() {
        assertTrue(bobService.removeOld(today));
    }

    @Test
    public void todaysRight() {
        assertThat(bobService.getToday(), equalTo(today));
    }
}
