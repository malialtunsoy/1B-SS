import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.util.ArrayList;

public class Map {
    int vertexSize = 11; // 18 - (4 inital combat) - (last 2 rest and boss)
    int numberOfCombatLeft = 4; // 8 - 4
    int numberOfMerchantLeft = 3;
    int numberOfRestLeft = 1; // 3 - 2
    int numberOfTreasureLeft = 3;

    int mapStartAtXLocation = 109;
    int mapStartAtYLocation = 136;

    int currentPosition;
    Vertex[] vertices ;
    String[] vertexTypes;
    int[] verticesXloc;
    int[] vericesYloc;

    VertexNode path1Root;
    VertexNode path2Root;
    VertexNode path3Root;
    VertexNode path4Root;

    VertexNode path1curNode;
    VertexNode path2curNode;
    VertexNode path3curNode;
    VertexNode path4curNode;

    int sizeOfPath1 = 0;
    int sizeOfPath2 = 0;
    int sizeOfPath3 = 0;
    int sizeOfPath4 = 0;

    VertexNode currentMainVertex;

    //DATA
    String[] path1Data; //Node types
    String[] path2Data;
    String[] path3Data;
    String[] path4Data;

    int[] path1NumbericalData;  //Node alternate node and locations
    int[] path2NumbericalData;  //[nodeIndex][alternatePath][alterNatePathNodeIndex][LocX][LoxY]
    int[] path3NumbericalData;
    int[] path4NumbericalData;

    int[] currentVertex = {-1,-1};  //[pathNumber][pathIndex}

    VertexNode boss;
    VertexNode rest1;
    VertexNode rest2;

    public class VertexNode{
        String vertex;
        VertexNode next;
        VertexNode alternativeNext;
        int locationX;
        int locationY;
        boolean available;

        int PathNumber;
        int PathIndex;

        public VertexNode(){
            vertex = null;
            next = null;
            alternativeNext = null;
            available = false;
        }

        public VertexNode(String vertex, VertexNode next, VertexNode alternativeNext){
            this.vertex = vertex;
            this.next = next;
            this.alternativeNext = alternativeNext;
        }

        public String getVertex() {
            return vertex;
        }

        public void setVertex(String vertex) {
            this.vertex = vertex;
        }

        public void setNext(VertexNode next) {
            this.next = next;
        }

        public VertexNode getNext() {
            return next;
        }

        public void setAlternativeNext(VertexNode alternativeNext) {
            this.alternativeNext = alternativeNext;
        }

        public VertexNode getAlternativeNext() {
            return alternativeNext;
        }

        public void setLocationX(int locationX) {
            this.locationX = locationX;
        }

        public int getLocationX() {
            return locationX;
        }

        public void setLocationY(int locationY) {
            this.locationY = locationY;
        }

        public int getLocationY() {
            return locationY;
        }

        public void setAvailable(boolean available){
            this.available = available;
        }

        public boolean getAvailable(){
            return available;
        }

        public void setPathIndex(int pathIndex) {
            PathIndex = pathIndex;
        }

        public int getPathIndex() {
            return PathIndex;
        }

        public void setPathNumber(int pathNumber) {
            PathNumber = pathNumber;
        }

        public int getPathNumber() {
            return PathNumber;
        }
    }


    public Map()
    {
        //System.out.println(Game.getInstance().getLoadedGameExist());
        //if(Game.getInstance().getLoadedGameExist()){System.out.println("AAAA"); loadMap();}
        //else{initializeMap();}
        //prepareData();
        //drawMap();
    }


    //DATA===============================================
    public String[][] getDataPath(){
        String[][] data = {path1Data, path2Data, path3Data, path4Data};
        return  data;
    }

    public int[][] getDataPathNumeric(){
        int[][] data = {path1NumbericalData, path2NumbericalData, path3NumbericalData, path4NumbericalData};
        return  data;
    }

    public int[] getCurrentVertexData(){return currentVertex;}

    public void initializeMap(){
        organizeMap();
        prepareData();
    }

