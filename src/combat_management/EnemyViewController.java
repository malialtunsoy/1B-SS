import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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


    public void setImage(String image) {
        //this.image = new ImageView (image);
        this.image.setImage(new Image(image));
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
}

