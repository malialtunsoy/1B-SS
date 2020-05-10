package com.company;

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

