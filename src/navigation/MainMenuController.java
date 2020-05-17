import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable, ControlledScreen {

    ScreenController myController;

    public void setScreenParent(ScreenController screenParent){
        myController = screenParent;
    }

    @FXML
    void newGame(ActionEvent event) throws IOException {
        myController.changeScreen(NavigationUI.newGameFirstScreen);
    }

    @FXML
    void loadGame(ActionEvent event) {
        //System.out.println("Loading Game");
        if(!Game.getInstance().getLoadedGameExist()){
            System.out.println("there is no loaded game");
        }
        else {
            //System.out.println("LOAD");
            RunUIManager myRun = new RunUIManager();
            Game.getInstance().loadRun();

            myController.screenLoadFromOtherSubs(myRun.screenNames, myRun.screenFiles);
            myController.changeScreen("MainRunScreen");
        }
    }



    @FXML
    void drawOptions(ActionEvent event)throws IOException  {
        myController.setGetBackFromSettings(NavigationUI.mainMenuScreen);
        myController.changeScreen(NavigationUI.optionsScreen);
        SaveAndExit.save();
    }

    @FXML
    void drawAchievements(ActionEvent event)throws IOException {
        myController.changeScreen(NavigationUI.achievementsScreen);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }




}
