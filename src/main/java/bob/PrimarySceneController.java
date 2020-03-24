package bob;

import bob.domain.BobService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

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
    private MenuItem lisääMuistutus;

    @FXML
    private Label todaysReminders;

    @FXML
    private void handleSetNewReminderScene() {
        application.setNewReminderScene();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // skip
    }

    void initialize() {
        List<String> reminders = bobService.getTodaysReminders();
        for (String reminder : reminders) {
            if (reminders.indexOf(reminder) == 0) {
                todaysReminders.setText(reminder);
            } else {
                todaysReminders.setText(todaysReminders.getText() + "\n" + reminder);
            }
        }
    }

}
