
public class AggressiveIntent extends Intent {
    // properties
    private int damage;
    
    // constructors
    public AggressiveIntent( Enemy e, int damage) {
        super(e, CombatManager.getInstance().getPlayer()) ;   // aggressive intents always target the player
        this.damage = damage;
    }

    // methods
    public int getDamage() {
        return damage;
    }

    public boolean setDamage(int damage) {
        if (damage < 0) {
            System.out.println("AggressiveIntent.setDamage called with invalid damage " + damage);
            return false;
        } else {
            this.damage = damage;
            return true;
        }
    }

    public void realize() {
        // deal the damage to the player
        intendingEnemy.dealDamage(damage, CombatManager.getInstance().getPlayer());
    }

    public String toString() {
        return "AggressiveIntent(" + damage + ")";
    }
}