package com.RunMan;


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

 class HPrelic extends Relic{
    int currentHP;
    Player p;
    public HPrelic(String name, int relicCost, String relicDescription , int currentHP,Player p){
        super(name, relicCost, relicDescription );
        this.currentHP = currentHP;
        this.p = p;
    }

    public void affect(){
        currentHP += p.getHp();
        p.setHp(currentHP);
    }
}
  class cardRelic extends Relic{
    int cardNum; //number of cards to be added
     Player p;
     Card[] cards;
     Combat combat;

     public cardRelic(String name, int relicCost, String relicDescription , int cardNum,Player p, Card[] cards, Combat combat){
         super (name, relicCost, relicDescription );
         this. cardNum = cardNum;
         this.p = p;
         this.cards = cards;
         this.combat = combat;
     }

     public void affect(){
         combat.addToHand(cards);//at the beginning of each combat new cards added to combat hand burası combata gidiyor combat sınıfını ekledim
     }
 }

class dmgRelic extends Relic {
    int dmg;
    Combat combat;


    public dmgRelic(String name, int relicCost, String relicDescription, int dmg,Combat combat) {
        super(name, relicCost, relicDescription);
        this.dmg = dmg;
        this.combat = combat;
    }

    @Override
    public void affect() {
        combat.decreaseHpofEnemy(dmg);

    }
}

class blockRelic extends Relic{
        int block;
        Combat combat;
        public blockRelic(String name, int relicCost, String relicDescription, int block, Combat combat){
            super(name,relicCost,relicDescription);
            this.block = block;
            this.combat = combat;
        }

    @Override
    public void affect() {
        combat.gainBlock(block);

    }
}

class energyRelic extends Relic{
        int energy;
        Combat combat;
        public energyRelic(String name, int relicCost, String relicDescription,int energy, Combat combat){
            super(name,relicCost,relicDescription);
            this.energy = energy;
            this.combat = combat;
        }

    @Override
    public void affect() {
        combat.gainEnergy(energy);

    }

}
