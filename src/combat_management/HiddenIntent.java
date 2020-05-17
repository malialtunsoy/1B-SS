// enemies only act through actions. These are used for enemy actions that can't be easily modeled by status effects
public abstract class HiddenIntent extends Intent {
    public HiddenIntent(CombatEntity intendingEnemy, CombatEntity target) {
        super(intendingEnemy, target);
    }

    @Override
    public abstract void realize();
}
