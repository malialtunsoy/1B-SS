public class Defend extends Card{
    // --- constants ---
    private static final int ENERGY_COST = 1;
    private static final int BLOCK_AMOUNT = 5;
    private static final String DESCRIPTION = "Grants " + BLOCK_AMOUNT + " block";
    private static final boolean TARGET_REQUIREMENT = false;
    private static int COST = 100;

    // constructors
    public Defend() {
        super("Defend", "Skill", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT, COST);
    }

    public void affect(Enemy target) {
        CombatManager.getInstance().getPlayer().addStatusEffect(new Block(BLOCK_AMOUNT));
        CombatManager.getInstance().playerDefenseAnimation(); //GUI purpose
    }
    public Card upgradedVersion() {
        return new DefendPlus();
    }
}
