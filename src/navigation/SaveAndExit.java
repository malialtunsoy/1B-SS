import java.io.FileNotFoundException;

public class SaveAndExit {

    boolean readyToExit;

    public Boolean checkReady(){
        return readyToExit;
    }

    static public void exit(){

    save();
    System.out.println("SEE YOU ^^");
    System.exit(0);
    }

    static public Boolean save(){

        try{FileWrite.writeFile();}
        catch(FileNotFoundException ex){System.out.println("failed to write"); return false;}
        System.out.println("GAME SAVED");
        return true;
    }



}
