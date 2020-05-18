public class Poison extends StatusEffect implements TriggeredAtTurnStart {
    public static final String DESCRIPTION = "Poison: At the beginning of its turn, the target loses X HP and 1 stack of poison.";

    public Poison(int counter) {
        super("Poison", counter, DESCRIPTION);
    }

    @Override
    public void triggered( Object triggerSource) {
        getAffectee().takeDamage(getCounter());
        decreaseCounter(1);
    }

}
