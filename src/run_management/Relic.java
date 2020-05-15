<<<<<<< HEAD

public abstract class Relic {
=======
public class Relic {
>>>>>>> 57867a0211314bc23ac7bc4941af8eaee6acd82d
    //attributes
    private String name,relicDescription;
    private int relicCost;
<<<<<<< HEAD
=======
    private String relicDescription;
    private RelicEffect effect;
>>>>>>> 57867a0211314bc23ac7bc4941af8eaee6acd82d

    //constructors
    public Relic(String name, int relicCost, String relicDescription){
        this.name = name;
        this.relicCost = relicCost;
        this.relicDescription = relicDescription;
    }
    //methods
    protected void setEffect( RelicEffect effect) {this.effect = effect;}
    public RelicEffect getEffect() {return effect;}
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
<<<<<<< HEAD
    public void setRelicCost(int relicCost){this.relicCost = relicCost;}
    public int getRelicCost(){return  relicCost;}
    public String toString(){return getName() + " "+ getRelicDescription();}
    abstract public void affect(Player P);
=======
>>>>>>> 57867a0211314bc23ac7bc4941af8eaee6acd82d
}

