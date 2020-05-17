import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
                //loadCombat();
            }
        }
        catch (IOException e){
            System.err.println("ERROR: IOException in loadRun");
        }
    }
    public boolean loadFileHasCombat() throws IOException {
        Boolean [] ongoingCombatLine = FileRead.convertToBool(FileRead.readFile(LOAD_FILENAME, "Combat::Ongoing"));
        if ( ongoingCombatLine.length < 1) {
            System.err.println("ERROR: Load file has no argument in line Combat::Ongoing");
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
        ArrayList<Enemy> enemies = getSavedEnemies();

        ArrayList<Card> hand = FileRead.convertToCards(FileRead.readFile(LOAD_FILENAME, "Combat::Hand"));
        ArrayList<Card> drawPile = FileRead.convertToCards(FileRead.readFile(LOAD_FILENAME, "Combat::DrawPile"));
        ArrayList<Card> discardPile = FileRead.convertToCards(FileRead.readFile(LOAD_FILENAME, "Combat::DiscardPile"));

        int [] nums = FileRead.convertToInt(FileRead.readFile(LOAD_FILENAME, "Combat::Energy/MaxEnergy/Turn"));
        if ( nums.length != 3) {
            System.err.println("ERROR: Load file has incorrect number of arguments in line Combat::Energy/MaxEnergy/Turn");
            return;
        }

        loadPlayerStatusEffects();
        CombatManager.loadState(enemies, hand, drawPile, discardPile, nums[0], nums[1], nums[2]);
    }

    private ArrayList<Enemy> getSavedEnemies() throws IOException{
        String [] enemiesRead = FileRead.readFile(LOAD_FILENAME, "Combat::Enemies");
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

        for ( String enemyStr : enemiesRead) {
            try {
                Enemy newEnemy = (Enemy) (Class.forName(enemyStr).getConstructor().newInstance());
                enemies.add(newEnemy);
            } catch (ClassNotFoundException e) {
                System.err.println("Exception caused by invalid Enemy subclass in " + LOAD_FILENAME+ ": " + e.getMessage());
            } catch (NoSuchMethodException e) {
                System.err.println("Exception caused by Enemy subclass with no default constructor in " + LOAD_FILENAME+ ": " + e.getMessage());
            } catch (ExceptionInInitializerError  e) {
                System.err.println("ExceptionInInitializerError in loadCombat caused by call to newInstance(): " + e.getMessage());
            } catch (IllegalAccessException  e) {
                System.err.println("IllegalAccessException in loadCombat caused by call to newInstance(): " + e.getMessage());
            } catch (InvocationTargetException e) {
                System.err.println("InvocationTargetException in loadCombat caused by call to newInstance(): " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println("IllegalArgumentException in loadCombat caused by call to newInstance(): " + e.getMessage());
            } catch (InstantiationException e) {
                System.err.println("InstantiationException in loadCombat caused by call to newInstance(): " + e.getMessage());
            }
        }

        for ( int i = 0; i < enemies.size(); i++) {
            // restore enemy state
            int [] healthParams = FileRead.convertToInt(FileRead.readFile(LOAD_FILENAME, "Combat::Enemy@" + i + "::HP/MAXHP" ));
            if (healthParams.length != 2) {
                System.err.println("Enemy " + enemies.get(i) + " at index " + i + " in " + LOAD_FILENAME + " does not have 2 lines at row HP/MAXHP");
            }
            enemies.get(i).setCurrentHP(healthParams[0]);
            enemies.get(i).setMaxHP(healthParams[1]);

            String [] effectNamesStr = FileRead.readFile(LOAD_FILENAME, "Combat::Enemy@" + i + "::StatusEffects::Names" );
            Boolean [] appliedByAnEnemy = FileRead.convertToBool(FileRead.readFile(LOAD_FILENAME, "Combat::Enemy@" + i + "::StatusEffects::AppliedByAnEnemy"));
            int [] counters = FileRead.convertToInt(FileRead.readFile(LOAD_FILENAME, "Combat::Enemy@" + i + "::StatusEffects::Counters"));

            if (effectNamesStr.length != appliedByAnEnemy.length || effectNamesStr.length != counters.length) {
                System.err.println("Enemy " + enemies.get(i) + " at index " + i + " in " + LOAD_FILENAME + " has inconsistent number of entries in lines related to status effects");
            }

            // add status effects
            try {
                for (int j = 0; j < effectNamesStr.length; j++) {
                    StatusEffect newEffect = (StatusEffect) (Class.forName(effectNamesStr[j]).getConstructor(int.class).newInstance(counters[j]));
                    newEffect.setAppliedByAnEnemy(appliedByAnEnemy[j]);
                    enemies.get(i).addStatusEffect(newEffect);
                }
            } catch (ClassNotFoundException e) {
                System.err.println("Exception caused by invalid StausEffect subclass in " + LOAD_FILENAME+ ": " + e.getMessage());
            } catch (NoSuchMethodException e) {
                System.err.println("Exception caused by StatusEffect subclass with no (int) constructor in " + LOAD_FILENAME+ ": " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Exception in loadCombat ABCD caused by call to newInstance()");
            }

            String [] extraParams = FileRead.readFile(LOAD_FILENAME, "Combat::Enemy@" + i + "::ExtraParams");

            enemies.get(i).restoreExtraState(extraParams);
            System.out.println( "ENEMY READ: " + enemies.get(i));
        }

        return enemies;
    }

    private void loadPlayerStatusEffects() throws IOException {
        String [] effectNamesStr = FileRead.readFile(LOAD_FILENAME, "Combat::Player::StatusEffects::Names" );
        Boolean [] appliedByAnEnemy = FileRead.convertToBool(FileRead.readFile(LOAD_FILENAME, "Combat::Player::StatusEffects::AppliedByAnEnemy"));
        int [] counters = FileRead.convertToInt(FileRead.readFile(LOAD_FILENAME, "Combat::Player::StatusEffects::Counters"));

        if (effectNamesStr.length != appliedByAnEnemy.length || effectNamesStr.length != counters.length) {
            System.err.println("Combat::Player in " + LOAD_FILENAME + " has inconsistent number of entries in lines related to status effects");
        }

        // add status effects
        try {
            for (int j = 0; j < effectNamesStr.length; j++) {
                StatusEffect newEffect = (StatusEffect) (Class.forName(effectNamesStr[j]).getConstructor(int.class).newInstance(counters[j]));
                newEffect.setAppliedByAnEnemy(appliedByAnEnemy[j]);
                myPlayer.addStatusEffect(newEffect);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Exception caused by invalid StausEffect subclass in " + LOAD_FILENAME+ ": " + e.getMessage());
        } catch (NoSuchMethodException e) {
            System.err.println("Exception caused by StatusEffect subclass with no (int) constructor in " + LOAD_FILENAME+ ": " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception in loadCombat ABCD caused by call to newInstance()");
        }
    }


}
