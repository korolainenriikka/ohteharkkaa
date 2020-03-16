/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bob;

/**
 *
 * @author riikoro
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class NewReminderSceneController implements Initializable{
    private BobMain application;
    //service-olio tähän
    
    public void setApplication(BobMain application) {
        this.application = application;
    }
    
    @FXML
    private ChoiceBox pv;
    private ChoiceBox kk;
    private ChoiceBox vvvv;
    private TextField kuvaus;
   
    @FXML
    private void handleNewReminder(ActionEvent event) {
        //tässä käsitellään uusi muistutus
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: selvitä mitäkummaaa tää on
    }  
}