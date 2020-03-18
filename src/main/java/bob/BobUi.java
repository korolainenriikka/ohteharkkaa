package bob;

import javafx.application.Application;
import javafx.stage.Stage;

public class BobUi extends Application {

    public static void main(String[] args) {
        launch(BobUi.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("bob the personal assistant <3");
        primaryStage.show();
    }
}
