
 public abstract class Potion {

    //attributes
    String potionName,potionDescription;
    int potionCost;
    boolean targetRequirement;
    //constructors
    public Potion(String potionName, int potionCost, String potionDescription, boolean targetRequirement) {
        this.potionName = potionName;
        this.potionCost = potionCost;
        this.potionDescription = potionDescription;
        this.targetRequirement = targetRequirement;
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

    public abstract void affect( CombatEntity target); // parameter unused in non-targeted potions
     public int getPotionCost(){return potionCost;}
     public boolean getTargetRequirement() { return targetRequirement;}
     public String getImage(){return getClass().getName() + ".png";}

     public void setCost(int cost){potionCost = cost;}
     public int getCost(){return potionCost;}
     public String toString(){return getPotionDescription();}
}
