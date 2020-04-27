package bob;

import bob.domain.BobService;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WorkSceneController implements SceneController {
    
    private BobUi app;
    private BobService bobService;
    private AnimationTimer timer;

    @Override
    public void setAttributes(BobUi app, BobService bobService) {
        this.app = app;
        this.bobService = bobService;
        workTimer.setText(app.getWorkTime()+"");
    }
    
    @FXML
    private Label workTimer;
  
    @FXML
    private void startWorking(){
        timer.start();
    }
    
    @FXML
    private void pauseTimer(){
        timer.stop();
    }
    
    @FXML
    private void handleSetPrimaryScene(){
        app.setPrimaryScene();
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        timer = new AnimationTimer() {
            long prev = 0;
            @Override
            public void handle(long now) {
                if (now - prev < 1000000000) {
                    return;
                }
                LocalTime worktime = LocalTime.parse(workTimer.getText(), DateTimeFormatter.ofPattern("HH[:mm[:ss]]")).plusSeconds(1);
                app.updateWorkTime(worktime);
                workTimer.setText(worktime+"");
                this.prev=now;
            }
        };
    }    
}