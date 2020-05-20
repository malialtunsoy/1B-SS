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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    String[] treasureType = new String[3];
    Potion[] potions = new Potion[3];
    Relic[] relics = new Relic[3];

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playerNameLabel.setText(Game.getInstance().myPlayer.getPlayerName());
        currentHPLabel.setText(""+(Game.getInstance().myPlayer.getHP()));
        maxHPLabel.setText(""+(Game.getInstance().myPlayer.getMaxHP()));
        MoneyLabel.setText(""+(Game.getInstance().myPlayer.getGold()));
        reloadPotions();
        reloadRelics();

        Game.getInstance().myPlayer.generateTresRandom();

        ArrayList<Potion> treasurePots = Game.getInstance().myPlayer.getTresPots();
        ArrayList<Relic> treasureRelics = Game.getInstance().myPlayer.getTresRelics();

        int currButton = 0;

        for(int i = 0; i < treasurePots.size(); i++, currButton++){
            String name = treasurePots.get(i).getName();
            String imageSource = treasurePots.get(i).getImage();
            Image tempImage = new Image(imageSource);
            String description = treasurePots.get(i).getPotionDescription();

            if(currButton == 0){firstButton.setText(name); firstImage.setImage(tempImage); Tooltip.install(firstButton, new Tooltip(description)); }
            if(currButton == 1){secondButton.setText(name); secondImage.setImage(tempImage); Tooltip.install(secondButton, new Tooltip(description));}
            if(currButton == 2){thirdButton.setText(name); thirdImage.setImage(tempImage); Tooltip.install(thirdButton, new Tooltip(description));}
            treasureType[currButton] = "Potion";
            potions[currButton] = treasurePots.get(i);
        }

        for(int i = 0; i < treasureRelics.size(); i++, currButton++){
            String name = treasureRelics.get(i).getName();
            String imageSource = treasureRelics.get(i).getImage();
            Image tempImage = new Image(imageSource);
            String description = treasureRelics.get(i).getRelicDescription();

            if(currButton == 0){firstButton.setText(name); firstImage.setImage(tempImage); Tooltip.install(firstButton, new Tooltip(description)); }
            if(currButton == 1){secondButton.setText(name); secondImage.setImage(tempImage); Tooltip.install(secondButton, new Tooltip(description));}
            if(currButton == 2){thirdButton.setText(name); thirdImage.setImage(tempImage); Tooltip.install(thirdButton, new Tooltip(description));}
            treasureType[currButton] = "Relic";
            relics[currButton] = treasureRelics.get(i);
        }

    }

    @FXML
    private HBox relicSlotHBox;

    public void reloadRelics(){
        ArrayList<Relic> relics = Game.getInstance().myPlayer.getRelics();
        relicSlotHBox.getChildren().clear();
        for(int i = 0; i < relics.size(); i++){
            ImageView tempRelicImage = new ImageView();
            tempRelicImage.setFitWidth(56);
            tempRelicImage.setFitHeight(56);
            Image relicImage = new Image(relics.get(i).getImage());
            //Image relicImage = new Image("BurningBlood.png");
            tempRelicImage.setImage(relicImage);
            Tooltip.install(tempRelicImage, new Tooltip(relics.get(i).getName() + ": "+  relics.get(i).getRelicDescription()));
            relicSlotHBox.getChildren().add(tempRelicImage);
        }
    }

    @FXML
    void openMap(ActionEvent event) {
        myController.setBackFromMap(RunUIManager.treasureScreen);
        myController.reloadScreen(RunUIManager.quickMapScreen, RunUIManager.quickMapScreenFile);
        myController.changeScreen(RunUIManager.quickMapScreen);
    }

    @FXML
    void showDeck(ActionEvent event) {
        myController.setBackFromDeck(RunUIManager.treasureScreen);
        myController.reloadScreen(RunUIManager.deckScreen, RunUIManager.deckScreenFile);
        myController.changeScreen(RunUIManager.deckScreen);
    }

    @FXML
    void openSettings(ActionEvent event) { ///yeni fxml ve controller kur
        myController.setGetBackFromSettings(RunUIManager.treasureScreen);
        myController.changeScreen(NavigationUI.optionsScreen);
       // SaveAndExit.save();
    }

    @FXML
    void backToMap(ActionEvent event) {
        myController.changeScreen(RunUIManager.mainRunScreen);
    }


    @FXML
    void firstButtonClicked(ActionEvent event) {
        if(treasureType[0].equals("Potion")){  Game.getInstance().myPlayer.addPot(potions[0]);  }
        else{  Game.getInstance().myPlayer.addRelic(relics[0]); }
        myController.reloadScreen(RunUIManager.mainRunScreen, RunUIManager.mainRunScreenFile);
        myController.changeScreen(RunUIManager.mainRunScreen);
        myController.playInventory();
    }

    @FXML
    void secondButtonClicked(ActionEvent event) {
        if(treasureType[1].equals("Potion")){  Game.getInstance().myPlayer.addPot(potions[1]);  }
        else{  Game.getInstance().myPlayer.addRelic(relics[1]); }
        myController.reloadScreen(RunUIManager.mainRunScreen, RunUIManager.mainRunScreenFile);
        myController.changeScreen(RunUIManager.mainRunScreen);
        myController.playInventory();
    }

    @FXML
    void thirdButtonClicked(ActionEvent event) {
        if(treasureType[2].equals("Potion")){  Game.getInstance().myPlayer.addPot(potions[2]);  }
        else{  Game.getInstance().myPlayer.addRelic(relics[2]); }
        myController.reloadScreen(RunUIManager.mainRunScreen, RunUIManager.mainRunScreenFile);
        myController.changeScreen(RunUIManager.mainRunScreen);
        myController.playInventory();
    }

    @FXML
    private ImageView potionSlot1;

    @FXML
    private ImageView potionSlot2;

    @FXML
    private ImageView potionSlot3;

    public void reloadPotions(){
        ArrayList<Potion> pots = Game.getInstance().myPlayer.getPots();

        if(pots.size() > 0){Image slot1  = new Image( pots.get(0).getImage() ); potionSlot1.setImage(slot1);
            Tooltip.install(potionSlot1, new Tooltip(pots.get(0).getName() + ": " +  pots.get(0).getPotionDescription()));}
        //if(pots.size() > 0){Image slot1  = new Image(pots.get(0).getImage()); potionSlot1.setImage(slot1); }
        else{potionSlot1.setImage(null);}
        if(pots.size() > 1){Image slot2  = new Image(pots.get(1).getImage()); potionSlot2.setImage(slot2);
            Tooltip.install(potionSlot2, new Tooltip(pots.get(1).getName() + ": " +  pots.get(1).getPotionDescription()));}
        //if(pots.size() > 1){Image slot2  = new Image(pots.get(1).getImage()); potionSlot2.setImage(slot2); }
        else{potionSlot2.setImage(null);}
        if(pots.size() > 2){Image slot3  = new Image(pots.get(2).getImage()); potionSlot3.setImage(slot3);
            Tooltip.install(potionSlot3, new Tooltip(pots.get(2).getName() + ": " +  pots.get(2).getPotionDescription()));}
        //if(pots.size() > 2){Image slot3  = new Image(pots.get(2).getImage()); potionSlot3.setImage(slot3); }
        else{potionSlot3.setImage(null);}
    }

    

}
