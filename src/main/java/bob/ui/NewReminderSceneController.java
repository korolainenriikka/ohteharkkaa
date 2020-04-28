package bob.ui;

import bob.ui.BobUi;
import bob.domain.BobService;
import bob.ui.BobUi;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;

public class NewReminderSceneController implements SceneController {

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
    private Label okmessage;

    @FXML
    private void handleSetPrimaryScene(ActionEvent event) {
        okmessage.setText("");
        application.setPrimaryScene();
    }

    @FXML
    private void handleNewReminder(ActionEvent event) {
        if (date.getValue() == null || description.getText() == null) {
            okmessage.setText("syötä pyydetyt tiedot");
        } else {
            String message = bobService.createNewReminder(date.getValue(), description.getText());
            description.setText("");
            okmessage.setText(message);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
}
