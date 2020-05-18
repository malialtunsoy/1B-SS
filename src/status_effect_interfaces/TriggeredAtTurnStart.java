//  triggers at different times for the player and the enemies (Start of "their turn")
public interface TriggeredAtTurnStart extends Triggered {
    public void triggered(Object triggerSource); // called with arg. null
}
