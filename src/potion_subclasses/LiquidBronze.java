class LiquidBronze extends Potion {
    private static final String NAME = "Liquid Bronze";
    private static final int THORNS_COUNTER = 3;
    private static final int COST = 250; // what does this parameter mean?
    private static final String DESCRIPTION = "Gain " + THORNS_COUNTER + " Thorns.";
    private static final boolean TARGET_REQUIREMENT = false;

    public LiquidBronze() {
        super(NAME, COST, DESCRIPTION, TARGET_REQUIREMENT);
    }

    @Override
    public void affect(CombatEntity target) {
        Player p = CombatManager.getInstance().getPlayer();
        p.addStatusEffect(new Thorns(THORNS_COUNTER));
    }
}
