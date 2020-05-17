import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class FileRead {

 String fileName;
    static String[] file;

 static public String[] readFile(String fileName, String key) throws FileNotFoundException {

     /*File aa = new File(".");
     for(String fileNames : aa.list()){ System.out.println(fileNames);}*/

      Scanner in = new Scanner(new File(fileName)); //input scanner

     while(in.hasNext()){

         String line = in.nextLine();


         String id = findIn(line);
         //System.out.println(id);
         if(key.equals(id))
         {
             //System.out.println(line);
             return collectData(line);
         }

     }
     in.close();

     String[] fail = {};
     return fail;
 }
    public static ArrayList<Card> convertToCards(String [] names) {
        ArrayList<Card> result = new ArrayList<Card>();
        try {
            for (int i = 0; i < names.length; i++) {
                Card newCard = (Card) (Class.forName(names[i]).getConstructor().newInstance());
                result.add(newCard);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Exception caused by invalid Card subclass load" + e.getMessage());
        } catch (NoSuchMethodException e) {
            System.err.println("Exception caused by Card subclass load with no default constructor:" + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception in convertToCards caused by call to newInstance()");
        }
        return result;
    }

 static public int[] convertToInt(String[] array){

     int[] converted = new int[array.length];

     for(int i = 0; i < array.length ; i++ )
     {
         converted[i] = Integer.parseInt(array[i]);
     }

     return converted;
 }

    static public Boolean[] convertToBool(String[] array){

        Boolean[] converted = new Boolean[array.length];

        for(int i = 0; i < array.length ; i++ )
        {
        if(array[i].equals("1") || array[i].equals("true") ){converted[i] = true;}
        else{converted[i] = false;}

        }


        return converted;
    }

    static public String findIn( String line)
    {
        String key = "###";
        int sentinelStart;
        int sentinelEnd;

        sentinelStart = line.indexOf(key);
        sentinelEnd = line.indexOf(key, sentinelStart +2);
        if(sentinelStart > -1)
        {
            return line.substring(sentinelStart + 3 , sentinelEnd);
        }
        return "";
    }

    static public String[] collectData(String line){
        int sizeOfArray = 0;
        sizeOfArray = line.indexOf("###", 0);
        sizeOfArray = Integer.parseInt(line.substring(0,sizeOfArray)) ;

     int ArraySize = sizeOfArray;
       // System.out.println(line);
     String[] data = new String[ArraySize];

        int sentinelStart=0;
        int sentinelEnd=0;
        int curIndex = 0;


        while(line.indexOf("**", sentinelStart) > 0)
        {
            sentinelStart = line.indexOf("**",sentinelStart);
            sentinelEnd = line.indexOf("**", sentinelStart +1);


           // System.out.println(sentinelStart + " " + sentinelEnd);
            String tempData = "";
            if(sentinelEnd > 0 && sizeOfArray != 0)
            {
                tempData = line.substring(sentinelStart + 2 , sentinelEnd);
               // System.out.println(line + "  "  + sizeOfArray + "   " + tempData);
                data[curIndex] = tempData;
                //System.out.println(data[curIndex]);
                curIndex++;
            }

            sentinelStart++;
        }

        //for(int i = 0; i < ArraySize ; i++){System.out.println(data[i]);}

     return data;

    }


}
