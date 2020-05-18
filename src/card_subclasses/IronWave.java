/*
 * A concrete subclass of Card.
 * Used in tests within Combat Management right now.
 * author: Can Cebeci
 */

public class IronWave extends Card {
    // --- constants ---
    private static final int ENERGY_COST = 1;
    private static final int DAMAGE = 5;
    private static final int BLOCK_AMOUNT = 5;
    private static final String DESCRIPTION = "Gain " + BLOCK_AMOUNT + " Block. Deal " + DAMAGE + " damage." ;
    private static final boolean TARGET_REQUIREMENT = true;
    private static int COST = 140;
    // constructors
    public IronWave() {
        super("Iron Wave", "Attack", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT,COST);
    }

    public void affect(Enemy target) {
        CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, target);
        CombatManager.getInstance().getPlayer().addStatusEffect(new Block(BLOCK_AMOUNT));
    }
    public Card upgradedVersion() {
        return new IronWavePlus();
    }
}
