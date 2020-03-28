import bob.dao.FakeBobDao;
import bob.domain.BobService;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class BobServiceTest {
    //täytyy toteuttaa rajapinnat ennen ko tää toimii
    private BobService bobService;
    private FakeBobDao bobDao;
    
    @Before
    public void setUp() {
        this.bobDao = new FakeBobDao();
        //this.bobService = new BobService(bobDao);
    }
    
    @Test
    public void bobServiceExists(){
        //assertThat(bobService, is(notNullValue()));
    }
    
    @Test
    public void addToDatabaseWorks(){
        //fake-luokka tarvii rajapintarakenteen!
    }


}
