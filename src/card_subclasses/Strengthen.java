public class Strengthen extends Card{
    private static final int ENERGY_COST = 1;
    private static final int STRENGTH_GAIN = 1;
    private static final boolean TARGET_REQUIREMENT = false;
    private static int COST = 120;

    public Strengthen() {
        super("Strengthen", "Skill", ENERGY_COST, "Gain " + STRENGTH_GAIN + " strength.", TARGET_REQUIREMENT, COST);
    }

    @Override
    public void affect(Enemy e) {
        // non-targeted card. parameter e is meaningless.
        CombatManager.getInstance().getPlayer().addStatusEffect(new Strength(STRENGTH_GAIN));
    }
    public Card upgradedVersion() {
        return new StrengthenPlus();
    }

}
