public class Vulnerable extends StatusEffect implements IncomingDamageModifier {
    private static final double INCREASE_FACTOR = 0.25;

    public Vulnerable(int counter) {
        super("Vulnerable", counter);
    }

    @Override
    public int modify(int amount) {
        return (int) (amount * (1 + INCREASE_FACTOR));
    }

    public void decay() {
        decreaseCounter(1);
    }
}
