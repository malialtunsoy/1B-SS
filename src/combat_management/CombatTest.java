import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class CombatTest {

    CombatManager manager;


    public void testCombat(Stage primaryStage) {
        System.out.println(Font.getFontNames());
        System.out.println(System.getProperty("user.dir"));
        manager = CombatManager.getInstance();
        initializeGUI(primaryStage);
        manager.setStage(primaryStage);


        Player player = new Player("playerName","Ironclad",50, 50, 0, 0, 0, 0);
        manager.setPlayer(player);

        // NOTE: it might be a better idea to create a new object for each card to be added.
        // playCard removes the card from the hand using its reference directly (not its index in hand).
        // Seems not to cause an issue now but better to be aware of it.
        
        Card sample = new Strike();
        Card sample2 = new Defend();
        Card sample3 = new TEST_PURPOSE_CARD_Strengthen();
        player.addToDeck(sample);
        player.addToDeck(sample);
        player.addToDeck(sample3);
        player.addToDeck(sample3);
        player.addToDeck(sample2);
        player.addToDeck(sample3);
        player.addToDeck(sample2);
        player.addToDeck(sample2);
        player.addToDeck(sample2);
        player.addToDeck(sample2);
        player.addToDeck(sample3);
        player.addToDeck(sample2);
        player.addToDeck(sample2);
        player.addToDeck(sample);
        player.addToDeck(sample3);
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
                Enemy armoredEnemy = new Alternatron();
                armoredEnemy.addStatusEffect(new Block(5));
                CombatManager.getInstance().addEnemy(armoredEnemy);
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