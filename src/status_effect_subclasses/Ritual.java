public class Ritual extends StatusEffect implements TriggeredAtTurnEnd {
    public Ritual(int counter) {
        super("Ritual", counter);
    }

    @Override
    public void triggered() {
        getAffectee().addStatusEffect(new Strength(getCounter()));
    }

    @Override
    void decay() {} // does not decay
}
