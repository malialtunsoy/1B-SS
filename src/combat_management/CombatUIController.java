import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CombatUIController {

    private CombatUIAdapter adapter;

    @FXML FlowPane potions;
    @FXML FlowPane enemies;
    @FXML FlowPane hand;
    @FXML Label drawPile;
    @FXML Label discardPile;
    @FXML Label playerHP;
    @FXML Label playerStatus;

    @FXML
    protected void sayHi(){
        adapter.endTurnPressed();
    }

    public void setUIAdapter( CombatUIAdapter adapter) {
        this.adapter = adapter;
    }

    public void updatePlayer(){
        playerHP.setText("HP: " + CombatManager.getInstance().getPlayer().getHP() + "/" + CombatManager.getInstance().getPlayer().getMaxHP() );
        String status = "";
        for(StatusEffect effect : CombatManager.getInstance().getPlayer().getStatusEffects())
            status = status + effect.toString() + "\n";//change this to effect.getName() later
        playerStatus.setText(status);
        //playerLabel.setText(CombatManager.getInstance().getPlayer().toString()
        //        + "\n Energy: " + CombatManager.getInstance().uiEnergyString());
    }

    public void updateEnemies() throws IOException {
        enemies.getChildren().clear();
        for (Enemy e : CombatManager.getInstance().getEnemies()) {

            FileInputStream file = new FileInputStream("src/res/EnemyView.fxml");
            FXMLLoader loader = new FXMLLoader();
            AnchorPane pane = loader.load(file);
            EnemyViewController controller = loader.getController();
            controller.setHp(e.getHP(), e.getMaxHP());
            ImageView img = new ImageView(e.getImage());
            img.setFitWidth(150);
            img.setFitHeight(150);
            controller.getButton().setGraphic(img);
            controller.getButton().setText("");
            controller.getButton().setOnAction(
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            CombatManager.getInstance().targetSelected(e);
                        }
                    }
            );
            controller.setStatus(e.getStatusEffects());
            controller.setName(e.getName());
            enemies.getChildren().add(pane);
        }
    }

    public void updatePotions(){
        this.potions.getChildren().clear();
        ArrayList<Potion> potions = CombatManager.getInstance().getPlayer().getPots();

        for( Potion pot : potions) {
            Button potBtn = new Button(pot.getName());
            potBtn.setOnAction(
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            CombatManager.getInstance().usePotion(pot, CombatManager.getInstance().getEnemies().get(0)); //for now, usepotion not implemented yet.
                        }
                    }
            );
            this.potions.getChildren().add(potBtn);
        }
    }

    public void updateCardPiles(){
        hand.getChildren().clear();
        ArrayList<Card> cards = CombatManager.getInstance().getHand();
        for (Card c : cards) {
            Button cardBtn = new Button("");
            ImageView img = new ImageView(c.getImage());
            img.setFitWidth( Math.min(147,((hand.getWidth() - 100)  / cards.size())));
            img.setFitHeight(200.0 / 147.0 * img.getFitWidth());
            cardBtn.setGraphic(img);
            cardBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    CombatManager.getInstance().cardSelected(c);
                }
            });

            hand.getChildren().add(cardBtn);
        }

        drawPile.setText("Draw Pile: " + CombatManager.getInstance().getDrawPileSize() + " cards");
        discardPile.setText("Discard Pile: " + CombatManager.getInstance().getDiscardPileSize() + " cards");
    }

    @FXML
    void backToMap() {
        CombatManager.getInstance().backToMap();
    }
}
