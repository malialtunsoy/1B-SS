class DamagePotion extends Potion {
    private static final String NAME = "Damage Potion";
    private static final int COST = 115; // what does this parameter mean?
    private static final int DAMAGE = 35;
    private static final String DESCRIPTION = "Deal " + DAMAGE + " damage to the target";
    private static final boolean TARGET_REQUIREMENT = true;

    public DamagePotion() {
        super(NAME, COST, DESCRIPTION, TARGET_REQUIREMENT);
    }

    @Override
    public void affect( CombatEntity target) {
        target.takeDamage(DAMAGE); // damage not dealt by player, outgoing damage modifiers don't apply
    }
}