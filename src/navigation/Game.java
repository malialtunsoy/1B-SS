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
     boolean loadedGameExist;

    public Game(){
        System.out.println("New Game created");
        achievements = new Achievements();
        options = new GameOptions();

        try {
            loadedGameExist =  FileRead.convertToBool( FileRead.readFile("Data.txt", "loadedGame") )[0];
            System.out.println("Loaded game: " + getLoadedGameExist() );
        }catch(FileNotFoundException ex){System.out.println("failed");}
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
        setLoadedGameExist(true);
        System.out.println(getLoadedGameExist());
        SaveAndExit.save();
        return true;
    }

    public boolean getLoadedGameExist(){return loadedGameExist;}

    public void setLoadedGameExist(boolean bool){loadedGameExist = bool;}

}
