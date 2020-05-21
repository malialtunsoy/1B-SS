import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CombatUIController implements  Initializable//,ControlledScreen {
{
    //ScreenController myController;

    private CombatUIAdapter adapter;
     @FXML FlowPane potions;
    @FXML FlowPane enemies;
    //@FXML AnchorPane hand;
    @FXML HBox hand;
    @FXML Button drawPile;
    @FXML Button discardPile;
    @FXML FlowPane statusEffects;
    @FXML Label energy;
    @FXML Label numDraw;
    @FXML Label numDiscard;
    @FXML AnchorPane popUpDisplay;
    @FXML Label targetPrompt;
    @FXML Label choosePrompt;

    @FXML ImageView cardHighlight1;
    @FXML ImageView cardHighlight2;
    @FXML ImageView cardHighlight3;

    @FXML private Text MoneyLabel;
    @FXML private Text currentHPLabel;
    @FXML private Text maxHPLabel;
    @FXML private Text playerNameLabel;
    @FXML private ImageView potionSlot1;
    @FXML private ImageView potionSlot2;
    @FXML private ImageView potionSlot3;
    @FXML ImageView closePopUp;
    @FXML private ImageView character;

    @FXML private ImageView rewardCard1;
    @FXML private ImageView rewardCard2;
    @FXML private ImageView rewardCard3;


    @FXML private AnchorPane rewardsScreen;
    @FXML private FlowPane rewardPanes;

    boolean rewardCardPicked;

    public HashMap<Enemy, FlowPane> EnemiesAndTheirFlowPanes = new HashMap<Enemy, FlowPane>(); //GUI++
    public HashMap<Enemy, EnemyViewController> EnemiesAndTheirControllers = new HashMap<Enemy, EnemyViewController>(); //GUI++

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        character.setImage(new Image(CombatManager.getInstance().getPlayer().playerChar + ".png"));
        rewardCardPicked = false;
        playerNameLabel.setText(Game.getInstance().myPlayer.getPlayerName());

        maxHPLabel.setText(""+(Game.getInstance().myPlayer.getMaxHP()));
        MoneyLabel.setText(""+(Game.getInstance().myPlayer.getGold()));

        reloadPotions();
        reloadRelics();
        popUpDisplay.setDisable(true);
        popUpDisplay.setVisible(false);
        closePopUp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                popUpDisplay.setVisible(false);
                popUpDisplay.setDisable(true);
            }
        });
        targetPrompt.setVisible(false);

        rewardsScreen.setVisible(false);
        rewardsScreen.setDisable(true);
    }

    public void reloadPotions(){
        System.out.println("enabled");
        ArrayList<Potion> pots = CombatManager.getInstance().getPlayer().getPots();

        if(pots.size() > 0){
            Image slot1  = new Image(pots.get(0).getImage()); potionSlot1.setImage(slot1);
            Tooltip.install(potionSlot1, new Tooltip(pots.get(0).getName() + ": " + pots.get(0).getPotionDescription()));
            if(CombatManager.getInstance().combatOngoing())
                potionSlot1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        CombatManager.getInstance().potionSelected(pots.get(0));
                    }
                });
            potionSlot1.setDisable(false);
        }
        else{potionSlot1.setImage(null);potionSlot1.setDisable(true);}



        if(pots.size() > 1){Image slot2  = new Image(pots.get(1).getImage()); potionSlot2.setImage(slot2);
            Tooltip.install(potionSlot2, new Tooltip( pots.get(1).getName() + ": " + pots.get(1).getPotionDescription()));
            if(CombatManager.getInstance().combatOngoing())
                potionSlot2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        CombatManager.getInstance().potionSelected(pots.get(1));
                    }

                });
            potionSlot2.setDisable(false);
        }
        else{potionSlot2.setImage(null);potionSlot2.setDisable(true);}

        if(pots.size() > 2){Image slot3  = new Image(pots.get(2).getImage()); potionSlot3.setImage(slot3);
            Tooltip.install(potionSlot3, new Tooltip( pots.get(2).getName() + ": " + pots.get(2).getPotionDescription()));
            if(CombatManager.getInstance().combatOngoing())
                potionSlot3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                   @Override
                    public void handle(MouseEvent event) {
                    CombatManager.getInstance().potionSelected(pots.get(2));
                }
                });
            potionSlot3.setDisable(false);
        }
        else{potionSlot3.setImage(null);potionSlot3.setDisable(true);}
    }



    @FXML
    private HBox relicSlotHBox;

    public void reloadRelics(){
        ArrayList<Relic> relics = CombatManager.getInstance().getPlayer().getRelics();
        relicSlotHBox.getChildren().clear();
        for(int i = 0; i < relics.size(); i++){
            ImageView tempRelicImage = new ImageView();
            tempRelicImage.setFitWidth(56);
            tempRelicImage.setFitHeight(56);
            Image relicImage = new Image(relics.get(i).getImage());
            tempRelicImage.setImage(relicImage);
            tempRelicImage.setPickOnBounds(true);
            Tooltip.install(tempRelicImage, new Tooltip( relics.get(i).getName() + ": " + relics.get(i).getRelicDescription()));
            relicSlotHBox.getChildren().add(tempRelicImage);

        }
    }

    @FXML
    protected void endTurn(){
        targetPrompt.setVisible(false);
        adapter.endTurnPressed();
    }

    public void setUIAdapter( CombatUIAdapter adapter) {
        this.adapter = adapter;
    }

    public void updatePlayer() throws IOException{
        statusEffects.getChildren().clear();
        for(StatusEffect effect : CombatManager.getInstance().getPlayer().getStatusEffects())
            if(! (effect instanceof RelicEffect)){
                FileInputStream file = new FileInputStream("src/res/StatusEffectView.fxml");
                FXMLLoader loader = new FXMLLoader();
                AnchorPane pane = loader.load(file);
                StatusEffectViewController controller = loader.getController();
                controller.setImage(effect.getImage());
                controller.setCounter(effect.getCounter());
                Tooltip.install(pane, new Tooltip(effect.getDescription()));
                statusEffects.getChildren().add(pane);

            }

        energy.setText(CombatManager.getInstance().uiEnergyString());

        currentHPLabel.setText(""+(CombatManager.getInstance().getPlayer().getHP()));
        maxHPLabel.setText(""+(CombatManager.getInstance().getPlayer().getMaxHP()));
    }

    boolean firstTime = true;
    public void updateEnemies() throws IOException {
        enemies.getChildren().clear();
        //==================================== new
       // EnemiesAndTheirFlowPanes.clear(); //GUI++
        if(firstTime){
            for (Enemy e : CombatManager.getInstance().getEnemies()) {

                FileInputStream file = new FileInputStream("src/res/EnemyView.fxml");
                FXMLLoader loader = new FXMLLoader();
                FlowPane pane = loader.load(file);
                EnemiesAndTheirFlowPanes.put(e, pane); //GUI++

                EnemyViewController controller = loader.getController();

                EnemiesAndTheirControllers.put(e, controller);
                controller.setHp(e.getHP(), e.getMaxHP());
                controller.setIntent(e.getIntents());
                controller.getImage().setImage(new Image(e.getImage()));
                controller.getImage().setFitWidth(150);
                controller.getImage().setFitHeight(150);
                controller.getButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        CombatManager.getInstance().targetSelected(e);
                    }
                });
                controller.setStatus(e.getStatusEffects());
                controller.setName(e.getName());

                enemies.getChildren().add(pane);
            }
            firstTime = false;
        }
        else{
            for (Enemy e : CombatManager.getInstance().getEnemies()) {
                FlowPane pane = EnemiesAndTheirFlowPanes.get(e);
                EnemyViewController controller = EnemiesAndTheirControllers.get(e);
                controller.setHp(e.getHP(), e.getMaxHP());
                controller.setIntent(e.getIntents());
                controller.getImage().setImage(new Image(e.getImage()));
                controller.getImage().setFitWidth(150);
                controller.getImage().setFitHeight(150);
                controller.getButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        CombatManager.getInstance().targetSelected(e);
                    }
                });
                controller.setStatus(e.getStatusEffects());
                controller.setName(e.getName());

                enemies.getChildren().add(pane);
            }
        }
        //====================================================== old
        /*
        for (Enemy e : CombatManager.getInstance().getEnemies()) {

            FileInputStream file = new FileInputStream("src/res/EnemyView.fxml");
            FXMLLoader loader = new FXMLLoader();
            FlowPane pane = loader.load(file);
            EnemiesAndTheirFlowPanes.put(e, pane); //GUI++
            EnemyViewController controller = loader.getController();
            controller.setHp(e.getHP(), e.getMaxHP());
            controller.setIntent(e.getIntents());
            controller.getImage().setImage(new Image(e.getImage()));
            controller.getImage().setFitWidth(150);
            controller.getImage().setFitHeight(150);
            controller.getButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    CombatManager.getInstance().targetSelected(e);
                }
            });
            controller.setStatus(e.getStatusEffects());
            controller.setName(e.getName());

            enemies.getChildren().add(pane);
        }*/
    }

    public void updatePotions(){
        reloadPotions();
    }

    public void updateCardPiles(){
        hand.getChildren().clear();
        ArrayList<Card> cards = CombatManager.getInstance().getHand();
        int i = 0;

        for (Card c : cards) {
            ImageView img = new ImageView(c.getImage());
            img.setFitWidth(75);
            img.setFitHeight(100);
            Button imageButton = new Button("", img);
            imageButton.getStylesheets().add("card.css");
            imageButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    CombatManager.getInstance().cardSelected(c);
                }
            });

            hand.getChildren().add(imageButton);
            //img.setStyle("-fx-background-color: rgba(0, 0, 0, 1);");
            imageButton.setLayoutX(147 * i);
            imageButton.setLayoutY(0);
            i++;
        }

        /*hand.getParent().getParent().setStyle("-fx-background-color: rgba(0, 0, 0, 0.1);");
        hand.getParent().setStyle("-fx-background-color: rgba(0, 0, 0, 0.1);");
        hand.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1);");*/
        //hand.setPrefWrapLength(147 * cards.size());

        numDraw.setText(CombatManager.getInstance().getDrawPileSize() + "");
        numDiscard.setText(CombatManager.getInstance().getDiscardPileSize() +"");

    }

    @FXML
    void backToMap() {
        CombatManager.getInstance().backToMap();
    }

    @FXML
    void showDrawPile() {
        choosePrompt.setText("");
        if(CombatManager.getInstance().getDrawPile().isEmpty())
            return;
        popUpDisplay.setDisable(false);
        popUpDisplay.setVisible(true);
        popUpDisplay.setStyle("-fx-background-color: rgba(0, 0, 0, 1);");
        popUpDisplay.getParent().setStyle("-fx-background-color: rgba(0, 0, 0, 1);");
        FlowPane cards = (FlowPane) (((ScrollPane)popUpDisplay.getChildren().get(0)).getContent());
        cards.getChildren().clear();
        for(Card c : CombatManager.getInstance().getDrawPile()) {
            ImageView img = new ImageView(c.getImage());
            img.setFitWidth(148*1.5);
            img.setFitHeight(200*1.5);
            cards.getChildren().add(img);
            img.setStyle("-fx-background-color: rgba(0, 0, 0, 1);");
        }
        System.out.println("Showing draw pile");
    }


    void chooseCard(ArrayList<Card> options, String prompt) {
        choosePrompt.setText(prompt);
        popUpDisplay.setDisable(false);
        popUpDisplay.setVisible(true);
        FlowPane cards = (FlowPane) (((ScrollPane)popUpDisplay.getChildren().get(0)).getContent());
        cards.getChildren().clear();

        for(Card c : options) {
            ImageView img = new ImageView(c.getImage());
            img.setFitWidth(148*1.5);
            img.setFitHeight(200*1.5);
            cards.getChildren().add(img);
            img.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    CombatManager.getInstance().cardSelectedForCallback(c);
                    popUpDisplay.setVisible(false);
                    popUpDisplay.setDisable(true);
                }
            });
        }
    }


    @FXML
    void showDiscardPile() {
        choosePrompt.setText("");
        if(CombatManager.getInstance().getDiscardPile().isEmpty())
            return;
        popUpDisplay.setDisable(false);
        popUpDisplay.setVisible(true);
        FlowPane cards = (FlowPane) (((ScrollPane)popUpDisplay.getChildren().get(0)).getContent());
        cards.getChildren().clear();
        for(Card c : CombatManager.getInstance().getDiscardPile()) {
            ImageView img = new ImageView(c.getImage());
            img.setFitWidth(148*1.5);
            img.setFitHeight(200*1.5);
            cards.getChildren().add(img);
        }
        System.out.println("Showing discard pile");
    }

    public void loadRewardsScreen() throws IOException{
        rewardsScreen.setDisable(false);
        rewardsScreen.setVisible(true);

        FileInputStream goldFile = new FileInputStream("src/res/RewardPane.fxml");
        FXMLLoader goldLoader = new FXMLLoader();
        AnchorPane goldPane = goldLoader.load(goldFile);
        RewardPaneController goldController = goldLoader.getController();
        goldController.setParentController(this);
        goldController.setRewardGold(CombatManager.getInstance().getPlayer().getRewardGold());
        rewardPanes.getChildren().add(goldPane);

        for(Potion pot : CombatManager.getInstance().getPlayer().getRewardPotions()) {
            FileInputStream file = new FileInputStream("src/res/RewardPane.fxml");
            FXMLLoader loader = new FXMLLoader();
            AnchorPane rewardPane = loader.load(file);
            RewardPaneController controller = loader.getController();
            controller.setParentController(this);
            controller.setRewardPotion(pot);
            Tooltip.install(rewardPane,new Tooltip(pot.getPotionDescription()));
            rewardPanes.getChildren().add(rewardPane);
        }

        for(Relic relic : CombatManager.getInstance().getPlayer().getRewardRelics()) {
            FileInputStream file = new FileInputStream("src/res/RewardPane.fxml");
            FXMLLoader loader = new FXMLLoader();
            AnchorPane rewardPane = loader.load(file);
            RewardPaneController controller = loader.getController();
            controller.setRewardRelic(relic);
            controller.setParentController(this);
            Tooltip.install(rewardPane,new Tooltip(relic.getRelicDescription()));
            rewardPanes.getChildren().add(rewardPane);
        }


        rewardCard1.setImage(new Image(CombatManager.getInstance().getPlayer().getRewardCards().get(0).getImage()));
        rewardCard1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                CombatManager.getInstance().getPlayer().addToDeck(CombatManager.getInstance().getPlayer().getRewardCards().get(0));
                cardHighlight1.setVisible(true);
                disableRewardCards();
            }
        });
        System.out.println(CombatManager.getInstance().getPlayer().getRewardCards().get(1).getImage());
        rewardCard2.setImage(new Image(CombatManager.getInstance().getPlayer().getRewardCards().get(1).getImage()));
        rewardCard2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                CombatManager.getInstance().getPlayer().addToDeck(CombatManager.getInstance().getPlayer().getRewardCards().get(1));
                cardHighlight2.setVisible(true);
                disableRewardCards();
            }
        });

        rewardCard3.setImage(new Image(CombatManager.getInstance().getPlayer().getRewardCards().get(2).getImage()));
        rewardCard3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                CombatManager.getInstance().getPlayer().addToDeck(CombatManager.getInstance().getPlayer().getRewardCards().get(2));
                cardHighlight3.setVisible(true);
                disableRewardCards();
            }
        });
        disablePotionButtons();
    }

    public void disableRewardCards() {
        rewardCard1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                return;
            }
        });

        rewardCard2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                return;
            }
        });

        rewardCard3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                return;
            }
        });

        rewardCardPicked = true;
        checkRewardsDone();
    }

    public void checkRewardsDone() {
        if(rewardPanes.getChildren().isEmpty() && rewardCardPicked)
            backToMap();
    }

    public void disablePotionButtons() {
        System.out.println("disabled");
        potionSlot1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                return;
            }
        });
        potionSlot2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                return;
            }
        });
        potionSlot3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                return;
            }
        });
    }

    public void reloadGold(){
        MoneyLabel.setText(Game.getInstance().myPlayer.getGold()+"");
    }

    @FXML
    void openMap(ActionEvent event) {
        CombatManager.getInstance().showMap();
    }

    @FXML
    void showDeck(ActionEvent event) {
        CombatManager.getInstance().showDeck();
    }

    @FXML
    void openSettings(ActionEvent event) { ///yeni fxml ve controller kur
        CombatManager.getInstance().showSettings();
    }

    public void showPrompt(boolean show ,String name) {
        targetPrompt.setText("Choose a target for " + name);
        targetPrompt.setVisible(show);
    }

    @FXML
    private Rectangle rect;
    public void takeDamageAnimation(){

        rect.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(500), rect);
        ft.setFromValue(0.3);
        ft.setToValue(0.0);
        //ft.setCycleCount(Timeline.INDEFINITE);
       //ft.setAutoReverse(true);
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rect.setVisible(false);
            }
        });
        ft.play();

    }

    public void attackAnimation(FlowPane pane){

        TranslateTransition translateTransition =
                new TranslateTransition(Duration.millis(250), pane);
        translateTransition.setFromX(0);
        translateTransition.setToX(-25);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);
        translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TranslateTransition back =
                        new TranslateTransition(Duration.millis(250), pane);
                back.setFromX(-25);
                back.setToX(0);
                back.setCycleCount(1);
                //back.
                back.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
                //back.setAutoReverse(true);
                back.play();
            }
        });
        translateTransition.play();
        //MAKE PLAYER SMALLER
        ScaleTransition smallPlayer =
                new ScaleTransition(Duration.millis(250), character);
        smallPlayer.setFromX(1);
        smallPlayer.setFromY(1);
        smallPlayer.setToX(0.9);
        smallPlayer.setToY(0.9);
        //scaleTransition.setCycleCount(1);
        //scaleTransition.setAutoReverse(true);
        smallPlayer.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ScaleTransition resizePlayer =
                        new ScaleTransition(Duration.millis(250), character);
                resizePlayer.setFromX(0.9);
                resizePlayer.setFromY(0.9);
                resizePlayer.setToX(1);
                resizePlayer.setToY(1);
                //scaleTransition.setCycleCount(1);
                //scaleTransition.setAutoReverse(true);
                resizePlayer.setOnFinished(new EventHandler<ActionEvent>() {   @Override
                public void handle(ActionEvent event) {
                }      });
                resizePlayer.play();
            }


        });

        smallPlayer.play();


    }

    public void playerAttackAnimation(FlowPane pane) {

        TranslateTransition translateTransition =
                new TranslateTransition(Duration.millis(250), character);
        translateTransition.setFromX(0);
        translateTransition.setToX(25);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);
        translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TranslateTransition back =
                        new TranslateTransition(Duration.millis(250), character);
                back.setFromX(25);
                back.setToX(0);
                back.setCycleCount(1);
                //back.
                back.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
                //back.setAutoReverse(true);
                back.play();
            }
        });
        translateTransition.play();

        /*TranslateTransition target =
                new TranslateTransition(Duration.millis(250), pane);
        target.setFromY(0);
        target.setToY(15);
        target.setCycleCount(1);
        target.setAutoReverse(true);
        target.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TranslateTransition back =
                        new TranslateTransition(Duration.millis(250), pane);
                back.setFromY(15);
                back.setToY(0);
                back.setCycleCount(1);
                //back.
                back.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
                //back.setAutoReverse(true);
                back.play();
            }
        });
        target.play();*/

        ScaleTransition smallEnemy =
                new ScaleTransition(Duration.millis(250), pane);
        smallEnemy.setFromX(1);
        smallEnemy.setFromY(1);
        smallEnemy.setToX(0.8);
        smallEnemy.setToY(0.8);
        //scaleTransition.setCycleCount(1);
        //scaleTransition.setAutoReverse(true);
        smallEnemy.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ScaleTransition resizeEnemy =
                        new ScaleTransition(Duration.millis(250), pane);
                resizeEnemy.setFromX(0.8);
                resizeEnemy.setFromY(0.8);
                resizeEnemy.setToX(1);
                resizeEnemy.setToY(1);
                //scaleTransition.setCycleCount(1);
                //scaleTransition.setAutoReverse(true);
                resizeEnemy.setOnFinished(new EventHandler<ActionEvent>() {   @Override
                public void handle(ActionEvent event) {
                }      });
                resizeEnemy.play();
            }


        });

        smallEnemy.play();

    }


    public void playerDefenceMotion(){

        ScaleTransition biggerPlayer =
                new ScaleTransition(Duration.millis(350), character);
        biggerPlayer.setFromX(1);
        biggerPlayer.setFromY(1);
        biggerPlayer.setToX(1.2);
        biggerPlayer.setToY(1.2);
        //scaleTransition.setCycleCount(1);
        //scaleTransition.setAutoReverse(true);
        biggerPlayer.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ScaleTransition resizePlayer =
                        new ScaleTransition(Duration.millis(350), character);
                resizePlayer.setFromX(1.2);
                resizePlayer.setFromY(1.2);
                resizePlayer.setToX(1);
                resizePlayer.setToY(1);
                //scaleTransition.setCycleCount(1);
                //scaleTransition.setAutoReverse(true);
                resizePlayer.setOnFinished(new EventHandler<ActionEvent>() {   @Override
                public void handle(ActionEvent event) {
                }      });
                resizePlayer.play();
            }


        });

        biggerPlayer.play();



    }



}
