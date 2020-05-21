import java.util.ArrayList;

public class Consecrate extends Card {
    // --- constants ---
    private static final int ENERGY_COST = 0;
    private static final int DAMAGE = 5;
    private static final String DESCRIPTION = "Deals " + DAMAGE + " damage to all enemies.";
    private static final boolean TARGET_REQUIREMENT = false;
    private static int COST = 200;
    // constructors
    public Consecrate() {
        super("Consecrate", "Attack", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT,COST);
    }

    public void affect(Enemy target) {
        // use shallow copy to avoid errors if the enemy dies and removes itself from the list of enemies in CombatManager
        ArrayList<Enemy> shallowCopy = new ArrayList<Enemy>(CombatManager.getInstance().getEnemies());
        for (int i = 0; i < shallowCopy.size(); i++) {
            Enemy e = shallowCopy.get(i);
            CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, e);
        }
    }
    public Card upgradedVersion() {
        return new ConsecratePlus();
    }
}
