
package bob.dao;

import bob.domain.Event;
import bob.domain.Reminder;
import java.time.LocalDate;
import java.util.List;

public interface BobDao {
    String addReminderToDatabase(Reminder newReminder);
    List<Event> getTodaysEvents(LocalDate today);
    List<String> getTodaysReminders(LocalDate today);
    void removeOldReminders(LocalDate today);

    public String addEventToDatabase(Event newEvent);
}
