public class DaggerSpray extends Card {
    // --- constants ---
    private static final int ENERGY_COST = 1;
    private static final int DAMAGE = 4;
    private static final int NUM_HITS = 2;
    private static final String DESCRIPTION = "Deals " + DAMAGE + " damage to ALL enemies twice.";
    private static final boolean TARGET_REQUIREMENT = false;
    private static int COST = 340;
    // constructors
    public DaggerSpray() {
        super("Dagger Spray", "Attack", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT,COST);
    }

    public void affect(Enemy target) {
        for (Enemy e: CombatManager.getInstance().getEnemies()) {
            for (int i = 0; i < NUM_HITS; i++) {
                CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, e);
            }
        }
    }
    public Card upgradedVersion() {
        return new DaggerSprayPlus();
    }
}