
import bob.BobUi;
import org.junit.Before;
import org.junit.Test;


public class BobUiTest {
    
    private BobUi bobui;
    
    @Before
    public void SetUp(){
        bobui = new BobUi();
    }
    
    //testi: luo olio
    
    @Test
    public void launchWorks(){
        bobui.launch(BobUi.class);
        // täällä testataan että sovellus käynnistyy
    }
}