    //=========================================== INITIALIZE ===========================================
    public void organizeMap(){ //Total number of vertices = 18 ---> Let 8 Combats, 3 Rests, 3 Treasure, 3 Merchant and 1 Boss
        //2 Rests and boss will be the last Nodes.

        path1Root = new VertexNode("Combat", null,null); path1curNode = path1Root; //all paths start with combat  //  []
        path2Root = new VertexNode("Combat", null,null); path2curNode = path2Root;                                //  []
        path3Root = new VertexNode("Combat", null,null); path3curNode = path3Root;                                //  []
        path4Root = new VertexNode("Combat", null,null); path4curNode = path4Root;                                //  [] 4

        path1curNode.setPathNumber(1); path1curNode.setPathIndex(sizeOfPath1++);
        path2curNode.setPathNumber(2); path2curNode.setPathIndex(sizeOfPath2++);
        path3curNode.setPathNumber(3); path3curNode.setPathIndex(sizeOfPath3++);
        path4curNode.setPathNumber(4); path4curNode.setPathIndex(sizeOfPath4++);

        for(int i = 0; i < 2; i++){
            VertexNode tempNode = new VertexNode(randomVertex(), null, null);   // [] [] []
            path1curNode.setNext(tempNode); path1curNode = path1curNode.getNext();                 // [] [] []
            path1curNode.setPathNumber(1); path1curNode.setPathIndex(sizeOfPath1++);
            tempNode = new VertexNode(randomVertex(), null, null);              // [] [] []
            path2curNode.setNext(tempNode); path2curNode = path2curNode.getNext();                 // [] [] []  12
            path2curNode.setPathNumber(2); path2curNode.setPathIndex(sizeOfPath2++);
            tempNode = new VertexNode(randomVertex(), null, null);
            path3curNode.setNext(tempNode); path3curNode = path3curNode.getNext();
            path3curNode.setPathNumber(3); path3curNode.setPathIndex(sizeOfPath3++);
            tempNode = new VertexNode(randomVertex(), null, null);
            path4curNode.setNext(tempNode); path4curNode = path4curNode.getNext();
            path4curNode.setPathNumber(4); path4curNode.setPathIndex(sizeOfPath4++);
        }

        VertexNode tempNode = new VertexNode(randomVertex(), null, null);   // [] [] [] [] []
        path2curNode.setNext(tempNode); path2curNode = path2curNode.getNext();                 // [] [] [] [] []
        path2curNode.setPathNumber(2); path2curNode.setPathIndex(sizeOfPath2++);                // [] [] [] []
        // [] [] [] [] [] 15
        tempNode = new VertexNode(randomVertex(), null, null);
        path4curNode.setNext(tempNode); path4curNode = path4curNode.getNext();
        path4curNode.setPathNumber(4); path4curNode.setPathIndex(sizeOfPath4++);
        tempNode = new VertexNode(randomVertex(), null, null);

        path1curNode.setNext(tempNode); path1curNode = path1curNode.getNext();
        path1curNode.setPathNumber(1); path1curNode.setPathIndex(sizeOfPath1++);

        VertexNode bossNode = new VertexNode("Boss", null, null);    //  []
        VertexNode lastRestNode1 = new VertexNode("Rest", bossNode,null);  //     []
        VertexNode lastRestNode2 = new VertexNode("Rest", bossNode,null);  //  []

        bossNode.setPathNumber(99); bossNode.setPathIndex(99);
        lastRestNode1.setPathNumber(99); lastRestNode1.setPathIndex(1);
        lastRestNode2.setPathNumber(99); lastRestNode2.setPathIndex(2);

        path1curNode.setNext(lastRestNode1);// [] [] [] [] []
        path2curNode.setNext(lastRestNode1);// [] [] [] [] []  []
        path3curNode.setNext(lastRestNode2);// [] [] [] []          []
        path4curNode.setNext(lastRestNode2);// [] [] [] [] []  []

        path1Root.setAvailable(true);
        path2Root.setAvailable(true);
        path3Root.setAvailable(true);
        path4Root.setAvailable(true);

        locationOrganizer();

        branchWithOtherNodes();
    }


    public String randomVertex(){
        if(vertexSize == 0){return null;}
        String randomVertex = null;
        while(randomVertex == null) {
            int i = (int) (Math.random() * 4);
            System.out.println("Random number: " + i);

            if (i == 0 && numberOfCombatLeft > 0) {
                randomVertex = "Combat";
                numberOfCombatLeft--;
            }
            if (i == 1 && numberOfMerchantLeft > 0) {
                randomVertex = "Merchant";
                numberOfMerchantLeft--;
            }
            if (i == 2 && numberOfTreasureLeft > 0) {
                randomVertex = "Treasure";
                numberOfTreasureLeft--;
            }
            if (i == 3 && numberOfRestLeft > 0) {
                randomVertex = "Rest";
                numberOfRestLeft--;
            }
        }
        System.out.println(randomVertex);
        return randomVertex;
    }

