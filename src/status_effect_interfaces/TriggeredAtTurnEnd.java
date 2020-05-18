//  triggers at different times for the player and the enemies (End of "their turn")
public interface TriggeredAtTurnEnd extends Triggered {
    public void triggered( Object triggerSource); // called with arg. null
}
