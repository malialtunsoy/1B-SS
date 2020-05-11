
class Rest extends Vertex{

    //methods
    public void upgradeCard(int index, Player p){
        p.getDeck().get(index).upgrade();
    }
    public void healHP(Player p){
        p.addHp(p.getHp() * 3 / 10);
    }
}
