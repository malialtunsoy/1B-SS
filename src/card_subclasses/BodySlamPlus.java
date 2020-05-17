import java.util.ArrayList;

public class BodySlamPlus extends Card {
    private static final int ENERGY_COST = 0;
    private static final String DESCRIPTION = "Deal damage equal to your Block";
    private static final int COST = 50;

    public BodySlamPlus() {
        super("Body Slam+", "Attack", ENERGY_COST, DESCRIPTION, true, COST);
    }

    @Override
    public void affect( Enemy target) {
        ArrayList<StatusEffect> playerSE = CombatManager.getInstance().getPlayer().getStatusEffects();
        StatusEffect block = null;
        for (StatusEffect se : playerSE) {
            if (se.getName().equals("Block")) {
                block = se;
            }
        }
        if (block != null) {
            CombatManager.getInstance().getPlayer().dealDamage(block.getCounter(), target);
        } else {
            CombatManager.getInstance().getPlayer().dealDamage(0, target); // may hit with damage modifiers.
        }
    }

    @Override
    public Card upgradedVersion() {
        return null;
    }
}
