/* Intent.java
 * 
 * Defines the abstract Intent class
 * Author: Can Cebeci
 * Date: 20.04.2020
 */
abstract class Intent {
    private int target; // an index into the list of enemies participating in combat.
                        // target -1 represents the player   
                        
    /*
     * called to realize the intent at the end of a turn.
     */                   
    abstract public void realize();
}