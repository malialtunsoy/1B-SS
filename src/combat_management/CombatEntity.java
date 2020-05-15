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

    // makes all status effects on this entity decay polymorphically.
    // called at the start of each turn.
    public void decayAllAffected( boolean isTurnStart) {
        // if any SE runs out, it will remove itself from affectedBy but not from the shallow copy.
        ArrayList<StatusEffect> shallowCopy = new ArrayList<StatusEffect>(affectedBy);

        for (int i = 0; i < shallowCopy.size(); i++) {
            StatusEffect se = affectedBy.get(i);
            if (isTurnStart == se.decayAtTurnStart()) {
                se.decay();
            }
        }
    }

    // a shell used to incorporate a class of status effects (IncomingDamageModifier)
    public void takeDamage(int amount) {
        int modifiedAmount = invokeAllModifiers(IncomingDamageModifier.class, amount);
        loseHP(modifiedAmount);
    }

    // modify amount with all status effects in affected by that implement cls
    // example call:  invokeAll(IncomingDamageModifier.class, 20)
    // declared public since it has to be invoked in CombatManager's draw
    public  <T extends Modifier> int invokeAllModifiers(Class<T> cls, int amount) {
        // if any SE runs out, it will remove itself from affectedBy but not from the shallow copy.
        ArrayList<StatusEffect> shallowCopy = new ArrayList<StatusEffect>(affectedBy);

        // for whatever reason, we get a concurrency related error if a foreach loop is used
        // this is a terrible implementation, though it should work for now.
        for (int i = 0; i < shallowCopy.size(); i++) {
            StatusEffect se = shallowCopy.get(i);
            if (cls.isAssignableFrom(se.getClass())) {
                amount = ((T) se).modify(amount);
            }
        }
        return amount;
    }

    public void dealDamage(int amount, CombatEntity target) {
        int modifiedAmount = invokeAllModifiers(OutgoingDamageModifier.class, amount);
        target.takeDamage(modifiedAmount);
    }

    public String toString() {
        return "HP: " + getHP() + "/" + getMaxHP() +
                "\nStatus Effects: " + affectedBy;
    }

    public ArrayList<StatusEffect> getStatusEffects(){
        return affectedBy;
    }

    abstract void die();
}