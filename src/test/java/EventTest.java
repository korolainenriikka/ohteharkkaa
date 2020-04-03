import bob.domain.Reminder;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class EventTest {
    
    private LocalDate today;
    private LocalTime now;
    private Reminder reminder;
    
    @Before
    public void setUp() {
        this.today = LocalDate.now();
        this.now = LocalTime.now();
        this.reminder = new Reminder(today, "tämä on testitapahtuma");
    }
    
    @Test
    public void reminderExists(){
        assertThat(reminder, is(notNullValue()));
    }
    
}
