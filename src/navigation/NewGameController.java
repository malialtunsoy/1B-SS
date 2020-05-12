import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewGameController implements Initializable, ControlledScreen {

    ScreenController myController;

    public void setScreenParent(ScreenController screenParent){
        myController = screenParent;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private TextField playerNameText;

    @FXML
    void charSelect(ActionEvent event) {

        String playerName = playerNameText.getText();

        if(playerName.equals("")){
            playerNameText.setPromptText("you must enter a name");
        }
        if(playerName.length()>10){
            playerNameText.clear();
            playerNameText.setPromptText("pick a shorter name");
        }
        else {
            Game.getInstance().setPlayerName(playerName);
            myController.changeScreen(NavigationUI.newGameSecondScreen);
        }


    }

    @FXML
    void backToMainMenu(ActionEvent event) {
        myController.changeScreen(NavigationUI.mainMenuScreen);
    }


    @FXML
    void newGame(ActionEvent event) throws IOException {
        myController.reloadScreen(NavigationUI.newGameFirstScreen, NavigationUI.newGameFirstScreenFile);
        myController.changeScreen(NavigationUI.newGameFirstScreen);
    }



    @FXML
    void char1selected(){ Game.getInstance().setCharacter("character1"); }

    @FXML
    void char2selected(){
        Game.getInstance().setCharacter("character2");
    }

    @FXML
    void char3selected(){
        Game.getInstance().setCharacter("character3");
    }

    @FXML
    void char4selected(){
        Game.getInstance().setCharacter("character4");
    }


    @FXML
    void Fight(ActionEvent event) throws IOException {
        {
            System.out.println("FIGHT");
            RunUIManager myRun = new RunUIManager();

            myController.screenLoadFromOtherSubs( myRun.screenNames, myRun.screenFiles);
            myController.changeScreen("MainRunScreen");
        }
    }


}
