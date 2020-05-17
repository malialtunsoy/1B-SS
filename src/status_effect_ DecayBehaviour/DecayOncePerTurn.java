public class DecayOncePerTurn extends DecayBehaviour {
    public DecayOncePerTurn(StatusEffect effect ) {
        super(effect);
    }

    public void decay() {
        effect.decreaseCounter(1);
    }
}
