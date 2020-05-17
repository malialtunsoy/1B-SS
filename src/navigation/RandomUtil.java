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
}
