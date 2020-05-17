import java.util.ArrayList;

public class Clash extends Card{
    // --- constants ---
    private static final int ENERGY_COST = 0;
    private static final int DAMAGE = 14;
    private static final String DESCRIPTION = "Can only be played if every card in your hand is an Attack. Deal " + DAMAGE + " damage.";
    private static final boolean TARGET_REQUIREMENT = true;
    private static int COST = 150;
    // constructors
    public Clash() {
        super("Clash", "Attack", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT,COST);
    }

    public void affect(Enemy target) {
        // check if all cards in hand are an Attack
        ArrayList<Card> hand = CombatManager.getInstance().getHand();
        boolean allAttack = true;
        for ( Card c: hand) {
            if (!c.getCardType().equals("Attack")) {
                allAttack = false;
            }
        }
        if (allAttack) {
            CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, target);
        } else {
            // unplayable cards are very uncommon. Not worth generalizing the behaviour. Just re-add the card before discarding.
            hand.add(this);
        }

    }
    public Card upgradedVersion() {
        return new ClashPlus();
    }
}
