public class StrategicIntent extends IntentWithStatusEffect {
    public StrategicIntent( Enemy intendingEnemy, StatusEffect effect) {
        super(intendingEnemy, CombatManager.getInstance().getPlayer(), effect); // they always target the player
    }

    public String toString() {
        return "StrategicIntent";
    }
}
