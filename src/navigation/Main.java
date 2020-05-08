import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {


    Game myGame;

    public static void main(String[] args) {

        NavigationUI myWindow = new NavigationUI();
        myWindow.launchApp(args);

    }
}
