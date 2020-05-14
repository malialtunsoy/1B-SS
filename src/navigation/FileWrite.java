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

        String[] cardNames;
        String[] cardDescriptions;
        String[] cardOwned;
        String[] cardCost;
        String[] cardType;

        String[] potionNames;
        String[] potionDescriptions;
        String[] potionOwned;
        String[] potionCost;
        String[] potionType;

        String[] relicNames;
        String[] relicDescription;
        String[] relicOwned;
        String[] relicCost;
        String[] relicType;

        out.println("1###loadedGame###    **" + Game.getInstance().getLoadedGameExist() + "**");
        out.println("1###PlayerName###    **" + Game.getInstance().getPlayerName() + "**");
        out.println("1###Character###    **" + Game.getInstance().getCharacter() + "**");

        out.print(achNames.length+"###Achievements###    **");
        for(int i = 0; i < achNames.length; i++){
        out.print(achNames[i] + "**");
        }
        out.println();

        out.print(achLocked.length+"###AchievementLock###    **");
        for(int i = 0; i < achLocked.length; i++){
            out.print(achLocked[i] + "**");
        }
        out.println();

        out.print(OptionNames.length+"###OptionNames###    **");
        for(int i = 0; i < OptionNames.length; i++){
            out.print(OptionNames[i] + "**");
        }
        out.println();

        out.print(optionss.length+"###Options###    **");
        for(int i = 0; i < optionss.length; i++){
            out.print(optionss[i] + "**");
        }
        out.println();

      /*  out.print(optionss.length+"###CardNames###    **");
        for(int i = 0; i < optionss.length; i++){
            out.print(optionss[i] + "**");
        }
        out.println();

        out.print(optionss.length+"###CardDescriptions###    **");
        for(int i = 0; i < optionss.length; i++){
            out.print(optionss[i] + "**");
        }
        out.println();

        out.print(optionss.length+"###CardOwned###    **");
        for(int i = 0; i < optionss.length; i++){
            out.print(optionss[i] + "**");
        }
        out.println();

        out.print(optionss.length+"###PotionNames###    **");
        for(int i = 0; i < optionss.length; i++){
            out.print(optionss[i] + "**");
        }
        out.println();

        out.print(optionss.length+"###PotionDescriptions###    **");
        for(int i = 0; i < optionss.length; i++){
            out.print(optionss[i] + "**");
        }
        out.println();

        out.print(optionss.length+"###PotionOwned###    **");
        for(int i = 0; i < optionss.length; i++){
            out.print(optionss[i] + "**");
        }
        out.println();

        out.print(optionss.length+"###RelicNames###    **");
        for(int i = 0; i < optionss.length; i++){
            out.print(optionss[i] + "**");
        }
        out.println();

        out.print(optionss.length+"###RelicDescriptions###    **");
        for(int i = 0; i < optionss.length; i++){
            out.print(optionss[i] + "**");
        }
        out.println();

        out.print(optionss.length+"###RelicOwned###    **");
        for(int i = 0; i < optionss.length; i++){
            out.print(optionss[i] + "**");
        }
        out.println();
        */





        out.close();

      return true;

    }






}
