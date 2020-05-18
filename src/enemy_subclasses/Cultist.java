public class Cultist extends Enemy {
    // --- constants ---
    private static final int MIN_HP = 48;
    private static final int MAX_HP = 54;
    private static final int DAMAGE = 2;
    private static final double RITUAL_PROB = 0.3;
    private static final int RITUAL_AMOUNT = 3;

    // --- constructors ---
    public Cultist() {
        super("Cultist", MIN_HP, MAX_HP);
    }

    @Override
    public void declareIntent() {
        if (RandomUtil.trueWithProb(RITUAL_PROB)) {
            addIntent(new BuffIntent(this, this, new StatusEffect[]{new Ritual(RITUAL_AMOUNT)}));
        } else {
            addIntent(new AggressiveIntent(this, DAMAGE));
        }
    }

}
