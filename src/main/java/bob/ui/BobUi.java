package bob.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.fxml.FXMLLoader;


public class BobUi extends Application {

    public static void main(String[] args) {
        launch(BobUi.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/scenebuildbob.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("bob the personal assistant <3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
