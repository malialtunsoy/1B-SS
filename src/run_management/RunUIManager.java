
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class RunUIManager{

    public static String mainRunScreen = "MainRunScreen";
    public static String mainRunScreenFile = "mainRunScreen.fxml";

    public static String merchantScreen = "MerchantScreen";
    public static String merchantScreenFile = "MerchantScreen.fxml";

    public static String restScreen = "RestScreen";
    public static String restScreenFile = "RestScreen.fxml";

    public static String treasureScreen = "TreasureScreen";
    public static String treasureScreenFile = "TreasureScreen.fxml";

    public ArrayList<String> screenNames;
    public ArrayList<String> screenFiles;

    public RunUIManager(){


        screenNames = new ArrayList<String>();
        screenFiles = new ArrayList<String>();


        screenNames.add(mainRunScreen);
        screenNames.add(merchantScreen);
        screenNames.add(restScreen);
        screenNames.add(treasureScreen);

        screenFiles.add(mainRunScreenFile);
        screenFiles.add(merchantScreenFile);
        screenFiles.add(restScreenFile);
        screenFiles.add(treasureScreenFile);



    }

    /*public void startRun(){

        runScreens = new ScreenController();

        runScreens.loadScreen(RunUIManager.mainRunScreen, RunUIManager.mainRunScreenFile);

    }*/




}
