public class Strength extends StatusEffect implements OutgoingDamageModifier{
    public static final String DESCRIPTION = "Strength: Increases damage.";
    // --- constructors ---
    public Strength( int counter) {
        super("Strength", counter, DESCRIPTION);

    }

    // --- methods ---
    @Override
    public int modify(int amount) {
        return amount + getCounter();
    }

    @Override
    public void decay() {
        // does not decay
    }
}
