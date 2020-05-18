public abstract class Stance extends StatusEffect {
    // initialize with counter 1. decrease by 1 when another stance is constructed (added immediately afterwards).
    public Stance (String name, String description) {
        super(name, 1, description);
        // remove all other stances
        Player p = CombatManager.getInstance().getPlayer();
        for ( StatusEffect se: p.getStatusEffects()) {
            if (se instanceof Stance) {
                se.decreaseCounter(1);
            }
        }
    }
}
