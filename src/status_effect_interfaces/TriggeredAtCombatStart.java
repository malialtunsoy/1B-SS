// No actual status effect present at combat start. Useful for Relic effects
public interface TriggeredAtCombatStart extends Triggered{
    void triggered(Object triggerSource);  //passed triggerSource is null.
}
