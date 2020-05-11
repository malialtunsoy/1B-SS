import java.io.FileNotFoundException;

public class GameOptions {

     int[] gameOptions;
     String[] optionNames;


    public GameOptions(){

        try {
            optionNames =  FileRead.readFile("Data.txt", "OptionNames");
        }catch(FileNotFoundException ex){System.out.println("failed");}

        //for(int i = 0; i < optionNames.length ; i++){System.out.println(optionNames[i]);}


        try {
            gameOptions =  FileRead.convertToInt( FileRead.readFile("Data.txt", "Options") );
        }catch(FileNotFoundException ex){System.out.println("failed");}

        //for(int i = 0; i < gameOptions.length ; i++){System.out.println(gameOptions[i]);}


    }

    public int getOption(int option){

        return 0;

    }

    public void setOption(int index, int option){



    }

    public String getOptionName(int index){

        return "";

    }

    public String[] getOptions(){

        String[] temp = new String[gameOptions.length];

        for(int i = 0; i<gameOptions.length ; i++){
            temp[i] = "" + gameOptions[i];
        }

        return temp;
    }

    public String[] getOptionNames(){
        return optionNames;
    }





}
