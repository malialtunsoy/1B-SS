import java.util.ArrayList;
public class Survivor extends Card implements MakesCardChoice {
    // --- constants ---
    private static final int ENERGY_COST = 1;
    private static final int BLOCK_AMOUNT = 8;
    private static final int DISCARD_AMOUNT = 1;
    private static final String DESCRIPTION = "Gain " + BLOCK_AMOUNT + " Block. Discard " + DISCARD_AMOUNT + " cards.";
    private static final boolean TARGET_REQUIREMENT = false;
    private static int COST = 100;
    private static String CHOOSE_PROMPT = "Choose a card to discard";

    // constructors
    public Survivor() {
        super("Survivor", "Skill", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT, COST);
    }

    public void affect(Enemy target) {
        ArrayList<Card> chooseFrom = new ArrayList<Card>(CombatManager.getInstance().getHand());
        chooseFrom.remove(this);
        CombatManager.getInstance().getPlayer().addStatusEffect(new Block(BLOCK_AMOUNT));
        CombatManager.getInstance().chooseCard( chooseFrom, CHOOSE_PROMPT,this);
    }

    @Override
    public void callback(Card c) {
        // discard c
        CombatManager.getInstance().getHand().remove(c);
        CombatManager.getInstance().getDiscardPile().add(c);
    }

    public Card upgradedVersion() {
        return new SurvivorPlus();
    }
}
