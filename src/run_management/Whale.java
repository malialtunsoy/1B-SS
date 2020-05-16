public class Whale {

    private int offeredGold;
    private Relic offeredRelic;
    private int offeredMaxHP;
    
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
        p.setMaxHP(getOfferedMaxHP());}// error will be fixed there is no such method (playerda setmaxhp sorun çıktı)
    }
    
    
}
