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

public class deckController implements Initializable, ControlledScreen {

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
        ArrayList<Card> myCards = Game.getInstance().myPlayer.getDeck();

        cardsVBox.setSpacing(10);
        //System.out.println( "size" + myCards.size() );
        int numberOfCards = myCards.size();
        int numberOfHboxesNeeded = (numberOfCards / 5) ;
        if(numberOfCards % 5 != 0){numberOfHboxesNeeded++;}

        HBox[] cardHBoxes = new HBox[numberOfHboxesNeeded];

        for(int i = 0; i < numberOfHboxesNeeded; i++){
            cardHBoxes[i] = new HBox();
            cardHBoxes[i].setAlignment(Pos.CENTER_LEFT);
            cardHBoxes[i].setPrefWidth(1045);
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

            //add card to HBox
            int cardHBoxPlace = (i/5);
            cardHBoxes[cardHBoxPlace].getChildren().add(cards[i]);

        }

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
    void back(ActionEvent event) {
        myController.changeScreen(myController.getBackFromDeck());
    }


}
