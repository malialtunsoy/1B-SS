public class Block extends StatusEffect implements IncomingDamageModifier{
    public static final String DESCRIPTION = "Block: Prevents damage.";

    public Block(int counter) {
        super("Block", counter, DESCRIPTION);
        setDecayBehaviour(new LastOneTurn(this));
    }

    @Override
    public int modify(int amount) {
        int result = amount - getCounter();
        if (result < 0) {
            result = 0;
        }
        decreaseCounter(amount);
        return result;
    }
}
