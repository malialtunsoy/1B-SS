import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;


public class CombatTest {

    CombatManager manager;


    public void testCombat(Stage primaryStage) {
        manager = CombatManager.getInstance();
        initializeGUI(primaryStage);

        Player player = new Player(true,"playerName","Ironclad",50, 50, 3, 0, 0, 0);
        manager.setPlayer(player);

        // NOTE: it might be a better idea to create a new object for each card to be added.
        // playCard removes the card from the hand using its reference directly (not its index in hand).
        // Seems not to cause an issue now but better to be aware of it.
        
        Card sample = new Strike();
        Card sample2 = new Defend();
        Card sample3 = new Strengthen();
        Card sample5 = new Pummel();
        Card sample4 = new Bash();
        player.addRelic(new RingOfTheSnake());
        player.addRelic(new BurningBlood());

        player.addToDeck(sample5);
        player.addToDeck(sample5);
        player.addToDeck(sample5);
        player.addToDeck(sample5);
        player.addToDeck(sample2);
        player.addToDeck(sample4);
        player.addToDeck(sample4);
        player.addToDeck(sample4);
        player.addToDeck(sample5);
        player.addToDeck(sample2);
        player.addToDeck(sample2);
        player.addToDeck(sample2);
        player.addToDeck(sample);
        player.addToDeck(sample);
        player.addToDeck(sample);
        player.addToDeck(sample);


        player.addRewardPotion(new HealthPotion());
        player.addRewardRelic(new RingOfTheSnake());
        player.addRewardRelic(new BurningBlood());
        player.addRewardCard(new Strengthen());
        player.addRewardCard(new Bash());
        player.addRewardCard(new Pummel());
        player.setRewardGold(220);

        player.addPot(new HealthPotion());
        player.addPot(new DamagePotion());
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
                Enemy e = new JawWorm();
                e.setCurrentHP(1);
                CombatManager.getInstance().addEnemy(e);
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