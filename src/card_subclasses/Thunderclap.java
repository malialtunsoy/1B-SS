import java.util.ArrayList;

public class Thunderclap extends Card {
    // --- constants ---
    private static final int ENERGY_COST = 1;
    private static final int DAMAGE = 4;
    private static final int VULNERABLE_APPLIED = 1;
    private static final String DESCRIPTION = "Deals " + DAMAGE + " damage and apply " + VULNERABLE_APPLIED + " to all enemies.";
    private static final boolean TARGET_REQUIREMENT = false;
    private static int COST = 200;
    // constructors
    public Thunderclap() {
        super("Thunderclap", "Attack", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT,COST);
    }

    public void affect(Enemy target) {
        ArrayList<Enemy> shallowCopy = new ArrayList<Enemy>(CombatManager.getInstance().getEnemies());
        for (int i = 0; i < shallowCopy.size(); i++) {
            Enemy e = shallowCopy.get(i);
            CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, e);
            e.addStatusEffect( new Vulnerable(VULNERABLE_APPLIED));
        }
    }
    public Card upgradedVersion() {
        return new ThunderclapPlus();
    }
}
