public class Bash extends Card {
    // --- constants ---
    private static final int ENERGY_COST = 2;
    private static final int DAMAGE = 8;
    private static final int VULNERABLE_COUNTER = 2;
    private static final String DESCRIPTION = "Deals " + DAMAGE + " damage.\n Applies " + VULNERABLE_COUNTER + " Vulnerable." ;

    // --- constructors ---
    public Bash() {
        super("Bash", "Attack", ENERGY_COST, DESCRIPTION);
    }

    @Override
    public void affect(Enemy target) {
        CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, target);
        target.addStatusEffect(new Vulnerable(VULNERABLE_COUNTER));
    }
}
