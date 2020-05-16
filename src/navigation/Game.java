import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Game {
    private static final String LOAD_FILENAME = "Data.txt";

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
        //myPlayer = new Player("null", "null", 0,0,0,0,0,0);
        try {
            loadedGameExist =  FileRead.convertToBool( FileRead.readFile(LOAD_FILENAME, "loadedGame") )[0];

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

        myPlayer = new Player(true, playerName, character, 60, 60, 3, 330,3,10);
        setLoadedGameExist(true);
        System.out.println(getLoadedGameExist());
        SaveAndExit.save();
        return true;
    }

    public void loadRun() {
        try {
            loadPlayer();
            if (loadFileHasCombat()) {
                loadCombat();
            }
        }
        catch (IOException e){
            System.err.println("ERROR: IOException in loadRun");
        }
    }
    public boolean loadFileHasCombat() throws IOException {
        Boolean [] ongoingCombatLine = FileRead.convertToBool(FileRead.readFile(LOAD_FILENAME, "CombatOngoing"));
        if ( ongoingCombatLine.length < 1) {
            System.err.println("ERROR: Load file has no argument in line CombatOngoing");
            return false;
        } else {
            return ongoingCombatLine[0];
        }
    }

    public boolean getLoadedGameExist(){return loadedGameExist;}

    public void setLoadedGameExist(boolean bool){loadedGameExist = bool;}

    public void loadPlayer() throws IOException {
        playerName = FileRead.readFile(LOAD_FILENAME, "PlayerName")[0];
        character = FileRead.readFile(LOAD_FILENAME, "Character")[0];

        int curHP =  FileRead.convertToInt(FileRead.readFile(LOAD_FILENAME, "playerHP"))[0];
        int maxHP = FileRead.convertToInt(FileRead.readFile(LOAD_FILENAME, "playerMaxHP"))[0];
        int playerGold = FileRead.convertToInt(FileRead.readFile(LOAD_FILENAME, "playerGold"))[0];
        int maxPot = FileRead.convertToInt(FileRead.readFile(LOAD_FILENAME, "maxPot"))[0];
        int relicCount = FileRead.convertToInt(FileRead.readFile(LOAD_FILENAME, "relicCount"))[0];
        int cardCount = FileRead.readFile(LOAD_FILENAME, "CardsInDeck").length;

        myPlayer = new Player(false, playerName, character, curHP, maxHP, maxPot ,playerGold, relicCount,cardCount );

    }

    public void loadCombat() throws IOException {
        CombatManager.getInstance().setPlayer(myPlayer);

        String [] enemiesRead = FileRead.readFile(LOAD_FILENAME, "Combat::Enemies");
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

        for ( String enemyStr : enemiesRead) {
            try {
                Enemy newEnemy = (Enemy) (Class.forName(enemyStr).getConstructor().newInstance());
                System.out.println( "ENEMY READ: " + newEnemy);
                enemies.add(newEnemy);
            } catch (ClassNotFoundException e) {
                System.err.println("Exception caused by invalid Enemy subclass in " + LOAD_FILENAME+ ": " + e.getMessage());
            } catch (NoSuchMethodException e) {
                System.err.println("Exception caused by Enemy subclass with no default constructor in " + LOAD_FILENAME+ ": " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Exception in loadCombat caused by call to newInstance()");
            }
        }

        CombatManager.loadState(enemies);
    }
}
