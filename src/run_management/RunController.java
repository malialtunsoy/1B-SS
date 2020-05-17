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
import javafx.scene.shape.Line;
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
            Tooltip.install(tempRelicImage, new Tooltip(relics.get(i).getName() + ": "+  relics.get(i).getRelicDescription()));
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
        CombatTest test = new CombatTest();
        test.testCombat(CombatManager.getInstance().getStage());
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

    @FXML
    private AnchorPane mapAnchorPane;

    void createMap() { //--------------------109,136 -------- 946,136"
        //System.out.println("map drawn");//    -                  -      488
        //  109,624-----------946,624
        int numberOfVertices = 25;          //       837
        int verticesSize = 50;
        int index = 0;

        mapAnchorPane.getChildren().clear();

        Button[] vertices = new Button[numberOfVertices];
        ImageView[] verticesImage = new ImageView[numberOfVertices];
        Image[] verticesImageIn = new Image[numberOfVertices];

        Map.VertexNode[] paths = Game.getInstance().myPlayer.myMap.getPaths();

        String verticeType;

        for (int i = 0; i < 4; i++) {    //DRAW LINES
            for (Map.VertexNode temp = paths[i]; temp != null; temp = temp.getNext()) {
                Line tempLine = null;
                Line tempLine2 = null;
                if (temp.getNext() != null) {
                    tempLine = new Line(temp.getLocationX() + 25, temp.getLocationY() + 25, temp.getNext().getLocationX() + 25, temp.getNext().getLocationY() + 25);
                    tempLine.getStrokeDashArray().addAll(10d, 10d);
                }

                if (temp.getAlternativeNext() != null) {
                    tempLine2 = new Line(temp.getLocationX() + 25, temp.getLocationY() + 25, temp.getAlternativeNext().getLocationX() + 25, temp.getAlternativeNext().getLocationY() + 25);
                    tempLine2.getStrokeDashArray().addAll(10d, 10d);
                }

                if (tempLine != null) mapAnchorPane.getChildren().add(tempLine);
                if (tempLine2 != null) mapAnchorPane.getChildren().add(tempLine2);

            }

        }

        for (int i = 0; i < 4; i++) { //DRAW BUTTONS
            for (Map.VertexNode temp = paths[i]; temp != null; temp = temp.getNext(), index++) {

                vertices[index] = new Button();
                vertices[index].setPrefHeight(verticesSize);
                vertices[index].setPrefWidth(verticesSize);

                verticesImage[index] = new ImageView();
                verticesImage[index].setFitHeight(verticesSize);
                verticesImage[index].setFitWidth(verticesSize);
                verticesImage[index].setPickOnBounds(true);
                verticesImage[index].setPreserveRatio(true);

                verticesImageIn[index] = new Image(findImage(temp.getVertex()));
                verticesImage[index].setImage(verticesImageIn[index]);

                vertices[index].setGraphic(verticesImage[index]);

                vertices[index].setLayoutX(temp.getLocationX());
                vertices[index].setLayoutY(temp.getLocationY());

                vertices[index].setDisable(!temp.getAvailable());
                vertices[index].setOpacity(100);

                Map.VertexNode thisVertex = temp;
                vertices[index].setOnAction(e -> {Game.getInstance().myPlayer.myMap.setCurrentVertex(thisVertex);
                createMap();

                if(thisVertex.getVertex().equals("Merchant")){
                    myController.reloadScreen(RunUIManager.merchantScreen, RunUIManager.merchantScreenFile);
                    myController.changeScreen(RunUIManager.merchantScreen);
                    SaveAndExit.save();
                }

                    if(thisVertex.getVertex().equals("Rest")){
                        myController.reloadScreen(RunUIManager.restScreen, RunUIManager.restScreenFile);
                        myController.changeScreen(RunUIManager.restScreen);
                        SaveAndExit.save();
                    }

                    if(thisVertex.getVertex().equals("Treasure")){
                        myController.reloadScreen(RunUIManager.treasureScreen, RunUIManager.treasureScreenFile);
                        myController.changeScreen(RunUIManager.treasureScreen);
                        SaveAndExit.save();
                    }

                    if(thisVertex.getVertex().equals("Combat")){
                        System.out.println("COMBAT");
                        //To be added
                    }

                    if(thisVertex.getVertex().equals("EliteCombat")){
                        System.out.println("ELITE COMBAT");
                        //To be added
                    }

                    if(thisVertex.getVertex().equals("Boss")){
                        System.out.println("BOSS");
                        //To be added
                    }

                });


                mapAnchorPane.getChildren().add(vertices[index]);

            }
        }

        //put pin
        ImageView pin = new ImageView();
        pin.setFitHeight(30);
        pin.setFitWidth(30);
        pin.setPickOnBounds(true);
        pin.setPreserveRatio(true);
        Map.VertexNode curNode = Game.getInstance().myPlayer.myMap.getCurrentVertex();
        if(Game.getInstance().myPlayer.myMap.getLevel() > 0) {
            pin.setLayoutX(Game.getInstance().myPlayer.myMap.getCurrentVertex().getLocationX()+18);
            pin.setLayoutY(Game.getInstance().myPlayer.myMap.getCurrentVertex().getLocationY()-20);

            Image pinImage = new Image("mapPin.png");
            pin.setImage(pinImage);

            mapAnchorPane.getChildren().add(pin);
        }

    }
        public String findImage (String VertexName){
            if (VertexName.equals("Combat")) {
                return "combat+.png";
            }
            if (VertexName.equals("EliteCombat")) {
                return "eliteCombat+.png";
            }
            if (VertexName.equals("Rest")) {
                return "rest+.png";
            }
            if (VertexName.equals("Treasure")) {
                return "treasure+.png";
            }
            if (VertexName.equals("Merchant")) {
                return "merchant+.png";
            }
            if (VertexName.equals("Boss")) {
                return "boss+.png";
            }
            return null;
        }



}
