
class Rest extends Vertex{
    // constants
    static final double PORTION_HP_HEALED = 0.3; // this portion of the maxhp is healed

    //methods
    public void upgradeCard(int index, Player p){
        // p.getDeck().get(index).upgrade();    COMMENTED OUT FOR NOW, WAS NOT USED ANYWAY
    }
    public void healHP(Player p){
        p.gainHP((int) (p.getMaxHP() * PORTION_HP_HEALED));
    }
}
