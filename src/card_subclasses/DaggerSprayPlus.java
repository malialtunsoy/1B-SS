import java.util.ArrayList;

public class DaggerSprayPlus extends Card {
    // --- constants ---
    private static final int ENERGY_COST = 1;
    private static final int DAMAGE = 6;
    private static final int NUM_HITS = 2;
    private static final String DESCRIPTION = "Deals " + DAMAGE + " damage to ALL enemies twice.";
    private static final boolean TARGET_REQUIREMENT = false;
    private static int COST = 340;
    // constructors
    public DaggerSprayPlus() {
        super("Dagger Spray+", "Attack", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT,COST);
    }

    public void affect(Enemy target) {
        // use shallow copy to avoid errors if the enemy dies and removes itself from the list of enemies in CombatManager
        ArrayList<Enemy> shallowCopy = new ArrayList<Enemy>(CombatManager.getInstance().getEnemies());
        for (int i = 0; i < shallowCopy.size(); i++) {
            Enemy e = shallowCopy.get(i);
            for (int j = 0; j < NUM_HITS; j++) {
                CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, e);
            }
        }
    }
    public Card upgradedVersion() {
        return null;
    }
}