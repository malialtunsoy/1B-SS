import java.util.Random;

public class RandomUtil {
    private static Random rand = new Random();

    public static Card getRandomBaseCard() {
        int index = rand.nextInt(SystemConstants.baseCards.length);
        Card choice = null;
        try {
            choice = (Card) SystemConstants.baseCards[index].getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            System.err.println("Exception in getRandomBaseCard caused by a Card in system without a default constructor: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Generic Exception in getRandomBaseCard: " + e.getMessage());
        }
        return choice;
    }

    public static Potion getRandomPotion() {
        int index = rand.nextInt(SystemConstants.potions.length);
        Potion choice = null;
        try {
            choice = (Potion) SystemConstants.potions[index].getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            System.err.println("Exception in getRandomPotion caused by a Potion in system without a default constructor: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Generic Exception in getRandomPotion: " + e.getMessage());
        }
        return choice;
    }

    public static Relic getRandomRelic() {
        int index = rand.nextInt(SystemConstants.relics.length);
        Relic choice = null;
        try {
            choice = (Relic) SystemConstants.relics[index].getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            System.err.println("Exception in getRandomRelic caused by a Relic in system without a default constructor: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Generic Exception in getRandomRelic: " + e.getMessage());
        }
        return choice;
    }

}
