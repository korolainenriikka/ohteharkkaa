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
    
    @FXML
    private void handleSetEndDayScene(){
        application.setEndDayScene();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    public void setSceneContent(LocalDate today) {
        setTopImage();
        todaysCalendarItems.getChildren().clear();
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
        todaysCalendarItems.getChildren().add(new Label("\n\n"));
        if (todaysEvents.isEmpty() && todaysReminders.isEmpty()) {
            createEmptyCalendarLabel();           
        } else {
            List<Node> sceneContent = new ArrayList<>();
            sceneContent.addAll(getItemsAsLabels(todaysEvents, "TÄNÄÄN"));
            sceneContent.addAll(getItemsAsLabels(todaysReminders, "MUISTA!"));
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

    private List<Label> getItemsAsLabels(List<String> todaysItems, String header) {
        List<Label> items = new ArrayList<>();
        if(!todaysItems.isEmpty()){
            items.add(new Label("\n"+header));
        }
        for (String item : todaysItems) {
            items.add(new Label(item.toString()));
        }
        return items;
    }

    
}
