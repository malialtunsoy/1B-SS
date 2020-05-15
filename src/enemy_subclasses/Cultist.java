public class Cultist extends Enemy {
    // --- constants ---
    private static final int HP = 50;
    private static final int DAMAGE = 2;
    private static final double INCANTATION_PROB = 0.3;

    // --- constructors ---
    public Cultist() {
        super("Cultist", HP);
    }

    @Override
    public void declareIntent() {
        if (STSUtilities.trueWithProb(INCANTATION_PROB)) {
            addIntent(new StrategicIntent(this, this, new Block(20)));
        } else {
            addIntent(new AggressiveIntent(this, DAMAGE));
        }
    }
}
