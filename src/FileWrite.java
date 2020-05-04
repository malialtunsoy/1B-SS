
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.*;


public class FileWrite extends Game{

    String FileName;
    String[] File;

    static public boolean writeFile(String fileName) throws FileNotFoundException{

        PrintWriter out = new PrintWriter(fileName);

        String[] achNames = achievements.getAchNames();
        String[] achLocked =  achievements.getAchLocks();
        String[] optionss = options.getOptions();
        String[] OptionNames = options.getOptionNames();

        out.println("1###PlayerName###    **" + playerName + "**");
        out.println("1###Character###    **" + character + "**");

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





        out.close();

      return true;

    }






}
