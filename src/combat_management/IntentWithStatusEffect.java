import javafx.scene.effect.Effect;

public abstract class IntentWithStatusEffect extends Intent{
    // properties
    private StatusEffect [] effects;

    // constructors
    public IntentWithStatusEffect( Enemy intendingEnemy, CombatEntity target, StatusEffect []effects) {
        super(intendingEnemy, target);
        this.effects = effects;
    }

    // methods
    public void realize() {
        // add the status effect to the target
        for (StatusEffect effect : effects) {
            effect.setAppliedByAnEnemy(true);
            target.addStatusEffect(effect);
        }
    }
}
