/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bob;
        
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author riikoro
 */
public class BobMain extends Application{
    private Stage primaryStage;
    //private TodoService todoService; eli tähän sovellusloogikko-olio!
    private Scene primaryScene;
    private Scene newReminderScene;
    
    @Override
    public void init() throws Exception {
        //todoService = new TodoService(new FakeTodoDao(), new FakeUserDao());
        
        FXMLLoader primarySceneLoader = new FXMLLoader(getClass().getResource("/fxml/PrimaryScene.fxml"));       
        Parent primaryPane = primarySceneLoader.load();
        PrimarySceneController primarySceneController = primarySceneLoader.getController();
        //loginSceneController.setTodoService(todoService); 
        primarySceneController.setApplication(this);
        primaryScene = new Scene(primaryPane);

        FXMLLoader newReminderSceneLoader = new FXMLLoader(getClass().getResource("/fxml/NewUserScene.fxml"));       
        Parent newReminderPane = newReminderSceneLoader.load();
        NewReminderSceneController newReminderSceneController = newReminderSceneLoader.getController();
        //newReminderSceneController.setTodoService(todoService); 
        newReminderSceneController.setApplication(this);
        newReminderScene = new Scene(newReminderPane);    
        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/PrimaryScene.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("bob the personal assistant <3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void setprimaryScene() {
        primaryStage.setScene(primaryScene);
    }

    public void setNewReminderScene() {
        primaryStage.setScene(newReminderScene);
    } 
    
    public static void main(String[] args) {
        launch(args);
    }
}
   