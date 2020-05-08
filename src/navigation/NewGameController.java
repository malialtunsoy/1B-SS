import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        else {
            Game.getInstance().setPlayerName(playerName);
            System.out.println("player name: " + playerName);
            myController.changeScreen(NavigationUI.newGameSecondScreen);
        }


    }

    @FXML
    void backToMainMenu(ActionEvent event) {
        myController.changeScreen(NavigationUI.mainMenuScreen);
    }


    @FXML
    void newGame(ActionEvent event) throws IOException {
        myController.changeScreen(NavigationUI.newGameFirstScreen);
    }

    @FXML
    void Fight(ActionEvent event) throws IOException {
        {
            System.out.println("FIGHT");
        }
    }

    @FXML
    void char1selected(){
        System.out.println("character 1 selected");
    }

    @FXML
    void char2selected(){
        System.out.println("character 2 selected");
    }

    @FXML
    void char3selected(){
        System.out.println("character 3 selected");
    }

    @FXML
    void char4selected(){
        System.out.println("character 4 selected");
    }



}
