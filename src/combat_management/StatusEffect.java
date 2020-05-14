public abstract class StatusEffect {
    // --- attributes ---
    private int counter;
    private CombatEntity affectee;

    // --- constructors ---
    public StatusEffect( int counter) {
        this.counter = counter;
    }

    // --- methods ---
    public void decreaseCounter(int amount) {
        counter -= amount;
        if (counter <= 0) {
            affectee.removeStatusEffect(this);
        }
    }

    public void setAffectee(CombatEntity affectee) {
        this.affectee = affectee;
    }

    public int getCounter() { return counter;}
}
