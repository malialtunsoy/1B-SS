import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class EnemyViewController {

    @FXML
    private ImageView image;

    @FXML
    private Label hp;

    @FXML
    private Label name;

    @FXML
    private ImageView intent;

    @FXML
    private Button enemieButton;

    @FXML
    private Label intentAmount;

    @FXML
    private FlowPane statusEffects;

    public ImageView getImage() {
        return image;
    }

    public Button getButton() {
        return enemieButton;
    }

    public void setHp(int hp, int maxHp) {
        this.hp.setText("HP: " + hp + "/" + maxHp);
    }

    public void setStatus(ArrayList<StatusEffect> status) {
        try {
            for (StatusEffect effect : status){
                FileInputStream file = new FileInputStream("src/res/StatusEffectView.fxml");
                FXMLLoader loader = new FXMLLoader();
                AnchorPane pane = loader.load(file);
                StatusEffectViewController controller = loader.getController();
                controller.setImage(effect.getImage());
                controller.setCounter(effect.getCounter());
                Tooltip.install(pane, new Tooltip(effect.getDescription()));
                statusEffects.getChildren().add(pane);
            }
        } catch (IOException e) {System.out.println(e.getMessage());}

    }

    public void setName( String name) {
        this.name.setText(name);
    }

    public void setIntent(ArrayList<Intent> intents) {
        String intentDesc = "";
        for( Intent i : intents) {
            intentDesc +=  i.toString();
        }
        Tooltip.install(intent, new Tooltip(intentDesc));
        intentAmount.setText("");
        if(intents.size() == 1) {
            Intent i = intents.get(0);
            if (i instanceof AggressiveIntent) {
                intentAmount.setText(((AggressiveIntent) i).getDamage() + "");
                int dmg = ((AggressiveIntent) i).getDamage();
                if(dmg < 10)
                    intent.setImage(new Image("Aggressive1.png"));
                else if(dmg < 20)
                    intent.setImage(new Image("Aggressive3.png"));
                else if(dmg < 30)
                    intent.setImage(new Image("Aggressive5.png"));
                else if(dmg < 40)
                    intent.setImage(new Image("Aggressive6.png"));
                else
                    intent.setImage(new Image("Aggressive2.png"));
            }
            else if (i instanceof DefensiveIntent)
                intent.setImage(new Image("Defensive.png"));
            else if (i instanceof StrategicIntent)
                intent.setImage(new Image("Strategic.png"));
            else if( i instanceof  BuffIntent)
                intent.setImage(new Image("Buff.png"));
            else if( i instanceof HiddenIntent)
                intent.setImage(new Image("Strategic.png"));
        }
        else {
            Intent i1 = intents.get(0);
            Intent i2 = intents.get(1);
            if( i1  instanceof AggressiveIntent) {
                if(i2 instanceof DefensiveIntent)
                    intent.setImage(new Image("Aggressive_defensive.png"));
                if(i2 instanceof BuffIntent)
                    intent.setImage(new Image("AggressiveBuff.png"));
                if(i2 instanceof StrategicIntent || i2 instanceof HiddenIntent)
                    intent.setImage(new Image("Aggressive_strategic.png"));
            }
            else if( i1 instanceof DefensiveIntent) {
                if(i2 instanceof AggressiveIntent)
                    intent.setImage(new Image("Aggressive_defensive.png"));
                if(i2 instanceof StrategicIntent || i2 instanceof HiddenIntent)
                    intent.setImage(new Image("Aggressive_defensive.png"));
                if(i2 instanceof BuffIntent)
                    intent.setImage(new Image("DefendBuff.png"));
            }
            else if( i1 instanceof BuffIntent) {
                if(i2 instanceof AggressiveIntent)
                    intent.setImage(new Image("AggressiveBuff.png"));
                if(i2 instanceof DefensiveIntent)
                    intent.setImage(new Image("DefendBuff.png"));
                if(i2 instanceof StrategicIntent || i2 instanceof HiddenIntent)
                    intent.setImage(new Image("DefendBuff.png"));
            }
            else if( i1 instanceof StrategicIntent || i1 instanceof HiddenIntent) {
                if(i2 instanceof DefensiveIntent)
                    intent.setImage(new Image("DefendBuff.png"));
                if(i2 instanceof AggressiveIntent)
                    intent.setImage(new Image("Aggressive_strategic.png"));
                if(i2 instanceof BuffIntent)
                    intent.setImage(new Image("Buff.png"));
            }
        }
    }

    @FXML
    void selectEnemie(ActionEvent event) {

    }
}

