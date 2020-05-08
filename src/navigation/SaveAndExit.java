import java.io.FileNotFoundException;

public class SaveAndExit {

    boolean readyToExit;

    public Boolean checkReady(){
        return readyToExit;
    }

    public void exit(){

    save();

    }

    static public Boolean save(){

        try{FileWrite.writeFile("Data.txt");}
        catch(FileNotFoundException ex){System.out.println("failed to write"); return false;}

        return true;
    }


}
