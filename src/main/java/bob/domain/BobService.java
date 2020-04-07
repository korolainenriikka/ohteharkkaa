package bob.domain;

import bob.dao.BobDao;
import java.time.*;
import java.util.*;

public class BobService {

    private BobDao bobDao;

    public BobService(BobDao bobDao, LocalDate today) {
        this.bobDao = bobDao;
    }

    public String createReminder(LocalDate date, String description) {
        Reminder newReminder = new Reminder(date, description);
        return bobDao.addReminderToDatabase(newReminder);
    }

    /*public List<Reminder> getTodaysReminders(LocalDate today) {
        return bobDao.getTodaysReminders(today);
    }
*/
    public String createEvent(LocalDate date, LocalTime time, String description) {
        Event newEvent = new Event(date, time, description);
        return bobDao.addEventToDatabase(newEvent);
    }

    /*public List<Event> getTodaysEvents(LocalDate today) {
        List<Event> todaysEvents = bobDao.getTodaysEvents(today);
        Collections.sort(todaysEvents);
        return todaysEvents;
    }*/

    public boolean removeOld(LocalDate today) {
        return bobDao.removeOld(today);
    }

    public List<String> getTodaysCalendarItems(LocalDate today) {
        List<CalendarItem> calendarItems = bobDao.getTodaysCalendarItems(today);
        List<String> itemToStrings = new ArrayList<>();
        for(CalendarItem item: calendarItems){
            itemToStrings.add(item.toString());
        }
        return  itemToStrings;
    }

}
