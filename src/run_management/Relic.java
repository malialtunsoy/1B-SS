public class Relic {
    //attributes
    private String name;
    private int relicCost;
    private String relicDescription;
    private RelicEffect effect;

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
    public void setRelicDescription(){
        this.relicDescription = relicDescription;
    }
    public String getRelicDescription(){
        return relicDescription;
    }
}

