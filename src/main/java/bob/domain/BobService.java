package bob.domain;

import bob.dao.BobDao;
import java.time.*;
import java.util.*;

public class BobService {

    private BobDao bobDao;
    private LocalDate today;

    public BobService(BobDao bobDao, LocalDate today) {
        this.bobDao = bobDao;
        this.today = today;
    }

    public String createNewEvent(LocalDate date, LocalTime time, String description) {
        Event newEvent = new Event(date, time, description);
        if (bobDao.addEventToDatabase(newEvent)) {
            return "uusi tapahtuma lisätty:\n" + newEvent.getDate() + "\n" + newEvent.getTime() + "\n" + newEvent.getDescription();
        } else {
            return "virhe!";
        }
    }

    public String createNewReminder(LocalDate date, String description) {
        Reminder newReminder = new Reminder(date, description);
        if (bobDao.addReminderToDatabase(newReminder)) {
            return "uusi muistutus lisätty:\n" + newReminder.getDate() + "\n" + newReminder.getDescription();
        } else {
            return "virhe!";
        }
    }

    public boolean removeOld() {
        return bobDao.removeOld(today);
    }

    public List<String> getTodaysItemsAsString(Class<?> cls) {
        List<String> itemToStrings = new ArrayList<>();
        List<CalendarItem> todaysItems = new ArrayList<>();
        if (cls == Event.class) {
            todaysItems = bobDao.getTodaysEvents(today);
        } else {
            todaysItems = bobDao.getTodaysReminders(today);
        }
        for (CalendarItem i : todaysItems) {
            itemToStrings.add(i.toString());
        }
        return itemToStrings;
    }
}
