public class ProbUtilities {
    // has the input probability of returning true.
    public static boolean trueWithProb( double prob) {
        double rand = Math.random();
        return rand < prob;
    }

    // returns a random index from the input array according to the probabilities
    public static int indexWithProb( double []probs) {
        // integrity check
        int checkSum = 0;
        for ( int i = 0; i < probs.length; i++) {
            if (probs[i] < 0) {
                return -1;
            }
            checkSum += probs[i];
        }
        if (checkSum != 1) {
            return -1;
        }

        double rand = Math.random();
        int sum = 0;
        for ( int i = 0; i < probs.length; i++) {
            sum += probs[i];
            if (rand < sum) {
                return i;
            }
        }
        return probs.length - 1;
    }
}
