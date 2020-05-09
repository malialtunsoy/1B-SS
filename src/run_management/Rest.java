package com.RunMan;

public class Rest {

    //methods
    public Card upgradeCard(Card card){
        card.setEffect(card.getEffect()+1);
        return card;
    }
    public int healHP(int maxHP){
        int heal = maxHP*30/100;
        return heal;
    }
}
