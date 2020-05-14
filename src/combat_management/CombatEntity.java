import java.util.ArrayList;

// generalizes Player and Enemy
public abstract class CombatEntity {
    // --- attributes ---
    private int maxHP;
    private int currentHP;
    private ArrayList<StatusEffect> affectedBy;

    // --- constructors ---
    public CombatEntity(int maxHP) {
        this.maxHP = maxHP;
        currentHP = maxHP;  // always created at full hp.
        affectedBy = new ArrayList<StatusEffect>();
    }

    // --- methods --- 
    // returns false if entity dies
    public boolean loseHP(int amount) {
        if (amount < 0) {
            System.out.println("loseHP called with negative amount " + amount);
            return false;
        }
        currentHP -= amount;
        if (currentHP <= 0) {
            die();
            return false;
        }
        return true;
    }

    public void gainHP(int amount) {
        if (amount < 0) {
            System.out.println("gainHP called with negative amount " + amount);
            return;
        }
        currentHP += amount;
        if (currentHP > maxHP) {
            currentHP = maxHP;
        }
    }

    public int getHP() { return currentHP; }

    public int getMaxHP() {return maxHP;}

    public void addStatusEffect(StatusEffect newEffect) {
        // see if an effect of the same sort is already applied
        StatusEffect sameEffect = null;
        for ( StatusEffect existingEffect: affectedBy) {
            if (existingEffect.getName().equals(newEffect.getName())) {
                sameEffect = existingEffect;
            }
        }

        if (sameEffect == null) {
            // add the effect
            newEffect.setAffectee(this);
            affectedBy.add(newEffect);
        } else {
            // stack the counter of the new effect to the existing one
            sameEffect.stackCounter(newEffect);
        }
    }
    public void removeStatusEffect(StatusEffect se) {
        affectedBy.remove(se);
    }

    // a shell used to incorporate a class of status effects (IncomingDamageModifier)
    public void takeDamage(int amount) {
        // for whatever reason, we get a concurrency related error if a foreach loop is used
        // this is a terrible implementation, though it should work for now.
        for (int i = 0; i < affectedBy.size(); i++) {
            StatusEffect se = affectedBy.get(i);
            if (se instanceof IncomingDamageModifier) {
                int sizeBeforeMod = affectedBy.size();
                amount = ((IncomingDamageModifier) se).modify(amount);
                if (affectedBy.size() < sizeBeforeMod) {    // see if the effect has removed itself.
                    i--;
                }
            }
        }
        loseHP(amount);
    }

    public void dealDamage(int amount, CombatEntity target) {
        target.takeDamage(amount);
    }

    public String toString() {
        return "HP: " + getHP() + "/" + getMaxHP() +
                "\nStatus Effects: " + affectedBy;
    }

    abstract void die();
}