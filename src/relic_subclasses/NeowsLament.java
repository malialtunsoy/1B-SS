public class NeowsLament extends Relic {
    private static final int HP_RESTORED = 1;
    private static final String NAME = "Neow's Lament";
    private static final int COST = 0;
    private static final String DESCRIPTION = "Enemies in your next three combats will have " + HP_RESTORED + " HP";
    private static final String IMAGE = " ";


    private class Effect extends RelicEffect implements TriggeredAtCombatEnd {
        public void triggered() {
                //will be updated
            }
    }

    public NeowsLament() {
        super(NAME, COST, DESCRIPTION, IMAGE);
        setEffect(new Effect());
    }
    }