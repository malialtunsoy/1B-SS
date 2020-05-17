import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StatusEffectViewController {

    @FXML
    private ImageView image;

    @FXML
    private Label counter;

    public void setImage(String image) {
        this.image.setImage(new Image(image));
    }

    public void setCounter(int counter){
        this.counter.setText(counter + "");
    }
}
