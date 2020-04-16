package bob;

import bob.domain.BobService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class endDaySceneController implements SceneController{
    
    private BobUi application;
    private BobService bobService;
    
    @FXML
    private void handleSetPrimaryScene(ActionEvent event) {
        application.setPrimaryScene();
    }

    @Override
    public void setAttributes(BobUi app, BobService bobService) {
        this.application = application;
        this.bobService = bobService;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
    
}
