public class Neutralize extends Card {
    // --- constants ---
    private static final int ENERGY_COST = 0;
    private static final int DAMAGE = 3;
    private static final int WEAK_APPLIED = 1;
    private static final String DESCRIPTION = "Deal " + DAMAGE + " damage. Apply " + WEAK_APPLIED + " Weak.";
    private static final boolean TARGET_REQUIREMENT = true;
    private static int COST = 200;
    // constructors
    public Neutralize() {
        super("Neutralize", "Attack", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT,COST);
    }

    public void affect(Enemy target) {
        CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, target);
        target.addStatusEffect( new Weak(WEAK_APPLIED));
    }
    public Card upgradedVersion() {
        return new NeutralizePlus();
    }
}
