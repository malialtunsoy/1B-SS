package com.company;

public class RunControl {

    public static void main(String[] args) {

        int hp, maxHp, maxPot, gold, relicCount, cardCount;
        hp = 50;
        maxHp = 100;
        maxPot = 3;
        gold = 50;
        relicCount = 1;
        cardCount = 10;


        Player testP = new Player(hp,maxHp,maxPot,gold, relicCount, cardCount);

        Potion testPot = new hpPotion("hppot", 30,"increase hp", hp,testP);


        testP.addPot(testPot);
        System.out.println(testP.getHp());

        testP.usePot(0);

        System.out.println(testP.getHp());


        Map test = new Map();

        test.createVertex();
        test.chooseVertex(2);
        test.chooseVertex(3);
        test.chooseVertex(4);


    }
}
