package bob;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BobUi extends Application {

    public static void main(String[] args) {
        launch(BobUi.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource("/kakkaa.fxml"));
        Parent root = sceneLoader.load();
        Scene primaryScene = new Scene(root);
        
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("bob the personal assistant <3");
        primaryStage.show();
    }
}
