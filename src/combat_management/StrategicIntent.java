public class StrategicIntent extends IntentWithStatusEffect {
    public StrategicIntent( Enemy intendingEnemy, StatusEffect [] effects) {
        super(intendingEnemy, CombatManager.getInstance().getPlayer(), effects); // they always target the player
    }

    public String toString() {
        return "This enemy intends to apply a debuff.\n";
    }
}
