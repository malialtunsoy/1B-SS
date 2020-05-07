import java.util.ArrayList;

/**
 * The Player class shared by the run management and combat management subsystems.
 * Run management may want to make this a singleton class, it's only here for testing purposes right now.
 * @version 06.05.2020 14:15
 */
public class Player extends CombatEntity{
    //constructors
    // Test purpose constructor. Actual constructor would take the character class as input
    public Player() {
        super(40);  // for now, fixed starting maxHP (40). 
        deck = new ArrayList<Card>();
    }

    //attributes
    private ArrayList<Card> deck;

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

}