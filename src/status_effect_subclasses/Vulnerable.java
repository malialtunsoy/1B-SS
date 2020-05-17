public class Vulnerable extends StatusEffect implements IncomingDamageModifier {
    private static final double INCREASE_FACTOR = 0.25;
    public static final String DESCRIPTION = "Vulnerable: Increases damage taken by %" + INCREASE_FACTOR * 100 +".";

    public Vulnerable(int counter) {
        super("Vulnerable", counter, DESCRIPTION);
        setDecayBehaviour(new DecayOncePerTurn(this));
    }

    @Override
    public int modify(int amount) {
        return (int) (amount * (1 + INCREASE_FACTOR));
    }
}
