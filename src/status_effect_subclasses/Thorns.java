public class Thorns extends StatusEffect implements TriggeredAtTurnEnd {
    public static final String DESCRIPTION = "Thorns: When attacked, deals X damage back.";

    public Thorns(int counter) {
        super("Thorns", counter, DESCRIPTION);
    }

    @Override
    public void triggered( Object triggerSource) {
        // damage dealt is not an attack, will not go through OutgoingDamageModifier s
        CombatEntity hitBy = (CombatEntity) triggerSource;
        hitBy.takeDamage(getCounter());
        decreaseCounter(1);
    }
}
