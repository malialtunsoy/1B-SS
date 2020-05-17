import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class RewardPaneController {

    @FXML
    private ImageView image;

    @FXML
    private Label text;

    private Relic rewardRelic;
    private Potion rewardPotion;
    private int rewardGold;

    private CombatUIController parentController;

    @FXML
    void claimReward(MouseEvent event) {
        if(rewardRelic != null) {
            CombatManager.getInstance().getPlayer().addRelic(rewardRelic);
        }
        else if(rewardPotion != null) {
            CombatManager.getInstance().getPlayer().addPot(rewardPotion);
        }
        else if (rewardGold != 0) {
            CombatManager.getInstance().getPlayer().addGold(rewardGold);
        }
        ((FlowPane)(text.getParent().getParent())).getChildren().remove(text.getParent());
        parentController.reloadRelics();
        parentController.reloadPotions();
        parentController.disablePotionButtons();
        parentController.checkRewardsDone();
    }

    public void setRewardRelic(Relic rewardRelic) {
        rewardGold = 0;
        this.rewardRelic = rewardRelic;
        image.setImage(new Image( rewardRelic.getImage()));
        text.setText(rewardRelic.getName());
    }

    public void setRewardPotion (Potion rewardPotion) {
        rewardGold = 0;
        this.rewardPotion = rewardPotion;
        image.setImage(new Image( rewardPotion.getImage()));
        text.setText(rewardPotion.getName());
    }

    public void setRewardGold (int rewardGold) {
        this.rewardGold = rewardGold;
        image.setImage(new Image ("coin.png"));
        text.setText(rewardGold + " gold");
    }

    public void setParentController( CombatUIController parentController) {
        this.parentController = parentController;
    }
}

