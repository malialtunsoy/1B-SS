import java.io.FileNotFoundException;


public class Game {

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
