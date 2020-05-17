import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


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
        Card sample4 = new Bash();
        Card sample5 = new Anger();
        Card sample6 = new BodySlam();
        Card sample7 = new Clash();
        Card sample8 = new Cleave();
        Card sample9 = new Clothesline();
        //for plus card
        Card sample10 = new StrikePlus();
        Card sample11 = new DefendPlus();
        Card sample12 = new StrengthenPlus();
        Card sample13 = new BashPlus();
        Card sample14 = new AngerPlus();
        Card sample15 = new BodySlamPlus();
        Card sample16 = new ClashPlus();
        Card sample17 = new CleavePlus();
        Card sample18 = new ClotheslinePlus();

        player.addRelic(new RingOfTheSnake());
        player.addRelic(new BurningBlood());

       /* player.addToDeck(sample);
        player.addToDeck(sample);
        player.addToDeck(sample3);
        player.addToDeck(sample4);
        player.addToDeck(sample2);
        player.addToDeck(sample3);
        player.addToDeck(sample2);
        player.addToDeck(sample4);
        player.addToDeck(sample2);
        player.addToDeck(sample4);
        player.addToDeck(sample3);
        player.addToDeck(sample2);
        player.addToDeck(sample2);
        player.addToDeck(sample);
        player.addToDeck(sample);*/
        player.addToDeck(sample);
        player.addToDeck(sample3);
        player.addToDeck(sample);
        player.addToDeck(sample3);
        player.addToDeck(sample4);
        player.addToDeck(sample2);
        player.addToDeck(sample5);
        player.addToDeck(sample6);
        player.addToDeck(sample7);
        player.addToDeck(sample8);
        player.addToDeck(sample10);
        /*
        player.addToDeck(sample10);
        player.addToDeck(sample11);
        player.addToDeck(sample12);
        player.addToDeck(sample13);
        player.addToDeck(sample14);
        player.addToDeck(sample15);
        player.addToDeck(sample16);
        player.addToDeck(sample17);
        player.addToDeck(sample18);*/

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