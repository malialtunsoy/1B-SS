public class Weak extends StatusEffect implements OutgoingDamageModifier{
    public static final double DAMAGE_DECREASE = 0.25;
    public static final String DESCRIPTION = "Weak: target deals %" + DAMAGE_DECREASE * 100 + " less attack damage";
    // --- constructors ---
    public Weak(int counter) {
        super("Weak", counter, DESCRIPTION);
        setDecayBehaviour(new DecayOncePerTurn(this));
    }

    // --- methods ---
    @Override
    public int modify(int amount) {
        return (int) (amount * (1 - DAMAGE_DECREASE));
    }
}
