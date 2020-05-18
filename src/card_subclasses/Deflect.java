public class Deflect extends Card{
    // --- constants ---
    private static final int ENERGY_COST = 0;
    private static final int BLOCK_AMOUNT = 4;
    private static final String DESCRIPTION = "Gain " + BLOCK_AMOUNT + " block";
    private static final boolean TARGET_REQUIREMENT = false;
    private static int COST = 100;

    // constructors
    public Deflect() {
        super("Deflect", "Skill", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT, COST);
    }

    public void affect(Enemy target) {
        CombatManager.getInstance().getPlayer().addStatusEffect(new Block(BLOCK_AMOUNT));
    }
    public Card upgradedVersion() {
        return new DeflectPlus();
    }
}
