public class AngerPlus extends Card {
    private static final int DAMAGE = 8;
    private static final int ENERGY_COST = 0;
    private static final String DESCRIPTION = "Deal " + DAMAGE + " damage. Add a copy of this card to your discard pile.";
    private static final int COST = 70;

    public AngerPlus() {
        super("Anger+", "Attack", ENERGY_COST, DESCRIPTION, true, COST);
    }

    @Override
    public void affect( Enemy target) {
        CombatManager.getInstance().getPlayer().dealDamage(DAMAGE, target);
        CombatManager.getInstance().getDiscardPile().add(new AngerPlus());
    }

    @Override
    public Card upgradedVersion() {
        return null;
    }
}