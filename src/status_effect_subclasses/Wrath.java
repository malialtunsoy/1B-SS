public class Wrath extends Stance implements OutgoingDamageModifier, IncomingDamageModifier {
    public static final int DAMAGE_FACTOR = 2;
    public static final String DESCRIPTION = "Wrath: deal and receive double attack damage";
    // --- constructors ---
    public Wrath() {
        super("Wrath", DESCRIPTION);
    }

    // --- methods ---
    @Override
    // This is an issue. Both IncomingDamageModifier and OutgoingDamageModifier call this method modify.
    // works for Wrath since they both double the damage, but would have to fix this for other possible effects
    // that implement both. Not fixing right now due to time constraint
    public int modify(int amount) {
        return amount * DAMAGE_FACTOR;
    }
}
