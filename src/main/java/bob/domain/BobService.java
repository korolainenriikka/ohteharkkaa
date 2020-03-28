package bob.domain;

import bob.dao.BobDao;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BobService {
    
    private BobDao bobDao;
    private String today;

    public BobService(BobDao bobDao) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        today = formatter.format(date);
        
        this.bobDao = bobDao;
    }
    
    //uuden muistutuksen lisääminen
    public String createReminder(String date, String description) {
        Reminder newReminder = new Reminder(date, description);
        return bobDao.addReminderToDatabase(newReminder);
    }  

    public List<String> getTodaysReminders() {
        return bobDao.getTodaysReminders(today);
    }

    public String getToday() {
        return today;
    }
    
}