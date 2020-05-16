public class Bash extends Card {
    // --- constants ---
    private static final int ENERGY_COST = 2;
    private static final int DAMAGE = 8;
    private static final int VULNERABLE_COUNTER = 2;
    private static final String DESCRIPTION = "Deals " + DAMAGE + " damage.\n Applies " + VULNERABLE_COUNTER + " Vulnerable." ;
    private static final boolean TARGET_REQUIREMENT = true;
    private static final boolean IS_UPGRADED = true;
    private static int COST = 160;

    // --- constructors ---
    public Bash() {
        super("Bash", "Attack", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT, IS_UPGRADED, COST);
    }

    @Override
    public void affect(Enemy target) {
        CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, target);
        target.addStatusEffect(new Vulnerable(VULNERABLE_COUNTER));
    }

    public Card upgradedVersion()
    {
        Card upgVer = null;
        if( IS_UPGRADED == false)
        {
        upgVer = new Bash();//upgraded version needed
        }
        return upgVer;
    }
}
