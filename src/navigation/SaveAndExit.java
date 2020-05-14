import java.io.FileNotFoundException;

public class SaveAndExit {

    boolean readyToExit;

    public Boolean checkReady(){
        return readyToExit;
    }

    static public void exit(){

    save();

    }

    static public Boolean save(){

        try{FileWrite.writeFile();}
        catch(FileNotFoundException ex){System.out.println("failed to write"); return false;}

        return true;
    }

    static public Boolean save(Game thisGame){

        try{FileWrite.writeFile();}
        catch(FileNotFoundException ex){System.out.println("failed to write"); return false;}

        return true;
    }


}
