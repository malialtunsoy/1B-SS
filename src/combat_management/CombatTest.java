import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class CombatTest extends Application {

    CombatManager manager;

    @Override
    public void start(Stage primaryStage) {
        manager = CombatManager.getInstance();
        initializeGUI(primaryStage);
        manager.setStage(primaryStage);


        Player player = new Player(50, 50, 0, 0, 0, 0);
        manager.setPlayer(player);
        Card sample = new Card("abc", "abc",0,0);
        player.addToDeck(sample);
        player.addToDeck(sample);
        player.addToDeck(sample);
        player.addToDeck(sample);
        player.addToDeck(sample);
        player.addToDeck(sample);
    }


    /*  Commented out because of the change in the testing scheme
    public void testCombat() {
        System.out.println("Testing combat");
        manager = CombatManager.getInstance();

        // initializeGUI(manager.getStage());

        Player player = new Player();
        manager.setPlayer(player);
        manager.createUIAdapter();      // there has to be a better way to handle that

        Card sample = new Card();
        player.addToDeck(sample);
        player.addToDeck(sample);
        player.addToDeck(sample);
        player.addToDeck(sample);
        player.addToDeck(sample);
        player.addToDeck(sample);
    }
    */

    private void initializeGUI(Stage primaryStage){

        //initialize the scene
        FlowPane root = new FlowPane();
        primaryStage.setScene(new Scene(root, 300, 250));

        //create and add the Add Enemy button
        Button addEnemyBtn = new Button();
        addEnemyBtn.setText("Add Enemy");
        addEnemyBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CombatManager.getInstance().addEnemy(new Enemy("Can", 69));
                System.out.println("Added Enemy");
            }
        });
        root.getChildren().add(addEnemyBtn);

        //create and add the Start Combat Button
        Button startCombatBtn = new Button();
        startCombatBtn.setText("Start Combat");
        startCombatBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CombatManager.getInstance().playCombat();
                System.out.println("Invoked playCombat()");
            }
        });
        root.getChildren().add(startCombatBtn);

        //primaryStage.setScene();
        primaryStage.show();
    }
}