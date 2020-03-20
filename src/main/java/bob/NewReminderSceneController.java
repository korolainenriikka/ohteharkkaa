package bob;

import bob.domain.BobService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class NewReminderSceneController implements Initializable{
    
    private BobUi application;
    private BobService bobService;
    
    public void setApplication(BobUi application){
        this.application = application;
    }
    
    public void setBobService(BobService bobService){
        this.bobService = bobService;
    }
    
    @FXML
    private TextField description;
    
    @FXML
    private ChoiceBox day;
    
    @FXML
    private ChoiceBox month;
    
    @FXML
    private ChoiceBox year;
    
    @FXML
    private void handleSetPrimaryScene(ActionEvent event){
        application.setPrimaryScene();
    }
    
    @FXML
    private void handleNewReminder(ActionEvent event){
        String date = year.getValue()+"/"+month.getValue()+"/"+day.getValue();
        boolean creationOk = bobService.createReminder(date, description.getText());
        
        if ( creationOk  ){
            description.setText("");
            application.setPrimaryScene(); 
        } else {
                System.out.println("ei onnaaa :(((");
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // sk i p
    }
    
}

