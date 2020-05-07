import java.util.ArrayList;

/**
 * The Player class shared by the run management and combat management subsystems.
 * Run management may want to make this a singleton class, it's only here for testing purposes right now.
 * @version 06.05.2020 14:15
 */
public class Player {
    //constructors
    // Test purpose constructor. Actual constructor would take the character class as input
    public Player() {
        currentHP = 40;
        maxHP = 40;
        deck = new ArrayList<Card>();
    }

    //attributes
    private ArrayList<Card> deck;
    private int currentHP;
    private int maxHP;

    //methods
    public void addToDeck( Card card) {
            deck.add(card);
    }

    //returns a deep copy of the deck (the ArrayList is a deep copy but not the card objects)
    public ArrayList<Card> getDeck() {
        ArrayList<Card> result = new ArrayList<Card>();
        for(Card c : deck) {
            result.add(c);
        }
        return result;
    }

    // returns false if player dies
    public boolean loseHP(int amount) {
        if (amount < 0) {
            System.out.println("loseHP called with negative amount " + amount);
            return false;
        }
        currentHP -= amount;
        if (currentHP < 0) {
            currentHP = 0;
            return false;
        }
        return true;
    }
}