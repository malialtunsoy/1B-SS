public class CrescendoPlus extends Card {
    private static final int ENERGY_COST = 0;
    private static final String DESCRIPTION = "Retain. Enter Wrath. Exhaust.";
    private static final int COST = 50;

    public CrescendoPlus() {
        super("Crescendo+", "Skill", ENERGY_COST, DESCRIPTION, false, COST);
        addExtraAttribute(ExtraCardAttribute.EXHAUST);
        addExtraAttribute(ExtraCardAttribute.RETAIN);
    }

    @Override
    public void affect( Enemy target) {
        CombatManager.getInstance().getPlayer().addStatusEffect(new Wrath());
    }

    @Override
    public Card upgradedVersion() {
        return null;
    }
}
