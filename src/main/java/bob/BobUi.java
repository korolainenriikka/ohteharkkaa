package bob;

import bob.dao.SQLBobDao;
import bob.domain.BobService;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BobUi extends Application {
    
    private BobService bobService;
    private Stage stage;
    private Scene primaryScene;
    private PrimarySceneController primarySceneController;
    private Scene newReminderScene;
    
    @Override
    public void init() throws Exception{
        bobService = new BobService(new SQLBobDao("jdbc:sqlite:bobData.db"));
        
        FXMLLoader primarySceneLoader = new FXMLLoader(getClass().getResource("/fxml/primaryScene.fxml"));
        Parent primaryRoot = primarySceneLoader.load();
        primarySceneController = primarySceneLoader.getController();
        primarySceneController.setBobService(bobService);
        primarySceneController.setApplication(this);
        primaryScene = new Scene(primaryRoot);
        
        FXMLLoader newReminderSceneLoader = new FXMLLoader(getClass().getResource("/fxml/newReminderScene.fxml"));
        Parent newReminderSceneRoot = newReminderSceneLoader.load();
        NewReminderSceneController newReminderSceneController = newReminderSceneLoader.getController();
        newReminderSceneController.setBobService(bobService);
        newReminderSceneController.setApplication(this);
        newReminderScene = new Scene(newReminderSceneRoot);
    }

    @Override
    public void start(Stage primaryStage)  {
        this.stage = primaryStage;
        setPrimaryScene();
        primaryStage.setTitle("bob the personal assistant <3");
        stage.show();
    }
    
    public void setPrimaryScene(){
        primarySceneController.initialize();
        stage.setScene(primaryScene);
    }
    
    public void setNewReminderScene(){
        stage.setScene(newReminderScene);
    }
    
    public static void main(String[] args) {
        launch(BobUi.class);
    }
}
