/* Intent.java
 * 
 * Defines the abstract Intent class
 * Author: Can Cebeci
 * Date: 20.04.2020
 */
abstract class Intent {
    // properties
    protected int target; // an index into the list of enemies participating in combat.
                        // target -1 represents the player

    protected CombatManager cm;

    // constructors
    public Intent(int target) {
        this.target = target;
    }                   

    // methods
    /*
     * called to realize the intent at the end of a turn.
     */                   
    abstract public void realize();

    public int getTarget() {
        return target;
    }

    public boolean setTarget(int target) {
        if (target < -1) {
            System.out.println("Intent.setTarget called with invalid target " + target);
            return false;
        } else {
            this.target = target;
            return true;
        }
    }
}