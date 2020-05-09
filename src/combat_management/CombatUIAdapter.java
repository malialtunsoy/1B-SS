import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    FlowPane endTurn;
    GridPane cardPiles;
    // --- methods ---
    public void updateView() {
        updateEnemies();
        updateCardPiles();
    }

    public void updateEnemies() {
        // for now, remove all displayed and re-add all enemies in combat
        enemies.getChildren().clear();
        for (Enemy e : CombatManager.getInstance().getEnemies()) {
            enemies.getChildren().add(new Text(e.toString()));
        }
    }

    public void updateCardPiles() {
        
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
        initalizeEnemies();

        endTurn = new FlowPane();
        root.add(endTurn,1,0);
        initalizeEndTurn();

        cardPiles = new GridPane();
        root.add(cardPiles,0,1);
        GridPane.setColumnSpan(cardPiles,2);
        //root.setConstraints(cardPiles ,1,0,2,1);
        initializeCardPiles();

        updateView();
        primaryStage.show();
    }

    public void initializeCardPiles() {
        Text drawPileText = new Text("drawPile Text");
        Text handText = new Text("hand Text");
        Text discardPileText = new Text("discardPile Text");

        cardPiles.add(drawPileText, 0,0);
        cardPiles.add(handText, 1,0);
        cardPiles.add(discardPileText,2,0);
    }

    public void initalizeEnemies() {
        enemies.setHgap(20);
        enemies.setVgap(20);
    }

    public void initalizeEndTurn() {
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
