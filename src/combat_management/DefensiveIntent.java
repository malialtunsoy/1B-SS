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
        Block block = new Block(blockAmount);
        block.setAppliedByAnEnemy(true);
        target.addStatusEffect(block);
    }

    public String toString() { return "This enemy intends to gain block.\n";}
}
