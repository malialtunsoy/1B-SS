public class BurningBlood extends Relic {
    private static final int HP_RESTORED = 6;
    private static final String NAME = "Burning Blood";
    private static final int COST = 250;
    private static final String DESCRIPTION = "At the end of combat, heal " + HP_RESTORED + " HP";

    private class Effect extends RelicEffect implements TriggeredAtCombatEnd {
        public void triggered(Object triggerSource) {
            CombatManager.getInstance().getPlayer().gainHP(HP_RESTORED);
        }
    }

    public BurningBlood() {
        super(NAME, COST, DESCRIPTION);
        setEffect(new Effect());
    }
}
