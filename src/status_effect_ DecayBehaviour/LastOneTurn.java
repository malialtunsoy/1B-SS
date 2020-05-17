public class LastOneTurn extends DecayBehaviour {
    public LastOneTurn(StatusEffect effect ) {
        super(effect);
    }

    public void decay() {
        effect.decreaseCounter(effect.getCounter());
    }
}
