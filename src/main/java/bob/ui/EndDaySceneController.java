package bob.ui;

import bob.domain.*;
import bob.ui.BobUi;
import java.net.URL;
import java.time.*;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class EndDaySceneController implements SceneController {

    private BobUi app;
    private BobService bobService;

    @Override
    public void setAttributes(BobUi app, BobService bobService) {
        this.app = app;
        this.bobService = bobService;
    }

    @FXML
    private VBox todaysReminders;

    @FXML
    private Label worktime;

    private HashMap<CheckBox, Label> reminders;

    @FXML
    private void handleSetPrimaryScene(ActionEvent event) {
        app.setPrimaryScene();
    }

    @FXML
    private void saveSelections() {
        for (CheckBox cb : reminders.keySet()) {
            if (!cb.isSelected()) {
                bobService.moveReminderToNextDay(reminders.get(cb).getText(), app.getToday());
            }
        }
        app.stop();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    public void setSceneContent(LocalDate today) {
        todaysReminders.getChildren().clear();
        reminders = new HashMap<>();
        addRemindersToScene();
        addWorkTimeToScene();
    }

    private void addRemindersToScene() {
        List<String> todaysRemindersAsString = bobService.getDaysItemsAsString(Reminder.class, app.getToday());
        List<HBox> remindersAsHBox = new ArrayList<>();
        if (!todaysRemindersAsString.isEmpty()) {
            HBox header = new HBox();
            header.getChildren().add(new Label("\nValitse suoritetut:"));
            remindersAsHBox.add(header);
        }
        for (String reminder : todaysRemindersAsString) {
            HBox hb = new HBox();
            hb.setSpacing(10);
            CheckBox checker = new CheckBox();
            Label label = new Label(reminder.toString());
            reminders.put(checker, label);
            hb.getChildren().addAll(checker, label);
            remindersAsHBox.add(hb);
        }
        todaysReminders.getChildren().addAll(remindersAsHBox);
    }

    private void addWorkTimeToScene() {
        LocalTime worked = app.getWorkTime();
        String formatted = worked.getHour() + " h " + worked.getMinute() + " min";
        worktime.setText(formatted);
    }
}
