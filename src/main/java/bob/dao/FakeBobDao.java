package bob.dao;

import bob.domain.Event;
import bob.domain.Reminder;
import java.time.LocalDate;
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
    
     public List<String> getTodaysReminders(LocalDate today) {
        ArrayList<String> todaysReminders = new ArrayList<>();
        
        for(Reminder r : reminders){
            if(r.getDate().equals(today)) todaysReminders.add(r.getDescription());
        }
        
        return todaysReminders;
    }

    @Override
    public void removeOldReminders(LocalDate today) {
    }

    @Override
    public String addEventToDatabase(Event newEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Event> getTodaysEvents(LocalDate today) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
