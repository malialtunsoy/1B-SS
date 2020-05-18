public class BladeDancePlus extends Card {
    private static final int NUM_SHIVS = 3;
    private static final int ENERGY_COST = 1;
    private static final String DESCRIPTION = "Add " + NUM_SHIVS + " Shivs into your hand";
    private static final int COST = 50;

    public BladeDancePlus() {
        super("Blade Dance+", "Skill", ENERGY_COST, DESCRIPTION, false, COST);
    }

    @Override
    public void affect( Enemy target) {
        for (int i = 0; i < NUM_SHIVS; i++) {
            CombatManager.getInstance().getHand().add(new Shiv());
        }
    }

    @Override
    public Card upgradedVersion() {
        return null;
    }
}
