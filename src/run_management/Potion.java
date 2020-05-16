
 public abstract class Potion {

    //attributes
    String potionName,potionDescription, image;
    int potionCost;

    //constructors
    public Potion(String potionName, int potionCost, String potionDescription, String image) {
        this.potionName = potionName;
        this.potionCost = potionCost;
        this.potionDescription = potionDescription;
        this.image = image;
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

     public void setImage( String image ){this.image = image;}
     public String getImage(){return image;}

     public void setCost(int cost){potionCost = cost;}
     public int getCost(){return potionCost;}
}

class EmptyPotion extends Potion
{
    public EmptyPotion(String potionName, int potionCost, String potionDescription)
    {
        super(potionName,potionCost,potionDescription, "null");
    }

    public void affect(CombatEntity target) {}
}

class HealthPotion extends Potion {
    private static final String NAME = "Health Potion";
    private static final int COST = 250; // what does this parameter mean?
    private static final int RESTORE_PERCENT = 30;
    private static final String DESCRIPTION = "Restores %" + RESTORE_PERCENT + " of the player's maximum health";
    private static final String IMAGE = "BloodPotion.png";

    public HealthPotion() {
        super(NAME, COST, DESCRIPTION, IMAGE);
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
    private static final String IMAGE = "FirePotion.png";

    public DamagePotion() {
        super(NAME, COST, DESCRIPTION,IMAGE);
    }

    @Override
    public void affect( CombatEntity target) {
        target.takeDamage(DAMAGE); // damage not dealt by player, outgoing damage modifiers don't apply
    }
}
