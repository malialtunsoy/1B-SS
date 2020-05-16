public class Whale {

    private int offeredGold;
    private Relic offeredRelic;
    private int offeredMaxHP;
    private final String whaleQuote = "Hello...again...";

    public Whale(int offeredGold,Relic offeredRelic, int offeredMaxHP){
        this.offeredGold = offeredGold;
        this.offeredRelic = offeredRelic;
        this.offeredMaxHP = offeredMaxHP;

    }

    public int getOfferedGold(){
        return offeredGold;
    }
    public Relic getOfferedRelic(){
        return offeredRelic;
    }
    public int getOfferedMaxHP(){return offeredMaxHP;}
    public void setOfferedGold(int newGold){
        offeredGold = newGold;
    }
    public void setOfferedRelic(Relic newRelic){
        offeredRelic = newRelic;
    }
    public void setOfferedMaxHP(int newMaxHP){
        offeredMaxHP = newMaxHP;
    }

    //according to the choice they will be added
    public void takeGold(Player p){
        p.addGold(getOfferedGold());
    }
    public void takeRelic(Player p){
        p.addRelic(getOfferedRelic());
    }
    public void takeMaxHP(Player p){
        p.increaseMaxHP(8);
    }

}