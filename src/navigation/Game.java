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
        System.out.println("New Game created");
        achievements = new Achievements();
        options = new GameOptions();
    }

     public  void setPlayerName(String playersName){
        playerName = playersName;
        SaveAndExit.save( achievements,  options,  playersName,  character);
         System.out.println("saved player name: " + playerName);
    }

      public String getPlayerName(){
        return playerName;
    }

    public void setCharacter(String character){
        this.character = character;
        SaveAndExit.save( achievements,  options,  playerName,  character);
        System.out.println("saved character: " + character);
    }

    public String getCharacter(){
        return character;
    }


}
