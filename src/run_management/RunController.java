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

    @FXML
    private Text MoneyLabel;

    @FXML
    private Text currentHPLabel;

    @FXML
    private Text maxHPLabel;

    @FXML
    private Text playerNameLabel;

    public void setScreenParent(ScreenController screenParent){
        myController = screenParent;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        playerNameLabel.setText(Game.getInstance().myPlayer.getPlayerName());
        currentHPLabel.setText(""+(Game.getInstance().myPlayer.getHP()));
        maxHPLabel.setText(""+(Game.getInstance().myPlayer.getMaxHP()));
        MoneyLabel.setText(""+(Game.getInstance().myPlayer.getGold()));

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

        //myController.changeScreen(RunUIManager.combatScreen);
    }




    @FXML
    void openMap(ActionEvent event) {
            Stage mapStage = new Stage();
            mapStage.setTitle("Map");
            mapStage.setMaxWidth(1000);
            mapStage.setMaxHeight(600);

            //mapStage.setScene(mapScene);
            mapStage.show();
    }

    @FXML
    void showDeck(ActionEvent event) {

    }

    @FXML
    void openSettings(ActionEvent event) { ///yeni fxml ve controller kur
        Stage mapStage = new Stage();
        mapStage.setTitle("Options");
        mapStage.setMaxWidth(1200);
        mapStage.setMaxHeight(800);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(NavigationUI.optionsScreenFile));
            Parent opitonsScreen = (Parent) loader.load();
            mapStage.setScene(new Scene(opitonsScreen));
        }
        catch (IOException e){

        }


        mapStage.show();

    }


}
