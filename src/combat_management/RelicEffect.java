public abstract class RelicEffect extends StatusEffect {
    // --- construcotrs ---
    public RelicEffect() {
        super(null, -1);  // counters and names do not matter for relic effects.
    }

    public void decay() {} // relic effects do not decay.
}
