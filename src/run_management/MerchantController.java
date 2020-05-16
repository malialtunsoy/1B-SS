import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MerchantController implements Initializable, ControlledScreen {

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
    private VBox cardsVBox;

    @FXML
    private VBox potionsVBox;

    @FXML
    private VBox relicsVBox;


    public void setScreenParent(ScreenController screenParent){
        myController = screenParent;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playerNameLabel.setText(Game.getInstance().myPlayer.getPlayerName());
        currentHPLabel.setText(""+(Game.getInstance().myPlayer.getHP()));
        maxHPLabel.setText(""+(Game.getInstance().myPlayer.getMaxHP()));
        MoneyLabel.setText(""+(Game.getInstance().myPlayer.getGold()));


        //CARDS SETTINGS INITALIZE************************************************************************************
        ArrayList<Card> myCards = Game.getInstance().myPlayer.getMerchantDeck();

        cardsVBox.setSpacing(10);
        //System.out.println( "size" + myCards.size() );
        int numberOfCards = myCards.size();
        int numberOfHboxesNeeded = (numberOfCards / 3) ;
        if(numberOfCards % 3 != 0){numberOfHboxesNeeded++;}

        HBox[] cardHBoxes = new HBox[numberOfHboxesNeeded];

        for(int i = 0; i < numberOfHboxesNeeded; i++){
            cardHBoxes[i] = new HBox();
            cardHBoxes[i].setAlignment(Pos.CENTER_LEFT);
            cardHBoxes[i].setPrefWidth(600);
            cardHBoxes[i].setPrefHeight(244);
            cardHBoxes[i].setSpacing(10);
            cardsVBox.getChildren().add(cardHBoxes[i]);
        }

        VBox[] cards = new VBox[numberOfCards];
        Button[] cardButton = new Button[numberOfCards];


        for(int i = 0; i < numberOfCards; i++){
            cards[i] = new VBox();
            cards[i].setAlignment(Pos.TOP_CENTER);
            cards[i].setPrefHeight(244);
            cards[i].setPrefWidth(193);
            cards[i].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

            //add image
            ImageView tempImage = new ImageView();
            tempImage.setFitHeight(160);
            tempImage.setFitWidth(200);
            tempImage.setPickOnBounds(true);
            tempImage.setPreserveRatio(true);

            Image tempImageIn = new Image(myCards.get(i).getImage());
            tempImage.setImage(tempImageIn);

            cards[i].getChildren().add(tempImage);
            //add text
            TextArea cardText = new TextArea();
            cardText.setEditable(false);

            cardText.setMinHeight(50);
            cardText.setPrefWidth(193);
            cardText.setMaxWidth(193);
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

            Text costText = new Text();
            costText.setText("Cost: " + myCards.get(i).getCost());


            cardButton[i] = new Button();
            cardButton[i].setText("Buy");
            int temp  =i;
            cardButton[i].setOnAction(e -> {Game.getInstance().myPlayer.purchaseCard(myCards.get(temp));
                        MoneyLabel.setText(""+(Game.getInstance().myPlayer.getGold()));});

            bottomBox.getChildren().add(costText);
            bottomBox.getChildren().add(cardButton[i]);

            cards[i].getChildren().add(bottomBox);

            //add card to HBox
            int cardHBoxPlace = (i/3);
            cardHBoxes[cardHBoxPlace].getChildren().add(cards[i]);

        }


        //POTION SETTINGS INITIALIZE  260*415 ********************************************************************
        potionsVBox.setSpacing(10);

        ArrayList<Potion> merchantPotion = Game.getInstance().myPlayer.getMerchantPotions();

        int numberOfPotions = merchantPotion.size();
        int numberOfHBoxes = numberOfPotions/3;
        if(numberOfPotions % 3 != 0){numberOfHBoxes++;}

        HBox[] potionsHBoxes = new HBox[numberOfHBoxes];

        for(int i = 0; i < numberOfHBoxes; i++){
            potionsHBoxes[i] = new HBox();
            potionsHBoxes[i].setAlignment(Pos.CENTER_LEFT);
            potionsHBoxes[i].setPrefWidth(415);
            potionsHBoxes[i].setPrefHeight(220);
            potionsHBoxes[i].setSpacing(10);
            potionsVBox.getChildren().add(potionsHBoxes[i]);
        }

        VBox[] potionsVBoxes = new VBox[numberOfPotions];
        Button[] potionButton = new Button[numberOfPotions];
        for(int i = 0; i < numberOfPotions ; i++){
            potionsVBoxes[i] = new VBox();
            potionsVBoxes[i].setAlignment(Pos.TOP_CENTER);
            potionsVBoxes[i].setPrefWidth(130);
            potionsVBoxes[i].setPrefHeight(220);
            potionsVBoxes[i].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

            //add image
            ImageView tempImage = new ImageView();
            tempImage.setFitHeight(100);
            tempImage.setFitWidth(100);
            tempImage.setPickOnBounds(true);
            tempImage.setPreserveRatio(true);

            Image tempImageIn = new Image("BlockPotion.png");
            tempImage.setImage(tempImageIn);

            potionsVBoxes[i].getChildren().add(tempImage);
            //add name
            Text nameText = new Text();
            nameText.setText(merchantPotion.get(i).getName());
            potionsVBoxes[i].getChildren().add(nameText);
            //add text
            TextArea potionText = new TextArea();
            potionText.setEditable(false);

            potionText.setMaxHeight(60);
            potionText.setPrefWidth(130);
            potionText.setMaxWidth(130);
            potionText.setText(merchantPotion.get(i).getPotionDescription());
            //cardText.getStyleClass().add("sample");
            potionText.setWrapText(true);

            potionsVBoxes[i].getChildren().add(potionText);

            //add cost and button

            HBox bottomBox = new HBox();

            bottomBox.setAlignment(Pos.CENTER);
            bottomBox.setPrefHeight(44);
            bottomBox.setPrefWidth(130);
            bottomBox.setSpacing(10);

            Text costText = new Text();
            costText.setText("Cost: " + merchantPotion.get(i).getPotionCost());


            potionButton[i] = new Button();
            potionButton[i].setText("Buy");
            int temp = i;
            potionButton[i].setOnAction(e -> {
            Game.getInstance().myPlayer.purchasePotion(merchantPotion.get(temp));
                MoneyLabel.setText(""+(Game.getInstance().myPlayer.getGold()));});

            bottomBox.getChildren().add(costText);
            bottomBox.getChildren().add(potionButton[i]);

            potionsVBoxes[i].getChildren().add(bottomBox);

            //add card to HBox
            int potionHBoxPlace = (i/3);
            potionsHBoxes[potionHBoxPlace].getChildren().add(potionsVBoxes[i]);

        }

        //RELIC SETTINGS INITIALIZE  260*415*******************************************************************
        relicsVBox.setSpacing(10);

        ArrayList<Relic> merchantRelic = Game.getInstance().myPlayer.getMerchantRelics();

        int numberOfRelics = merchantRelic.size();
        int numberOfHBoxesRelic = numberOfRelics/3;
        if(numberOfRelics % 3 != 0){numberOfHBoxesRelic++;}

        HBox[] relicHBoxes = new HBox[numberOfHBoxesRelic];

        for(int i = 0; i < numberOfHBoxesRelic; i++){
            relicHBoxes[i] = new HBox();
            relicHBoxes[i].setAlignment(Pos.CENTER_LEFT);
            relicHBoxes[i].setPrefWidth(415);
            relicHBoxes[i].setPrefHeight(220);
            relicHBoxes[i].setSpacing(10);
            relicsVBox.getChildren().add(relicHBoxes[i]);
        }

        VBox[] relicsVBoxes = new VBox[numberOfRelics];
        Button[] relicsButtons = new Button[numberOfRelics];
        for(int i = 0; i < numberOfRelics ; i++){
            relicsVBoxes[i] = new VBox();
            relicsVBoxes[i].setAlignment(Pos.TOP_CENTER);
            relicsVBoxes[i].setPrefWidth(130);
            relicsVBoxes[i].setPrefHeight(220);
            relicsVBoxes[i].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

            //add image
            ImageView tempImage = new ImageView();
            tempImage.setFitHeight(100);
            tempImage.setFitWidth(100);
            tempImage.setPickOnBounds(true);
            tempImage.setPreserveRatio(true);

            Image tempImageIn = new Image("BurningBloodRelic.png");
            tempImage.setImage(tempImageIn);

            relicsVBoxes[i].getChildren().add(tempImage);
            //add name
            Text nameText = new Text();
            nameText.setText(merchantRelic.get(i).getName());
            relicsVBoxes[i].getChildren().add(nameText);
            //add text
            TextArea relicText = new TextArea();
            relicText.setEditable(false);

            relicText.setMinHeight(30);
            relicText.setMaxHeight(50);
            relicText.setMaxWidth(130);
            relicText.setText(merchantRelic.get(i).getRelicDescription());
            relicText.setWrapText(true);

            relicsVBoxes[i].getChildren().add(relicText);

            //add cost and button

            HBox bottomBox = new HBox();

            bottomBox.setAlignment(Pos.CENTER);
            bottomBox.setPrefHeight(44);
            bottomBox.setPrefWidth(130);
            bottomBox.setSpacing(10);

            Text costText = new Text();
            costText.setText("Cost: 130");


            relicsButtons[i] = new Button();
            relicsButtons[i].setText("Buy");
            int temp = i;
            relicsButtons[i].setOnAction(e -> {Game.getInstance().myPlayer.purchaseRelic(merchantRelic.get(temp));
            MoneyLabel.setText(""+(Game.getInstance().myPlayer.getGold()));});

            bottomBox.getChildren().add(costText);
            bottomBox.getChildren().add(relicsButtons[i]);

            relicsVBoxes[i].getChildren().add(bottomBox);
            //relicsVBoxes[i].setBackground(Background.EMPTY);
            //add card to HBox
            int relicHBoxPlace = (i/3);
            relicHBoxes[relicHBoxPlace].getChildren().add(relicsVBoxes[i]);

        }


    }

    @FXML
    void openMap(ActionEvent event) {

    }

    @FXML
    void showDeck(ActionEvent event) {
        myController.setBackFromDeck(RunUIManager.merchantScreen);
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


}
