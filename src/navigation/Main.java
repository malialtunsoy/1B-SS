import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {



    public static void main(String[] args) {
        Game.getInstance();
        NavigationUI myWindow = new NavigationUI();
        myWindow.launchApp(args);

    }
}
