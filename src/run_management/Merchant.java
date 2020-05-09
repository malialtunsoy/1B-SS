package com.RunMan;

import java.util.ArrayList;

public class Merchant {
    //attributes
    private ArrayList<Relic> offeredRelics;
    private int relicSize;
    private  ArrayList<Potion> offeredPotions;
    private int potionSize;
    private ArrayList<Card> offeredCards;
    private int cardsSize;

    //methods(set methods are used for adding new items to arrays)
    public Merchant(ArrayList<Relic> offeredRelics, ArrayList<Potion> offeredPotions, ArrayList<Card> offeredCards ){
          offeredRelics = new ArrayList<Relic>(3);
          offeredPotions = new ArrayList<Potion>(3);
          offeredCards = new ArrayList<Card>(5);

    }
    public ArrayList<Relic> getOfferedRelics(){
        return offeredRelics;
    }
    public void setOfferedRelics(Relic[] newRelics){
           for(int i =0; i < newRelics.length; i++){
               offeredRelics.add(newRelics);
           }
    }
    public ArrayList<Potion> getOfferedPotions(){
        return offeredPotions;
    }
    public void setOfferedPotions(Potion[] newPotions){
        for(int i =0; i < newPotions.length; i++){
            offeredPotions.add(newPotions);
        }
    }
    public  ArrayList<Card> getOfferedCards(){
        return offeredCards;
    }
    public void setOfferedCards(Card[] newCards){
        for(int i =0; i < newCards.length; i++){
            offeredCards.add(newCards);
        }
    }

    public void buyPotion(Potion newPotion){
        for(int i =0; i < offeredPotions.size(); i++){
            if(offeredPotions[i]==newPotion){
                 offeredPotions.remove(i);

            }
        }

        //new potion will be added to player

    }

    public void buyRelic(Relic newRelic){
        for(int i =0; i < offeredRelics.size(); i++){
            if(offeredRelics[i]==newRelic){
                offeredRelics.remove(i);

            }
            //new relic will be added to player
        }
    }

    public void buyCard(Card newCard){
        for(int i =0; i < offeredCards.size(); i++){
            if(offeredCards[i]==newCard){
                offeredCards.remove(i);
            }
         //new card will be added to player
        }
    }

    public void costRemovalCard(Card card){
        static int cost =75;
        for(int i =0; i < offeredCards.size(); i++){
            if(offeredCards[i]==newCard){
                offeredCards.remove(i);
            }
            //new card will be added to player
        }
        cost += 25;
    }


}
