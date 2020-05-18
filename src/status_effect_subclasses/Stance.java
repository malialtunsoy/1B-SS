import java.util.ArrayList;

public abstract class Stance extends StatusEffect {
    // initialize with counter 1. decrease by 1 when another stance is constructed (added immediately afterwards).
    public Stance (String name, String description) {
        super(name, 1, description);
        // remove all other stances
        ArrayList<StatusEffect> effects = CombatManager.getInstance().getPlayer().getStatusEffects();

        // if any SE runs out, it will remove itself from affectedBy but not from the shallow copy.
        ArrayList<StatusEffect> shallowCopy = new ArrayList<StatusEffect>(effects);

        // for whatever reason, we get a concurrency related error if a foreach loop is used
        for (int i = 0; i < shallowCopy.size(); i++) {
            StatusEffect se = shallowCopy.get(i);
            if (se instanceof Stance) {
                se.decreaseCounter(1);
            }
        }
    }

}
