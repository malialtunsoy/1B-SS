
public class RunControl {

    public static void main(String[] args) {

        int hp, maxHp, maxPot, gold, relicCount, cardCount;
        hp = 50;
        maxHp = 100;
        maxPot = 3;
        gold = 50;
        relicCount = 0;
        cardCount = 10;


        Player testP = new Player("playerName", "Ironclad", hp,maxHp,maxPot,gold, relicCount, cardCount);

        Potion testPot = new HealthPotion();


        testP.addPot(testPot);
        System.out.println(testP.potCount);
        System.out.println(testP.getHP());

        testP.usePot(testPot, null);

        System.out.println(testP.getHP());
        System.out.println(testP.potCount);
        System.out.println(testP);

        //Map test = new Map(testP);

        //test.createVertex();
        //test.chooseVertex(2);


    }
}
