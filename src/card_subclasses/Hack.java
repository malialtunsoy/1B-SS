public class Hack extends Card {
    private static final int DAMAGE = 999;
    private static final int ENERGY_COST = 0;
    private static final String DESCRIPTION = "Deal " + DAMAGE + " damage.";
    private static final int COST = 0;

    public Hack() {
        super("Hack", "Attack", ENERGY_COST, DESCRIPTION, true, COST);
    }

    @Override
    public void affect( Enemy target) {
        CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, target);
    }

    @Override
    public Card upgradedVersion() {
        return null;
    }
}
