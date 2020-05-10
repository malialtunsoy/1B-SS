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

    @FXML
    void backToMainMenu(ActionEvent event) {
        myController.changeScreen(NavigationUI.mainMenuScreen);
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



}
