
 public abstract class Potion {

    //attributes
    private String potionName,potionDescription;
    private int potionCost;

    //constructors
    public Potion(String potionName, int potionCost, String potionDescription) {
        this.potionName = potionName;
        this.potionCost = potionCost;
        this.potionDescription = potionDescription;
    }

    //methods
    public void setName(String newName)
    {
        this.potionName = newName;
    }
    public String getName()
    {
        return potionName;
    }
    public String getPotionDescription(){
        return potionDescription;
    }
    public void setPotionDescription() {
        this.potionDescription = potionDescription;
    }
    public int getPotionCost(){return potionCost;}
    public void setPotionCost(int potionCost){this.potionCost = potionCost;}
    abstract public void affect(Player p);
    public String toString() {return potionName + "  " +potionDescription;}
}

/*
public class emptyPotion extends Potion
{
    public emptyPotion(String potionName, int potionCost, String potionDescription)
    {
        super(potionName,potionCost,potionDescription);
    }

}*/


/*
class dmgPotion extends Potion
{
    int dmg;
    public dmgPotion(String potionName, int potionCost, String potionDescription,int dmg)
    {
        super(potionName, potionCost, potionDescription);
        this.dmg = dmg;
    }
    @Override
    public void affect() {

    }
}*/