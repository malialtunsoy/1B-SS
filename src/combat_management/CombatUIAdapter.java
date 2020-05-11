import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
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
    GridPane root;
    FlowPane enemies;
    FlowPane player;
    FlowPane endTurn;
    // --- methods ---
    public void updateView() {
        updateEnemies();
        updatePlayer();
    }

    private void updateEnemies() {
        // for now, remove all displayed and re-add all enemies in combat
        enemies.getChildren().clear();
        for (Enemy e : CombatManager.getInstance().getEnemies()) {
            enemies.getChildren().add(new Text(e.toString()));
        }
    }

    private void updatePlayer() {
        ((Text)(player.getChildren().get(0))).setText(CombatManager.getInstance().getPlayer().toString());
    }

    public CombatUIAdapter(Stage primaryStage){
        // set the primaryStage attribute to be able to invoke Stage.show() again
        this.primaryStage = primaryStage;
        // initialize the scene
        root = new GridPane();
        root.setGridLinesVisible(true); // for debugging purposes
        primaryStage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));

        enemies = new FlowPane();
        root.add(enemies, 0, 0);
        initializeEnemies();

        initializePlayer();
        root.add(player, 1,0);

        endTurn = new FlowPane();
        root.add(endTurn,2,0);
        initializeEndTurn();

        initializeCardPiles();

        updateView();
        primaryStage.show();
    }

    public void initializeCardPiles() {

    }

    public void initializeEnemies() {
        enemies.setHgap(20);
        enemies.setVgap(20);
    }

    public void initializePlayer() {
        player = new FlowPane();
        player.getChildren().add(new Text(CombatManager.getInstance().getPlayer().toString()));
    }

    public void initializeEndTurn() {
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
        endTurn.getChildren().add(endTurnBtn);
    }
}
