package com.RunMan;

public class Treasure {
    private int offeredGold;
    private Relic offeredRelic;
    private Potion offeredPotion;

    public int getOfferedGold(){
        return offeredGold;
    }
    public void setOfferedGold(int newGold){
        offeredGold = newGold;
    }
    public Relic getOfferedRelic(){
        return offeredRelic;
    }
    public void setOfferedRelic(Relic newRelic){
        offeredRelic = newRelic;
    }
    public Potion getOfferedPotion(){
        return offeredPotion;
    }
    public void setOfferedPotion(Potion newPotion){
        offeredPotion = newPotion;
    }

    public int buyGold(int newGold){
        offeredGold=0;
        return gold;
    }
    public Relic buyRelic(Relic newRelic){
        offeredRelic = null;
        return newRelic;
    }
    public Potion buyRelic(Potion newPotion){
        offeredPotion = null;
        return newPotion;
    }
}
