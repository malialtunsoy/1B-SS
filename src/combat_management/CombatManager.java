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
    MakesCardChoice callbackTarget;

    private static CombatManager instance = new CombatManager();

    private Stage stage;
    private Scene menu;
    private ScreenController myController;
    private boolean sceneChanged;
    private Scene currentScene;

    boolean deckClickedBefore = false;
    boolean settingsClickedBefore = false;
    boolean mapClickedBefore = false;

    private int turn;
    private int energy;
    private int maxEnergy;

    private ArrayList<Enemy> enemies;
    private Player player;

    private ArrayList<Card> drawPile;
    private ArrayList<Card> hand;
    private ArrayList<Card> discardPile;

    private boolean playersTurn;
    private boolean ongoing;
    private Card selectedCard;
    private Potion selectedPotion;

    private CombatUIAdapter uiAdapter;


    // -----  methods  ----

    //plays the combat, acts as a main method for the Combat Management subsystem.
    public void playCombat() {
        ongoing = true;
        initializeCombat();
        // TriggeredAtCombatStart effects triggered at all entities
        player.triggerAll(TriggeredAtCombatStart.class, null);
        for ( Enemy e : enemies) {
            e.triggerAll(TriggeredAtCombatStart.class, null);
        }
        playTurn();
    }

    //the tasks that should be done at the start of every combat before the first turn is taken.
    private void initializeCombat() {
         deckClickedBefore = false;
         settingsClickedBefore = false;
         mapClickedBefore = false;
        sceneChanged = false;
        // add relic effects
        for (int i = 0; i < player.getRelics().size(); i++) {
            player.addStatusEffect(player.getRelics().get(i).getEffect());
        }
        sceneChanged = false;
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
        player.triggerAll(TriggeredAtTurnStart.class, null);
        decayAllEffects(true);
        energy = maxEnergy;
        declareIntents();
        hand.addAll(draw(DRAW_PER_TURN));
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
        if (!c.getExtraAttributes().contains(Card.ExtraCardAttribute.EXHAUST)) {
            discardPile.add(c);
        }
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
        player.triggerAll(TriggeredAtTurnEnd.class, null);

        playersTurn = false;
        // discard
        // TODO: Bad impl. of RETAIN, fix later
        ArrayList<Card> retained = new ArrayList<Card>();
        for ( Card c : hand) {
            if (c.getExtraAttributes().contains(Card.ExtraCardAttribute.RETAIN)) {
                retained.add(c);
            }
        }
        hand.removeAll(retained);
        discardPile.addAll(hand);
        hand = retained;


        // enemies' "turn starts"
        for (Enemy e : enemies) {
            e.triggerAll(TriggeredAtTurnStart.class, null);
        }
        decayAllEffects(false);

        // realize all enemy intents
        for ( Enemy e: enemies) {
            e.realizeAllIntents();
        }

        // enemies end "their turn", trigger their end-turn effects.
        for ( Enemy e: enemies) {
            e.triggerAll(TriggeredAtTurnEnd.class, null);
        }

        // restore energy
        energy = maxEnergy;

        selectedCard = null;
        selectedPotion = null;
        playTurn(); // play the next turn
    }

    //called after the combat ends by the run management
    public void reportResults() {

    }

    public void gainEnergy( int amount) {
        energy += amount;
        uiAdapter.updateView(); // not sure if necessary
    }

    public void chooseCard( ArrayList<Card> from, String propmt ,MakesCardChoice client) {
        uiAdapter.chooseCard(from,propmt);
        callbackTarget = client;
    }

    public void cardSelectedForCallback( Card c) {
        callbackTarget.callback(c);
        uiAdapter.updateView();
    }

    public void cardSelected(Card c) {
        if(c.getTargetRequirement()) {
            selectedPotion = null;
            selectedCard = c;
            uiAdapter.showPrompt(true, c.getName());
        } else {
            uiAdapter.showPrompt(false, "");
            playCard(c,null);
        }
    }

    public void potionSelected(Potion p) {
        if(p.getTargetRequirement()) {
            selectedCard = null;
            selectedPotion = p;
            uiAdapter.showPrompt(true,p.getName());
        } else {
            usePotion(p,null);
            uiAdapter.showPrompt(false, "");
        }
    }

    public void targetSelected(Enemy enemy) {
        if(selectedCard != null) {
            uiAdapter.showPrompt(false, "");
            playCard(selectedCard,enemy);
            selectedCard = null;
        } else if (selectedPotion != null) {
            uiAdapter.showPrompt(false, "");
            usePotion(selectedPotion, enemy);
            selectedPotion = null;
        }
        else {
            System.out.println("You have chosen a target but no card/potion is selected.");
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
        // trigger end of combat effects, no need to consider enemies. The list is empty.
        player.triggerAll(TriggeredAtCombatEnd.class, null);

        // remove all status effects from the player
        player.getStatusEffects().clear();

        ongoing = false;
        // TODO: interface with run management

        uiAdapter.loadRewardsScreen();
    }

    // ---- methods used by UIAdapter to update the view: ----
    public ArrayList<Enemy> getEnemies() {return enemies;}
    public ArrayList<Card> getHand() {return hand;}
    public int getDrawPileSize() {return drawPile.size();}
    public int getDiscardPileSize() {return discardPile.size();}
    public String uiEnergyString() {return energy + "/" + maxEnergy;} // generalize these in the actual GUI
                                                                        // ad-hoc and untidy at the moment
    // -------------------------------------------------------


    public ArrayList<Card> getDrawPile() { return drawPile; }

    public ArrayList<Card> getDiscardPile() { return discardPile;}

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
        if(sceneChanged) {
            myController.reloadScreen(RunUIManager.mainRunScreen, RunUIManager.mainRunScreenFile);
            myController.changeScreen("MainRunScreen"); //need a better solution.
        }
        stage.show();
    }

    public void showMap(){
        sceneChanged = true;
        myController.setBackFromMap("CombatUI.fxml");
        if(!mapClickedBefore){ myController.changeScreen(RunUIManager.quickMapScreen);}
        mapClickedBefore = true;
        currentScene = stage.getScene();
        stage.setScene(menu);
        System.out.println("Show Map");
    }

    public void showDeck(){
        sceneChanged = true;
        myController.setBackFromDeck("CombatUI.fxml");
        if(!deckClickedBefore){ myController.changeScreen(RunUIManager.deckScreen);}
        deckClickedBefore = true;
        currentScene = stage.getScene();
        stage.setScene(menu);
        System.out.println("Show Deck");
    }

    public void showSettings(){
        myController.setGetBackFromSettings("CombatUI.fxml");
        if(!settingsClickedBefore){ myController.changeScreen(NavigationUI.optionsScreen); }
        settingsClickedBefore = true;
        currentScene = stage.getScene();
        stage.setScene(menu);
        System.out.println("Show Settings");
    }

    public void comeBackFromDeck(){
        System.out.println("back to combat");
        stage.setScene(currentScene);
    }

    public void comeBackFromSetting(){
        System.out.println("back to combat");
        stage.setScene(currentScene);
    }

    public void comeBackFromMap(){
        System.out.println("back to combat");
        stage.setScene(currentScene);
    }

    /*Changes the singleton instance. called when loading a game
     *
     *   how to call:   1) call setPlayer
     *                  2) call loadState
     */
    public static void loadState(ArrayList<Enemy> enemies,
                             ArrayList<Card> hand, ArrayList<Card> drawPile, ArrayList<Card> discardPile,
                             int energy, int maxEnergy, int turn) {

        // standard setters
        instance.enemies = enemies;
        instance.hand = hand;
        instance.drawPile = drawPile;
        Collections.shuffle(drawPile);  // re-shuffle in order to keep the user from checking the save file to see next draw.
        instance.discardPile = discardPile;
        instance.energy = energy;
        instance.maxEnergy = maxEnergy;
        instance.turn = turn;

        instance.selectedCard = null;

        //add relic effects
        for (int i = 0; i < instance.player.getRelics().size(); i++) {
            instance.player.addStatusEffect(instance.player.getRelics().get(i).getEffect());
        }

        // don't call playTurn(), we don't want to reset the turn upon loading.
        instance.declareIntents();  // intents not saved right now.
        instance.playersTurn = true;
        try { instance.uiAdapter = new CombatUIAdapter(instance.stage); } catch (IOException e ) {System.out.println("Error: " + e.getMessage());}
        instance.uiAdapter.updateView();
    }

    public String getCombatState() {
        if (!ongoing) {
            return "1###Combat::Ongoing###\t**false**\n";
        }

        // compute the line Combat::Enemies
        String enemyNames = "**";
        for (Enemy e : enemies) {
            enemyNames += e.getClass().getName() + "**";
        }
        if (enemies.size() == 0) {
            enemyNames += "**";
        }
        enemyNames = enemies.size() + "###Combat::Enemies###\t" + enemyNames;

        // compute all lines for enemy status effects
        String enemyStates = "";
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            enemyStates += "2###Combat::Enemy@" + i + "::HP/MAXHP###\t**" + e.getHP() + "**" + e.getMaxHP() + "**\n";
            enemyStates += getStatusEffectsString(e, i) + "\n";
        }



        String playerStatusEffects = getStatusEffectsString(instance.player, -1);

        String handString = getCardPileString(hand);
        String drawPileString = getCardPileString(drawPile);
        String discardPileString = getCardPileString(discardPile);

        String EngMaxEngTurn = "3###Combat::Energy/MaxEnergy/Turn###\t**" + energy + "**" + maxEnergy + "**" + turn + "**";

        return "1###Combat::Ongoing###\t**true**\n" +
                enemyNames + "\n" +
                enemyStates + // already has \n
                playerStatusEffects + "\n" +
                handString + "\n" +
                drawPileString + "\n" +
                discardPileString + "\n" +
                EngMaxEngTurn + "\n";
    }

    // Helper method for getCombatState. index unused if ent is Player.
    private static String getCardPileString( ArrayList<Card> pile) {
        String classNames = "**";
        for (Card c: pile) {
            classNames += c.getClass().getName() + "**";
        }
        if (pile.size() < 0) {
            classNames += "**";
        }

        String lineStart = pile.size() + "###Combat::";
        if (pile == instance.hand) {
            lineStart += "Hand";
        } else if (pile == instance.drawPile) {
            lineStart += "DrawPile";
        } else if (pile == instance.discardPile) {
            lineStart += "DiscardPile";
        }

        return lineStart + "###\t" + classNames;
    }

    // Helper method for getCombatState. index unused if ent is Player.
    private static String getStatusEffectsString( CombatEntity ent, int index) {
        String effectNames = "**";
        String effectCounters = "**";
        String effectAppliedByEnemy = "**";
        int numNonRelicEffects = 0;
        for (StatusEffect se : ent.getStatusEffects()) {
            if (!(se instanceof RelicEffect)) { // relic effects are not saved. Relics themselves are.
                effectNames += se.getClass().getName() + "**";
                effectCounters += se.getCounter() + "**";
                effectAppliedByEnemy += (!se.decayAtTurnStart()) + "**";
                numNonRelicEffects++;
            }
        }
        if (numNonRelicEffects == 0) {
            effectNames += "**";
            effectCounters += "**";
            effectAppliedByEnemy += "**";
        }
        String identifier = "";
        if (ent == instance.player) {
            identifier = "Player";
        } else {
            identifier = "Enemy@" + index;
        }

        String lineStart = numNonRelicEffects + "###Combat::";

        effectNames = lineStart + identifier + "::StatusEffects::Names###\t" + effectNames;
        effectCounters = lineStart + identifier +"::StatusEffects::Counters###\t" + effectCounters;
        effectAppliedByEnemy = lineStart + identifier + "::StatusEffects::AppliedByAnEnemy###\t" + effectAppliedByEnemy;

        return effectNames + "\n" + effectCounters + "\n" + effectAppliedByEnemy;
    }

    public boolean combatOngoing() {return ongoing;}


    public void gameOver(){
        myController.changeScreen(RunUIManager.gameOverScreen);
        stage.setScene(menu);
        enemies = new ArrayList<Enemy>();
        myController.playGameOver();
        stage.show();
    }
}