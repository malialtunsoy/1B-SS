public class Shiv extends Card {
    // --- constants ---
    private static final int ENERGY_COST = 0;
    private static final int DAMAGE = 4;
    private static final String DESCRIPTION = "Deals " + DAMAGE + " damage. Exhaust.";
    private static final boolean TARGET_REQUIREMENT = true;
    private static int COST = 140;
    // constructors
    public Shiv() {
        super("Shiv", "Attack", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT,COST);
        addExtraAttribute(ExtraCardAttribute.EXHAUST);
    }

    public void affect(Enemy target) {
        CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, target);
    }
    public Card upgradedVersion() {
        return null;
    }
}
