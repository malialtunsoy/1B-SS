public class PureWater extends Relic {
    private static final String NAME = "Pure Water";
    private static final int COST = 250;
    private static final String DESCRIPTION = "At the start of each combat, add a Miracle to your hand.";


    private class Effect extends RelicEffect implements TriggeredAtCombatStart {
        public void triggered(Object triggerSource) {
            CombatManager.getInstance().getHand().add(new Miracle());
        }
    }

    public PureWater() {
        super(NAME, COST, DESCRIPTION);
        setEffect(new Effect());
    }
}
