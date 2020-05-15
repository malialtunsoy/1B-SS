public class STSUtilities {
    // has the input probability of returning true.
    public static boolean trueWithProb( double prob) {
        double rand = Math.random();
        return rand < prob;
    }
}
