public class BowlingBash extends Card {
    // --- constants ---
    private static final int ENERGY_COST = 1;
    private static final int DAMAGE = 7;
    private static final String DESCRIPTION = "Deals " + DAMAGE + " damage for each enemy in combat.";
    private static final boolean TARGET_REQUIREMENT = true;
    private static int COST = 200;
    // constructors
    public BowlingBash() {
        super("Strike", "Attack", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT,COST);
    }

    public void affect(Enemy target) {
        int enemyCount = 0;
        for (Enemy e: CombatManager.getInstance().getEnemies()) {
            enemyCount++;
        }
        CombatManager.getInstance().getPlayer().dealDamage(DAMAGE*enemyCount, target);
    }
    public Card upgradedVersion() {
        return new BowlingBashPlus();
    }
}