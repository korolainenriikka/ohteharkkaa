package bob;

import bob.domain.*;
import java.net.URL;
import java.time.*;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class PrimarySceneController implements SceneController {

    private BobUi application;
    private BobService bobService;

    public void setAttributes(BobUi application, BobService bobService) {
        this.application = application;
        this.bobService = bobService;
    }

    @FXML
    private VBox todaysCalendarItems;

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
            createEmptyCalendarLabel();           
        } else {
            List<Node> sceneContent = new ArrayList<>();
            sceneContent.addAll(getEventsAsLabels(todaysEvents));
            sceneContent.addAll(getRemindersAsHBox(todaysReminders));
            todaysCalendarItems.getChildren().addAll(sceneContent);
            Label eventheader = new Label("\nTÄNÄÄN");
        }
    }
    
    private void createEmptyCalendarLabel() {
        Label nonothing = new Label("\n“Sometimes the most important thing to do \nis to do nothing.” ");
            makeTextItalic(nonothing);
            todaysCalendarItems.getChildren().add(nonothing);
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

    private List<Label> getEventsAsLabels(List<String> todaysEvents) {
        List<Label> events = new ArrayList<>();
        if(!todaysEvents.isEmpty()){
            events.add(new Label("TÄNÄÄN"));
        }
        for (String event : todaysEvents) {
            events.add(new Label(event.toString()));
        }
        return events;
    }

    private List<HBox> getRemindersAsHBox(List<String> todaysReminders) {
        List<HBox> reminders = new ArrayList<>();
        if(!todaysReminders.isEmpty()){
            HBox header = new HBox();
            header.getChildren().add(new Label("MUISTA!"));
            reminders.add(header);
        }
        for (String reminder : todaysReminders) {
            HBox hb = new HBox();
            hb.getChildren().addAll(new CheckBox(), new Label(reminder.toString()));
            reminders.add(hb);
        }
        return reminders;
    }

    
}
