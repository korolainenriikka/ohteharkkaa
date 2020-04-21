package bob;

import bob.domain.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import javafx.beans.value.ChangeListener;
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
    private Button save;

    private HashMap<CheckBox, Label> reminders;

    @FXML
    private void handleSetPrimaryScene(ActionEvent event) {
        app.setPrimaryScene();
    }

    @FXML
    private void saveSelections() {
        for (CheckBox cb : reminders.keySet()) {
            if (!cb.isSelected()) {
                //undone reminder is added to next day
                bobService.moveReminderToNextDay(reminders.get(cb).getText(), app.getToday());
            }
        }
        app.setPrimaryScene();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    public void setSceneContent(LocalDate today) {
        todaysReminders.getChildren().clear();
        reminders = new HashMap<>();
        addRemindersToScene();
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

}
