
import java.util.ArrayList;

public class Player extends CombatEntity {

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

    public Player(String name, String character, int hp, int maxHp, int maxPot, int gold, int relicCount, int cardCount) {

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
        initializePlayer();
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

    public void initializePlayer(){
                intializeDeck();
                intializeRelic();
        intializePotion();

        intializeGold();

        initalizeMerchantDeck();
        initalizeMerchantRelic();
        initalizeMerchantPotion();

    }

    public void intializeDeck(){

        deck.add( new Strike() );
        deck.add( new Strike() );
        deck.add( new Strike() );
        deck.add( new Strike() );
        deck.add( new Strike() );

        deck.add(new Defend() );
        deck.add(new Defend() );
        deck.add(new Defend() );
        deck.add(new Defend() );
        deck.add(new Defend() );

    }

    public void intializeRelic(){
        relics.add( new BurningBlood() );
    }

    public void intializePotion(){
        potions.add( new DamagePotion() );
    }

    public void intializeGold(){
       // gold = 330;
    }

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
       /* if(gold >= relic.getCost()) {
            relics.add(relic);
            gold = gold - relic.getCost();
        }
        else{return false;}*/
        System.out.println("purchased: " + relic.getName() );
        return true;
    }

    public boolean purchasePotion(Potion potion){
       /* if(gold >= potion.getCost()) {
            potions.add(potion);
            gold = gold - potion.getCost();
        }
        else{return false;}*/
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
        return merchantCard;
    }
    public ArrayList<Relic> getMerchantRelics() { return merchantRelic; }

    public ArrayList<Potion> getMerchantPotions() { return merchantPotion; }





    public void purchaseRelic(){}

    public void purchasePotion(){}

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
    public void die() {
        // TODO: combat lost
    }
}
