import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

public class Achievements {

     String[] achievementNames;
     Boolean[] locked;

    public Achievements(){

        try {
            achievementNames =  FileRead.readFile("Data.txt", "Achievements");
        }catch(FileNotFoundException ex){System.out.println("failed");}

        for(int i = 0; i < achievementNames.length ; i++){System.out.println(achievementNames[i]);}


        try {
            locked =  FileRead.convertToBool( FileRead.readFile("Data.txt", "AchievementLock") );
        }catch(FileNotFoundException ex){System.out.println("failed");}

        for(int i = 0; i < locked.length ; i++){System.out.println(locked[i]);}


    }

    public String getName(int index){
        return "";
    }

    public void setAch(boolean ach, int index){

    }

    public boolean getAch(int index){
        return true;
    }

     public String[] getAchNames(){
        return achievementNames;
    }

     public String[] getAchLocks(){

        String[] temp = new String[locked.length];

        for(int i = 0; i<locked.length ; i++){
            if(locked[i]){temp[i] = "1";}
            else{ temp[i] = "0";}
        }

        return temp;
    }







}
