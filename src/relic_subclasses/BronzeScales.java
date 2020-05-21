public class BronzeScales extends Relic {
    private static final int THORNS_COUNTER = 3;
    private static final String NAME = "Bronze Scales";
    private static final int COST = 250;
    private static final String DESCRIPTION = "Start each combat with " + THORNS_COUNTER + " Thorns.";

    private class Effect extends RelicEffect implements TriggeredAtCombatStart {
        public void triggered(Object triggerSource) {
            CombatManager.getInstance().getPlayer().addStatusEffect(new Thorns(THORNS_COUNTER));
        }
    }

    public BronzeScales() {
        super(NAME, COST, DESCRIPTION);
        setEffect(new Effect());
    }
}
