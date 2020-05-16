public class BuffIntent extends IntentWithStatusEffect {
    public BuffIntent( Enemy intendingEnemy, Enemy target, StatusEffect effect) {
        super(intendingEnemy, target, effect);
    }

    public String toString() {
        return "BuffIntent";
    }
}
