public class VigilancePlus extends Card {
    private static final int BLOCK_AMOUNT = 12;
    private static final int ENERGY_COST = 2;
    private static final String DESCRIPTION = "Gain " + BLOCK_AMOUNT + " BLock. Enter Calm.";
    private static final int COST = 50;

    public VigilancePlus() {
        super("Vigilance+", "Skill", ENERGY_COST, DESCRIPTION, false, COST);
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
