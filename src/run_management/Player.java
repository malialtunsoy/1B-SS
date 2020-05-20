
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Player extends CombatEntity {
    private static final String LOAD_FILENAME = "Data.txt";


    String playerName;
    String playerChar;
    int potCount, maxPot, gold, relicCount, cardCount;
    ArrayList<Potion> potions;
    ArrayList<Relic> relics;
    ArrayList<Card> deck;
    Pet myPet;
    Map myMap;

    ArrayList<Card> merchantCard;
    ArrayList<Relic> merchantRelic;
    ArrayList<Potion> merchantPotion;

    ArrayList<Relic> tresRelics;
    ArrayList<Potion> tresPots;

    ArrayList<Relic> allRelics;
    ArrayList<Potion> allPots;

    ArrayList<Potion> rewardPotions;
    ArrayList<Relic> rewardRelics;
    ArrayList<Card> rewardCards;
    int rewardGold;

    public Player(boolean isItNewGame, String name, String character, int hp, int maxHp, int maxPot, int gold, int relicCount, int cardCount) {

        super(maxHp);
        this.loseHP(maxHp-hp);
        playerName = name;
        playerChar = character;
        this.maxPot = maxPot;
        this.gold = gold;
        this.cardCount = cardCount;
        this.relicCount = relicCount;
        potions = new ArrayList<Potion>();
        relics = new ArrayList<Relic>();
        deck = new ArrayList<Card>();
        myMap = new Map();
        potCount = 0;
        rewardPotions = new ArrayList<Potion>();
        rewardRelics = new ArrayList<Relic>();
        rewardCards = new ArrayList<Card>();
        rewardGold = 0;
        //if(isItNewGame){initializePlayer();}
        //else{loadPlayer();}
        if(isItNewGame){initializePlayer(); myMap.initializeMap();}
        else{loadPlayer(); myMap.loadMap();}
    }

    //methods
    public int getGold() {
        return gold;
    }

    public void setGold(int newGold) {
        this.gold = newGold;
    }

    public void addGold(int lootedGold) {
        gold = gold + lootedGold;
    }

    public void subGold(int decrGold) {
        gold = gold + decrGold;
    }

    public void setMaxPots(int newMaxPots) {
        this.maxPot = newMaxPots;
    }

    public int getMaxPots() {
        return maxPot;
    }

    public int getRelicCount() {
        return relicCount;
    }

    // required in CombatManager
    public ArrayList<Relic> getRelics() {return relics;}

    public String getPlayerName(){return playerName;}

    public String getPlayerChar(){return playerChar;}

    public ArrayList<Potion> getPots() {
        return potions;
    }

    public ArrayList<Potion> getRewardPotions() {
        return rewardPotions;
    }

    public ArrayList<Relic> getRewardRelics() {
        return rewardRelics;
    }

    public ArrayList<Card> getRewardCards() { return rewardCards; }

    public int getRewardGold(){ return rewardGold;}

    public void addRewardCard(Card card) { rewardCards.add(card);}

    public void setRewardGold(int rewardGold) { this.rewardGold = rewardGold;}

    public void addRewardRelic(Relic relic) {
        rewardRelics.add(relic);
    }

    public void addRewardPotion(Potion pot) {
        rewardPotions.add(pot);
    }

    public void initializePlayer(){
        intializeDeck();
        intializeRelic();
        intializePotion();

        intializeGold();

        initalizeMerchantDeck();
        initalizeMerchantRelic();
        initalizeMerchantPotion();
    }

    public void loadPlayer(){
        loadDeck();
        loadRelic();
        loadPotion();

        initalizeMerchantDeck();
        initalizeMerchantRelic();
        initalizeMerchantPotion();

        loadMerchantDeck();
        loadMerchantRelic();
        loadMerchantPotion();
    }


    //==========================================INITIALIZE====================================
    public void intializeDeck(){
        deck.add( new Strike() );
        deck.add( new Strike() );
        deck.add( new Strike() );
        deck.add( new Strike() );
        deck.add( new Strike() );

        deck.add( new Defend());
        deck.add( new Defend());
        deck.add( new Defend());
        deck.add( new Defend());
        deck.add( new Defend());
    }

    public void intializeRelic(){
        try {
            if (playerChar.equals("Ironclad"))
                relics.add((Relic)SystemConstants.ironcladRelic.getConstructor().newInstance());
            else if (playerChar.equals("Silent"))
                relics.add((Relic)SystemConstants.silentRelic.getConstructor().newInstance());
            else if (playerChar.equals("Watcher"))
                relics.add((Relic)SystemConstants.watcherRelic.getConstructor().newInstance());
        } catch (NoSuchMethodException e) {
            System.err.println("Exception in initializeRelic caused by a Relic in system without a default constructor: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Generic Exception in initializeRelic: " + e.getMessage());
        }
    }

    public void intializePotion(){
        
    }

    public void intializeGold(){
        gold = 330;
    }
   // =========================================================LOAD===============================================

    public void loadDeck() {
        String[] deckCardNames;
        try {
            deckCardNames = FileRead.readFile(LOAD_FILENAME, "PlayerDeck");
            deck = FileRead.convertTo(Card.class, deckCardNames);
        } catch (IOException e){System.err.println(e.getMessage());}
    }

    public void loadPotion() {
        String[] potionNames;
        try{potionNames = FileRead.readFile(LOAD_FILENAME, "PlayerPotions");
            potions = FileRead.convertTo(Potion.class, potionNames);
        }
        catch (IOException e){System.err.println(e.getMessage());}
    }

    public void loadRelic(){
        String[] relicNames;
        try{relicNames = FileRead.readFile(LOAD_FILENAME, "PlayerRelics");
            relics = FileRead.convertTo(Relic.class, relicNames);
        }
        catch (IOException e){System.err.println(e.getMessage());}
    }

    public void loadMerchantDeck(){ }
    public void loadMerchantRelic(){ }
    public void loadMerchantPotion(){ }








    //MERCHANT ****************************************
    public boolean purchaseCard(Card card){
        if(gold >= card.getCost()) {
            deck.add(card);
            gold = gold - card.getCost();
        }
        else{return false;}
        System.out.println("purchased: " + card.getName() );
        return true;
    }

    public boolean purchaseRelic(Relic relic){
        if(gold >= relic.getCost()) {
            relics.add(relic);
            gold = gold - relic.getCost();
       }
        else{return false;}
        System.out.println("purchased: " + relic.getName() );
        return true;
    }

    public boolean purchasePotion(Potion potion){
        if(gold >= potion.getCost() && (potions.size() < 3) ) {
                potions.add(potion);
            gold = gold - potion.getCost();
        }
        else{return false;}
        System.out.println("purchased: " + potion.getName() );
        return true;
    }

    public void initalizeMerchantDeck(){
        merchantCard = new ArrayList<Card>();
        merchantCard.add(new Strike());
        merchantCard.add(new Defend());
    }

    public void initalizeMerchantRelic(){
        merchantRelic = new ArrayList<Relic>();
        merchantRelic.add(new RingOfTheSnake());
        merchantRelic.add(new BurningBlood());
    }

    public void initalizeMerchantPotion(){
        merchantPotion = new ArrayList<Potion>();
        merchantPotion.add(new HealthPotion());
        merchantPotion.add(new DamagePotion());
    }

    public ArrayList<Card> getMerchantDeck(){
        return RandomUtil.getRandomBaseCardsNoDuplicate( 7, playerChar);
    }
    public ArrayList<Relic> getMerchantRelics() {
        return RandomUtil.getRandomRelicsNoDuplicate(3);
    }

    public ArrayList<Potion> getMerchantPotions() {
        return RandomUtil.getRandomPotionsNoDuplicate(2);
    }
    //END OF MERCHANT ********************************************************************


    //REST CARD UPGRADE ***************************************************************************
    public void upgradeCard(Card card,int index){
        deck.remove(index);
        deck.add(card.upgradedVersion());

    }
    //END OF REST CARD UPGRADE ***************************************************************************

    //TREASURE *************************************************************************
    public void generateTresRandom()
    {
        putAll();
        tresPots = new ArrayList<Potion>();;
        tresRelics = new ArrayList<Relic>();
        Random randomG = new Random();
        int tresCount = 3;
        int randomCount = randomG.nextInt(tresCount);
        tresPots = RandomUtil.getRandomPotionsNoDuplicate(randomCount);
        tresRelics = RandomUtil.getRandomRelicsNoDuplicate(tresCount - randomCount);

    }
    public void putAll()
    {
        allPots = new ArrayList<Potion>();
        allPots.add(new HealthPotion());
        allPots.add(new DamagePotion());

        allRelics = new ArrayList<Relic>();
        allRelics.add(new RingOfTheSnake());
        allRelics.add(new BurningBlood());
    }
    public ArrayList<Potion> getTresPots(){return tresPots;}
    public ArrayList<Relic> getTresRelics(){return tresRelics;}
    //END OF TREASURE ************************************************************


    //******************Combat*********************
    public void setupCombat(boolean isElite){
        setupEnemies(isElite);
        setupRewards(isElite);
        CombatManager.getInstance().setPlayer(this);
        CombatManager.getInstance().playCombat();
    }

    public void setupEnemies(boolean isElite) {

        Random random = new Random();
        if(isElite) {
            Class<?> [][] encounterPool = SystemConstants.elites;
            Class<?> []encounter = encounterPool[random.nextInt(encounterPool.length)];
            for(Class<?> enemyClass : encounter)
                try {
                    CombatManager.getInstance().addEnemy((Enemy)enemyClass.getConstructor().newInstance());
                } catch (NoSuchMethodException e) {
                    System.err.println("Exception in setupEnemies caused by a Card in system without a default constructor: " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Generic Exception in setupEnemies: " + e.getMessage());
                }
            return;
        }

        int numberOfEnemies = random.nextInt(3 ) + 1;
        for( int i = 0; i < numberOfEnemies; i++) {
            Enemy e = RandomUtil.getRandomEnemy();
            CombatManager.getInstance().addEnemy(e);
        }
    }

    public void setupRewards(boolean isElite) {

        Random random = new Random();
        rewardCards = new ArrayList<Card>();
        rewardRelics = new ArrayList<Relic>();
        rewardPotions = new ArrayList<Potion>();

        if(isElite)
            addRewardRelic(RandomUtil.getRandomRelic());
        addRewardPotion(RandomUtil.getRandomPotion());
        setRewardGold(random.nextInt(40) * 5 + 200);

        //choose three different cards.
        rewardCards = RandomUtil.getRandomBaseCardsNoDuplicate(3, playerChar);
    }

    //array arttır ekle
    public void addToDeck(Card card) {
        deck.add(card);
    }

    //copy of deck
    public ArrayList<Card> getDeck() {
        ArrayList<Card> temp = new ArrayList<Card>();
        for (Card c : deck)
            temp.add(c);
        return temp;
    }

    public void addPot( Potion pot )
    {
        if(potCount <= maxPot)
        {
            potions.add(pot);
            potCount++;
        }
    }

    public void usePot(Potion potion, Enemy target)
    {
        potion.affect(target);
        //Potion empty = new emptyPotion("Empty Potion",0,"No description");
        potions.remove(potion);
        potCount--;
    }

    public void addRelic(Relic relic)
    {
        relics.add(relic);
    }
    public String toString()
    {
        return "Player" + "\n-------\n" + super.toString();

    }
    @Override
    // called when the player dies
    public void die() { CombatManager.getInstance().gameOver();
        // TODO: combat lost
    }

    @Override
    public boolean loseHP(int amount) { //Overritten for UI Animation Purposes
       if(amount > 0){
           CombatManager.getInstance().playerTakeDamageAnmiation();
       }
       return super.loseHP(amount);
    }

    @Override
    public boolean dealDamage(int amount, CombatEntity target) { //Overritten for UI Animation Purposes
        if(amount > 0){
            CombatManager.getInstance().playerAttackAnimation();
        }
        return super.dealDamage(amount, target);
    }
}
