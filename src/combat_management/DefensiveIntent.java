public class DefensiveIntent extends Intent {
    // --- attributes ---
    private int blockAmount;

    // --- constructors ---
    public DefensiveIntent( Enemy e, int blockAmount) {
        super(e, e);    // targets the enemy itself
        this.blockAmount = blockAmount;
    }

    // --- methods ---
    public void realize() {
        target.addStatusEffect(new Block(blockAmount));
    }
}
