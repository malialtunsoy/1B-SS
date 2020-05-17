/*
 * A concrete subclass of Card.
 * Used in tests within Combat Management right now.
 * author: Can Cebeci
 */

public class PummelPlus extends Card {
    // --- constants ---
    private static final int ENERGY_COST = 1;
    private static final int DAMAGE = 2;
    private static final int NUM_HITS = 5;
    private static final String DESCRIPTION = "Deals " + DAMAGE + " damage " + NUM_HITS + " times. Exhaust.";
    private static final boolean TARGET_REQUIREMENT = true;
    private static int COST = 500;
    // constructors
    public PummelPlus() {
        super("Pummel+", "Attack", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT,COST);
        addExtraAttribute(ExtraCardAttribute.EXHAUST);
    }

    public void affect(Enemy target) {
        boolean enemyAlive = true;
        for (int i = 0; i < NUM_HITS && enemyAlive; i++) {
            enemyAlive = CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, target);
        }
    }
    public Card upgradedVersion() {
        return null;
    }
}
