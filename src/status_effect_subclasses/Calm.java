public class Calm extends Stance implements TriggeredOnEffectRemoval {
    public static final int ENRGY_GAIN = 2;
    public static final String DESCRIPTION = "Calm: upon exiting Calm, get 2 energy";
    // --- constructors ---
    public Calm() {
        super("Calm", DESCRIPTION);
    }

    // --- methods ---
    @Override
    public void triggered(Object triggerSource) {
        CombatManager.getInstance().gainEnergy(ENRGY_GAIN);
    }
}
