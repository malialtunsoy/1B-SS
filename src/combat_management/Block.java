public class Block extends StatusEffect implements IncomingDamageModifier{
    public Block(int counter) {
        super(counter);
    }

    public int modify(int amount) {
        System.out.println("modify called");
        int result = amount - getCounter();
        if (result < 0) {
            result = 0;
        }
        decreaseCounter(amount);
        return result;
    }

    public String toString() {
        return "Block(" + getCounter() + ")";
    }
}
