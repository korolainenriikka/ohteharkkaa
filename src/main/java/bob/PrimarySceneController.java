package bob;

import bob.domain.*;
import java.net.URL;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.control.Label;

public class PrimarySceneController implements SceneController {

    private BobUi application;
    private BobService bobService;

    public void setAttributes(BobUi application, BobService bobService) {
        this.application = application;
        this.bobService = bobService;
    }

    @FXML
    private Label todaysReminders;

    @FXML
    private Label todaysEvents;

    @FXML
    private void handleSetNewReminderScene() {
        application.setNewReminderScene();
    }

    @FXML
    private void handleSetNewEventScene() {
        application.setNewEventScene();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // skip
    }

    public void getTodays() {
        List<Reminder> reminders = bobService.getTodaysReminders();
        List<Event> events = bobService.getTodaysEvents();
        for (Reminder reminder : reminders) {
            if (reminders.indexOf(reminder) == 0) {
                todaysReminders.setText(reminder.getDescription());
            } else {
                todaysReminders.setText(todaysReminders.getText() + "\n" + reminder.getDescription());
            }
        }
        for (Event event : events) {
            if (events.indexOf(event) == 0) {
                todaysEvents.setText(event.toString());
            } else {
                todaysEvents.setText(todaysEvents.getText() + "\n" + event.toString());
            }
        }
    }

}
