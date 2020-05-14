import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CombatUIController {

    private CombatUIAdapter adapter;

    @FXML Label playerLabel;
    @FXML FlowPane enemies;
    @FXML FlowPane hand;
    @FXML Label drawPile;
    @FXML Label discardPile;
    
    @FXML
    protected void sayHi(){
        playerLabel.setText("yarrak");
        adapter.endTurnPressed();
    }

    public void setUIAdapter( CombatUIAdapter adapter) {
        this.adapter = adapter;
    }

    public void updatePlayer(){
        playerLabel.setText(CombatManager.getInstance().getPlayer().toString()
                + "\n Energy: " + CombatManager.getInstance().uiEnergyString());
    }

    public void updateEnemies() {
        enemies.getChildren().clear();
        for (Enemy e : CombatManager.getInstance().getEnemies()) {
            enemies.getChildren().add(new Text(e.toString()));
        }
    }

    public void updateCardPiles(){
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
}
