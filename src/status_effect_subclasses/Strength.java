public class Strength extends StatusEffect implements OutgoingDamageModifier{

    // --- constructors ---
    public Strength( int counter) {
        super("Strength", counter);

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
