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
    public String toString() {
        return name + "\n-------\n" +  super.toString() + "\n intents: " + intents;
    }

    // test purpose implementation, declares aggressive intent with 5 dmg
    public void declareIntent() {
        AggressiveIntent aggr = new AggressiveIntent(this,5);
        intents.add(aggr);
    }

    public void realizeAllIntents() {
        for (Intent i : intents) {
            i.realize();
        }
        intents.clear();
    }

}