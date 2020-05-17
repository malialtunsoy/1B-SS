public interface Triggered {
    // param. triggerSource has no default meaning. General way to pass arguments to a trigger method.
    // The intended use is to determine what caused the trigger but it can be used for anything.
    void triggered( Object triggerSource);
}
