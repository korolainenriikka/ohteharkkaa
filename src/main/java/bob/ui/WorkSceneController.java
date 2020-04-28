package bob.ui;

import bob.ui.SceneController;
import bob.domain.BobService;
import bob.ui.BobUi;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.media.*;

public class WorkSceneController implements SceneController {

    private BobUi app;
    private BobService bobService;
    private AnimationTimer timer;
    private MediaPlayer libraryPlayer;
    private MediaPlayer hufflepuffPlayer;

    @Override
    public void setAttributes(BobUi app, BobService bobService) {
        this.app = app;
        this.bobService = bobService;
        workTimer.setText(app.getWorkTime() + "");
    }

    @FXML
    private Label workTimer;

    @FXML
    private RadioButton hogwartsLibrary;

    @FXML
    private RadioButton hufflepuffCommonRoom;

    @FXML
    private void startWorking() {
        if (hogwartsLibrary.isSelected()) {
            libraryPlayer.play();
        } else if (hufflepuffCommonRoom.isSelected()) {
            hufflepuffPlayer.play();
        }
        timer.start();
    }

    @FXML
    private void pauseTimer() {
        libraryPlayer.stop();
        hufflepuffPlayer.stop();
        timer.stop();
    }

    @FXML
    private void handleSetPrimaryScene() {
        app.setPrimaryScene();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        initializeAmbientPlayer();
        initializeWorkTimer();
    }

    private void initializeAmbientPlayer() {
        createToggleGroup();
        libraryPlayer = createMediaPlayerFromURL("/sounds/Hogwarts-Library.mp3");
        hufflepuffPlayer = createMediaPlayerFromURL("/sounds/Hufflepuff-Commonroom.mp3");
    }
       
    private void createToggleGroup() {
        final ToggleGroup group = new ToggleGroup();
        hogwartsLibrary.setToggleGroup(group);
        hufflepuffCommonRoom.setToggleGroup(group);
    }

    private MediaPlayer createMediaPlayerFromURL(String url) {
        URL mediaUrl = getClass().getResource(url);
        String mediaStringUrl = mediaUrl.toExternalForm();
        Media sound = new javafx.scene.media.Media(mediaStringUrl);
        MediaPlayer player = new MediaPlayer(sound);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        return player;
    }

    private void initializeWorkTimer() {
        timer = new AnimationTimer() {
            long prev = 0;

            @Override
            public void handle(long now) {
                if (now - prev < 1000000000) {
                    return;
                }
                LocalTime worktime = LocalTime.parse(workTimer.getText(), DateTimeFormatter.ofPattern("HH[:mm[:ss]]")).plusSeconds(1);
                app.updateWorkTime(worktime);
                workTimer.setText(worktime + "");
                this.prev = now;
            }
        };
    }
}
