package bob.dao;

import bob.domain.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class FakeBobDao implements BobDao {

    private ArrayList<CalendarItem> reminders;
    private ArrayList<CalendarItem> events;
    private LocalTime worktime;

    public FakeBobDao() {
        this.reminders = new ArrayList<>();
        this.events = new ArrayList<>();
        this.worktime = LocalTime.parse("00:00:00");
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

    @Override
    public void updateWorkTime(LocalTime workTime, LocalDate date) {
        this.worktime = workTime;
    }

    @Override
    public LocalTime getWorkTime(LocalDate date) {
        return worktime;
    }

}
