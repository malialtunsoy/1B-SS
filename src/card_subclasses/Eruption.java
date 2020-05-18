public class Eruption extends Card {
    private static final int DAMAGE = 9;
    private static final int ENERGY_COST = 2;
    private static final String DESCRIPTION = "Deal " + DAMAGE + " damage. Enter Wrath.";
    private static final int COST = 50;

    public Eruption() {
        super("Eruption", "Attack", ENERGY_COST, DESCRIPTION, true, COST);
    }

    @Override
    public void affect( Enemy target) {
        CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, target);
        CombatManager.getInstance().getPlayer().addStatusEffect(new Wrath());
    }

    @Override
    public Card upgradedVersion() {
        return null;
    }
}
