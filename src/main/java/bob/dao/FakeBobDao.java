package bob.dao;

import bob.domain.*;
import java.time.LocalDate;
import java.util.*;

public class FakeBobDao implements BobDao {

    private ArrayList<CalendarItem> reminders;
    private ArrayList<CalendarItem> events;

    public FakeBobDao() {
        this.reminders = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    @Override
    public boolean addEventToDatabase(Event newEvent) {
        events.add(newEvent);
        return true;
    }

    @Override
    public boolean addReminderToDatabase(Reminder newReminder) {
        reminders.add(newReminder);
        return true;
    }

    @Override
    public List<CalendarItem> getTodaysEventsSorted(LocalDate today) {
        return events;
    }

    @Override
    public List<CalendarItem> getTodaysReminders(LocalDate today) {
        return reminders;
    }

    @Override
    public boolean removeOld(LocalDate today) {
        return true;
    }
    
    
}
