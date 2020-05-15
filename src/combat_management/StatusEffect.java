public abstract class StatusEffect {
    // --- attributes ---
    private int counter;
    private String name;   // uniquely identifies a subclass.
                        // a workaround for when it must be decided whether two
                        // status effects are of the same type. Also useful in ui
    private CombatEntity affectee;

    // --- constructors ---
    public StatusEffect( int counter, String name) {
        this.counter = counter;
        this.name = name;
    }

    // --- methods ---
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

    abstract void decay();   // some SE might decrease their counter by 1 per turn (Poison), some might set it to 0 (Block) etc.
}
