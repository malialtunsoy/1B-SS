// a concrete enemy class. Alternates each turn between gaining block and attacking
public class Alternatron extends Enemy {
    // --- constants ---
    private static final int HP = 40;
    private static final int BLOCK_AMOUNT = 4;
    private static final int DAMAGE = 4;
    private static final boolean ATTACK_FIRST_TURN = true;

    // --- attributes ---
    private boolean attackThisTurn;

    // --- constructors ---
    public Alternatron() {
        super("Alternatron", HP);
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
}
