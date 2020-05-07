
public class AggressiveIntent extends Intent {
    // properties
    private int damage;
    
    // constructors
    public AggressiveIntent(int target, int damage) {
        super(target);
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
        
    }

}