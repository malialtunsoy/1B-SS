import java.io.FileNotFoundException;



public class Game {


    private static Game ourInstance = new Game();

    public static Game getInstance() {
        return ourInstance;
    }

     String playerName;
     String character;
     Achievements achievements;
     GameOptions options;
     Player myPlayer;

    public Game(){
        System.out.println("New Game created");
        achievements = new Achievements();
        options = new GameOptions();
    }

     public  void setPlayerName(String playersName){
        playerName = playersName;
        SaveAndExit.save();
         System.out.println("saved player name: " + playerName);
    }

      public String getPlayerName(){
        return playerName;
    }

    public void setCharacter(String characterChoosen){
        character = characterChoosen;
        SaveAndExit.save( );
        System.out.println("saved character: " + character);
    }

    public String getCharacter(){
        return character;
    }

    public boolean startNewRun(String playerName, String character){

        myPlayer = new Player(playerName, character, 60, 60, 3, 330,3,10);
        return true;
    }


}
