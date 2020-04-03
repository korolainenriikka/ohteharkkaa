package bob;

import bob.domain.BobService;
import bob.domain.Event;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PrimarySceneController implements Initializable {

    private BobUi application;
    private BobService bobService;

    public void setApplication(BobUi application) {
        this.application = application;
    }

    public void setBobService(BobService bobService) {
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

    public void initialize() {
        List<String> reminders = bobService.getTodaysReminders();
        List<Event> events = bobService.getTodaysEvents();
        for (String reminder : reminders) {
            if (reminders.indexOf(reminder) == 0) {
                todaysReminders.setText(reminder);
            } else {
                todaysReminders.setText(todaysReminders.getText() + "\n" + reminder);
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
