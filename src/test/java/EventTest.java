import bob.domain.Event;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class EventTest {
    
    private LocalDate today;
    private LocalTime now;
    private Event event;
    
    @Before
    public void setUp() {
        this.today = LocalDate.now();
        this.now = LocalTime.now();
        this.event = new Event(today, now, "tämä on testitapahtuma");
    }
    
    @Test
    public void reminderExists(){
        assertThat(event, is(notNullValue()));
    }
    
    @Test
    public void getDateReturnsDate(){
        assertThat(event.getDate(), equalTo(today));      
    }
    
    @Test
    public void getTimeReturnsTimee(){
        assertThat(event.getTime(), equalTo(now));
    }
    
    @Test
    public void getDescriptionReturnsDescription(){
        assertThat(event.getDescription(), equalTo("tämä on testitapahtuma"));
    }
    
    @Test
    public void eventHappeningBeforeComparesFalse(){
        Event otherEvent = new Event(today, LocalTime.parse("00:00"), "moikkumoi");
        assertThat(event.compareTo(otherEvent), is(1));
    }
    
    @Test
    public void testToString(){
        assertThat(event.toString(), equalTo("klo " + now  + ": tämä on testitapahtuma"));
    }
}
