import java.io.FileNotFoundException;

public class Game {

    static String playerName;
    static String character;
    static Achievements achievements;
    static GameOptions options;

    public Game(){
        achievements = new Achievements();
        options = new GameOptions();

        playerName = "NULL";
    }

    public Game(String[] args){

        achievements = new Achievements();
        options = new GameOptions();

        playerName = "NULL";
        character = "NULL";

        NavigationUI myWindow = new NavigationUI();
        startGame(myWindow, args);

        SaveAndExit.save();
    }

    public static boolean startGame(NavigationUI game, String[] args){

        game.launchApp(args);

        return true;
    }

    static public  void setPlayerName(String playersName){
        playerName = playersName;
        SaveAndExit.save();
    }

     static public String getPlayerName(){
         String[] playerNameTemp = {};

         try {
             playerNameTemp =  FileRead.readFile("Data.txt", "PlayerName");
         }catch(FileNotFoundException ex){System.out.println("failed");}

        return playerNameTemp[0];
    }


}
