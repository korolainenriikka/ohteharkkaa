import bob.domain.Reminder;
import java.time.LocalDate;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class ReminderTest {
    
    private LocalDate today;
    private Reminder reminder;
    
    @Before
    public void setUp() {
        this.today = LocalDate.now();
        this.reminder = new Reminder(today, "tämä on testimuistutus");
    }
    
    @Test
    public void reminderExists(){
        assertThat(reminder, is(notNullValue()));
    }
    
    @Test
    public void getDateWorks(){
        assertThat(reminder.getDate(), equalTo(today));
    }
    
    @Test
    public void getDescriptionWorks(){
        assertThat(reminder.getDescription(), equalTo("tämä on testimuistutus"));
    }


}
