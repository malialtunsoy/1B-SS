import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AchievementsController implements Initializable, ControlledScreen {

    ScreenController myController;

    public void setScreenParent(ScreenController screenParent){
        myController = screenParent;
    }

    @FXML
    private HBox achievementHBox;

    /*@FXML
    private ImageView singleAchievementImage;

    @FXML
    private Text singleAchievementName;

    @FXML
    private VBox singleAchievementVBox;*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //singleAchievementName.setText("aaa");
        int boxLength = Game.getInstance().achievements.achievementNames.length;
        boxLength *= 243;
        boxLength -= 10;
        achievementHBox.setPrefWidth(boxLength);

        for(int i = 0; i < Game.getInstance().achievements.achievementNames.length ; i++) {
            VBox temp = new VBox();
            temp.setAlignment(Pos.CENTER);
            temp.setPrefHeight(393.0);
            temp.setPrefWidth(233.0);
            temp.setSpacing(10.0);
            temp.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));

            ImageView tempImage = new ImageView();
            tempImage.setFitHeight(225);
            tempImage.setFitWidth(218);
            tempImage.setPickOnBounds(true);
            tempImage.setPreserveRatio(true);

            //File tempFile = new File("tempAch.jpg");
            Image tempImageIn = new Image("tempAch.jpg");
            tempImage.setImage(tempImageIn);

            Text tempText = new Text();
            tempText.setStrokeWidth(0);
            tempText.setText( Game.getInstance().achievements.achievementNames[i] );
            tempText.setTextAlignment(TextAlignment.CENTER);

            temp.getChildren().addAll(tempImage, tempText);
            if( !Game.getInstance().achievements.locked[i] ){
            temp.setOpacity(0.3);}
            //temp.getChildren().addAll(tempText);

            achievementHBox.getChildren().add(temp);
        }
    }

    @FXML
    void backToMainMenu(ActionEvent event) {
        myController.changeScreen(NavigationUI.mainMenuScreen);
    }

}
