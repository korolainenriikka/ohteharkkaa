package bob;

import bob.BobUi;
import bob.domain.BobService;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;

public class NewEventSceneController implements SceneController {

    private BobUi application;
    private BobService bobService;

    public void setAttributes(BobUi application, BobService bobService) {
        this.application = application;
        this.bobService = bobService;
    }

    @FXML
    private TextField description;

    @FXML
    private DatePicker date;

    @FXML
    private ChoiceBox hour;

    @FXML
    private ChoiceBox minute;

    @FXML
    private Label okmessage;

    @FXML
    private void handleSetPrimaryScene(ActionEvent event) {
        okmessage.setText("");
        application.setPrimaryScene();
    }

    @FXML
    private void handleNewEvent(ActionEvent event) {
        LocalTime time = LocalTime.parse(hour.getValue() + ":" + minute.getValue());
        String message = bobService.createEvent(date.getValue(), time, description.getText());
        description.setText("");
        okmessage.setText(message);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // sk i p
    }

}
