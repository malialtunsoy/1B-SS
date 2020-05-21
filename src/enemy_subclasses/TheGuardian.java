public class TheGuardian extends Enemy {
    // --- constants ---
    private static final int MIN_HP = 240;
    private static final int MAX_HP = 240;

    // aggressive mode
    private static final int CHARGING_UP_BLOCK = 9;
    private static final int FIERCE_BASH_DAMAGE = 32;
    private static final int VENT_STEAM_VULNERABLE = 2;
    private static final int VENT_STEAM_WEAK = 2;
    private static final int WHIRLWIND_HIT_COUNT = 4;
    private static final int WHIRLWIND_DAMAGE = 5;

    // defensive mode
    private static final int SHARPEN_HIDE_COUNTER= 3;
    private static final int ROLL_ATTACK_DAMAGE = 9;
    private static final int TWIN_SLAM_HIT_COUNT = 2;
    private static final int TWIN_SLAM_DAMAGE = 8;

    private static final int MODE_SHIFT_INITIAL = 5;
    private static final int MODE_SHIFT_INCREMENT = 10;

    int moveIndex;
    boolean inDefensiveMode;
    int modeShiftCurrent;
    int modeShiftReset;

    // --- constructors ---
    public TheGuardian() {
        super("The Guardian", MIN_HP, MAX_HP);
        moveIndex = 0;
        modeShiftCurrent = modeShiftReset = MODE_SHIFT_INITIAL - MODE_SHIFT_INCREMENT;
        switchToAggressiveMode();
    }

    @Override
    public void declareIntent() {
        if (!inDefensiveMode) {
            if (moveIndex == 0) {
                // charging up
                addIntent(new BuffIntent(this, this, new StatusEffect[]{new Block(CHARGING_UP_BLOCK)}));
            } else if (moveIndex == 1) {
                // fierce bash
                addIntent(new AggressiveIntent(this, FIERCE_BASH_DAMAGE));
            } else if (moveIndex == 2) {
                // vent steam
                addIntent(new StrategicIntent(this, new StatusEffect[]{
                        new Vulnerable(VENT_STEAM_VULNERABLE),
                        new Weak(VENT_STEAM_WEAK)}));
            } else if (moveIndex == 3) {
                // whirlwind
                // TODO: hit multiple times
                addIntent(new AggressiveIntent(this,WHIRLWIND_DAMAGE * WHIRLWIND_HIT_COUNT));
                moveIndex = -1;
            }
        } else {
            if (moveIndex == 0) {
                // sharpen hide
                addIntent(new BuffIntent(this, this, new StatusEffect[]{new SharpHide(SHARPEN_HIDE_COUNTER, this)}));
            } else if (moveIndex == 1) {
                // roll attack
                addIntent(new AggressiveIntent(this, ROLL_ATTACK_DAMAGE));
            } else if (moveIndex == 2) {
                // twin slam
                //TODO: Hit multiple times
                addIntent(new AggressiveIntent(this,TWIN_SLAM_DAMAGE * TWIN_SLAM_HIT_COUNT));
                addIntent(new HiddenIntent(this, this) {
                    @Override
                    public void realize() {
                        switchToAggressiveMode();
                    }
                });
            }
        }
        moveIndex++;
    }
    public void switchToAggressiveMode() {
        addStatusEffect(new AggressiveMode(this));
        moveIndex = 0;
        modeShiftReset += MODE_SHIFT_INCREMENT;
        modeShiftCurrent = modeShiftReset;
        inDefensiveMode = false;
    }

    public void switchToDefensiveMode() {
        moveIndex = 0;
        inDefensiveMode = true;
        getIntents().clear();
        declareIntent(); // re-declare intents
        CombatManager.getInstance().redrawUI();
    }

    class AggressiveMode extends StatusEffect implements TriggeredOnDamageTake {
        public static final String DESCRIPTION = "Aggressive mode: enters defensive mode when enough damage is received.";
        TheGuardian guardian;
        public AggressiveMode(TheGuardian guardian) {
            super("Aggressive Mode", 1, DESCRIPTION);
            this.guardian = guardian;
        }

        public void triggered(Object triggerSource) {
            int damage = (int) ((Object[]) triggerSource) [1];
            guardian.modeShiftCurrent -= damage;
            if (guardian.modeShiftCurrent <= damage) {
                decreaseCounter(getCounter());
                guardian.switchToDefensiveMode();
            }
        }
    }

    // similar to thorns, but it is removed when mode is shifted
    class SharpHide extends StatusEffect implements TriggeredOnDamageTake {
        public static final String DESCRIPTION = "Sharp Hide: deals X damage back when hit.";
        private TheGuardian guardian;
        public SharpHide(int counter, TheGuardian guardian) {
            super("Sharp Hide", counter, DESCRIPTION);
            this.guardian = guardian;
            setDecayBehaviour(new DecayBehaviour(this) {
                @Override
                public void decay() {
                    if (!((SharpHide)effect).guardian.inDefensiveMode ){
                        effect.decreaseCounter(effect.getCounter());
                    }
                }
            });
        }

        @Override
        public void triggered(Object triggerSource) {
            CombatEntity hitBy = (CombatEntity) ((Object []) triggerSource)[0];
            hitBy.takeDamage(getCounter());
        }
    }

    @Override
    public void die(){
        CombatManager.getInstance().bossDefeated();
    }

}
