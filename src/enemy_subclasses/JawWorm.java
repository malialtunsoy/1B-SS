public class JawWorm extends Enemy {
    // --- constants ---
    private static final int HP = 40;

    private static final int CHOMP_DAMAGE = 11;
    private static final double CHOMP_PROB = 0.25;

    private static final int THRASH_DAMAGE = 7;
    private static final int THRASH_BLOCK = 5;
    private static final double THRASH_PROB = 0.45;

    private static final int BELLOW_STRENGTH_GAIN = 3;
    private static final int BELLOW_BLOCK = 7;
    private static final double BELLOW_PROB = 0.45;

    // --- constructors ---
    public JawWorm() {
        super("JawWorm", HP);
    }

    @Override
    public void declareIntent() {
        double [] probs = {CHOMP_PROB, THRASH_PROB, BELLOW_PROB};
        int choice = ProbUtilities.indexWithProb(probs);
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
            addIntent(new BuffIntent(this, this, new Strength(BELLOW_STRENGTH_GAIN)));
        }
    }
}
