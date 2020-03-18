package bob;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BobUi extends Application {
    
    private Stage stage;
    private Scene primaryScene;
    private Scene newReminderScene;
    
    @Override
    public void init() throws Exception{
        FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource("/fxml/primaryScene.fxml"));
        Parent root = sceneLoader.load();
        primaryScene = new Scene(root);
    }

    public static void main(String[] args) {
        launch(BobUi.class);
    }

    @Override
    public void start(Stage primaryStage)  {
        this.stage = primaryStage;
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("bob the personal assistant <3");
        stage.show();
    }
}
