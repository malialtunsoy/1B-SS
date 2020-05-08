import java.util.ArrayList;

// This is a singleton class.
public class CombatManager {

    //constructors
    private CombatManager() {
        enemies = new ArrayList<Enemy>();
    }

    //attributes
    private static CombatManager instance = new CombatManager();

    private int turn;
    private int energy;
    private int maxEnergy;

    private ArrayList<Enemy> enemies;
    private Player player;

    private ArrayList<Card> drawPile;
    private ArrayList<Card> hand;
    private ArrayList<Card> discardPile;

    // -----  methods  ----

    public static CombatManager getInstance() {
        return instance;
    }

    //plays the combat, acts as a main method for the Combat Management subsystem.
    public void playCombat(){
        System.out.println("Am invoked ( ͡° ͜ʖ ͡°)");
        initializeCombat();
        playTurn();
    }

    //the tasks that should be done at the start of every combat before the first turn is taken.
    private void initializeCombat() {
        drawPile = player.getDeck();
        discardPile = new ArrayList<Card>();
        hand = new ArrayList<Card>();
        turn = 0;
        energy = 0;
        maxEnergy = 0;
    }

    private void playTurn() {
        declareIntents();
        energy = maxEnergy;
        hand = draw(4);
    }

    //Declares the intents of all enemies, called at the start of a turn.
    private void declareIntents() {
        for(Enemy enemy : enemies)
            enemy.declareIntent();
    }
    //draws cards from the drawPile, returns the cards drawn.
    private ArrayList<Card> draw( int number) {
        if( number <= 0 )
            return new ArrayList<Card>();

        if(drawPile.size() <= 0){
            drawPile = discardPile;
            discardPile.clear();
        }

        Card drawn = drawPile.get(0);
        drawPile.remove(0);
        ArrayList<Card> result = draw(number-1);
        result.add(drawn);
        return result;
    }

    //plays the card at the given index.
    public void playCard( int index) {

    }

    //uses the potion at the given index.
    public void usePotion( int index) {

    }

    //adds the effect to the player.
    public void addStatusEffect( StatusEffect effect) {

    }

    //ends the player's turn.
    public void endTurn() {

    }

    //called after the combat ends by the run management
    public void reportResults() {

    }

    //used to add the enemies before the combat starts.
    public void addEnemy( Enemy enemy) { enemies.add(enemy); }

    public void setPlayer(Player player) { this.player = player; }
    public Player getPlayer() { return player; }

    public String getCombatState() {
        return "getCombatState() not implemented yet.";
    }
}