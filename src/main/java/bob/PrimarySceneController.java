package bob;

import bob.domain.*;
import java.net.URL;
import java.time.*;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.image.*;
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
    private ImageView topImage;

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

    public void setSceneContent(LocalDate today) {
        setTopImage();
        setSceneContent();
    }

    private void setTopImage() {
        LocalTime time = LocalTime.now();
        if (time.isAfter(LocalTime.parse("05:00")) && time.isBefore(LocalTime.NOON)) {
            topImage.setImage(new Image("file:src/main/resources/images/aamuarde.jpg"));
        } else if (time.isBefore(LocalTime.parse("16:00"))) {
            topImage.setImage(new Image("file:src/main/resources/images/paiva_arde.jpg"));
        } else {
            topImage.setImage(new Image("file:src/main/resources/images/ilta_arde.jpg"));
        }
    }

    private void setSceneContent() {
        List<String> todaysEvents = bobService.getTodaysItemsAsString(Event.class);
        List<String> todaysReminders = bobService.getTodaysItemsAsString(Reminder.class);
        if (todaysEvents.isEmpty() && todaysReminders.isEmpty()) {
            makeTextItalic(todaysCalendarItems);
            todaysCalendarItems.setText("\n“Sometimes the most important thing to do \nis to do nothing.” ");
        } else {
            makeTextDefaultFont(todaysCalendarItems);
            todaysCalendarItems.setText("\n");
            addTodaysItemsToScene(todaysEvents, "TÄNÄÄN");
            addTodaysItemsToScene(todaysReminders, "MUISTA!");
        }
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
            todaysCalendarItems.setText(todaysCalendarItems.getText() + "\n" + header);
            for (String event : todaysItems) {
                todaysCalendarItems.setText(todaysCalendarItems.getText() + "\n" + event);
            }
            todaysCalendarItems.setText(todaysCalendarItems.getText() + "\n");
        }
    }
}
