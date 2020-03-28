
package bob.dao;

import bob.domain.Reminder;
import java.util.List;

public interface BobDao {
    String addReminderToDatabase(Reminder newReminder);
    List<String> getTodaysReminders(String today);
}
