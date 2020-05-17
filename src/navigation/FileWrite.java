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

        //player
        ArrayList<Card> playerDeck = Game.getInstance().myPlayer.getDeck();
        ArrayList<Potion> playerPots = Game.getInstance().myPlayer.getPots();
        ArrayList<Relic> playerRelics = Game.getInstance().myPlayer.getRelics();
        int playerLocationOnMap;
        String[] mapVertices;

        //mapData
        String[][] mapPathsData = Game.getInstance().myPlayer.myMap.getDataPath();
        int[][] mapPathsDataNumberic = Game.getInstance().myPlayer.myMap.getDataPathNumeric();
        int[] mapCurrentVertex = Game.getInstance().myPlayer.myMap.getCurrentVertexData();
        int mapLevel = Game.getInstance().myPlayer.myMap.getLevel();


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

        // ############################################################
        // ######## WHY DO WE HAVE BOTH CardsInDeck and PlayerDeck ???????????
        // #########################################################
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

        out.print( playerDeck.size()+"###PlayerDeck###    **");
        for(int i = 0; i < playerDeck.size(); i++){
            out.print(playerDeck.get(i).getClass().getName() + "**");
        }
        if(playerDeck.size() == 0){out.print("**");}
        out.println();

        out.print(playerPots.size()+"###PlayerPotions###    **");
        for(int i = 0; i < playerPots.size(); i++){
            out.print(playerPots.get(i).getClass().getName() + "**");
        }
        if(playerPots.size() == 0){out.print("**");}
        out.println();

        out.print(playerRelics.size()+"###PlayerRelics###    **");
        for(int i = 0; i < playerRelics.size(); i++){
            out.print(playerRelics.get(i).getClass().getName() + "**");
        }
        if(playerRelics.size() == 0){out.print("**");}
        out.println();


        //MAP--------------
        for(int j = 0; j < 4; j++) {
            out.print(mapPathsData[j].length + "###MapPath"+(j+1)+"###    **");
            for (int i = 0; i < mapPathsData[j].length; i++) {
                out.print(mapPathsData[j][i] + "**");
            }
            if (mapPathsData[j].length == 0) {
                out.print("**");
            }
            out.println();

        }

        for(int j = 0; j < 4; j++) {
            out.print(mapPathsDataNumberic[j].length + "###MapPathNumeric"+(j+1)+"###    **");
            for (int i = 0; i < mapPathsDataNumberic[j].length; i++) {
                out.print(mapPathsDataNumberic[j][i] + "**");
            }
            if (mapPathsDataNumberic[j].length == 0) {
                out.print("**");
            }
            out.println();

        }

        out.print(mapCurrentVertex.length+"###MapCurrentVertex###    **");
        for(int i = 0; i < mapCurrentVertex.length; i++){
            out.print(mapCurrentVertex[i] + "**");
        }
        if(mapCurrentVertex.length == 0){out.print("**");}
        out.println();

        out.println("1###mapLevel###    **" + mapLevel + "**");
        //**************************************************************

        // --- COMBAT STATE ---
        if (CombatManager.getInstance().combatOngoing()) {
            out.println(CombatManager.getInstance().getCombatState());
        }

        out.close();

      return true;

    }


    static public String[] convertDecksToString(ArrayList<Card> deck){
        String[] deckCardNames = new String[deck.size()];

        for(int i= 0; i < deck.size(); i++ ){
            deckCardNames[i]  = deck.get(i).getClass().getName(); // changed to get the class name directly
        }

        return deckCardNames;
    }
}
