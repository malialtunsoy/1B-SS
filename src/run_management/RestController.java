import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RestController implements Initializable, ControlledScreen {

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

    int cardID;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playerNameLabel.setText(Game.getInstance().myPlayer.getPlayerName());
        currentHPLabel.setText(""+(Game.getInstance().myPlayer.getHP()));
        maxHPLabel.setText(""+(Game.getInstance().myPlayer.getMaxHP()));
        MoneyLabel.setText(""+(Game.getInstance().myPlayer.getGold()));
        reloadPotions();
        reloadRelics();

        cardID = -1;

        //initalize upgrade screen
        CardsVBox.setSpacing(10);

        ArrayList<Card> myCards = Game.getInstance().myPlayer.getDeck();
        int numberOfCardsToDisplay = myCards.size();
        int numberOfHBoxesNeeded = numberOfCardsToDisplay / 5;
        if(numberOfCardsToDisplay % 5 != 0){numberOfHBoxesNeeded++;}

        HBox[] cardHBoxes = new HBox[numberOfHBoxesNeeded];

        for(int i = 0; i < numberOfHBoxesNeeded; i++){
            cardHBoxes[i] = new HBox();
            cardHBoxes[i].setAlignment(Pos.CENTER_LEFT);
            cardHBoxes[i].setPrefWidth(980);
            cardHBoxes[i].setPrefHeight(240);
            cardHBoxes[i].setSpacing(10);
            CardsVBox.getChildren().add(cardHBoxes[i]);
        }

        VBox[] cards = new VBox[numberOfCardsToDisplay];
        Button[] cardButton = new Button[numberOfCardsToDisplay];

        //240 * 190

        for(int i = 0; i < numberOfCardsToDisplay; i++){
            cards[i] = new VBox();
            cards[i].setAlignment(Pos.TOP_CENTER);
            cards[i].setPrefHeight(240);
            cards[i].setPrefWidth(190);
            cards[i].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

            //add image
            ImageView tempImage = new ImageView();
            tempImage.setFitHeight(100);
            tempImage.setFitWidth(100);
            tempImage.setPickOnBounds(true);
            tempImage.setPreserveRatio(true);

            Image tempImageIn = new Image(myCards.get(i).getImage());
            tempImage.setImage(tempImageIn);

            cards[i].getChildren().add(tempImage);
            //add text
            TextArea cardText = new TextArea();
            cardText.setEditable(false);

            cardText.setMinHeight(100);
            cardText.setPrefWidth(180);
            cardText.setMaxWidth(180);
            cardText.setText(myCards.get(i).getDescription());
            //cardText.getStyleClass().add("sample");
            cardText.setWrapText(true);

            cards[i].getChildren().add(cardText);

            //add cost and button

            HBox bottomBox = new HBox();

            bottomBox.setAlignment(Pos.CENTER);
            bottomBox.setPrefHeight(44);
            bottomBox.setPrefWidth(193);
            bottomBox.setSpacing(10);

            int a = i;

            cardButton[i] = new Button();
            cardButton[i].setText("Upgrade");
            //BUTTON CLICKED **********************************************
            int temp = i;
            cardButton[i].setOnAction(e -> {

                cardID = a;

                System.out.println("Card  upgrade.");
                confirmTheUpdateVBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                confirmTheUpdateVBox.setVisible(true);

                currentCardVBox.setAlignment(Pos.TOP_CENTER);
                currentCardVBox.setPrefHeight(240);
                currentCardVBox.setPrefWidth(190);
                currentCardVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                currentCardVBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

                ImageView tempI = new ImageView();
                tempI.setFitHeight(100);
                tempI.setFitWidth(100);
                tempI.setPickOnBounds(true);
                tempI.setPreserveRatio(true);

                Image tempImIn = new Image(myCards.get(temp).getImage());
                tempI.setImage(tempImIn);

                currentCardVBox.getChildren().add(tempI);
                //add text
                TextArea text = new TextArea();
                text.setEditable(false);

                text.setMinHeight(100);
                text.setPrefWidth(180);
                text.setMaxWidth(180);
                text.setText(myCards.get(temp).getDescription());
                //cardText.getStyleClass().add("sample");
                text.setWrapText(true);

                currentCardVBox.getChildren().add(text);


                //OTHER CARD SET

                upgradedCardVBox.setAlignment(Pos.TOP_CENTER);
                upgradedCardVBox.setPrefHeight(240);
                upgradedCardVBox.setPrefWidth(190);
                upgradedCardVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                upgradedCardVBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

                ImageView tempI2 = new ImageView();
                tempI2.setFitHeight(100);
                tempI2.setFitWidth(100);
                tempI2.setPickOnBounds(true);
                tempI2.setPreserveRatio(true);

                Image tempImIn2 = new Image(myCards.get(temp).upgradedVersion().getImage());
                tempI2.setImage(tempImIn2);

                upgradedCardVBox.getChildren().add(tempI2);
                //add text
                TextArea text2 = new TextArea();
                text2.setEditable(false);

                text2.setMinHeight(100);
                text2.setPrefWidth(180);
                text2.setMaxWidth(180);
                text2.setText(myCards.get(temp).upgradedVersion().getDescription());
                //cardText.getStyleClass().add("sample");
                text2.setWrapText(true);

                upgradedCardVBox.getChildren().add(text2);
                Game.getInstance().myPlayer.upgradeCard(myCards.get(temp),temp);//does when card choosed but must do when 'confirmed'

            });//*************************************************************

            bottomBox.getChildren().add(cardButton[i]);

            cards[i].getChildren().add(bottomBox);

            //add card to HBox
            int cardHBoxPlace = (i/5);
            cardHBoxes[cardHBoxPlace].getChildren().add(cards[i]);

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
            //Image relicImage = new Image(relics.get(i).getImage());
            Image relicImage = new Image("BurningBloodRelic.png");
            tempRelicImage.setImage(relicImage);
            relicSlotHBox.getChildren().add(tempRelicImage);
        }
    }


    @FXML
    void openMap(ActionEvent event) {

    }

    @FXML
    void showDeck(ActionEvent event) {
        myController.setBackFromDeck(RunUIManager.restScreen);
        myController.reloadScreen(RunUIManager.deckScreen, RunUIManager.deckScreenFile);
        myController.changeScreen(RunUIManager.deckScreen);
    }

    @FXML
    void openSettings(ActionEvent event) {

    }

    @FXML
    void backToMap(ActionEvent event) {
        myController.changeScreen(RunUIManager.mainRunScreen);
    }


    @FXML
    private VBox confirmTheUpdateVBox;

    @FXML
    private VBox upgradeCardsVBox;

    @FXML
    private VBox CardsVBox;

    @FXML
    private VBox currentCardVBox;

    @FXML
    private VBox upgradedCardVBox;

    @FXML
    void healPlayer() {
        System.out.println("HEAL");
        //heal and go back to map
        double hp = Game.getInstance().myPlayer.getHP();
        double gainedHP = Game.getInstance().myPlayer.getMaxHP() * (0.3);
        double newHP = gainedHP + hp;
        if(newHP >= Game.getInstance().myPlayer.getMaxHP()){
            gainedHP = gainedHP - (newHP - Game.getInstance().myPlayer.getMaxHP()) ;
        }

        System.out.println("gained HP: "+ gainedHP);
        Game.getInstance().myPlayer.gainHP((int)gainedHP);

        myController.reloadScreen(RunUIManager.mainRunScreen, RunUIManager.mainRunScreenFile);
        myController.changeScreen(RunUIManager.mainRunScreen);

        System.out.println("Players HP: "+Game.getInstance().myPlayer.getHP());

    }

    @FXML
    void openUpgradePane() {
        upgradeCardsVBox.setVisible(true);
    }

    @FXML
    void closeUpgrade(ActionEvent event) {
        confirmTheUpdateVBox.setVisible(false);
        currentCardVBox.getChildren().clear();
        upgradedCardVBox.getChildren().clear();
    }

    @FXML
    void confirmUpgrade(ActionEvent event) {
            System.out.println("Card updated: " + cardID);//TO BE ADDED UPDATE THE CARDS
        myController.reloadScreen(RunUIManager.mainRunScreen, RunUIManager.mainRunScreenFile);
        myController.changeScreen(RunUIManager.mainRunScreen);
    }

    @FXML
    private ImageView potionSlot1;

    @FXML
    private ImageView potionSlot2;

    @FXML
    private ImageView potionSlot3;

    public void reloadPotions(){
        ArrayList<Potion> pots = Game.getInstance().myPlayer.getPots();

        if(pots.size() > 0){Image slot1  = new Image("BlockPotion.png"); potionSlot1.setImage(slot1); }
        //if(pots.size() > 0){Image slot1  = new Image(pots.get(0).getImage()); potionSlot1.setImage(slot1); }
        else{potionSlot1.setImage(null);}
        if(pots.size() > 1){Image slot2  = new Image("BlockPotion.png"); potionSlot2.setImage(slot2); }
        //if(pots.size() > 1){Image slot2  = new Image(pots.get(1).getImage()); potionSlot2.setImage(slot2); }
        else{potionSlot2.setImage(null);}
        if(pots.size() > 2){Image slot3  = new Image("BlockPotion.png"); potionSlot3.setImage(slot3); }
        //if(pots.size() > 2){Image slot3  = new Image(pots.get(2).getImage()); potionSlot3.setImage(slot3); }
        else{potionSlot3.setImage(null);}
    }


}
