import java.util.Random;

public class SystemConstants {
    // all non-upgraded cards in the system
    // I would constrain this to Class<? extends Card> but generic Array Creation is not allowed.
    public static Class<?>[] neutralCards = {
            // Cards that are not tested/given image yet are commented out.


            // BodySlam.class,
            // Clash.class,
            // Cleave.class,
            // Clothesline.class,
            Defend.class,
            Strike.class
    };

    public static Class<?>[] ironcladCards = {
            Anger.class,
            Bash.class,
            BodySlam.class,
            Clash.class,
            Cleave.class,
            Clothesline.class,
            Pummel.class,
            Strengthen.class
    };

    public static Class<?>[] potions = {
            DamagePotion.class,
            HealthPotion.class
    };

    public static Class<?>[] relics = {
            BurningBlood.class,
            RingOfTheSnake.class,
            BronzeScales.class
    };

    public static Class<?>[] enemies = {
            Alternatron.class,
            Cultist.class,
            JawWorm.class,
            AnnoyingHedgehog.class,
            AcidSlimeMedium.class
    };
}
