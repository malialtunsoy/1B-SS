import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RunController implements Initializable, ControlledScreen {

    ScreenController myController;

    @FXML
    private AnchorPane anchorPaneMain;

    @FXML
    private Text MoneyLabel;

    @FXML
    private Text currentHPLabel;

    @FXML
    private Text maxHPLabel;

    @FXML
    private Text playerNameLabel;

    @FXML
    private ImageView potionSlot1;

    @FXML
    private ImageView potionSlot2;

    @FXML
    private ImageView potionSlot3;


    public void setScreenParent(ScreenController screenParent){
        myController = screenParent;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        playerNameLabel.setText(Game.getInstance().myPlayer.getPlayerName());
        currentHPLabel.setText(""+(Game.getInstance().myPlayer.getHP()));
        maxHPLabel.setText(""+(Game.getInstance().myPlayer.getMaxHP()));
        MoneyLabel.setText(""+(Game.getInstance().myPlayer.getGold()));

        reloadPotions();
        reloadRelics();

        createMap();
    }

    public void reloadPotions(){
        ArrayList<Potion> pots = Game.getInstance().myPlayer.getPots();

        if(pots.size() > 0){Image slot1  = new Image( pots.get(0).getImage() ); potionSlot1.setImage(slot1);
            Tooltip.install(potionSlot1, new Tooltip(pots.get(0).getPotionDescription()));}
        //if(pots.size() > 0){Image slot1  = new Image(pots.get(0).getImage()); potionSlot1.setImage(slot1); }
        else{potionSlot1.setImage(null);}
        if(pots.size() > 1){Image slot2  = new Image(pots.get(1).getImage()); potionSlot2.setImage(slot2);
            Tooltip.install(potionSlot2, new Tooltip(pots.get(1).getPotionDescription()));}
        //if(pots.size() > 1){Image slot2  = new Image(pots.get(1).getImage()); potionSlot2.setImage(slot2); }
        else{potionSlot2.setImage(null);}
        if(pots.size() > 2){Image slot3  = new Image(pots.get(2).getImage()); potionSlot3.setImage(slot3);
            Tooltip.install(potionSlot3, new Tooltip(pots.get(2).getPotionDescription()));}
        //if(pots.size() > 2){Image slot3  = new Image(pots.get(2).getImage()); potionSlot3.setImage(slot3); }
        else{potionSlot3.setImage(null);}
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
            //Image relicImage = new Image("BurningBloodRelic.png");
            tempRelicImage.setImage(relicImage);
            tempRelicImage.setPickOnBounds(true);
            Tooltip.install(tempRelicImage, new Tooltip(relics.get(i).getRelicDescription()));
            relicSlotHBox.getChildren().add(tempRelicImage);

        }
    }

    @FXML
    private Button backToMainMenuButton;

    @FXML
    void backToMainMenu(ActionEvent event) {
        myController.reloadScreen(NavigationUI.newGameFirstScreen, NavigationUI.newGameFirstScreenFile);
        myController.changeScreen(NavigationUI.mainMenuScreen);
        SaveAndExit.save();
    }

    @FXML
    void goToTreasure(ActionEvent event) {
        myController.reloadScreen(RunUIManager.treasureScreen, RunUIManager.treasureScreenFile);
        myController.changeScreen(RunUIManager.treasureScreen);
        SaveAndExit.save();
    }

    @FXML
    void goToMerchant(ActionEvent event) {
        myController.reloadScreen(RunUIManager.merchantScreen, RunUIManager.merchantScreenFile);
        myController.changeScreen(RunUIManager.merchantScreen);
        SaveAndExit.save();
    }

    @FXML
    void goToRest(ActionEvent event) {
        myController.reloadScreen(RunUIManager.restScreen, RunUIManager.restScreenFile);
        myController.changeScreen(RunUIManager.restScreen);
        SaveAndExit.save();
    }

    @FXML
    void goToCombat(ActionEvent event) {

        //myController.changeScreen(RunUIManager.combatScreen);
    }




    @FXML
    void openMap(ActionEvent event) {
        myController.setBackFromMap(RunUIManager.mainRunScreen);
        myController.reloadScreen(RunUIManager.quickMapScreen, RunUIManager.quickMapScreenFile);
        myController.changeScreen(RunUIManager.quickMapScreen);
        SaveAndExit.save();
    }

    @FXML
    void showDeck(ActionEvent event) {
        myController.setBackFromDeck(RunUIManager.mainRunScreen);
        myController.reloadScreen(RunUIManager.deckScreen, RunUIManager.deckScreenFile);
        myController.changeScreen(RunUIManager.deckScreen);
        SaveAndExit.save();
    }

    @FXML
    void openSettings(ActionEvent event) { ///yeni fxml ve controller kur
        myController.setGetBackFromSettings(RunUIManager.mainRunScreen);
        myController.changeScreen(NavigationUI.optionsScreen);
        SaveAndExit.save();
    }


    void createMap(){ //--------------------109,136 -------- 946,136"
        System.out.println("map drawn");//    -                  -      488
                                        //  109,624-----------946,624
        int numberOfVertices = 18;          //       837
        int verticesSize = 50;

        Button[] vertices = new Button[numberOfVertices];
        ImageView[] verticesImage = new ImageView[numberOfVertices];
        Image[] verticesImageIn = new Image[numberOfVertices];

        int layerXstart = 110;
        int layerXend = 200;

        int layerYstart = 140;
        int layerYend = 210;

        int positionY = 136;
        int positionX = 110;

        for(int i = 0; i < numberOfVertices; i++){
            String verticeType;
            int randomVertice = (int) ((Math.random()*4));
            verticeType = "combat+.png";
            if(randomVertice == 0){verticeType = "combat+.png";}
            if(randomVertice == 1){verticeType = "rest+.png";}
            if(randomVertice == 2){verticeType = "treasure+.png";}
            if(randomVertice == 3){verticeType = "merchant+.png";}
            if(i == numberOfVertices-1){verticeType = "boss+.png";}



            vertices[i] = new Button();
            vertices[i].setPrefHeight(verticesSize);
            vertices[i].setPrefWidth(verticesSize);

            verticesImage[i] = new ImageView();
            verticesImage[i].setFitHeight(verticesSize);
            verticesImage[i].setFitWidth(verticesSize);
            verticesImage[i].setPickOnBounds(true);
            verticesImage[i].setPreserveRatio(true);

            verticesImageIn[i] = new Image(verticeType );
            verticesImage[i].setImage(verticesImageIn[i]);

            vertices[i].setGraphic(verticesImage[i]);

            anchorPaneMain.getChildren().add(vertices[i]);

            if(i % 3 == 2){layerXstart = layerXend + 100; layerXend+=100;}

            if(i % 3 == 0){layerYstart  = 140; layerYend = 210;}
            if(i % 3 == 1){layerYstart  = 280; layerYend = 350;}
            if(i % 3 == 2){layerYstart  = 420; layerYend = 500;}

            positionX = (int)(Math.random()* (layerXend - layerXstart) + layerXstart);
            positionY = (int)(Math.random()* (layerYend - layerYstart) + layerYstart);

            vertices[i].setLayoutX(positionX);
            vertices[i].setLayoutY(positionY);

           // System.out.println(i % 3+": "+positionX+","+positionY + "       " + layerYstart + "," + layerYend);
            if(i > 3){vertices[i].setDisable(true);}


        }


    }


}
