public abstract class StatusEffect {
    // --- attributes ---
    private int counter;
    private String name;   // uniquely identifies a subclass.
                        // a workaround for when it must be decided whether two
                        // status effects are of the same type. Also useful in ui
    private CombatEntity affectee;
    private boolean appliedByAnEnemy;   // affects when the status effect decays

    // --- constructors ---
    // appliedByAnEnemy defaults to false. Intent realizations call the set method
    public StatusEffect( int counter, String name) {
        this.appliedByAnEnemy = false;
        this.counter = counter;
        this.name = name;
    }


    // --- methods ---
    public void setAppliedByAnEnemy(boolean appliedByAnEnemy) {
        this.appliedByAnEnemy = appliedByAnEnemy;
    }

    public void decreaseCounter(int amount) {
        counter -= amount;
        if (counter <= 0) {
            affectee.removeStatusEffect(this);
        }
    }

    public String getName() {return name;}

    public void setAffectee(CombatEntity affectee) {
        this.affectee = affectee;
    }

    public int getCounter() { return counter;}

    public void stackCounter(StatusEffect other) { counter += other.getCounter();}

    // for now, it seems to be the case that SE applied by the enemies decay not at the turn start but before
    // intents are realized.
    public boolean decayAtTurnStart() { return !appliedByAnEnemy; }

    abstract void decay();   // some SE might decrease their counter by 1 per turn (Poison), some might set it to 0 (Block) etc.
}
