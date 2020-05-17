public class AnnoyingHedgehog extends Enemy {
    // --- constants ---
    private static final int MIN_HP = 40;
    private static final int MAX_HP = 50;

    private static final int THORNS_PER_TURN = 3;

    // --- constructors ---
    public AnnoyingHedgehog() {
        super("Annoying Hedgehog", MIN_HP, MAX_HP);
    }

    @Override
    public void declareIntent() {
        double [] probs = {CHOMP_PROB, THRASH_PROB, BELLOW_PROB};
        int choice = ProbUtilities.indexWithProb(probs);
        System.out.println("JawWorm choice: " + choice);
        if (CombatManager.getInstance().getTurn() == 0) {
            choice = 0; // always chomp at the first turn
        }
        if (choice == 0) {
            // chomp
            addIntent(new AggressiveIntent(this, CHOMP_DAMAGE));
        } else if (choice == 1) {
            // thrash
            addIntent(new DefensiveIntent(this, THRASH_BLOCK));
            addIntent(new AggressiveIntent(this, THRASH_DAMAGE));
        } else if (choice == 2) {
            // bellow
            addIntent(new BuffIntent(this, this, new Strength(BELLOW_STRENGTH_GAIN)));
            addIntent(new DefensiveIntent(this, BELLOW_BLOCK));
        }
    }

    @Override
    public void restoreExtraState(String[] extraParams) {} // no extra state
}