    public void branchWithOtherNodes(){
        int rand = (int)(Math.random() * 10);
        VertexNode[] curNodes = getPaths();


        for(int layers = 0; layers < 3; layers++) { //layer 1 to 4
            for(int path = 0; path < 4; path++ ) {  //path no
                rand = (int)(Math.random() * 10);
                if (rand < 3) { //3/10 possibility

                    if(path == 0){         curNodes[path].setAlternativeNext(curNodes[1].getNext());         }

                    if(path == 1){
                        if(curNodes[0].getAlternativeNext() == null) { curNodes[path].setAlternativeNext(curNodes[0].getNext()); }
                        else {curNodes[path].setAlternativeNext(curNodes[2].getNext());}}

                    if(path == 2){
                        if(curNodes[1].getAlternativeNext() == null) { curNodes[path].setAlternativeNext(curNodes[1].getNext()); }
                        else {curNodes[path].setAlternativeNext(curNodes[3].getNext());}}

                    if(path == 3 && curNodes[2].getAlternativeNext() == null){       curNodes[path].setAlternativeNext(curNodes[2].getNext());           }
                }
            }
            for(int i = 0; i< 4; i++){
                curNodes[i] = curNodes[i].getNext();
            }
        }
    }

    public void locationOrganizer(){
        int curLocX = mapStartAtXLocation;
        int curLocY = mapStartAtYLocation;

        for(VertexNode temp = path1Root; temp !=null ;temp = temp.getNext()){
            temp.setLocationX(curLocX + RandomLocation()); curLocX += 115;
            temp.setLocationY(curLocY + RandomLocation());
        }
        curLocY += 120; curLocX = mapStartAtXLocation;
        for(VertexNode temp = path2Root; temp !=null ;temp = temp.getNext()){
            temp.setLocationX(curLocX + RandomLocation()); curLocX += 115;
            temp.setLocationY(curLocY + RandomLocation());
        }
        curLocY += 120; curLocX = mapStartAtXLocation;
        for(VertexNode temp = path3Root; temp !=null ;temp = temp.getNext()){
            temp.setLocationX(curLocX + RandomLocation()); curLocX += 115;
            temp.setLocationY(curLocY + RandomLocation());
        }
        curLocY += 120; curLocX = mapStartAtXLocation;
        for(VertexNode temp = path4Root; temp !=null ;temp = temp.getNext()){
            temp.setLocationX(curLocX + RandomLocation()); curLocX += 115;
            temp.setLocationY(curLocY + RandomLocation());
        }
    }

    public int RandomLocation(){
        return (int)(Math.random() * 60);
    }

    //=========================================== LOAD =================================================
    public void loadMap(){
        readMap();
        setMap();
        detectNextPossibleVertices(currentMainVertex);
    }


    public void readMap(){
        try {
            path1Data = FileRead.readFile("Data.txt", "MapPath1");
            path2Data = FileRead.readFile("Data.txt", "MapPath2");
            path3Data = FileRead.readFile("Data.txt", "MapPath3");
            path4Data = FileRead.readFile("Data.txt", "MapPath4");

            path1NumbericalData = FileRead.convertToInt(  FileRead.readFile("Data.txt", "MapPathNumeric1")  );
            path2NumbericalData = FileRead.convertToInt(  FileRead.readFile("Data.txt", "MapPathNumeric2")  );
            path3NumbericalData = FileRead.convertToInt(  FileRead.readFile("Data.txt", "MapPathNumeric3")  );
            path4NumbericalData = FileRead.convertToInt(  FileRead.readFile("Data.txt", "MapPathNumeric4")  );

            currentVertex = FileRead.convertToInt(  FileRead.readFile("Data.txt", "MapCurrentVertex")  );

            //String[] a = FileRead.readFile("Data.txt", "MatPathNumeric1");
            //System.out.println(path2NumbericalData.length);
        }
        catch (IOException e){System.out.println(e);}
    }

