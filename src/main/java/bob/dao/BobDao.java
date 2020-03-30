
package bob.dao;

import bob.domain.Reminder;
import java.time.LocalDate;
import java.util.List;

public interface BobDao {
    String addReminderToDatabase(Reminder newReminder);
    List<String> getTodaysReminders(LocalDate today);
    void removeOldReminders(LocalDate today);
}
