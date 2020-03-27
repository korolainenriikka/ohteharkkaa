import bob.domain.Reminder;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class ReminderTest {
    
    private Reminder reminder;
    
    @Before
    public void setUp() {
        this.reminder = new Reminder("2020-03-27", "tämä on testimuistutus");
    }
    
    @Test
    public void reminderExists(){
        assertThat(reminder, is(notNullValue()));
    }
    
    @Test
    public void getDateWorks(){
        assertThat(reminder.getDate(), equalTo("2020-03-27"));
    }
    
    @Test
    public void getDescriptionWorks(){
        assertThat(reminder.getDescription(), equalTo("tämä on testimuistutus"));
    }


}
