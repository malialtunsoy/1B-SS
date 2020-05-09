import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CombatUIAdapter {
    // --- constants ---
    static final int SCENE_WIDTH = 300;
    static final int SCENE_HEIGHT = 300;

    static final int ENEMIES_HGAP = 300;
    static final int ENEMIES_VGAP = 300;

    // --- attributes ---
    Stage primaryStage;
    FlowPane root;
    FlowPane enemies;

    // --- methods ---
    public void updateView() {
        updateEnemies();
    }

    public void updateEnemies() {
        // for now, remove all displayed and re-add all enemies in combat
        enemies.getChildren().clear();
        for (Enemy e : CombatManager.getInstance().getEnemies()) {
            enemies.getChildren().add(new Text(e.toString()));
        }
    }

    public CombatUIAdapter(Stage primaryStage){
        // set the primaryStage attribute to be able to invoke Stage.show() again
        this.primaryStage = primaryStage;

        // initialize the scene
        root = new FlowPane();
        primaryStage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));

        enemies = new FlowPane();
        enemies.setHgap(20);
        enemies.setVgap(20);
        root.getChildren().add(enemies);


        //create and add the Add Enemy button
        Button addEnemyBtn = new Button();
        addEnemyBtn.setText("Add Enemy");
        addEnemyBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Enemy e = new Enemy("Can", 69);
                CombatManager.getInstance().addEnemy(e);
                enemies.getChildren().add(new Text(e.toString()));
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

        //create and add the End Turn Button
        Button endTurnBtn = new Button();
        endTurnBtn.setText("End Turn");
        endTurnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CombatManager.getInstance().endTurn();
                System.out.println("Turn ended...");
                System.out.println("The player has " + CombatManager.getInstance().getPlayer().getHP() + " HP left");
            }
        });
        root.getChildren().add(endTurnBtn);
        updateView();
        primaryStage.show();
    }
}
