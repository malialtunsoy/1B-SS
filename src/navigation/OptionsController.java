import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionsController implements Initializable, ControlledScreen {

    ScreenController myController;

    public void setScreenParent(ScreenController screenParent){
        myController = screenParent;
    }

    @FXML
    private CheckBox musicCheckBox;

    @FXML
    private CheckBox advancedMainMenuBox;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    private boolean advancedMainMenuSelected = false;
    private boolean musicPlaying = true;

    String mainMenuOption = NavigationUI.mainMenuScreenFile;

    /*@FXML
    void backToMainMenu(ActionEvent event) {
        myController.changeScreen(NavigationUI.mainMenuScreen);
    }*/

    @FXML
    void back(ActionEvent event) {
        if(myController.getGetBackFromSettings().equals("CombatUI.fxml")){CombatManager.getInstance().comeBackFromSetting();}

        else{myController.changeScreen(myController.getGetBackFromSettings());}
    }

    @FXML
    void applySettings(ActionEvent event) {
        advancedMainMenuSelected = advancedMainMenuBox.isSelected();
        if(advancedMainMenuSelected){mainMenuOption = "experimental.fxml"; }
        else{mainMenuOption = "mainMenu.fxml";};

        if(!musicCheckBox.isSelected()){
            myController.stopMusic();
            musicPlaying = false;
        }
        else{
            if(!musicPlaying){
                myController.playMusic();
            }
        }

    }


    @FXML
    void saveGame(ActionEvent event) {
        SaveAndExit.save();
    }

    @FXML
    void saveAndExit(ActionEvent event) {
        SaveAndExit.exit();
    }


    @FXML
    void hpHack(ActionEvent event) {
        if(Game.getInstance().myPlayer != null){Game.getInstance().myPlayer.setMaxHP(999); Game.getInstance().myPlayer.setCurrentHP(999);}
    }

    @FXML
    void moneyHack(ActionEvent event) {
        if(Game.getInstance().myPlayer != null){Game.getInstance().myPlayer.setGold(999);}
    }

    @FXML
    void cardHack(ActionEvent event) {

    }


}
