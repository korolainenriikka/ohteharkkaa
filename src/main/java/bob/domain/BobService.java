package bob.domain;

import bob.dao.BobDao;
import java.util.List;

public class BobService {
    
    private BobDao bobDao;

    public BobService(BobDao bobDao) {
        this.bobDao = bobDao;
    }
    
    //uuden muistutuksen lisääminen
    public String createReminder(String date, String description) {
        Reminder newReminder = new Reminder(date, description);
        return bobDao.createReminder(newReminder);
    }  

    public List<String> getTodaysReminders() {
        return bobDao.getTodaysReminders();
    }
    
}
