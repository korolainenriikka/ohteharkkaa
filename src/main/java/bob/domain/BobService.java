package bob.domain;

import bob.dao.BobDao;
import java.time.LocalDate;
import java.util.List;

public class BobService {
    
    private BobDao bobDao;
    private LocalDate today;

    public BobService(BobDao bobDao, LocalDate today) {
        this.bobDao = bobDao;
        this.today = today;
    }
    
    //uuden muistutuksen lisääminen
    public String createReminder(LocalDate date, String description) {
        Reminder newReminder = new Reminder(date, description);
        return bobDao.addReminderToDatabase(newReminder);
    }  

    public List<String> getTodaysReminders() {
        return bobDao.getTodaysReminders(today);
    }

    public LocalDate getToday() {
        return today;
    }

    public void removeOldReminders(LocalDate today) {
        bobDao.removeOldReminders(today);
    }
    
}