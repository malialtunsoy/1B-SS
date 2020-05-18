public class Vigilance extends Card {
    private static final int BLOCK_AMOUNT = 8;
    private static final int ENERGY_COST = 2;
    private static final String DESCRIPTION = "Gain " + BLOCK_AMOUNT + " BLock. Enter Calm.";
    private static final int COST = 50;

    public Vigilance() {
        super("Vigilance", "Skill", ENERGY_COST, DESCRIPTION, false, COST);
    }

    @Override
    public void affect( Enemy target) {
        CombatManager.getInstance().getPlayer().addStatusEffect(new Block(BLOCK_AMOUNT));
        CombatManager.getInstance().getPlayer().addStatusEffect(new Calm());
    }

    @Override
    public Card upgradedVersion() {
        return null;
    }
}
