
public abstract class Relic {
    //attributes
    private String name,relicDescription;
    private int relicCost;

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
    public void setRelicDescription(String relicDescription){
        this.relicDescription = relicDescription;
    }
    public String getRelicDescription(){
        return relicDescription;
    }
    public void setRelicCost(int relicCost){this.relicCost = relicCost;}
    public int getRelicCost(){return  relicCost;}
    public String toString(){return getName() + " "+ getRelicDescription();}
    abstract public void affect(Player P);
}