    public void setMap(){

        // SET PATHS========================================================
        path1Root = new VertexNode(path1Data[0], null, null); path1curNode = path1Root;
        path2Root = new VertexNode(path2Data[0], null, null); path2curNode = path2Root;
        path3Root = new VertexNode(path3Data[0], null, null); path3curNode = path3Root;
        path4Root = new VertexNode(path4Data[0], null, null); path4curNode = path4Root;

        path1curNode.setPathNumber(1); path1curNode.setPathIndex(0);
        path2curNode.setPathNumber(2); path2curNode.setPathIndex(0);
        path3curNode.setPathNumber(3); path3curNode.setPathIndex(0);
        path4curNode.setPathNumber(4); path4curNode.setPathIndex(0);



        for(int i = 1; !path1Data[i].equals("null") ; i++){
            VertexNode tempNode = new VertexNode(path1Data[i],null,null);
            if(path1Data[i+1].equals("Boss")){tempNode.setPathNumber(99); tempNode.setPathIndex(1);} //rest1
            else if(path1Data[i].equals("Boss")){tempNode.setPathNumber(99); tempNode.setPathIndex(99);} //boss
            else{tempNode.setPathNumber(1); tempNode.setPathIndex(i);}
            path1curNode.setNext(tempNode); path1curNode = path1curNode.getNext();
        }


        for(int i = 1; !path2Data[i].equals("null") ; i++){
            VertexNode tempNode = new VertexNode(path2Data[i],null,null);
            if(path2Data[i+1].equals("Boss")){tempNode.setPathNumber(99); tempNode.setPathIndex(1); rest1 = tempNode;} //rest1
            else if(path2Data[i].equals("Boss")){tempNode.setPathNumber(99); tempNode.setPathIndex(99);} //boss
            else{ tempNode.setPathNumber(2); tempNode.setPathIndex(i);}
            path2curNode.setNext(tempNode); path2curNode = path2curNode.getNext();
        }

        for(int i = 1; !path3Data[i].equals("null") ; i++){
            VertexNode tempNode = new VertexNode(path3Data[i],null,null);
            if(path3Data[i+1].equals("Boss")){tempNode.setPathNumber(99); tempNode.setPathIndex(2); rest2 = tempNode;} //rest2
            else if(path3Data[i].equals("Boss")){tempNode.setPathNumber(99); tempNode.setPathIndex(99);} //boss
            else{ tempNode.setPathNumber(3); tempNode.setPathIndex(i);}
            path3curNode.setNext(tempNode); path3curNode = path3curNode.getNext();
        }

        for(int i = 1; !path4Data[i].equals("null") ; i++){
            VertexNode tempNode = new VertexNode(path4Data[i],null,null);
            if(path4Data[i+1].equals("Boss")){tempNode.setPathNumber(99); tempNode.setPathIndex(2);} //rest2
            else if(path4Data[i].equals("Boss")){tempNode.setPathNumber(99); tempNode.setPathIndex(99); boss = tempNode;} //boss
            else{  tempNode.setPathNumber(4); tempNode.setPathIndex(i); }
            path4curNode.setNext(tempNode); path4curNode = path4curNode.getNext();
        }

        for(VertexNode a = path1Root; a != null; a = a.getNext()){
            System.out.print(a.getVertex()+": "+ a.getPathNumber() +", " + a.getPathIndex() + "-------->");
        }
        System.out.println();
        for(VertexNode a = path2Root; a != null; a = a.getNext()){
            System.out.print(a.getVertex()+": "+ a.getPathNumber() +", " + a.getPathIndex() + "-------->");
        }
        System.out.println();
        for(VertexNode a = path3Root; a != null; a = a.getNext()){
            System.out.print(a.getVertex()+": "+ a.getPathNumber() +", " + a.getPathIndex() + "-------->");
        }
        System.out.println();
        for(VertexNode a = path4Root; a != null; a = a.getNext()){
            System.out.print(a.getVertex()+": "+ a.getPathNumber() +", " + a.getPathIndex() + "-------->");
        }
        System.out.println();

        // SET LOCATION AND BRANCHES========================================================

        int index = 0;
        for(VertexNode tempNode = path1Root; tempNode != null; tempNode = tempNode.getNext(), index+=5 ) {
            //BRANCHING

            if(path1NumbericalData[index+1]  > 0){ tempNode.setAlternativeNext(retrieveVertexNode(path1NumbericalData[index+1], path1NumbericalData[index+2])); }

            //LOCATION
            tempNode.setLocationX(path1NumbericalData[index+3]);
            tempNode.setLocationY(path1NumbericalData[index+4]);
        }

        index = 0;
        for(VertexNode tempNode = path2Root; tempNode != null; tempNode = tempNode.getNext(), index+=5 ) {
            //BRANCHING

            if(path2NumbericalData[index+1] > 0){ tempNode.setAlternativeNext(retrieveVertexNode(path2NumbericalData[index+1], path2NumbericalData[index+2])); }

            //LOCATION
            tempNode.setLocationX(path2NumbericalData[index+3]);
            tempNode.setLocationY(path2NumbericalData[index+4]);
        }

        index = 0;
        for(VertexNode tempNode = path3Root; tempNode != null; tempNode = tempNode.getNext(), index+=5 ) {
            //BRANCHING

            if(path3NumbericalData[index+1] > 0){ tempNode.setAlternativeNext(retrieveVertexNode(path3NumbericalData[index+1], path3NumbericalData[index+2])); }

            //LOCATION
            tempNode.setLocationX(path3NumbericalData[index+3]);
            tempNode.setLocationY(path3NumbericalData[index+4]);
        }

        index = 0;
        for(VertexNode tempNode = path4Root; tempNode != null; tempNode = tempNode.getNext(), index+=5 ) {
            //BRANCHING

            if(path4NumbericalData[index+1]  > 0){ tempNode.setAlternativeNext(retrieveVertexNode(path4NumbericalData[index+1], path4NumbericalData[index+2])); }

            //LOCATION
            tempNode.setLocationX(path4NumbericalData[index+3]);
            tempNode.setLocationY(path4NumbericalData[index+4]);
        }

       if(currentVertex[0] != -1){ currentMainVertex = retrieveVertexNode(currentVertex[0], currentVertex[1]);}

    }

