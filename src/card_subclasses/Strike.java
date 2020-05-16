/*
 * A concrete subclass of Card.
 * Used in tests within Combat Management right now.
 * author: Can Cebeci
 */

public class Strike extends Card {
    // --- constants ---
    private static final int ENERGY_COST = 1;
    private static final int DAMAGE = 6;
    private static final String DESCRIPTION = "Deals " + DAMAGE + " damage";
    private static final boolean TARGET_REQUIREMENT = true;
    private static final boolean IS_UPGRADED = false;
    // constructors
    public Strike() {
        super("Strike", "Attack", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT,IS_UPGRADED);
    }

    public void affect(Enemy target) {
        CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, target);
    }
    public Card upgradedVersion()
    {
        if( IS_UPGRADED == false)
        {
            Card upgVer = new StrikePlus();
            return upgVer;
        }
        return null;
    }
}
