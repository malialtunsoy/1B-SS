public class BurningBlood extends Relic {
    private static final int HP_RESTORED = 6;
    private static final String NAME = "Burning Blood";
    private static final String DESCRIPTION = "At the end of combat, heal " + HP_RESTORED + " HP";
    private static final String IMAGE = "BurningBloodRelic.png";

    private class Effect extends RelicEffect implements TriggeredAtCombatEnd {
        public void triggered() {
            CombatManager.getInstance().getPlayer().gainHP(HP_RESTORED);
        }
    }

    public BurningBlood() {
        super(NAME, 0, DESCRIPTION, IMAGE);
        setEffect(new Effect());
    }
}
