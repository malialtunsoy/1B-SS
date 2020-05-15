public class Defend extends Card{
    // --- constants ---
    private static final int ENERGY_COST = 1;
    private static final int BLOCK_AMOUNT = 5;
    private static final String DESCRIPTION = "Grants " + BLOCK_AMOUNT + " block";
    private static final boolean REQUIRES_TARGET = false;
    // constructors
    public Defend() {
        super("Defend", "Skill", ENERGY_COST, 0, DESCRIPTION, REQUIRES_TARGET);
    }

    public void affect(Enemy target) {
        CombatManager.getInstance().getPlayer().addStatusEffect(new Block(BLOCK_AMOUNT));
    }
}
