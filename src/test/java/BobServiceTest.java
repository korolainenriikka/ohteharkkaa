import bob.dao.BobDao;
import bob.dao.FakeBobDao;
import bob.domain.BobService;
import java.time.LocalDate;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class BobServiceTest {
    private BobService bobService;
    private BobDao bobDao;
    private LocalDate today;
    
    @Before
    public void setUp() {
        this.today = LocalDate.now();
        this.bobDao = new FakeBobDao();
        this.bobService = new BobService(bobDao, today);
    }
    
    @Test
    public void bobServiceExists(){
        assertThat(bobService, is(notNullValue()));
    }
    
    @Test
    public void addToDatabaseWorks(){
        assertThat(bobService.createReminder(today, "heimoi"), equalTo("uusi muistutus lisätty:\n"+ today +"\nheimoi"));
    }
    
    @Test
    public void findsTodaysReminders(){
        bobService.createReminder(bobService.getToday(), "lisätään testimuistutus! :)");
        assertThat(bobService.getTodaysReminders(), hasItem("lisätään testimuistutus! :)"));
    }
}
