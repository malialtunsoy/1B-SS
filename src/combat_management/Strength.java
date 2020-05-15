public class Strength extends StatusEffect implements OutgoingDamageModifier{

    // --- constructors ---
    public Strength( int counter) {
        super(counter, "Strength");

    }

    // --- methods ---
    @Override
    public int modify(int amount) {
        return amount + getCounter();
    }

    public String toString() {
        return "Strength(" + getCounter() + ")";
    }
    @Override
    public void decay() {
        // does not decay
    }
}
