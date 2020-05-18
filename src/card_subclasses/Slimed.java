public class Slimed extends Card {
    // --- constants ---
    private static final int ENERGY_COST = 1;
    private static final String DESCRIPTION = "Exhaust.";
    private static final boolean TARGET_REQUIREMENT = false;
    private static int COST = 340;
    // constructors
    public Slimed() {
        super("Slimed", "Status", ENERGY_COST, DESCRIPTION, TARGET_REQUIREMENT,COST);
        addExtraAttribute(ExtraCardAttribute.EXHAUST);
    }

    public void affect(Enemy target) { } // does nothing
    public Card upgradedVersion() {
        return null;
    }
}
