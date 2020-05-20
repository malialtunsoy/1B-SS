import java.util.ArrayList;

public abstract class Enemy extends CombatEntity{
    // --- attributes --- 
    String name;
    ArrayList<Intent> intents;

    // --- constructors ---
    public Enemy(String name, int maxHPLowerLimit, int maxHPUpperLimit) {
        super(RandomUtil.generateEnemyHP(maxHPLowerLimit, maxHPUpperLimit));
        this.name       = name;
        intents         = new ArrayList<Intent>();
    }

    // --- methods ---
    public String toString() {
        return name + "\n-------\n" +  super.toString() + "\n intents: " + intents;
    }

    abstract void declareIntent();

    public void addIntent(Intent i) { intents.add(i); }   // used when declaring intents

    public void realizeAllIntents() {
        for (Intent i : intents) {
            i.realize();
        }
        intents.clear();
    }

    public String getImage() {
        return getClass().getName() + ".png";
    }
    public ArrayList<Intent> getIntents() {
        return intents;
    }
    public String getName() {
        return name;
    }
    public void die() {
        CombatManager.getInstance().removeEnemy(this);
    }

    public void restoreExtraState(String[] extraParams) {}

    @Override
    public boolean dealDamage(int amount, CombatEntity target) { //Overritten for UI Animation Purposes
       if(amount > 0){
           CombatManager.getInstance().enemyAttackAnimation(this);
       }
       return super.dealDamage(amount, target);
    }
}