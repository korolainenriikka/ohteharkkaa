import bob.dao.BobDao;
import bob.domain.BobService;
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
        this.bobDao = new BobDao();
        this.bobService = new BobService(bobDao);
    }
    
    @Test
    public void bobServiceExists(){
        assertThat(bobService, is(notNullValue()));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    //atm: ei testitarvetta; ainoot viitteet daoon
}
