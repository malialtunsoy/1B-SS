
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.event.*;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
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

    public static String mainMenuScreen = "MainMenuScreen";
    public static String mainMenuScreenFile = "mainMenuScreen.fxml";

    public static String newGameFirstScreen = "NewGameFirstScreen";
    public static String newGameFirstScreenFile = "newGameFirstScreen.fxml";

    public static String newGameSecondScreen = "NewGameSecondScreen";
    public static String newGameSecondScreenFile = "newGameSecondScreen.fxml";

    public static String achievementsScreen = "AchievementsScreen";
    public static String achievementsScreenFile = "achievementsScreen.fxml";

    public static String optionsScreen = "OptionsScreen";
    public static String optionsScreenFile = "optionsScreen.fxml";



   public static void launchApp(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{

        ScreenController myScreens = new ScreenController();

        myScreens.loadScreen(NavigationUI.mainMenuScreen, NavigationUI.mainMenuScreenFile);
        myScreens.loadScreen(NavigationUI.newGameFirstScreen, NavigationUI.newGameFirstScreenFile);
        myScreens.loadScreen(NavigationUI.newGameSecondScreen, NavigationUI.newGameSecondScreenFile);
        myScreens.loadScreen(NavigationUI.achievementsScreen, NavigationUI.achievementsScreenFile);
        myScreens.loadScreen(NavigationUI.optionsScreen, NavigationUI.optionsScreenFile);

        myScreens.changeScreen(NavigationUI.mainMenuScreen);

        Group root = new Group();
        root.getChildren().addAll(myScreens);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Slay the Spire");
        primaryStage.setScene(scene);
        primaryStage.show();

    }






}
