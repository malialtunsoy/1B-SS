public class TEST_PURPOSE_CARD_Strengthen extends Card{
    private static final int ENERGY_COST = 1;
    private static final int STRENGTH_GAIN = 1;

    public TEST_PURPOSE_CARD_Strengthen() {
        super("Strengthen", "Skill", ENERGY_COST, "Gain " + STRENGTH_GAIN + " strength.");
    }

    @Override
    public void affect(Enemy e) {
        // non-targeted card. parameter e is meaningless.
        CombatManager.getInstance().getPlayer().addStatusEffect(new Strength(STRENGTH_GAIN));
    }

}
