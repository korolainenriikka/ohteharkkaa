
package bob.dao;

import bob.domain.Event;
import bob.domain.Reminder;
import java.time.LocalDate;
import java.util.List;

public interface BobDao {
    String addReminderToDatabase(Reminder newReminder);
    String addEventToDatabase(Event newEvent);
    List<Event> getTodaysEvents(LocalDate today);
    List<Reminder> getTodaysReminders(LocalDate today);
    boolean removeOld(LocalDate today);
}
