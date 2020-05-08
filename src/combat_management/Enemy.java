import java.util.ArrayList;

//Only here for testing purposes, not implemented yet.
public class Enemy extends CombatEntity{
    // --- attributes --- 
    String name;
    ArrayList<Intent> intents;

    // --- constructors ---
    public Enemy(String name, int maxHP) {
        super(maxHP);
        this.name       = name;
        intents         = new ArrayList<Intent>();
    }

    // --- methods ---
    public void declareIntent() {
        System.out.println(name + ": I declare my intent!");
    }

}