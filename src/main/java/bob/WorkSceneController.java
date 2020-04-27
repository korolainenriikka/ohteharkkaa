package bob;

import bob.domain.BobService;
import java.net.URL;
import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WorkSceneController implements SceneController {
    
    private BobUi app;
    private BobService bobService;

    @Override
    public void setAttributes(BobUi app, BobService bobService) {
        this.app = app;
        this.bobService = bobService;
    }
    
    @FXML
    private Label workTimer;
  
    @FXML
    private void startWorking(){
        new AnimationTimer() {
            long edellinen = 0;
            @Override
            public void handle(long nykyhetki) {
                if (nykyhetki - edellinen < 100000000) {
                    return;
                }
                this.edellinen = nykyhetki;
                workTimer.setText(edellinen+"");
            }
        }.start();

    }
                        
    
    
    @FXML
    private void pauseTimer(){
        System.out.println("pause!");
    }
    
    @FXML
    private void handleSetPrimaryScene(){
        app.setPrimaryScene();
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }    
}