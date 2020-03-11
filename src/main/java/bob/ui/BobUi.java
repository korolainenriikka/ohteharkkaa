
package bob.ui;

import java.util.HashSet;
import java.util.Set;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.FlowPane;


public class BobUi extends Application{
    public static void main(String[] args){
        launch(BobUi.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Dis Is BOB");
        FlowPane komponenttiryhma = new FlowPane();
        Scene scene = new Scene(komponenttiryhma);
        primaryStage.setMinHeight(300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

