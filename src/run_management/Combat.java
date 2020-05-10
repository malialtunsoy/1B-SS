package com.RunMan;

import java.util.ArrayList;

public class Combat {
   ArrayList<Card> hand;
   int enemyHp;
   int block;
   int energy;

   public Combat(ArrayList<Card> hand, int enemyHp, int block,int energy){
       this.hand= hand;
       this.enemyHp = enemyHp;
       this.block = block;
       this.energy = energy;
   }
    public int getEnemyHp(){
        return enemyHp;
    }
    public void setEnemyHp(int newHp){
        enemyHp = newHp;
    }
    public int getBlock(){
        return block;
    }
    public void setBlock(int newBlock){
        block = newBlock;
    }



    //for the use of relic to add cards to hand in the beginning
    public void addToHand(Card[] newCards){
        for(int i =0; i < newCards.length; i++){
            hand.add(newCards[i]);
        }

    }

    //for the use of relic combat classes enemy hp decrease
    public void decreaseHpofEnemy(int newHp){
        enemyHp -= newHp;
    }

    //for the use of relic start combat with block
    public void gainBlock(int newBlock){
        block += newBlock;

    }

    //for the use of relic, gain energy in combat
    public void gainEnergy(int newEnergy){
        energy += newEnergy;
    }
}
