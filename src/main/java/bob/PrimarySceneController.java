package bob;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class PrimarySceneController implements Initializable{
    private BobMain application;
    //service-olio tähän
    
    public void setApplication(BobMain application) {
        this.application = application;
    }
    
    @FXML
    private MenuButton menu;
   
    @FXML
    private void handleCreateRemind(ActionEvent event) {
        System.out.println("You clicked me!");
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: mitähhhhh
    }  
}

/*
todo scenecontroller


package todoapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import todoapp.domain.TodoService;

public class LoginSceneController implements Initializable {
    private TodoService todoService;
    private TodoAppMain application;

    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    public void setApplication(TodoAppMain application) {
        this.application = application;
    }
    
    @FXML
    private TextField username;
    NewClass
    @FXML
    private void handleLogin(ActionEvent event) {
        System.out.println("You clicked me!");
    }
   
    @FXML
    private void handleNewUser(ActionEvent event) {
        application.setNewUserScene();
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
*/