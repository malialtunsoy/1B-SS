public class RingOfTheSnake extends Relic {
    private static final int NUM_EXTRA_DRAWS = 2;
    private static final String NAME = "Ring of the Snake";
    private static final int COST = 260;
    private static final String DESCRIPTION = "Draw " + NUM_EXTRA_DRAWS + " cards at the start of each combat";
    private static final String IMAGE = "RingoftheSnake.png";

    private class Effect extends RelicEffect implements CardDrawModifier {
        public int modify(int amount) {
            if (CombatManager.getInstance().getTurn() == 0) {
                return amount + NUM_EXTRA_DRAWS;
            } else {
                return amount;
            }
        }
    }

    public RingOfTheSnake() {
        super(NAME, COST, DESCRIPTION, IMAGE);
        setEffect(new Effect());
    }
}
