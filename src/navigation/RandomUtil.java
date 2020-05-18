import java.util.ArrayList;
import java.util.Random;

public class RandomUtil {
    private static Random rand = new Random();


    public static ArrayList<Card> getRandomBaseCardsNoDuplicate( int count, String playerChar){
        ArrayList<Card> current = new ArrayList<Card>();
        boolean duplicate = true;
        for( int i = 0; i < count; i++){
            Card card = null;
            while(duplicate) {
                duplicate = false;
                card = RandomUtil.getRandomBaseCard(playerChar);
                for (Card included : current) {
                    if (included.getClass().getName().equals(card.getClass().getName()))
                        duplicate = true;
                }
            }
            duplicate = true;
            current.add(card);
        }
        return current;
    }

    public static ArrayList<Relic> getRandomRelicsNoDuplicate(int count){
        ArrayList<Relic> current = new ArrayList<Relic>();
        boolean duplicate = true;
        for( int i = 0; i < count; i++){
            Relic relic = null;
            while(duplicate) {
                duplicate = false;
                relic = RandomUtil.getRandomRelic();
                for (Relic included : current) {
                    if (included.getClass().getName().equals(relic.getClass().getName()))
                        duplicate = true;
                }
            }
            duplicate = true;
            current.add(relic);
        }
        return current;
    }

    public static ArrayList<Potion> getRandomPotionsNoDuplicate(int count){
        ArrayList<Potion> current = new ArrayList<Potion>();
        boolean duplicate = true;
        for( int i = 0; i < count; i++){
            Potion potion = null;
            while(duplicate) {
                duplicate = false;
                potion = RandomUtil.getRandomPotion();
                for (Potion included : current) {
                    if (included.getClass().getName().equals(potion.getClass().getName()))
                        duplicate = true;
                }
            }
            duplicate = true;
            current.add(potion);
        }
        return current;
    }

    // has the input probability of returning true.
    public static boolean trueWithProb( double prob) {
        double rand = Math.random();
        return rand < prob;
    }

    // returns a random index from the input array according to the probabilities
    public static int indexWithProb( double [] probs) {
        // integrity check
        double checkSum = 0;
        for ( int i = 0; i < probs.length; i++) {
            if (probs[i] < 0) {
                return -1;
            }
            checkSum += probs[i];
        }
        if (checkSum - 1 < -0.00001 || checkSum - 1 > 0.00001) {
            return -1;
        }

        double rand = Math.random();
        double sum = 0;
        for ( int i = 0; i < probs.length; i++) {
            sum += probs[i];
            if (rand < sum) {
                return i;
            }
        }
        return probs.length - 1;
    }

    public static int generateEnemyHP(int lowerLimit, int upperLimit) {
        return lowerLimit + rand.nextInt(upperLimit - lowerLimit + 1);
    }

    public static Card getRandomBaseCard( String character) {

        Class<?>[] cardPool = SystemConstants.ironcladCards;
        Class<?>[] classPool = null;
        if( character.equals("Ironclad")){
            classPool = SystemConstants.ironcladCards;
        } else if (character.equals("Silent")){
            classPool = SystemConstants.silentCards;
        } else {
            System.err.println("Character class not recognized");
        }

        cardPool = new Class<?>[classPool.length + SystemConstants.neutralCards.length];
        for( int i = 0; i < classPool.length; i++)
            cardPool[i] = classPool[i];
        for( int i = 0; i < SystemConstants.neutralCards.length; i++)
            cardPool[i + classPool.length] = SystemConstants.neutralCards[i];


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
