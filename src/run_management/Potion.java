
 public abstract class Potion {

    //attributes
    String potionName,potionDescription, image;
    int potionCost;
    boolean targetRequirement;
    //constructors
    public Potion(String potionName, int potionCost, String potionDescription, String image, boolean targetRequirement) {
        this.potionName = potionName;
        this.potionCost = potionCost;
        this.potionDescription = potionDescription;
        this.image = image;
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
     public void setImage( String image ){this.image = image;}
     public String getImage(){return getClass().getName() + ".png";}

     public void setCost(int cost){potionCost = cost;}
     public int getCost(){return potionCost;}
     public String toString(){return getPotionDescription();}
}


class HealthPotion extends Potion {
    private static final String NAME = "Health Potion";
    private static final int COST = 250; // what does this parameter mean?
    private static final int RESTORE_PERCENT = 30;
    private static final String DESCRIPTION = "Restores %" + RESTORE_PERCENT + " of the player's maximum health";
    private static final boolean TARGET_REQUIREMENT = false;
    private static final String IMAGE = "HealthPotion.png";

    public HealthPotion() {
        super(NAME, COST, DESCRIPTION, IMAGE, TARGET_REQUIREMENT);
    }

    @Override
    public void affect(CombatEntity target) {
        Player p = CombatManager.getInstance().getPlayer();
        p.gainHP(p.getMaxHP() * RESTORE_PERCENT / 100 );
    }
}

class DamagePotion extends Potion {
    private static final String NAME = "Damage Potion";
    private static final int COST = 115; // what does this parameter mean?
    private static final int DAMAGE = 35;
    private static final String DESCRIPTION = "Deal " + DAMAGE + " damage to the target";
    private static final boolean TARGET_REQUIREMENT = true;
    private static final String IMAGE = "DamagePotion.png";
    
    public DamagePotion() {
        super(NAME, COST, DESCRIPTION, IMAGE, TARGET_REQUIREMENT);
    }

    @Override
    public void affect( CombatEntity target) {
        target.takeDamage(DAMAGE); // damage not dealt by player, outgoing damage modifiers don't apply
    }
}
