import java.io.FileNotFoundException;
import java.io.IOException;


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
        myPlayer = new Player("null", "null", 0,0,0,0,0,0);
        try {
            loadedGameExist =  FileRead.convertToBool( FileRead.readFile("Data.txt", "loadedGame") )[0];

            if(loadedGameExist){
                try {
                    loadPlayer();
                }
                catch (IOException e){

                }
            }

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

    public void loadRun(){

    }


    public boolean getLoadedGameExist(){return loadedGameExist;}

    public void setLoadedGameExist(boolean bool){loadedGameExist = bool;}

    public void loadPlayer() throws IOException {
        playerName = FileRead.readFile("Data.txt", "PlayerName")[0];
        character = FileRead.readFile("Data.txt", "Character")[0];

        int curHP =  FileRead.convertToInt(FileRead.readFile("Data.txt", "playerHP"))[0];
        int maxHP = FileRead.convertToInt(FileRead.readFile("Data.txt", "playerMaxHP"))[0];
        int playerGold = FileRead.convertToInt(FileRead.readFile("Data.txt", "playerGold"))[0];
        int maxPot = FileRead.convertToInt(FileRead.readFile("Data.txt", "maxPot"))[0];
        int relicCount = FileRead.convertToInt(FileRead.readFile("Data.txt", "relicCount"))[0];
        int cardCount = FileRead.readFile("Data.txt", "CardsInDeck").length;

        myPlayer = new Player(playerName, character, curHP, maxHP, maxPot ,playerGold, relicCount,cardCount );

    }

}
