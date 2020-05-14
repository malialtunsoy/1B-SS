/* Intent.java
 * 
 * Defines the abstract Intent class
 * Author: Can Cebeci
 * Date: 20.04.2020
 */
abstract class Intent {
    // properties
    protected CombatEntity intendingEnemy;  // need this to apply enemy's status effects when realizing intent.
    protected CombatEntity target;

    // constructors
    public Intent(CombatEntity intendingEnemy, CombatEntity target) {
        this.target = target;
        this.intendingEnemy = intendingEnemy;
    }                   

    // methods
    /*
     * called to realize the intent at the end of a turn.
     */                   
    abstract public void realize();

}