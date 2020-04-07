package bob.domain;

import bob.dao.BobDao;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class BobService {

    private BobDao bobDao;
    private LocalDate today;

    public BobService(BobDao bobDao, LocalDate today) {
        this.bobDao = bobDao;
        this.today = today;
    }

    public String createReminder(LocalDate date, String description) {
        Reminder newReminder = new Reminder(date, description);
        return bobDao.addReminderToDatabase(newReminder);
    }

    public List<Reminder> getTodaysReminders() {
        return bobDao.getTodaysReminders(today);
    }

    public String createEvent(LocalDate date, LocalTime time, String description) {
        Event newEvent = new Event(date, time, description);
        return bobDao.addEventToDatabase(newEvent);
    }

    public List<Event> getTodaysEvents() {
        List<Event> todaysEvents = bobDao.getTodaysEvents(today);
        Collections.sort(todaysEvents);
        return todaysEvents;
    }

    public LocalDate getToday() {
        return today;
    }

    public boolean removeOld(LocalDate today) {
        return bobDao.removeOld(today);
    }

}
