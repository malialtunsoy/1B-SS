class HealthPotion extends Potion {
    private static final String NAME = "Health Potion";
    private static final int COST = 250; // what does this parameter mean?
    private static final int RESTORE_PERCENT = 30;
    private static final String DESCRIPTION = "Restores %" + RESTORE_PERCENT + " of the player's maximum health";
    private static final boolean TARGET_REQUIREMENT = false;

    public HealthPotion() {
        super(NAME, COST, DESCRIPTION, TARGET_REQUIREMENT);
    }

    @Override
    public void affect(CombatEntity target) {
        Player p = CombatManager.getInstance().getPlayer();
        p.gainHP(p.getMaxHP() * RESTORE_PERCENT / 100 );
    }
}
