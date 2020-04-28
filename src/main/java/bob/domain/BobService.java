package bob.domain;

import bob.dao.BobDao;
import java.time.*;
import java.util.*;

public class BobService {

    private BobDao bobDao;

    public BobService(BobDao bobDao) {
        this.bobDao = bobDao;
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

    public boolean removeOld(LocalDate date) {
        return bobDao.removeOld(date);
    }

    public List<String> getDaysItemsAsString(Class<?> cls, LocalDate date) {
        List<String> itemToStrings = new ArrayList<>();
        List<CalendarItem> todaysItems = new ArrayList<>();
        if (cls == Event.class) {
            todaysItems = bobDao.getTodaysEventsSorted(date);
        } else {
            todaysItems = bobDao.getTodaysReminders(date);
        }
        for (CalendarItem i : todaysItems) {
            itemToStrings.add(i.toString());
        }
        return itemToStrings;
    }

    public void moveReminderToNextDay(String text, LocalDate date) {
        bobDao.addReminderToDatabase(new Reminder(date.plusDays(1), text));
    }
    
    public void saveWorkedTime(LocalTime time, LocalDate date){
        bobDao.updateWorkTime(time, date);
    }

    public LocalTime getWorkTime(LocalDate date) {
        return bobDao.getWorkTime(date);
    }

}
