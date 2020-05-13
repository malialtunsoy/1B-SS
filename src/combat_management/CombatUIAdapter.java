import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class CombatUIAdapter {
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

    public CombatUIAdapter(Stage primaryStage) throws IOException{
        // set the primaryStage attribute to be able to invoke Stage.show() again
        this.primaryStage = primaryStage;

        //
        FileInputStream file = new FileInputStream("src/combat_management/CombatUI.fxml");
        FXMLLoader loader = new FXMLLoader();
        Scene combatScene = loader.load(file);
        ((CombatUIController)loader.getController()).setUIAdapter(this);
        primaryStage.setScene(combatScene);
        primaryStage.show();
    }

    public void endTurnPressed() {
        CombatManager.getInstance().endTurn();
        System.out.println("Turn ended...");
        System.out.println("The player has " + CombatManager.getInstance().getPlayer().getHP() + " HP left");
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


}
