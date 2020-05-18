public class TwinStrikePlus extends Card {
    // --- constants ---
    private static final int ENERGY_COST = 1;
    private static final int DAMAGE = 7;
    private static final int NUM_HITS = 2;
    private static final String DESCRIPTION = "Deals " + DAMAGE + " damage twice";
    private static final boolean TARGET_REQUIREMENT = true;
    private static int COST = 340;
    // constructors
    public TwinStrikePlus() {
        super("Twin Strike+", "Attack", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT,COST);
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