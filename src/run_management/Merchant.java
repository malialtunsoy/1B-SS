package com.RunMan;

import java.util.ArrayList;

public class Merchant extends Vertex{
    //attributes
    private int relicSize,potionSize,cardsSize;
    private ArrayList<Relic> offeredRelics;
    private ArrayList<Potion> offeredPotions;
    private ArrayList<Card> offeredCards;

    //methods(set methods are used for adding new items to arrays)
    public Merchant(ArrayList<Relic> offeredRelics, ArrayList<Potion> offeredPotions, ArrayList<Card> offeredCards) {
        this.offeredCards = offeredCards;
        this.offeredPotions = offeredPotions;
        this.offeredRelics = offeredRelics;

    }

    public ArrayList<Relic> getOfferedRelics() {
        return offeredRelics;
    }

    public void setOfferedRelics(Relic[] newRelics) {
        for (int i = 0; i < newRelics.length; i++) {
            offeredRelics.add(newRelics[i]);
        }
    }

    public ArrayList<Potion> getOfferedPotions() {
        return offeredPotions;
    }

    public void setOfferedPotions(Potion[] newPotions) {
        for (int i = 0; i < newPotions.length; i++) {
            offeredPotions.add(newPotions[i]);
        }
    }

    public ArrayList<Card> getOfferedCards() {
        return offeredCards;
    }

    public void setOfferedCards(Card[] newCards) {
        for (int i = 0; i < newCards.length; i++) {
            offeredCards.add(newCards[i]);
        }
    }

    public void buyPotion(int index, Player p) {
        Potion temp = offeredPotions.get(index);
        p.addPot(temp);
        offeredPotions.remove(index);

    }

    public void buyRelic(int index, Player p) {
        Relic temp = offeredRelics.get(index);
        p.addRelic(temp);
        offeredRelics.remove(index);
    }

    public void buyCard(int index, Player p) {
        Card temp = offeredCards.get(index);
        p.addToDeck(temp);
        offeredCards.remove(index);
    }

    public int costRemovalCard(int index, Player p) {
        int cost = 75;
        p.getDeck().remove(index);
        return cost;
    }
}