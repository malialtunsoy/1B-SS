public class BronzeScales extends Relic {
    private static final int THORNS_COUNTER = 3;
    private static final String NAME = "Bronze Scales";
    private static final int COST = 250;
    private static final String DESCRIPTION = "Start each combat with " + THORNS_COUNTER + " Thorns.";
    private static final String IMAGE = "BronzeScales.png";

    private class Effect extends RelicEffect implements TriggeredOnDamageTake {
        private Thorns delegatedTo = new Thorns(THORNS_COUNTER);
        public void triggered(Object triggerSource) {
            delegatedTo.triggered(triggerSource);
        }
    }

    public BronzeScales() {
        super(NAME, COST, DESCRIPTION, IMAGE);
        setEffect(new Effect());
    }
}
