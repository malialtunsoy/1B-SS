public abstract class IntentWithStatusEffect extends Intent{
    // properties
    private StatusEffect effect;

    // constructors
    public IntentWithStatusEffect( Enemy intendingEnemy, CombatEntity target, StatusEffect effect) {
        super(intendingEnemy, target);
        this.effect = effect;
    }

    // methods
    public void realize() {
        // add the status effect to the target
        effect.setAppliedByAnEnemy(true);
        target.addStatusEffect(effect);
    }
}
