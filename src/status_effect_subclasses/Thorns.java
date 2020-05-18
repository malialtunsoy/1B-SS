public class Thorns extends StatusEffect implements TriggeredOnDamageTake {
    public static final String DESCRIPTION = "Thorns: When attacked, deals X damage back.";

    public Thorns(int counter) {
        super("Thorns", counter, DESCRIPTION);
    }

    @Override
    public void triggered( Object triggerSource) {
        // damage dealt is not an attack, will not go through OutgoingDamageModifier s
        System.out.println("Triggered Thorns !!!!!!!!!!");
        CombatEntity hitBy = (CombatEntity) triggerSource;
        hitBy.takeDamage(getCounter());
    }
}
