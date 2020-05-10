package com.RunMan;

public class Card {


        private String cardName;
        private String cardType;
        private int effect;
        private int energy;

        public Card(String cardName, String cardType, int energy, int effect){
            this.cardName = cardName;
            this.cardType = cardType;
            this.energy = energy;
            this.effect = effect;
        }
        public String getName(){
            return cardName;
        };
        public void setName(String newCardName){
            this.cardName = newCardName;
        }
        public String getCardType(){
            return cardType;
        }
        public void setCardType(String newCardType){
            this.cardType = newCardType;
        }
        public void setEnergy(int newEnergy ){
            this.energy = energy;
        }
        public int getEnergy(){
            return energy;
        }
        public void setEffect(int newEffect){
            this.effect = newEffect;
        }
        public int getEffect(){
            return effect;
        }
        public void upgrade(int index){
            index = index*2;
            setEffect(index);
        }
        public void affect(){

        }

}
