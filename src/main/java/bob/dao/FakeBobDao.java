package bob.dao;

import bob.domain.*;
import java.time.LocalDate;
import java.util.*;

public class FakeBobDao implements BobDao {

    private ArrayList<Reminder> reminders;
    private ArrayList<Event> events;

    public FakeBobDao() {
        this.reminders = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public String addReminderToDatabase(Reminder newReminder) {
        reminders.add(newReminder);
        return ("uusi muistutus lisätty:\n" + newReminder.getDate() + "\n" + newReminder.getDescription());
    }

    public List<Reminder> getTodaysReminders(LocalDate today) {
        return reminders;
    }

    @Override
    public boolean removeOld(LocalDate today) {
        return true;
    }

    @Override
    public String addEventToDatabase(Event newEvent) {
        events.add(newEvent);
        return ("uusi tapahtuma lisätty:\n" + newEvent.getDate() + "\n" + newEvent.getTime() + "\n" + newEvent.getDescription());
    }

    @Override
    public List<CalendarItem> getTodaysCalendarItems(LocalDate today) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
