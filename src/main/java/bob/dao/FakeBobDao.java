package bob.dao;

import bob.domain.Reminder;
import java.util.ArrayList;
import java.util.List;

public class FakeBobDao implements BobDao{
    private ArrayList<Reminder> reminders;

    public FakeBobDao() {
        this.reminders = new ArrayList<>();
    }
    
    public String addReminderToDatabase(Reminder newReminder) {
        reminders.add(newReminder);
        return("uusi muistutus lisätty:\n"+ newReminder.getDate() +"\n" + newReminder.getDescription());
    }
    
     public List<String> getTodaysReminders(String today) {
        ArrayList<String> todaysReminders = new ArrayList<>();
        
        for(Reminder r : reminders){
            if(r.getDate().equals(today)) todaysReminders.add(r.getDescription());
        }
        
        return todaysReminders;
    }
    
}
