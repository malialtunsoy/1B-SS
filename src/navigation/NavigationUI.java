
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.event.*;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class NavigationUI extends Application  {

    //Game myGame;

    Stage window;

    Scene achievementsScene;

    public NavigationUI(){
        //myGame  = new Game();
    }

    public static void launchApp(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

        window = primaryStage;
        window.setTitle("Slay the Spire");
        window.setScene(new Scene(root));
        window.show();

        //VBox achievemetnsLayout = new VBox();

        //achievementsScene = new Scene(achievemetnsLayout, 800,1200);

    }

  /*  public Game getGame(){
        return myGame;
    }*/




}
