import java.util.ArrayList;

//Only here for testing purposes, not implemented yet.
public class Enemy {
    // --- attributes --- 
    int currentHP;
    int maxHP;
    String name;
    ArrayList<Intent> intents;

    // --- constructors ---
    public Enemy(String name, int maxHP) {
        this.name       = name;
        this.maxHP      = maxHP;
        this.currentHP  = maxHP; // enemies are always created at maxHP
        intents         = new ArrayList<Intent>();
    }

    public void loseHP(int amount) {

    }


}