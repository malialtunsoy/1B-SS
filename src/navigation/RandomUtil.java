import java.util.Random;

public class RandomUtil {
    private static Random rand = new Random();

    public static Card getRandomBaseCard( String character) {

        Class<?>[] cardPool = SystemConstants.ironcladCards;
        if( character.equals("Ironclad")){
            cardPool = new Class<?>[SystemConstants.ironcladCards.length + SystemConstants.neutralCards.length];
            for( int i = 0; i < SystemConstants.ironcladCards.length; i++)
                cardPool[i] = SystemConstants.ironcladCards[i];
            for( int i = 0; i < SystemConstants.neutralCards.length; i++)
                cardPool[i + SystemConstants.ironcladCards.length] = SystemConstants.neutralCards[i];

        } else {
            System.err.println("Character class not recognized");
        }

        int index = rand.nextInt(cardPool.length);
        Card choice = null;
        try {
            choice = (Card) cardPool[index].getConstructor().newInstance();
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

    public static Enemy getRandomEnemy() {
        int index = rand.nextInt(SystemConstants.relics.length);
        Enemy choice = null;
        try {
            choice = (Enemy) SystemConstants.enemies[index].getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            System.err.println("Exception in getRandomEnemies caused by an Enemy in system without a default constructor: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Generic Exception in getRandomEnemies: " + e.getMessage());
        }
        return choice;
    }

}
