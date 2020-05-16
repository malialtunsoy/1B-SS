public class Cultist extends Enemy {
    // --- constants ---
    private static final int HP = 50;
    private static final int DAMAGE = 2;
    private static final double RITUAL_PROB = 0.3;
    private static final int RITUAL_AMOUNT = 3;

    // --- constructors ---
    public Cultist() {
        super("Cultist", HP);
    }

    @Override
    public void declareIntent() {
        if (ProbUtilities.trueWithProb(RITUAL_PROB)) {
            addIntent(new BuffIntent(this, this, new Ritual(RITUAL_AMOUNT)));
        } else {
            addIntent(new AggressiveIntent(this, DAMAGE));
        }
    }
}
