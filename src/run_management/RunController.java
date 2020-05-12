import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RunController implements Initializable, ControlledScreen {

    ScreenController myController;

    public void setScreenParent(ScreenController screenParent){
        myController = screenParent;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playerNameLabel.setText(Game.getInstance().getPlayerName());
    }

    @FXML
    private Button backToMainMenuButton;

    @FXML
    void backToMainMenu(ActionEvent event) {
        myController.reloadScreen(NavigationUI.newGameFirstScreen, NavigationUI.newGameFirstScreenFile);
        myController.changeScreen(NavigationUI.mainMenuScreen);
    }

    @FXML
    void goToTreasure(ActionEvent event) {
        myController.changeScreen(RunUIManager.treasureScreen);
    }

    @FXML
    void goToMerchant(ActionEvent event) {
        myController.changeScreen(RunUIManager.merchantScreen);
    }

    @FXML
    void goToRest(ActionEvent event) {
        myController.changeScreen(RunUIManager.restScreen);
    }

    @FXML
    void goToCombat(ActionEvent event) {
        //myController.changeScreen(RunUIManager.c);
    }

    @FXML
    private Text MoneyLabel;

    @FXML
    private Text currentHPLabel;

    @FXML
    private Text maxHPLabel;

    @FXML
    private Text playerNameLabel;


    @FXML
    void openMap(ActionEvent event) {

    }

    @FXML
    void showDeck(ActionEvent event) {

    }

    @FXML
    void openSettings(ActionEvent event) {

    }


}
