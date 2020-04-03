package bob;

import bob.BobUi;
import bob.domain.BobService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class NewReminderSceneController implements Initializable {

    private BobUi application;
    private BobService bobService;

    public void setApplication(BobUi application) {
        this.application = application;
    }

    public void setBobService(BobService bobService) {
        this.bobService = bobService;
    }

    @FXML
    private TextField description;

    @FXML
    private DatePicker date;
    
    @FXML
    private Label okmessage;

    @FXML
    private void handleSetPrimaryScene(ActionEvent event) {
        okmessage.setText("");
        application.setPrimaryScene();
    }

    @FXML
    private void handleNewReminder(ActionEvent event) {  
        String message = bobService.createReminder(date.getValue(), description.getText());
        description.setText("");
        okmessage.setText(message);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // sk i p
    }

}
