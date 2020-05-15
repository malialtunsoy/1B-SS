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
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TreasureController implements Initializable, ControlledScreen {

    ScreenController myController;

    @FXML
    private Text MoneyLabel;

    @FXML
    private Text currentHPLabel;

    @FXML
    private Text maxHPLabel;

    @FXML
    private Text playerNameLabel;



    @FXML
    private ImageView firstImage;

    @FXML
    private ImageView secondImage;

    @FXML
    private ImageView thirdImage;

    @FXML
    private Button firstButton;

    @FXML
    private Button secondButton;

    @FXML
    private Button thirdButton;




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
    void openMap(ActionEvent event) {

    }

    @FXML
    void showDeck(ActionEvent event) {

    }

    @FXML
    void openSettings(ActionEvent event) {

    }

    @FXML
    void backToMap(ActionEvent event) {
        myController.changeScreen(RunUIManager.mainRunScreen);
    }


    @FXML
    void firstButtonClicked(ActionEvent event) {
        //TO DO

        myController.changeScreen(RunUIManager.mainRunScreen);
    }

    @FXML
    void secondButtonClicked(ActionEvent event) {
       //TO DO

        myController.changeScreen(RunUIManager.mainRunScreen);
    }

    @FXML
    void thirdButtonClicked(ActionEvent event) {
        //TO DO

        myController.changeScreen(RunUIManager.mainRunScreen);
    }

}
