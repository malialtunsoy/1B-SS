
public class CombatManager {
    //properties

    private static CombatManager instance;
    public Player p;

    //constructors
    CombatManager() {
        if(instance == null) 
            instance = this;
    }

}