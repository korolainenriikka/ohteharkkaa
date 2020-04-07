package bob;

import bob.domain.*;
import java.net.URL;
import java.time.LocalDate;
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
    private Label todaysCalendarItems;

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
    }

    public void addTodaysCalendarToScene(LocalDate today) {
        // get all items, form: events, *, reminders
        List<String> todaysCalendarItemsList = bobService.getTodaysCalendarItems(today);
        for (String moi : todaysCalendarItemsList) {
            if (moi.equals("*") && !todaysCalendarItemsList.isEmpty()) {
                todaysCalendarItems.setText(todaysCalendarItems.getText() + "\n\n" + "MUISTA!");
                continue;
            }
            if (todaysCalendarItemsList.indexOf(moi) == 0) {
                todaysCalendarItems.setText("TÄNÄÄN");
            } else {
                todaysCalendarItems.setText(todaysCalendarItems.getText() + "\n" + moi);
            }
        }

        /*for (Reminder reminder : reminders) {
            if (reminders.indexOf(reminder) == 0) {
                todaysReminders.setText(reminder.getDescription());
            } else {
                todaysReminders.setText(todaysReminders.getText() + "\n" + reminder);
            }
        }
        for (Event event : events) {
            if (events.indexOf(event) == 0) {
                todaysEvents.setText(event.toString());
            } else {
                todaysEvents.setText(todaysEvents.getText() + "\n" + event);
            }
        }*/
    }

}
