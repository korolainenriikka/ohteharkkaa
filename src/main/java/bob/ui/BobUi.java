package bob.ui;

import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;

public class BobUi extends Application {

    public static void main(String[] args) {
        launch(BobUi.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //tänne vielä: designii! meil on datapisteet mut sivunlukeminen falskaa
        //sit kans kysbäriihän on onks tää milläi taval hyvä tapa tehä asioita :))))
//        primaryStage.setTitle("Dis Is BOB");
//        BorderPane components = new BorderPane();
//
//        Canvas topheader = new Canvas(650, 250);
//        GraphicsContext drawTop = topheader.getGraphicsContext2D();
//        drawTop.setFill(Color.BLACK);
//
//        Canvas foot = new Canvas(650, 80);
//        GraphicsContext drawBottom = foot.getGraphicsContext2D();
//        drawBottom.setFill(Color.BLACK);
//        
//        VBox textElements = new VBox();
//        textElements.setPadding(new Insets(180));
//        textElements.getChildren().add(new Label("MUISTUTUKSET"));
//
//        components.setTop(topheader);
//        components.setCenter(textElements);
//        components.setBottom(foot);
//
//        Scene scene = new Scene(components);
//        primaryStage.setMinHeight(880);
//        primaryStage.setMinWidth(680);
//        primaryStage.setScene(scene);
//        primaryStage.show();
        Parent root = FXMLLoader.load(getClass().getResource("/kakkaa.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("JavaFX and Maven");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
