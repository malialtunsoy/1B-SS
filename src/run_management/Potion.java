
 public abstract class Potion {

    //attributes
    String potionName,potionDescription;
    int potionCost;

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
    public abstract void affect();
}

class emptyPotion extends Potion
{
    public emptyPotion(String potionName, int potionCost, String potionDescription)
    {
        super(potionName,potionCost,potionDescription);
    }

    public void affect() {}
}

class HealthPotion extends Potion {
    private static final String NAME = "HealthPotion";
    private static final int COST = 0; // what does this parameter mean?
    private static final int RESTORE_PERCENT = 30;
    private static final String DESCRIPTION = "Restores %" + RESTORE_PERCENT + " of the player's maximum health";

    public HealthPotion() {
        super(NAME, COST, DESCRIPTION);
    }

    @Override
    public void affect() {
        // This part is changed by Can C. to adapt to the fact that there is no setHP method for CombatEntity
        // Here is the old piece of code:
        /*
        currentHp = currentHp + currentHp;
        p.setHP(currentHp);
        */
        // Here is the new suggested implementation (assuming we want the potion to double the currentHP):
        Player p = CombatManager.getInstance().getPlayer();
        p.gainHP(p.getHP() * RESTORE_PERCENT / 100 );
    }
}

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
}
