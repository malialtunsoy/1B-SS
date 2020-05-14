import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.*;


public class FileWrite extends Game{

    String FileName;
    String[] File;

    static public boolean writeFile() throws FileNotFoundException{

        PrintWriter out = new PrintWriter("Data.txt");

        String[] achNames = Game.getInstance().achievements.getAchNames();
        String[] achLocked =  Game.getInstance().achievements.getAchLocks();
        String[] optionss = Game.getInstance().options.getOptions();
        String[] OptionNames = Game.getInstance().options.getOptionNames();

        //String[] cardNames;
        String[] cardOwned = convertDecksToString(Game.getInstance().myPlayer.getDeck());

        String[] potionOwned;
        String[] relicOwned;


        out.println("1###loadedGame###    **" + Game.getInstance().getLoadedGameExist() + "**");
        out.println("1###PlayerName###    **" + Game.getInstance().getPlayerName() + "**");
        out.println("1###Character###    **" + Game.getInstance().getCharacter() + "**");

        out.print(achNames.length+"###Achievements###    **");
        for(int i = 0; i < achNames.length; i++){
        out.print(achNames[i] + "**");
        }
        if(achNames.length == 0){out.print("**");}
        out.println();

        out.print(achLocked.length+"###AchievementLock###    **");
        for(int i = 0; i < achLocked.length; i++){
            out.print(achLocked[i] + "**");
        }
        if(achLocked.length == 0){out.print("**");}
        out.println();

        out.print(OptionNames.length+"###OptionNames###    **");
        for(int i = 0; i < OptionNames.length; i++){
            out.print(OptionNames[i] + "**");
        }
        if(OptionNames.length == 0){out.print("**");}
        out.println();

        out.print(optionss.length+"###Options###    **");
        for(int i = 0; i < optionss.length; i++){
            out.print(optionss[i] + "**");
        }
        if(optionss.length == 0){out.print("**");}
        out.println();

        out.print(cardOwned.length+"###CardsInDeck###    **");
        for(int i = 0; i < cardOwned.length; i++){
            out.print(cardOwned[i] + "**");
        }
        if(cardOwned.length == 0){out.print("**");}
        out.println();

        out.println("1###playerHP###    **" + Game.getInstance().myPlayer.getHP() + "**");
        out.println("1###playerMaxHP###    **" + Game.getInstance().myPlayer.getMaxHP() + "**");
        out.println("1###playerGold###    **" + Game.getInstance().myPlayer.getGold() + "**");
        out.println("1###maxPot###    **" + Game.getInstance().myPlayer.getMaxPots() + "**");
        out.println("1###relicCount###    **" + Game.getInstance().myPlayer.getRelicCount() + "**");


        out.close();

      return true;

    }


    static public String[] convertDecksToString(ArrayList<Card> deck){
        String[] deckCardNames = new String[deck.size()];

        for(int i= 0; i < deck.size(); i++ ){
            deckCardNames[i]  = deck.get(i).getName();
        }

        return deckCardNames;

    }




}
