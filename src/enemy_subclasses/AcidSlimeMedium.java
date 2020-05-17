public class AcidSlimeMedium extends Enemy {
    // --- constants ---
    private static final int MIN_HP = 28;
    private static final int MAX_HP = 32;

    private static final double CORROSIVE_SPIT_PROB = 0.3;
    private static final int CORROSIVE_SPIT_DAMAGE = 7;

    private static final double LICK_PROB = 0.3;
    private static final int LICK_WEAK = 1;

    private static final double TACKLE_PROB = 0.4;
    private static final int TACKLE_DAMAGE = 1;

    // --- constructors ---
    public AcidSlimeMedium() {
        super("Acid Slime", MIN_HP, MAX_HP);
    }

    @Override
    public void declareIntent() {
        double [] probs = {CORROSIVE_SPIT_PROB, LICK_PROB, TACKLE_PROB};
        int choice = RandomUtil.indexWithProb(probs);
        if (choice == 0) {
            // corrosive spit
            addIntent(new HiddenIntent(this, CombatManager.getInstance().getPlayer()) {
                @Override
                public void realize() {
                    CombatManager.getInstance().getHand().add(new Slimed());
                }
            });
            addIntent(new AggressiveIntent(this, CORROSIVE_SPIT_DAMAGE));
        } else if (choice == 1) {
            // lick
            addIntent(new StrategicIntent(this, new Weak(LICK_WEAK)));
        } else if (choice == 2) {
            // tackle
            addIntent(new AggressiveIntent(this, TACKLE_DAMAGE));
        }
    }

}
