public class Miracle extends Card {
    private static final int ENERGY_COST = 0;
    private static final int ENERGY_GAIN = 1;
    private static final String DESCRIPTION = "Retain. Gain 1 energy. Exhaust.";
    private static final int COST = 50;

    public Miracle() {
        super("Miracle", "Skill", ENERGY_COST, DESCRIPTION, false, COST);
        addExtraAttribute(ExtraCardAttribute.EXHAUST);
        addExtraAttribute(ExtraCardAttribute.RETAIN);
    }

    @Override
    public void affect( Enemy target) {
        CombatManager.getInstance().gainEnergy(ENERGY_GAIN);
    }

    @Override
    public Card upgradedVersion() {
        return null;
    }
}
