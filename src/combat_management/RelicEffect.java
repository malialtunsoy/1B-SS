public abstract class RelicEffect extends StatusEffect {
    // --- construcotrs ---
    public RelicEffect() {
        super(-1, null);  // counters and names do not matter for relic effects.
    }

    public void decay() {} // relic effects do not decay.
}
