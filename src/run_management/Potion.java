
 public class Potion {

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
    public void affect()
    {}
}

class emptyPotion extends Potion
{
    public emptyPotion(String potionName, int potionCost, String potionDescription)
    {
        super(potionName,potionCost,potionDescription);
    }

}

class hpPotion extends Potion
{
    int currentHp;
    Player p;
    public hpPotion(String potionName, int potionCost, String potionDescription,int currentHp,Player p)
    {
        super(potionName, potionCost, potionDescription);
        this.currentHp = currentHp;
        this.p = p;
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
        p.gainHP(p.getHP());
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
