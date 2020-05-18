// a concrete enemy class. Alternates each turn between gaining block and attacking
public class Alternatron extends Enemy {
    // --- constants ---
    private static final int MIN_HP = 30;
    private static final int MAX_HP = 40;
    private static final int BLOCK_AMOUNT = 4;
    private static final int DAMAGE = 4;
    private static final boolean ATTACK_FIRST_TURN = true;

    // --- attributes ---
    private boolean attackThisTurn;

    // --- constructors ---
    public Alternatron() {
        super("Alternatron", MIN_HP, MAX_HP);
        attackThisTurn = ATTACK_FIRST_TURN;
    }

    @Override
    public void declareIntent() {
        if (attackThisTurn) {
            addIntent(new AggressiveIntent(this, DAMAGE));
        } else {
            addIntent(new DefensiveIntent(this, BLOCK_AMOUNT));
        }
        attackThisTurn = !attackThisTurn;
    }
    @Override
    public void restoreExtraState(String[] extraParams) {
        if (extraParams.length != 1) {
            System.err.println("Number of extra state parameters provided for Alternaton is not 1");
        } else {
            attackThisTurn = Boolean.parseBoolean(extraParams[0]);
        }
    }
}
