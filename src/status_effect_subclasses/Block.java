public class Block extends StatusEffect implements IncomingDamageModifier{
    public Block(int counter) {
        super("Block", counter);
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

    @Override
    public void decay() {
        // lose all block
        decreaseCounter(getCounter());
    }
}
