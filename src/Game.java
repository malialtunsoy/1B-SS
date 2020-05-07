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

    public Game(){

        achievements = new Achievements();
        options = new GameOptions();
    }

     public  void setPlayerName(String playersName){
        playerName = playersName;
        SaveAndExit.save( achievements,  options,  playersName,  character);
         System.out.println("saved" + playerName);
    }

      public String getPlayerName(){
        return playerName;
    }


}
