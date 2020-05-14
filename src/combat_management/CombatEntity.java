import java.util.ArrayList;

// generalizes Player and Enemy
public class CombatEntity {
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
        if (currentHP < 0) {
            currentHP = 0;
            return false;
        }
        return true;
    }

    public void gainHP(int amount) {
        if (amount < 0) {
            System.out.println("gainHP called with negative amount " + amount);
            return;
        }
        System.out.println("hphp " + amount);

        currentHP += amount;
        if (currentHP > maxHP) {
            currentHP = maxHP;
        }
    }

    public int getHP() { return currentHP; }

    public int getMaxHP() {return maxHP;}

    public void addStatusEffect(StatusEffect se) {
        affectedBy.add(se);
    }

}