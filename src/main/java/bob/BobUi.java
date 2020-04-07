package bob;

import bob.dao.SQLBobDao;
import bob.domain.BobService;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BobUi extends Application {

    private BobService bobService;
    private LocalDate today;
    private Stage stage;
    private Scene primaryScene;
    private PrimarySceneController primarySceneController;
    private Scene newReminderScene;
    private Scene newEventScene;

    @Override
    public void init() throws Exception {
        today = LocalDate.now();

        bobService = new BobService(new SQLBobDao("jdbc:sqlite:bobData.db"), today);
        bobService.removeOld(today);

        FXMLLoader primarySceneLoader = new FXMLLoader(getClass().getResource("/fxml/primaryScene.fxml"));
        Parent primaryRoot = primarySceneLoader.load();
        primarySceneController = primarySceneLoader.getController();
        primarySceneController.setAttributes(this, bobService);
        primaryScene = new Scene(primaryRoot);

        newReminderScene = initScene("/fxml/newReminderScene.fxml", new NewReminderSceneController());
        newEventScene = initScene("/fxml/newEventScene.fxml", new NewReminderSceneController());
    }

    private Scene initScene(String fxml, SceneController sceneController) throws Exception {
        FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = sceneLoader.load();
        sceneController = sceneLoader.getController();
        sceneController.setAttributes(this, bobService);
        return new Scene(root);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        setPrimaryScene();
        primaryStage.setTitle("bob the personal assistant <3");
        stage.show();
    }

    public void setPrimaryScene() {
        primarySceneController.getTodays();
        stage.setScene(primaryScene);
    }

    public void setNewReminderScene() {
        stage.setScene(newReminderScene);
    }

    public void setNewEventScene() {
        stage.setScene(newEventScene);
    }

    public static void main(String[] args) {
        launch(BobUi.class);
    }

}