    //==========================================OTHERS==================================================
    public VertexNode[] getPaths(){
        VertexNode[] paths = new VertexNode[4];
        paths[0] = path1Root;
        paths[1] = path2Root;
        paths[2] = path3Root;
        paths[3] = path4Root;
        return  paths;
    }



    public void drawMap(){

        for(int i = 0; i<path1NumbericalData.length;i++){
            if(i % 5 == 0){   System.out.print( path1Data[i/5] + ": ");  }
            System.out.print(path1NumbericalData[i] + ",");
            if(i % 5 == 4){System.out.print("---->");}
        }
        System.out.println();
        for(int i = 0; i<path2NumbericalData.length;i++){
            if(i % 5 == 0){   System.out.print( path2Data[i/5] + ": ");  }
            System.out.print(path2NumbericalData[i] + ",");
            if(i % 5 == 4){System.out.print("---->");}
        }
        System.out.println();
        for(int i = 0; i<path3NumbericalData.length;i++){
            if(i % 5 == 0){   System.out.print( path3Data[i/5] + ": ");  }
            System.out.print(path3NumbericalData[i] + ",");
            if(i % 5 == 4){System.out.print("---->");}
        }
        System.out.println();
        for(int i = 0; i<path4NumbericalData.length;i++){
            if(i % 5 == 0){   System.out.print( path4Data[i/5] + ": ");  }
            System.out.print(path4NumbericalData[i] + ",");
            if(i % 5 == 4){System.out.print("---->");}
        }
        System.out.println();

    }

    public void pickAPath(int i){
        if(i == 0){currentMainVertex = path1Root;}
        if(i == 1){currentMainVertex = path2Root;}
        if(i == 2){currentMainVertex = path3Root;}
        if(i == 3){currentMainVertex = path4Root;}
    }


    public void detectNextPossibleVertices(VertexNode vertex){
        //vertex.setAvailable(false);
        VertexNode[] paths = getPaths();
        for(int i = 0; i < 4; i++){
            for(VertexNode temp = paths[i]; temp != null; temp = temp.getNext()) {
                temp.setAvailable(false);
            }

        }

        if(vertex == null){
            path1Root.setAvailable(true);
            path2Root.setAvailable(true);
            path3Root.setAvailable(true);
            path4Root.setAvailable(true);
        }
        else{

        if(vertex.getNext() != null){vertex.getNext().setAvailable(true);}
        if(vertex.getAlternativeNext() != null){vertex.getAlternativeNext().setAvailable(true);}
         }
    }


    public void setCurrentVertex(VertexNode vertex){
        currentMainVertex = vertex;
        currentVertex[0] = vertex.getPathNumber(); currentVertex[1] = vertex.getPathIndex();
        detectNextPossibleVertices(vertex);

        //System.out.println(currentVertex[0] +" "  +currentVertex[1]);
    }

