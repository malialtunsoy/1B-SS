
public class CombatTest {
    public static void main( String[] args) {
        CombatManager manager = CombatManager.getInstance();
        manager.addEnemy(new Enemy("error veriyo", 0));
        manager.setPlayer(new Player());
        System.out.println("done");
    }
}