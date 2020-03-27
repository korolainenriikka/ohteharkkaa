

import bob.dao.BobDao;
import bob.domain.Reminder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class BobDaoTest {

    private BobDao bobDao;
    private String testDescription;

    @Before
    public void setUp() {
        this.bobDao = new BobDao();
        this.testDescription = "tämä testikuvaus lisätään tietokantaan";
    }

    @Test
    public void bobDaoObjectIsNotNull() {
        assertThat(bobDao, is(notNullValue()));
    }

    @Test
    public void addReminderToDatabase() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(date);
        Reminder reminder = new Reminder(today, testDescription);
        assertThat(bobDao.addReminderToDatabase(reminder), equalTo("uusi muistutus lisätty:\n"+ today +"\n" + testDescription));
    }

    //@Test
    //public void reminderWithFalseDateWontAddToDatabase(){
        //Reminder reminder = new Reminder("2020-02-31", "tämä testikuvaus ei saisi löytyä tietokannasta");
        //assertThat();
        //miten tietetään että tietokantaan menee vain ns. valideja päivämääriä, ei esim. edellämainittuja? käyttöliittymässä atm mahdollista että näin käy!
    //}
    
    @Test
    public void reminderIsFoundInDatabase(){
        List<String> reminders = bobDao.getTodaysReminders();
        assertThat(reminders, hasItem(testDescription));
    }
    
}
