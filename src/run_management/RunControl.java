import java.util.ArrayList;

public class RunControl {

    public static void main(String[] args) {

        int hp, maxHp, maxPot, gold, relicCount, cardCount;
        hp = 50;
        maxHp = 100;
        maxPot = 3;
        gold = 50;
        relicCount = 0;
        cardCount = 10;


        //Player testP = new Player(hp,maxHp, maxPot,gold, relicCount, cardCount);
        Player testP = new Player("playerName", "Ironclad", hp,maxHp,maxPot,gold, relicCount, cardCount);

<<<<<<< HEAD
        ArrayList<Relic> offeredRelics = new ArrayList<Relic>();
        ArrayList<Potion> offeredPotions = new ArrayList<Potion>();
        ArrayList<Card> offeredCards = new ArrayList<Card>();
        Potion testPot = new HPPotion();
        Potion testPot2 = new HPPotion();
        offeredPotions.add(testPot);
        offeredPotions.add(testPot2);
=======
        Potion testPot = new HealthPotion();
>>>>>>> 57867a0211314bc23ac7bc4941af8eaee6acd82d


        /*
        testP.addPot(testPot);
        System.out.println(testP.potCount);
        System.out.println(testP.getHP());

        testP.usePot(testPot, null);

        System.out.println(testP.getHP());
        System.out.println(testP.potCount);
        System.out.println(testP);

        Map test = new Map(testP);
        System.out.println(testP.getHP());
        //test.createVertex();
        test.chooseVertex(2);
        System.out.println(testP.getHP());
        */

        Vertex mercTest = new Merchant(offeredRelics,offeredPotions,offeredCards);
        ((Merchant) mercTest).showItems();

    }
}
