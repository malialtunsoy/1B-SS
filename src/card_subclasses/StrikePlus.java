/*
 * A concrete subclass of Card.
 * Used in tests within Combat Management right now.
 * author: Can Cebeci
 */

public class StrikePlus extends Card {
    // --- constants ---
    private static final int ENERGY_COST = 1;
    private static final int DAMAGE = 8;
    private static final String DESCRIPTION = "Deals " + DAMAGE + " damage";
    private static final int COST = -1;

    // constructors
    public StrikePlus() {
        super("Strike+", "Attack", ENERGY_COST, DESCRIPTION, COST);
    }

    public void affect(Enemy target) {
        CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, target);
    }
}
