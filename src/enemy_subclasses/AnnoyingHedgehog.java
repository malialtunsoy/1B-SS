public class AnnoyingHedgehog extends Enemy {
    // --- constants ---
    private static final int MIN_HP = 70;
    private static final int MAX_HP = 80;

    private static final int THORNS_PER_TURN = 3;

    // --- constructors ---
    public AnnoyingHedgehog() {
        super("Annoying Hedgehog", MIN_HP, MAX_HP);
    }

    @Override
    public void declareIntent() {
        addIntent(new BuffIntent(this, this, new Thorns(THORNS_PER_TURN)));
    }

}
