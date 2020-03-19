package bob;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class PrimarySceneController implements Initializable{
    private BobUi application;
    
    public void setApplication(BobUi application){
        this.application = application;
    }
    
    @FXML
    private MenuItem lisääMuistutus;
    
    @FXML
    private void handleSetNewReminderScene(){
        application.setNewReminderScene();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // skip
    }
    
}
