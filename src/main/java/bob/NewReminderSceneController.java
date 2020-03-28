package bob;

import bob.domain.BobService;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    private ChoiceBox day;

    @FXML
    private ChoiceBox month;

    @FXML
    private ChoiceBox year;
    
    @FXML
    private Label okmessage;

    @FXML
    private void handleSetPrimaryScene(ActionEvent event) {
        okmessage.setText("");
        application.setPrimaryScene();
    }

    @FXML
    private void handleNewReminder(ActionEvent event) {
        //formatting
        String dayString = this.day.getValue()+"";
        if(Integer.valueOf(dayString) < 10){
            dayString = 0+dayString;
        }
        
        List<String> months = new ArrayList<>(Arrays.asList("tammi", "helmi", "maalis", "huhti", "touko", "kesä", "heinä", "elo", "syys", "loka", "marras", "joulu"));
        int monthInt = months.indexOf(this.month.getValue())+1;
        String monthString = monthInt+"";
        if(monthInt < 10){
            monthString = "0"+monthInt;
        }
        
        String date = year.getValue() + "-" + monthString + "-" + dayString;
        String message = bobService.createReminder(date, description.getText());
        description.setText("");
        day.setValue(null);
        month.setValue(null);
        year.setValue(null);
        okmessage.setText(message);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // sk i p
    }

}
