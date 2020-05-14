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
     int ArraySize = Integer.parseInt(line.substring(0,1));
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
            if(sentinelEnd > 0 && !line.substring(0,1).equals("0"))
            {
                tempData = line.substring(sentinelStart + 2 , sentinelEnd);

                data[curIndex] = tempData;
                curIndex++;
            }

            sentinelStart++;
        }

        //for(int i = 0; i < ArraySize ; i++){System.out.println(data[i]);}

     return data;

    }


}
