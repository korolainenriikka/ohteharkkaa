

import bob.dao.SQLBobDao;
import bob.domain.Reminder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class BobDaoTest {

    private SQLBobDao bobDao;
    private String testDescription;
    private LocalDate today;

    @Before
    public void setUp() {
        today = LocalDate.now();
        this.bobDao = new SQLBobDao("jdbc:sqlite:testData.db");
        this.testDescription = "tämä testikuvaus lisätään tietokantaan";
    }

    @Test
    public void bobDaoObjectIsNotNull() {
        assertThat(bobDao, is(notNullValue()));
    }

    @Test
    public void addReminderToDatabase() {
        Reminder reminder = new Reminder(today, testDescription);
        assertThat(bobDao.addReminderToDatabase(reminder), equalTo("uusi muistutus lisätty:\n"+ today +"\n" + testDescription));
    }

    //@Test
    //public void reminderWithFalseDateWontAddToDatabase(){
        //Reminder reminder = new Reminder("2020-02-31", "tämä testikuvaus ei saisi löytyä tietokannasta");
        //assertThat();
        //miten tiedetään että tietokantaan menee vain ns. valideja päivämääriä, ei esim. edellämainittua
    //}
    
    @Test
    public void reminderIsFoundInDatabase(){
        List<String> reminders = bobDao.getTodaysReminders(today);
        assertThat(reminders, hasItem(testDescription));
    }
    
    
    
}
