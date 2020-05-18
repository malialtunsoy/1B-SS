public class CloakAndDaggerPlus extends Card {
    private static final int NUM_SHIVS = 2;
    private static final int BLOCK_AMOUNT = 2;
    private static final int ENERGY_COST = 1;
    private static final String DESCRIPTION = "Gain " + BLOCK_AMOUNT + " Block. Add " + NUM_SHIVS + " Shivs into your hand";
    private static final int COST = 50;

    public CloakAndDaggerPlus() {
        super("Cloak and Dagger+", "Skill", ENERGY_COST, DESCRIPTION, false, COST);
    }

    @Override
    public void affect( Enemy target) {
        CombatManager.getInstance().getPlayer().addStatusEffect(new Block(BLOCK_AMOUNT));
        for (int i = 0; i < NUM_SHIVS; i++) {
            CombatManager.getInstance().getHand().add(new Shiv());
        }
    }

    @Override
    public Card upgradedVersion() {
        return null;
    }
}
