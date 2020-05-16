
public class Treasure extends Vertex{
    private int offeredGold;
    private Relic offeredRelic;
    private Potion offeredPotion;

    public Treasure(int offeredGold, Relic offeredRelic, Potion offeredPotion)
    {
        this.offeredGold =   offeredGold;
        this.offeredRelic = offeredRelic;
        this.offeredPotion = offeredPotion;
    }

    public int getOfferedGold(){
        return offeredGold;
    }
    public void setOfferedGold(int newGold){
        offeredGold = newGold;
    }
    public Relic getOfferedRelic(){
        return offeredRelic;
    }
    public void setOfferedRelic(Relic newRelic){
        offeredRelic = newRelic;
    }
    public Potion getOfferedPotion(){
        return offeredPotion;
    }
    public void setOfferedPotion(Potion newPotion){
        offeredPotion = newPotion;
    }

    public void takeGold(Player p){
        p.addGold(getOfferedGold());
    }
    public void takeRelic(Player p){
        p.addRelic(offeredRelic);
    }
    public void takePotion(Player p){
        p.addPot(offeredPotion);
    }
}
