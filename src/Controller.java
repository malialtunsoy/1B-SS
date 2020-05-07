import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller{

    @FXML
     void newGame(ActionEvent event) throws IOException {
        Parent newGameParent = FXMLLoader.load(getClass().getResource("newGame.fxml"));
        Scene newGameScenee = new Scene(newGameParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(newGameScenee);
        window.show();
    }

    @FXML
    void loadGame(ActionEvent event) {
        System.out.println("Loading Game");
    }

    @FXML
    void drawOptions(ActionEvent event)throws IOException  {
        Parent tempParent = FXMLLoader.load(getClass().getResource("options.fxml"));
        Scene tempScene = new Scene(tempParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tempScene);
        window.show();
    }

    @FXML
    void drawAchievements(ActionEvent event)throws IOException {
        Parent tempParent = FXMLLoader.load(getClass().getResource("achievements.fxml"));
        Scene tempScene = new Scene(tempParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tempScene);
        window.show();
    }


    public void Fight(ActionEvent event) throws IOException {
        //Parent tempParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        //Scene tempScene = new Scene(tempParent);

        //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        //window.setScene(tempScene);
        //window.show();

        System.out.println("FIGHT");
    }

    @FXML
    private TextField playerNameText;

    @FXML
    void charSelect(ActionEvent event) throws IOException  {
        String playerName = playerNameText.getText();

        if(playerName.equals("")){
            playerNameText.setPromptText("you must enter a name");
        }
        else {
            System.out.println("player name: " + playerName);
            Parent tempParent = FXMLLoader.load(getClass().getResource("charSelect.fxml"));
            Scene tempScene = new Scene(tempParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tempScene);
            window.show();
        }
    }

    @FXML
    void backToMainMenu(ActionEvent event) throws IOException {
        Parent tempParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene tempScene = new Scene(tempParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tempScene);
        window.show();
    }


    @FXML
    void char1selected() {
        System.out.println("character 1 selected");
    }

    @FXML
    void char2selected() {
        System.out.println("character 2 selected");
    }

    @FXML
    void char3selected() {
        System.out.println("character 3 selected");
    }

    @FXML
    void char4selected() {
        System.out.println("character 4 selected");
    }



}
