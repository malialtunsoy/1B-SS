/*
 * A concrete subclass of Card.
 * Used in tests within Combat Management right now.
 * author: Can Cebeci
 */

public class Strike extends Card {
    // --- constants ---
    private static final int ENERGY_COST = 1;
    private static final int DAMAGE = 6;
    private static final String DESCRIPTION = "Deals " + DAMAGE + " damage";
    private static final boolean TARGET_REQUIREMENT = true;
    // constructors
    public Strike() {
        super("Strike", "Attack", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT);
    }

    public void affect(Enemy target) {
        CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, target);
    }
}
