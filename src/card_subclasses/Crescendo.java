public class Crescendo extends Card {
    private static final int ENERGY_COST = 1;
    private static final String DESCRIPTION = "Retain. Enter Wrath. Exhaust.";
    private static final int COST = 50;

    public Crescendo() {
        super("Crescendo", "Skill", ENERGY_COST, DESCRIPTION, false, COST);
        addExtraAttribute(ExtraCardAttribute.EXHAUST);
        addExtraAttribute(ExtraCardAttribute.RETAIN);
    }

    @Override
    public void affect( Enemy target) {
        CombatManager.getInstance().getPlayer().addStatusEffect(new Wrath());
    }

    @Override
    public Card upgradedVersion() {
        return new CrescendoPlus();
    }
}
