public class StrategicIntent extends Intent {
    // properties
    private StatusEffect effect;

    // constructors
    public StrategicIntent( Enemy intendingEnemy, CombatEntity target, StatusEffect effect) {
        super(intendingEnemy, target) ;   // aggressive intents always target the player
        this.effect = effect;
    }

    // methods
    public void realize() {
        // add the status effect to the target
        effect.setAppliedByAnEnemy(true);   // stategic intents are always realized by an enemy
        target.addStatusEffect(effect);
    }

    public String toString() {
        return "StrategicIntent";
    }
}
