// used to delegate decay behaviour in StatusEffect
// an application of the strategy design pattern
public abstract class DecayBehaviour {
    protected StatusEffect effect;
    public DecayBehaviour( StatusEffect effect ) {
        this.effect = effect;
    }
    abstract public void decay();
}
