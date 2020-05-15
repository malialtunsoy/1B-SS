import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
// not an actual subclass. this is just an easy fix to use the test version in place of the actual one.
public class TESTCombatUIAdapter extends CombatUIAdapter{
    // --- constants ---
    static final int SCENE_WIDTH = 300;
    static final int SCENE_HEIGHT = 300;

    static final int ENEMIES_HGAP = 300;
    static final int ENEMIES_VGAP = 300;

    // --- attributes ---
    Stage primaryStage;
    GridPane root;
    FlowPane enemies;
    FlowPane player;
    FlowPane endTurn;

    GridPane cardPiles; // contains the three following items
    FlowPane hand;
    Text drawPile;
    Text discardPile;

    // --- methods ---
    public void updateView() {
        updateEnemies();
        updatePlayer();
        updateCardPiles();
    }

    private void updateEnemies() {
        // for now, remove all displayed and re-add all enemies in combat
        enemies.getChildren().clear();
        for (Enemy e : CombatManager.getInstance().getEnemies()) {
            enemies.getChildren().add(new Text(e.toString()));
        }
    }

    private void updatePlayer() {
        ((Text)(player.getChildren().get(0))).setText(
                CombatManager.getInstance().getPlayer().toString()
                + "\n Energy: " + CombatManager.getInstance().uiEnergyString());
    }

    private void updateCardPiles() {
        hand.getChildren().clear();
        for (Card c : CombatManager.getInstance().getHand()) {
            Button cardBtn = new Button(c.toString());
            cardBtn.setFont(new Font("Consolas", 10));
            cardBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    CombatManager.getInstance().playCard(c, CombatManager.getInstance().getEnemies().get(0));
                }
            });

            hand.getChildren().add(cardBtn);
        }

        drawPile.setText("Draw Pile: " + CombatManager.getInstance().getDrawPileSize() + " cards");
        discardPile.setText("Discard Pile: " + CombatManager.getInstance().getDiscardPileSize() + " cards");
    }

    public TESTCombatUIAdapter(Stage primaryStage){
        // set the primaryStage attribute to be able to invoke Stage.show() again
        this.primaryStage = primaryStage;
        // initialize the scene
        root = new GridPane();
        root.setGridLinesVisible(true); // for debugging purposes
        primaryStage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));

        enemies = new FlowPane();
        root.add(enemies, 0, 0);
        initializeEnemies();

        initializePlayer();
        root.add(player, 1,0);

        endTurn = new FlowPane();
        root.add(endTurn,2,0);
        initializeEndTurn();

        initializeCardPiles();
        root.add(cardPiles, 0, 1);

        initializePotSimulators();

        updateView();
        primaryStage.show();
    }

    private void initializeCardPiles() {
        hand = new FlowPane();
        drawPile = new Text();
        discardPile = new Text();

        cardPiles = new GridPane();
        cardPiles.add(drawPile, 0, 0);
        cardPiles.add(hand, 0, 1);
        cardPiles.add(discardPile, 0, 2);
    }

    private void initializeEnemies() {
        enemies.setHgap(20);
        enemies.setVgap(20);
    }

    private void initializePlayer() {
        player = new FlowPane();
        player.getChildren().add(new Text(CombatManager.getInstance().getPlayer().toString()));
    }

    private void initializeEndTurn() {
        //create and add the End Turn Button
        Button endTurnBtn = new Button();
        endTurnBtn.setText("End Turn");
        endTurnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CombatManager.getInstance().endTurn();
                System.out.println("Turn ended...");
                System.out.println("The player has " + CombatManager.getInstance().getPlayer().getHP() + " HP left");
            }
        });
        endTurn.getChildren().add(endTurnBtn);
    }

    private void  initializePotSimulators() {
        //create and add the UHB Button
        Button healthPotBtn = new Button();
        healthPotBtn.setText("use health pot");
        healthPotBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CombatManager.getInstance().usePotion(new HealthPotion(), null);
            }
        });
        endTurn.getChildren().add(healthPotBtn); // add to endTurn is a quick fix.


        Button dmgPotBtn = new Button();
        dmgPotBtn.setText("use damage pot");
        dmgPotBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CombatManager.getInstance().usePotion(new DamagePotion(), CombatManager.getInstance().getEnemies().get(0));
            }
        });
        endTurn.getChildren().add(dmgPotBtn); // add to endTurn is a quick fix.

    }
}
