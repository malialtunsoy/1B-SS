public abstract class StatusEffect {
    // --- attributes ---
    private int counter;
    private String name;   // uniquely identifies a subclass.
                        // a workaround for when it must be decided whether two
                        // status effects are of the same type. Also useful in ui
    private CombatEntity affectee;
    private boolean appliedByAnEnemy;   // affects when the status effect decays
    private String description;
    private DecayBehaviour decay;

    // --- constructors ---
    // appliedByAnEnemy defaults to false. Intent realizations call the set method
    public StatusEffect(String name, int counter, String description) {
        this.appliedByAnEnemy = false;
        this.counter = counter;
        this.name = name;
        this.description = description;
        this.decay = new NoDecay(this);  // defaults to NoDecay
    }


    // --- methods ---
    public void setAppliedByAnEnemy(boolean appliedByAnEnemy) {
        this.appliedByAnEnemy = appliedByAnEnemy;
    }

    public void setDecayBehaviour( DecayBehaviour decay) {this.decay = decay;}

    public void decreaseCounter(int amount) {
        counter -= amount;
        if (counter <= 0) {
            affectee.removeStatusEffect(this);
        }
    }

    public String getDescription() {
        return description;
    }

    public String getName() {return name;}

    public String getImage() {return name + ".png";}

    public void setAffectee(CombatEntity affectee) {
        this.affectee = affectee;
    }
    public CombatEntity getAffectee() {return affectee;}

    public int getCounter() { return counter;}

    public void stackCounter(StatusEffect other) { counter += other.getCounter();}

    // for now, it seems to be the case that SE applied by the enemies decay not at the turn start but before
    // intents are realized.
    public boolean decayAtTurnStart() { return !appliedByAnEnemy; }

    public String toString() {return name + "(" + counter + ")";}

    public void decay() {
        decay.decay();
    }
}
