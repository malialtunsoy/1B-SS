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
    private Label intent;

    @FXML
    private FlowPane statusEffects;

    public ImageView getImage() {
        return image;
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
        String cur = "";
        for( Intent i : intents) {
            cur += i.toString() + "\n";
        }
        intent.setText(cur);
    }
}

