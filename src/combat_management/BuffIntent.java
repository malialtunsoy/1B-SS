public class BuffIntent extends IntentWithStatusEffect {
    public BuffIntent( Enemy intendingEnemy, Enemy target, StatusEffect [] effects) {
        super(intendingEnemy, target, effects);
    }

    public String toString() {
        return "This enemy intends to apply a buff.\n";
    }
}
