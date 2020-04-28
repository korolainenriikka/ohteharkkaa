package bob.dao;

import bob.domain.*;
import java.time.*;
import java.util.List;

public interface BobDao {

    boolean addEventToDatabase(Event newEvent);

    boolean addReminderToDatabase(Reminder newReminder);

    List<CalendarItem> getTodaysEventsSorted(LocalDate today);

    List<CalendarItem> getTodaysReminders(LocalDate today);

    boolean removeOld(LocalDate today);

    public void updateWorkTime(LocalTime workTime, LocalDate date);

    public LocalTime getWorkTime(LocalDate date);

}
