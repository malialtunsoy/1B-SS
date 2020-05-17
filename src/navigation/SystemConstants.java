import java.util.Random;

public class SystemConstants {
    // all non-upgraded cards in the system
    // I would constrain this to Class<? extends Card> but generic Array Creation is not allowed.
    public static Class<?>[] baseCards = {
            // Cards that are not tested/given image yet are commented out.

            // Anger.class,
            Bash.class,
            // BodySlam.class,
            // Clash.class,
            // Cleave.class,
            // Clothesline.class,
            Defend.class,
            // Pummel.class,
            Strengthen.class,
            Strike.class
    };

}
