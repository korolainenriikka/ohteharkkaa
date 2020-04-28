package bob.ui;

import bob.dao.SQLBobDao;
import bob.domain.BobService;
import java.time.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

/**
 * Käyttöliittymäluokka.
 */
public class BobUi extends Application {

    private BobService bobService;
    private LocalDate today;
    private Stage stage;
    private Scene primaryScene;
    private PrimarySceneController primarySceneController;
    private Scene newReminderScene;
    private Scene newEventScene;
    private Scene workScene;
    private Scene endDayScene;
    private EndDaySceneController endDaySceneController;
    private boolean pandemic;
    private LocalTime workTime;

    /**
     * Metodi alustaa sovelluksen: määrittää päivämäärän, pandemiatilanteen,
     * alustaa sovelluslogiikan, hakee työajan sekä alustaa näkymät.
     */
    @Override
    public void init() throws Exception {
        today = LocalDate.now();
        pandemic = true;
        initializeBobService();
        workTime = bobService.getWorkTime(today);
        initializeScenes();
    }

    private void initializeBobService() {
        bobService = new BobService(new SQLBobDao("jdbc:sqlite:bobData.db"));
        bobService.removeOld(today);
    }

    private void initializeScenes() throws Exception {
        FXMLLoader primarySceneLoader = new FXMLLoader(getClass().getResource("/fxml/primaryScene.fxml"));
        Parent primaryRoot = primarySceneLoader.load();
        primarySceneController = primarySceneLoader.getController();
        primarySceneController.setAttributes(this, bobService);
        primaryScene = new Scene(primaryRoot);

        FXMLLoader endDaySceneLoader = new FXMLLoader(getClass().getResource("/fxml/endDayScene.fxml"));
        Parent endDayRoot = endDaySceneLoader.load();
        endDaySceneController = endDaySceneLoader.getController();
        endDaySceneController.setAttributes(this, bobService);
        endDayScene = new Scene(endDayRoot);

        newReminderScene = initScene("/fxml/newReminderScene.fxml", new NewReminderSceneController());
        workScene = initScene("/fxml/workScene.fxml", new WorkSceneController());
        newEventScene = initScene("/fxml/newEventScene.fxml", new NewReminderSceneController());
    }

    private Scene initScene(String fxml, SceneController sceneController) throws Exception {
        FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = sceneLoader.load();
        sceneController = sceneLoader.getController();
        sceneController.setAttributes(this, bobService);
        return new Scene(root);
    }

    /**
     * Metodi käynnistää sovelluksen.
     *
     * @param primaryStage ikkuna, jolle sovellus käynnistyy
     */
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        setPrimaryScene();
        primaryStage.setTitle("bob the personal assistant <3");
        stage.show();
    }

    /**
     * Metodi sulkee sovelluksen ja tallentaa samalla työajan.
     */
    @Override
    public void stop() {
        bobService.saveWorkedTime(workTime, today);
    }

    /**
     * Metodi kutsuu päänäkymään tiedot lisäävää metodia ja asettaa ikkunaan
     * päänäkymän.
     */
    public void setPrimaryScene() {
        primarySceneController.setSceneContent(today);
        stage.setScene(primaryScene);
    }

    /**
     * Metodi asettaa ikkunaan näkymän, jolla lisätään muistutuksia.
     */
    public void setNewReminderScene() {
        stage.setScene(newReminderScene);
    }

    /**
     * Metodi asettaa ikkunaan näkymän, jolla lisätään tapahtumia.
     */
    public void setNewEventScene() {
        stage.setScene(newEventScene);
    }

    /**
     * Metodi kutsuu näkymään tiedot lisäävää metodia ja asettaa ikkunaan päivän
     * lopetusnäkymän.
     */
    public void setEndDayScene() {
        endDaySceneController.setSceneContent(today);
        stage.setScene(endDayScene);
    }

    /**
     * Metodi asettaa ikkunaan työskentelynäkymän.
     */
    void setWorkScene() {
        stage.setScene(workScene);
    }

    public LocalDate getToday() {
        return today;
    }

    public LocalTime getWorkTime() {
        return workTime;
    }

    boolean isPandemic() {
        return pandemic;
    }

    /**
     * Metodi päivittää ui-luokan työaikamuuttujan.
     *
     */
    void updateWorkTime(LocalTime time) {
        workTime = time;
    }

    public static void main(String[] args) {
        launch(BobUi.class);
    }
}
