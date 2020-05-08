import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class NavigationUI  extends Application {

    Button newGameButton, loadGameButton, backButton, optionsButton, achievementsButton;
    Button backToMenuButton, resumeButton;

    Stage window;
    Scene mainScene, newGameScene, loadGameScene, optionScene, achievementsScene;
    Scene charNameScene, charSelectScene;

    public void NavigationUI(){ }

    public static void launchApp(String[] args){

        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {


        window = primaryStage;

        //Main Menu
        Label GameLabel = new Label("Slay The Spire");

        newGameButton = new Button();
        newGameButton.setText("New Game");
        newGameButton.setOnAction(e -> window.setScene(charNameScene));

        loadGameButton = new Button();
        loadGameButton.setText("Load Game");
        loadGameButton.setOnAction(e -> window.setScene(loadGameScene));

        optionsButton = new Button();
        optionsButton.setText("Options");
        optionsButton.setOnAction(e -> window.setScene(optionScene));

        achievementsButton = new Button();
        achievementsButton.setText("Achievements");
        achievementsButton.setOnAction(e -> window.setScene(achievementsScene));

        VBox MainLayout = new VBox();
        MainLayout.getChildren().addAll(GameLabel, newGameButton, loadGameButton, optionsButton, achievementsButton);
        mainScene = new Scene(MainLayout, 600,300);

        //New Game

        //char Name
        Label charNameLabel = new Label("What is your name warrior?");
        Label charSelectLabel = new Label("Choose your Character " + Game.getPlayerName() );

        final TextField charName = new TextField ();
        charName.setPromptText("your name");

        backToMenuButton = new Button();
        backToMenuButton.setText("Back");
        backToMenuButton.setOnAction(e -> {window.setScene(mainScene);
            charName.setPromptText("your name");
            charNameLabel.setText("What is your name warrior?");
            charNameLabel.setTextFill(Color.web("black"));
        });

        resumeButton = new Button();
        resumeButton.setText("Resume");
        resumeButton.setOnAction(e -> {
            String name = charName.getText();
            if(name.equals("")) {
                charNameLabel.setText("You must enter a name");
                charNameLabel.setTextFill(Color.web("red"));
            }

            else {
                Game.setPlayerName(name);
                charSelectLabel.setText("Choose your Character " + Game.getPlayerName());
                window.setScene(charSelectScene);
                charName.clear();
            }
        });

        VBox charNameLayout = new VBox();
        charNameLayout.getChildren().add(charNameLabel);
        charNameLayout.getChildren().add(charName);
        charNameLayout.getChildren().add(backToMenuButton);
        charNameLayout.getChildren().add(resumeButton);
        charNameScene = new Scene(charNameLayout, 600,300);

        //char select


        Button backToNameButton = new Button();
        backToNameButton.setText("Back");
        backToNameButton.setOnAction(e -> {window.setScene(charNameScene);
            charNameLabel.setText("What is your name warrior?");
            charNameLabel.setTextFill(Color.web("black"));});

        Button fightButton = new Button();
        fightButton.setText("Fight");
        fightButton.setOnAction(e -> System.out.println("FIGHT"));

        VBox charSelectLayout = new VBox();
        charSelectLayout.getChildren().add(charSelectLabel);
        charSelectLayout.getChildren().add(backToNameButton);
        charSelectLayout.getChildren().add(fightButton);
        charSelectScene = new Scene(charSelectLayout, 600,300);

        //Load Game
        Label loadGameLabel = new Label("Load Game");

        backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> window.setScene(mainScene));

        VBox loadGameLayout = new VBox();
        loadGameLayout.getChildren().addAll(loadGameLabel, backButton );
        loadGameScene = new Scene(loadGameLayout, 600,300);

        //Options
        Label optionsLabel = new Label("Options");

        backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> window.setScene(mainScene));

        VBox optionsLayout = new VBox();
        optionsLayout.getChildren().addAll(optionsLabel, backButton );
        optionScene = new Scene(optionsLayout, 600,300);

        //Achievements
        Label achievementsLabel = new Label("Achievements");

        backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> window.setScene(mainScene));

        VBox achievementsLayout = new VBox();
        //achievementsLayout.setMargin(achievementsLabel, new Insets(20, 20, 20, 20));
        achievementsLayout.getChildren().addAll(achievementsLabel, backButton );
        achievementsScene = new Scene(achievementsLayout, 600,300);

        //stage
        window.setScene(mainScene);
        window.setTitle("Slay the Spire");
        window.show();


    }


}
