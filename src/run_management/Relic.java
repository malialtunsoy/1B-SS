package com.RunMan;

public class Relic {
    public class Relic {
        //attributes
        private String name;
        private int relicCost;
        private String relicDescription;

        //constructors
        public Relic(String name, int relicCost, String relicDescription){
            this.name = name;
            this.relicCost = relicCost;
            this.relicDescription = relicDescription;
        }
        //methods
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }
        public void setRelicDescription(){
            this.relicDescription = relicDescription;
        }
        public String getRelicDescription(){
            return relicDescription;
        }
        public void affect(){

        }
    }
}
 class HPrelic extends Relic{
    int currentHP;
    Player p;
    public HPrelic(String name, int relicCost, String relicDescription , int currentHP,Player p){
        super(name, relicCost, relicDescription );
        this.currentHP = currentHP;
        this.p = p;
    }

    public void affect(){
        currentHP += currentHP;
        p.setHp(currentHP);
    }
}
  class cardRelic extends Relic{
    int cardNum; //number of cards to be added
     Player p;

     public cardRelic(String name, int relicCost, String relicDescription , int cardNum,Player p){
         super (name, relicCost, relicDescription );
         this. cardNum = cardNum;
         this.p = p;
     }

     public void affect(){

     }
 }

class dmgRelic extends Relic
{
    int dmg;
    public dmgRelic(String name, int relicCost, String relicDescription,int dmg)
    {
        super(name, relicCost, relicDescription);
        this.dmg = dmg;
    }
    @Override
    public void affect() {

    }
}
