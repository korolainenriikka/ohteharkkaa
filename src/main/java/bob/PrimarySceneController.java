package bob;

import bob.domain.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.text.*;

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
        List<String> todaysEvents = bobService.getTodaysItemsAsString(Event.class);
        List<String> todaysReminders = bobService.getTodaysItemsAsString(Reminder.class);
        if (todaysEvents.isEmpty() && todaysReminders.isEmpty()) {
            makeTextItalic(todaysCalendarItems);
            todaysCalendarItems.setText("“Sometimes the most important thing to do is to do nothing.” ");
        }
        makeTextDefaultFont(todaysCalendarItems);
        addTodaysItemsToScene(todaysEvents, "TÄNÄÄN");
        addTodaysItemsToScene(todaysReminders, "MUISTA!");
    }

    private void makeTextItalic(Label label) {
        Font ITALIC_FONT
                = Font.font(
                        "Serif",
                        FontPosture.ITALIC,
                        Font.getDefault().getSize()
                );
        label.setFont(ITALIC_FONT);
    }
    
    private void makeTextDefaultFont(Label label) {
        Font DEFAULT_FONT
                = Font.font(
                        "Serif",
                        FontPosture.REGULAR,
                        Font.getDefault().getSize()
                );
        label.setFont(DEFAULT_FONT);
    }

    private void addTodaysItemsToScene(List<String> todaysItems, String header) {
        if (!todaysItems.isEmpty()) {
            todaysCalendarItems.setText(header+"\n");
            for (String event : todaysItems) {
                if (todaysItems.indexOf(event) == 0) {
                    todaysCalendarItems.setText(event);
                }
                todaysCalendarItems.setText(todaysCalendarItems.getText() + "\n" + event);
            }
        }
    }
}
