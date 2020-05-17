import java.util.ArrayList;

public abstract class Card {
    private String cardName;
    private String cardType;
    private int energy;
    private String description;
    private int cost;
    private boolean isUpgraded;
    private boolean requiresTarget;

    public enum ExtraCardAttribute {EXHAUST, ETHEREAL};
    private ArrayList<ExtraCardAttribute> extraAttributes;

    public Card(String cardName, String cardType, int energy, String description, boolean requiresTarget, int cost){
        this.cardName = cardName;
        this.cardType = cardType;
        this.energy = energy;
        this.description = description;
        this.requiresTarget = requiresTarget;
        this.cost = cost;
        this.extraAttributes = new ArrayList();     // defaults to none
    }

    public ArrayList<ExtraCardAttribute> getExtraAttributes() {
        return extraAttributes;
    }

    public void addExtraAttribute(ExtraCardAttribute extraAttribute) {
        this.extraAttributes.add(extraAttribute);
    }

    public boolean getTargetRequirement() { return requiresTarget;}
    public String getImage (){ return getClass().getName() + ".png"; }
    public String getName(){
        return cardName;
    };
    public void setName(String newCardName){
        this.cardName = newCardName;
    }
    public String getCardType(){
        return cardType;
    }

    public String getDescription(){return description;}

    public void setCardType(String newCardType){
        this.cardType = newCardType;
    }
    public void setEnergy(int newEnergy ){
        this.energy = energy;
    }
    public int getEnergy(){
        return energy;
    }
    public int getCost(){return cost;}
    abstract public void affect( Enemy target); // target = null if the card is not targeted.
    abstract public Card upgradedVersion();

    // added for test purposes to use in CombatManager.
    // implementers of RunManager can comment this out and write their own toString() if they need to
    public String toString() {
        return  "|-----------|\n" +
                "| "+ energy + "        |\n" +
                "|           |\n" +
                "| " + cardName + " |\n" +
                "| " + description + " |\n" +
                "|           |\n" +
                "|-----------|\n";
    }
}
