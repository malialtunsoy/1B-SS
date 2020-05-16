import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EnemyViewController {

    @FXML
    private ImageView image;

    @FXML
    private Label hp;

    @FXML
    private Label status;

    @FXML
    private Label name;

    @FXML
    private Label intent;

    public ImageView getImage() {
        return image;
    }

    public void setHp(int hp, int maxHp) {
        this.hp.setText("HP: " + hp + "/" + maxHp);
    }

    public void setStatus(ArrayList<StatusEffect> status) {
        String cur = "";
        for(StatusEffect effect : status)
            cur = cur + effect.toString() + "\n";//change this to effect.getName() later
        this.status.setText(cur);
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

