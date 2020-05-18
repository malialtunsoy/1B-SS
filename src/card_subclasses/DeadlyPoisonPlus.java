public class DeadlyPoisonPlus extends Card{
    // --- constants ---
    private static final int ENERGY_COST = 1;
    private static final int POISON_AMOUNT = 7;
    private static final String DESCRIPTION = "Apply " + POISON_AMOUNT + " Poison.";
    private static final boolean TARGET_REQUIREMENT = true;
    private static int COST = 100;

    // constructors
    public DeadlyPoisonPlus() {
        super("Deadly Poison+", "Skill", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT, COST);
    }

    public void affect(Enemy target) {
        target.addStatusEffect(new Poison(POISON_AMOUNT));
    }
    public Card upgradedVersion() {
        return null;
    }
}
