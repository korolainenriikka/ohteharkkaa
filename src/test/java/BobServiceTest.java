import bob.dao.BobDao;
import bob.dao.FakeBobDao;
import bob.domain.BobService;
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
    
    @Before
    public void setUp() {
        this.bobDao = new FakeBobDao();
        this.bobService = new BobService(bobDao);
    }
    
    @Test
    public void bobServiceExists(){
        assertThat(bobService, is(notNullValue()));
    }
    
    @Test
    public void addToDatabaseWorks(){
        assertThat(bobService.createReminder("11-02-2022", "heimoi"), equalTo("uusi muistutus lisätty:\n11-02-2022\nheimoi"));
    }
    
    @Test
    public void findsTodaysReminders(){
        bobService.createReminder(bobService.getToday(), "lisätään testimuistutus! :)");
        assertThat(bobService.getTodaysReminders(), hasItem("lisätään testimuistutus! :)"));
    }
}
