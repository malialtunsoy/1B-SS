import java.io.FileNotFoundException;

public class SaveAndExit {

    boolean readyToExit;

    public Boolean checkReady(){
        return readyToExit;
    }

    public void exit(Achievements myAch, GameOptions myOpt, String playerName, String character){

    save( myAch,  myOpt,  playerName,  character);

    }

    static public Boolean save(Achievements myAch, GameOptions myOpt, String playerName, String character){

        try{FileWrite.writeFile("Data.txt", myAch, myOpt, playerName, character);}
        catch(FileNotFoundException ex){System.out.println("failed to write"); return false;}

        return true;
    }

    static public Boolean save(Game thisGame){

        try{FileWrite.writeFile("Data.txt", thisGame.achievements, thisGame.options, thisGame.playerName, thisGame.character);}
        catch(FileNotFoundException ex){System.out.println("failed to write"); return false;}

        return true;
    }


}
