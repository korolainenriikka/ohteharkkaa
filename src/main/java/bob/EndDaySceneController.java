package bob;

import bob.domain.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class EndDaySceneController implements SceneController{
    
    private BobUi application;
    private BobService bobService;
    
    @Override
    public void setAttributes(BobUi application, BobService bobService) {
        this.application = application;
        this.bobService = bobService;
    }
    
    @FXML
    private VBox todaysReminders;
    
    @FXML
    private void handleSetPrimaryScene(ActionEvent event) {
        application.setPrimaryScene();
    }
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    public void setSceneContent(LocalDate today) {
        List<String> todaysRemindersAsString = bobService.getTodaysItemsAsString(Reminder.class);
        List<HBox> reminders = new ArrayList<>();
        if(!todaysRemindersAsString.isEmpty()){
            HBox header = new HBox();
            header.getChildren().add(new Label("\nMUISTA!"));
            reminders.add(header);
        }
        for (String reminder : todaysRemindersAsString) {
            HBox hb = new HBox();
            hb.setSpacing(10);
            hb.getChildren().addAll(new CheckBox(), new Label(reminder.toString()));
            reminders.add(hb);
        }
        todaysReminders.getChildren().addAll(reminders);
    }
    
}
