public class PoisonedStabPlus extends Card{
    // --- constants ---
    private static final int ENERGY_COST = 1;
    private static final int DAMAGE = 8;
    private static final int POISON_AMOUNT = 4;
    private static final String DESCRIPTION = "Deal " + DAMAGE + " damage. " + "Apply " + POISON_AMOUNT + " Poison.";
    private static final boolean TARGET_REQUIREMENT = true;
    private static int COST = 100;

    // constructors
    public PoisonedStabPlus() {
        super("Poisoned Stab+", "Attack", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT, COST);
    }

    public void affect(Enemy target) {
        CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, target);
        target.addStatusEffect(new Poison(POISON_AMOUNT));
    }
    public Card upgradedVersion() {
        return null;
    }
}
