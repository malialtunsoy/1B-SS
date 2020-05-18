public class TranquilityPlus extends Card {
    private static final int ENERGY_COST = 0;
    private static final String DESCRIPTION = "Retain. Enter Calm. Exhaust.";
    private static final int COST = 50;

    public TranquilityPlus() {
        super("Tranquility+", "Skill", ENERGY_COST, DESCRIPTION, false, COST);
        addExtraAttribute(ExtraCardAttribute.EXHAUST);
        addExtraAttribute(ExtraCardAttribute.RETAIN);
    }

    @Override
    public void affect( Enemy target) {
        CombatManager.getInstance().getPlayer().addStatusEffect(new Calm());
    }

    @Override
    public Card upgradedVersion() {
        return null;
    }
}