    public VertexNode retrieveVertexNode(int path, int index){
        if(path == 99 && index == 99){return boss;} //boss
        if(path == 99 && index == 1){return  rest1;} //rest1
        if(path == 99 && index == 2){return  rest2;} //rest2
        VertexNode curVertex = getPaths()[path-1];

        System.out.println(curVertex.getPathIndex());
        System.out.println(path + "  " + index);

        while(curVertex.getPathIndex() != index){
            System.out.println(curVertex.getVertex());
            curVertex = curVertex.getNext();
        }

        return curVertex;
    }


    public void prepareData() {

        path1Data = new String[7]; //Node types
        path2Data = new String[7];
        path3Data = new String[6];
        path4Data = new String[7];

        path1NumbericalData = new int[7 * 5];  //Node alternate node and locations  [nodeIndex][alternatePath][alterNatePathNodeIndex][LocX][LoxY]
        path2NumbericalData = new int[7 * 5];
        path3NumbericalData = new int[6 * 5];
        path4NumbericalData = new int[7 * 5];

        int index = 0;
        for (VertexNode temp = path1Root; temp != null; temp = temp.getNext(), index++) {
            path1Data[index] = temp.getVertex();
        }

        index = 0;
        for (VertexNode temp = path2Root; temp != null; temp = temp.getNext(), index++) {
            path2Data[index] = temp.getVertex();
        }

        index = 0;
        for (VertexNode temp = path3Root; temp != null; temp = temp.getNext(), index++) {
            path3Data[index] = temp.getVertex();
        }

        index = 0;
        for (VertexNode temp = path4Root; temp != null; temp = temp.getNext(), index++) {
            path4Data[index] = temp.getVertex();
        }

        index = 0;
        for (VertexNode temp = path1Root; temp != null; temp = temp.getNext(), index += 5) {
            path1NumbericalData[index] = temp.getPathIndex();
            if (temp.getAlternativeNext() != null) {
                path1NumbericalData[index + 1] = temp.getAlternativeNext().getPathNumber();
                path1NumbericalData[index + 2] = temp.getAlternativeNext().getPathIndex();
            } else {
                path1NumbericalData[index + 1] = -1;
                path1NumbericalData[index + 2] = -1;
            }
            path1NumbericalData[index + 3] = temp.getLocationX();
            path1NumbericalData[index + 4] = temp.getLocationY();
        }

            index = 0;
            for (VertexNode temp = path2Root; temp != null; temp = temp.getNext(), index += 5) {
                path2NumbericalData[index] = temp.getPathIndex();
                if (temp.getAlternativeNext() != null) {
                    path2NumbericalData[index + 1] = temp.getAlternativeNext().getPathNumber();
                    path2NumbericalData[index + 2] = temp.getAlternativeNext().getPathIndex();
                } else {
                    path2NumbericalData[index + 1] = -1;
                    path2NumbericalData[index + 2] = -1;
                }
                path2NumbericalData[index + 3] = temp.getLocationX();
                path2NumbericalData[index + 4] = temp.getLocationY();
            }

            index = 0;
            for (VertexNode temp = path3Root; temp != null; temp = temp.getNext(), index += 5) {
                path3NumbericalData[index] = temp.getPathIndex();
                if (temp.getAlternativeNext() != null) {
                    path3NumbericalData[index + 1] = temp.getAlternativeNext().getPathNumber();
                    path3NumbericalData[index + 2] = temp.getAlternativeNext().getPathIndex();
                } else {
                    path3NumbericalData[index + 1] = -1;
                    path3NumbericalData[index + 2] = -1;
                }
                path3NumbericalData[index + 3] = temp.getLocationX();
                path3NumbericalData[index + 4] = temp.getLocationY();
            }

            index = 0;
            for (VertexNode temp = path4Root; temp != null; temp = temp.getNext(), index += 5) {
                path4NumbericalData[index] = temp.getPathIndex();
                if (temp.getAlternativeNext() != null) {
                    path4NumbericalData[index + 1] = temp.getAlternativeNext().getPathNumber();
                    path4NumbericalData[index + 2] = temp.getAlternativeNext().getPathIndex();
                } else {
                    path4NumbericalData[index + 1] = -1;
                    path4NumbericalData[index + 2] = -1;
                }
                path4NumbericalData[index + 3] = temp.getLocationX();
                path4NumbericalData[index + 4] = temp.getLocationY();
            }
        }







}
