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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
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

    public void setScreenParent(ScreenController screenParent){
        myController = screenParent;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        playerNameLabel.setText(Game.getInstance().myPlayer.getPlayerName());
        currentHPLabel.setText(""+(Game.getInstance().myPlayer.getHP()));
        maxHPLabel.setText(""+(Game.getInstance().myPlayer.getMaxHP()));
        MoneyLabel.setText(""+(Game.getInstance().myPlayer.getGold()));

        createMap();
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
        myController.reloadScreen(RunUIManager.treasureScreen, RunUIManager.treasureScreenFile);
        myController.changeScreen(RunUIManager.treasureScreen);
    }

    @FXML
    void goToMerchant(ActionEvent event) {
        myController.reloadScreen(RunUIManager.merchantScreen, RunUIManager.merchantScreenFile);
        myController.changeScreen(RunUIManager.merchantScreen);
    }

    @FXML
    void goToRest(ActionEvent event) {
        myController.reloadScreen(RunUIManager.restScreen, RunUIManager.restScreenFile);
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
        myController.setBackFromDeck(RunUIManager.mainRunScreen);
        myController.reloadScreen(RunUIManager.deckScreen, RunUIManager.deckScreenFile);
        myController.changeScreen(RunUIManager.deckScreen);
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

            System.out.println(i % 3+": "+positionX+","+positionY + "       " + layerYstart + "," + layerYend);
            if(i > 3){vertices[i].setDisable(true);}


        }


    }


}
