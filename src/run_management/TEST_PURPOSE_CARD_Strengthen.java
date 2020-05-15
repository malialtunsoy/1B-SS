public class TEST_PURPOSE_CARD_Strengthen extends Card{

    public TEST_PURPOSE_CARD_Strengthen() {
        super("Strengthen", "Skill", 1, 0, "get 1 strength");
    }

    @Override
    public void affect(Enemy e) {
        // non-targeted card. parameter e is meaningless.
        CombatManager.getInstance().getPlayer().addStatusEffect(new Strength(1));
    }

}
