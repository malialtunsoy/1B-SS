import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

// This is a singleton class.
public class CombatManager {
    // constants
    private static final int DRAW_PER_TURN = 5; // will be removed later on
    private static final int INITIAL_ENERGY = 3; // will be removed later on

    //constructors
    private CombatManager() {
        enemies = new ArrayList<Enemy>();
    }

    //attributes
    private static CombatManager instance = new CombatManager();

    private Stage stage;
    private Scene menu;
    private ScreenController myController;
    private Scene currentScene;

    private int turn;
    private int energy;
    private int maxEnergy;

    private ArrayList<Enemy> enemies;
    private Player player;

    private ArrayList<Card> drawPile;
    private ArrayList<Card> hand;
    private ArrayList<Card> discardPile;

    private boolean playersTurn;

    private Card selectedCard;

    private CombatUIAdapter uiAdapter;

    // -----  methods  ----

    //plays the combat, acts as a main method for the Combat Management subsystem.
    public void playCombat(){
        initializeCombat();
        playTurn();
    }

    //the tasks that should be done at the start of every combat before the first turn is taken.
    private void initializeCombat() {
        // add relic effects
        for (int i = 0; i < player.getRelics().size(); i++) {
            player.addStatusEffect(player.getRelics().get(i).getEffect());
        }

        drawPile = player.getDeck();
        Collections.shuffle(drawPile);
        discardPile = new ArrayList<Card>();
        hand = new ArrayList<Card>();
        turn = 0;
        energy = INITIAL_ENERGY;
        maxEnergy = INITIAL_ENERGY;

        selectedCard = null;
        try { uiAdapter = new CombatUIAdapter(stage); } catch (IOException e ) {System.out.println("Error: " + e.getMessage());}

    }

    private void playTurn() {
        decayAllEffects(true);
        energy = maxEnergy;
        declareIntents();
        hand = draw(DRAW_PER_TURN);
        playersTurn = true;
        turn++;
        uiAdapter.updateView();
    }

    public int getTurn() { return turn; }

    private void decayAllEffects( boolean isPlayerTurnStart) {
        for (int i  = 0; i < enemies.size(); i++) {
            enemies.get(i).decayAllAffected(isPlayerTurnStart);
        }
        player.decayAllAffected(isPlayerTurnStart);
    }

    //Declares the intents of all enemies, called at the start of a turn.
    private void declareIntents() {
        for(Enemy enemy : enemies)
            enemy.declareIntent();
    }

    //draws cards from the drawPile, returns the cards drawn.
    public ArrayList<Card> draw(int number) {
        int modifiedNumber = player.invokeAllModifiers(CardDrawModifier.class, number);
        return drawRecursive(modifiedNumber);
    }

    private ArrayList<Card> drawRecursive( int number) {
        if( number <= 0 )
            return new ArrayList<Card>();

        if(drawPile.size() <= 0){
            drawPile = new ArrayList<Card>(discardPile);
            Collections.shuffle(drawPile);
            discardPile.clear();
        }

        Card drawn = drawPile.get(0);
        drawPile.remove(0);
        ArrayList<Card> result = drawRecursive(number-1);
        result.add(drawn);
        return result;
    }

    //plays the card at the given index. Target unused for non-targeted cards.
    public void playCard( Card c, Enemy target) {
        if (energy < c.getEnergy() || !playersTurn) {
            return;
        }
        c.affect(target);
        hand.remove(c);
        discardPile.add(c);
        energy -= c.getEnergy();
        uiAdapter.updateView();
    }

    //uses the potion at the given index.
    public void usePotion( Potion p, Enemy target) {
        player.usePot(p, target);
        uiAdapter.updateView();
    }

    //ends the player's turn.
    public void endTurn() {
        // player ends "his turn". trigger his end-turn effects.
        player.triggerAll(TriggeredAtTurnEnd.class);

        playersTurn = false;
        // discard all cards
        discardPile.addAll(hand);
        hand.clear();

        // enemies' "turn starts"
        decayAllEffects(false);

        // realize all enemy intents
        for ( Enemy e: enemies) {
            e.realizeAllIntents();
        }

        // enemies end "their turn", trigger their end-turn effects.
        for ( Enemy e: enemies) {
            e.triggerAll(TriggeredAtTurnEnd.class);
        }

        // restore energy
        energy = maxEnergy;

        selectedCard = null;
        playTurn(); // play the next turn
    }

    //called after the combat ends by the run management
    public void reportResults() {

    }

    public void cardSelected(Card c) {
        if(c.getTargetRequirement()) {
            selectedCard = c;
        } else {
            playCard(c,null);
        }
    }

    public void targetSelected(Enemy enemy) {
        if(selectedCard != null) {
            playCard(selectedCard,enemy);
            selectedCard = null;
        } else {
            System.out.println("You have chosen a target but no card is selected.");
        }
    }

    //used to add the enemies before the combat starts.
    public void addEnemy( Enemy enemy) { enemies.add(enemy); }
    // called by the enemy when they die
    public void removeEnemy( Enemy enemy) {
        enemies.remove(enemy);
        if (enemies.isEmpty()) {
            combatWon();
        }
    }

    private void combatWon() {
        System.out.println("asd");

        // trigger end of combat effects, no need to consider enemies. The list is empty.
        player.triggerAll(TriggeredAtCombatEnd.class);

        // remove all status effects from the player
        player.getStatusEffects().clear();

        // TODO: interface with run management
    }

    // ---- methods used by UIAdapter to update the view: ----
    public ArrayList<Enemy> getEnemies() {return enemies;}
    public ArrayList<Card> getHand() {return hand;}
    public int getDrawPileSize() {return drawPile.size();}
    public int getDiscardPileSize() {return discardPile.size();}
    public String uiEnergyString() {return energy + "/" + maxEnergy;} // generalize these in the actual GUI
                                                                        // ad-hoc and untidy at the moment
    // -------------------------------------------------------



    public void setPlayer(Player player) { this.player = player; }

    public Player getPlayer() { return player; }

    public void setStage(Stage primaryStage){
        stage = primaryStage;
    }

    public Stage getStage() { return stage; }

    public static CombatManager getInstance() {
        return instance;
    }

    public void setMenuScene(Scene menu) {
        this.menu = menu;
    }

    public void setScreenController(ScreenController myController){this.myController = myController;}

    public void backToMap() {
        stage.setScene(menu);
        stage.show();
    }

    public void showDeck(){
        myController.setBackFromDeck("CombatUI.fxml");
        myController.changeScreen(RunUIManager.deckScreen);
        currentScene = stage.getScene();
        stage.setScene(menu);
        System.out.println("Show Deck");
    }

    public void comeBackFromDeck(){
        System.out.println("back to combat");
        stage.setScene(currentScene);
    }

    public void showSettings(){
        System.out.println("Show Settings");
    }

    public void showMap(){
        System.out.println("Show Map");
    }

    public String getCombatState() {
        return "getCombatState() not implemented yet.";
    }
}