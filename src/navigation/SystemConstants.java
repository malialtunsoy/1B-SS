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
            Strike.class,
            Shiv.class
    };

    public static Class<?>[] ironcladCards = {
            Anger.class,
            Bash.class,
            BodySlam.class,
            Clash.class,
            Cleave.class,
            Clothesline.class,
            Pummel.class,
            Strengthen.class,
            IronWave.class,
            Thunderclap.class,
            TwinStrike.class
    };

    public static Class<?> ironcladRelic = BurningBlood.class;

    public static Class<?>[] silentCards = {
            BladeDance.class,
            CloakAndDagger.class,
            DeadlyPoison.class,
            DaggerSpray.class,
            PoisonedStab.class,
            Deflect.class,
            Survivor.class
    };

    public static Class<?> silentRelic = RingOfTheSnake.class;

    public static Class<?>[] watcherCards = {
            Eruption.class,
            Vigilance.class,
            BowlingBash.class,
            Consecrate.class,
            Crescendo.class,
            Tranquility.class
    };
    public static Class<?> watcherRelic = PureWater.class;
    
    //public static Class<?> watcherRelic = RingOfTheSnake.class;

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


    private static Class<?>[] alternatronElite = {
            Alternatron.class,
            Alternatron.class,
            Alternatron.class,
            Alternatron.class,
            Alternatron.class,
    };

    public static Class<?>[][] elites = {
            alternatronElite,
    };
}
