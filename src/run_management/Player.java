
import java.util.ArrayList;

public class Player extends CombatEntity{

    int hp, maxHp, potCount, maxPot, gold, relicCount, cardCount;
    ArrayList<Potion> pots;
    ArrayList<Relic> relics;
    ArrayList<Card> deck;
    Pet myPet;

    public Player(int hp, int maxHp, int maxPot, int gold, int relicCount, int cardCount) {
        super(hp);
        this.hp = hp;
        this.maxHp = maxHp;
        this.maxPot = maxPot;
        this.gold = gold;
        this.cardCount = cardCount;
        this.relicCount = relicCount;
        pots = new ArrayList<Potion>();
        relics = new ArrayList<Relic>();
        deck = new ArrayList<Card>();
        potCount = 0;
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

    public int getHp() {
        return hp;
    }

    public void setHp(int newHp) {
        this.hp = newHp;
    }

    public void addHp(int addHp) {
        hp = hp + addHp;
    }

    public void loseHp(int amount) {
        hp = hp - amount;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int newMaxHp) {
        this.maxHp = newMaxHp;
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
            pots.add(pot);
            potCount++;
        }
    }

    public void usePot(int index)
    {
        pots.get(index).affect();
        //Potion empty = new emptyPotion("Empty Potion",0,"No description");
        pots.remove(index);
        potCount--;
    }

    public void addRelic(Relic relic)
    {
        relics.add(relic);
    }
    public String toString()
    {
        String temp = "Hp is " + hp + " Max hp is " + maxHp + "Gold is " + gold;
        return temp;
    }
}
