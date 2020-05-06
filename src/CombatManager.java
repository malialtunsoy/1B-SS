import java.util.ArrayList;

public class CombatManager {

    //constructors
    private CombatManager() {
        turn = 1;
        energy = 3;
        maxEnergy = 3;
        enemies = new ArrayList<Enemy>();
    }

    //attributes
    private static CombatManager instance = new CombatManager();

    private int turn;
    private int energy;
    private int maxEnergy;

    private ArrayList<Enemy> enemies;
    private Player player;

    //methods
    public static CombatManager getInstance() {
        return instance;
    }

    //plays the combat, acts as a main method for the Combat Management subsystem.
    public void playCombat(){
    }

    //called after the combat ends by the run management
    public void reportResults() {

    }

    //used to add the enemies before the combat starts.
    public void addEnemy( Enemy enemy) {
        enemies.add(enemy);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}