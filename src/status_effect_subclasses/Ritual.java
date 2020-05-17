public class Ritual extends StatusEffect implements TriggeredAtTurnEnd {
    public static final String DESCRIPTION = "Ritual: Grants Strength at the end of every turn.";

    public Ritual(int counter) {
        super("Ritual", counter, DESCRIPTION);
    }

    @Override
    public void triggered( Object triggerSource) {
        getAffectee().addStatusEffect(new Strength(getCounter()));
    }

    @Override
    void decay() {} // does not decay
}
