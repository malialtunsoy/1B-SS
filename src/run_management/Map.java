
import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


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

    VertexNode currentMainVertex;

    public class VertexNode{
        String vertex;
        VertexNode next;
        VertexNode alternativeNext;
        int locationX;
        int locationY;
        boolean available;

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


    }


    public Map()
    {
        organizeMap();
        drawMap();
    }

    public VertexNode[] getPaths(){
        VertexNode[] paths = new VertexNode[4];
        paths[0] = path1Root;
        paths[1] = path2Root;
        paths[2] = path3Root;
        paths[3] = path4Root;
        return  paths;
    }

    public void organizeMap(){ //Total number of vertices = 18 ---> Let 8 Combats, 3 Rests, 3 Treasure, 3 Merchant and 1 Boss
                                                                        //2 Rests and boss will be the last Nodes.

        path1Root = new VertexNode("Combat", null,null); path1curNode = path1Root; //all paths start with combat  //  []
        path2Root = new VertexNode("Combat", null,null); path2curNode = path2Root;                                //  []
        path3Root = new VertexNode("Combat", null,null); path3curNode = path3Root;                                //  []
        path4Root = new VertexNode("Combat", null,null); path4curNode = path4Root;                                //  [] 4

        for(int i = 0; i < 2; i++){
            VertexNode tempNode = new VertexNode(randomVertex(), null, null);   // [] [] []
            path1curNode.setNext(tempNode); path1curNode = path1curNode.getNext();                 // [] [] []
            tempNode = new VertexNode(randomVertex(), null, null);              // [] [] []
            path2curNode.setNext(tempNode); path2curNode = path2curNode.getNext();                 // [] [] []  12
            tempNode = new VertexNode(randomVertex(), null, null);
            path3curNode.setNext(tempNode); path3curNode = path3curNode.getNext();
            tempNode = new VertexNode(randomVertex(), null, null);
            path4curNode.setNext(tempNode); path4curNode = path4curNode.getNext();
        }

        VertexNode tempNode = new VertexNode(randomVertex(), null, null);   // [] [] [] [] []
        path2curNode.setNext(tempNode); path2curNode = path2curNode.getNext();                 // [] [] [] [] []
        tempNode = new VertexNode(randomVertex(), null, null);              // [] [] [] []
        path4curNode.setNext(tempNode); path4curNode = path4curNode.getNext();                 // [] [] [] [] [] 15
        tempNode = new VertexNode(randomVertex(), null, null);
        path1curNode.setNext(tempNode); path1curNode = path1curNode.getNext();

        VertexNode bossNode = new VertexNode("Boss", null, null);    //  []
        VertexNode lastRestNode1 = new VertexNode("Rest", bossNode,null);  //     []
        VertexNode lastRestNode2 = new VertexNode("Rest", bossNode,null);  //  []

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



    public void drawMap(){

        for(VertexNode temp = path1Root; temp !=null ;temp = temp.getNext()){
            System.out.print("[" + temp.getVertex() + "  ("+temp.getLocationX()+","+temp.getLocationY()+")  ] ---> ");
        }
        System.out.println();
        for(VertexNode temp = path2Root; temp !=null ;temp = temp.getNext()){
            System.out.print("[" + temp.getVertex() + "  ("+temp.getLocationX()+","+temp.getLocationY()+")  ] ---> ");
        }
        System.out.println();
        for(VertexNode temp = path3Root; temp !=null ;temp = temp.getNext()){
            System.out.print("[" + temp.getVertex() + "  ("+temp.getLocationX()+","+temp.getLocationY()+")  ] ---> ");
        }
        System.out.println();
        for(VertexNode temp = path4Root; temp !=null ;temp = temp.getNext()){
            System.out.print("[" + temp.getVertex() + "  ("+temp.getLocationX()+","+temp.getLocationY()+")  ] ---> ");
        }
        System.out.println();


    }

    public void pickAPath(int i){
        if(i == 0){currentMainVertex = path1Root;}
        if(i == 1){currentMainVertex = path2Root;}
        if(i == 2){currentMainVertex = path3Root;}
        if(i == 3){currentMainVertex = path4Root;}
    }


    public void createVertex()
    {
        int nextI1, nextI2;
        nextI1 = 1;
        nextI2 = 2;
        String type = "Rest";
        Vertex temp = new Vertex();
        for(int i = 0; i < vertexSize; i++)
        {
            //read each row of file and define vertices
            if( type.equals("Merchant"))
            {
                //vertices[i] = new Merchant();
                temp.setType("Merchant");
            }
            else if(type.equals("Treasure"))
            {
                //vertices[i] = new Treasure;
                temp.setType("Treasure");
            }
            else if(type.equals("Rest"))
            {
                temp = new Rest();
                temp.setType("Rest");
            }
            else
            {
                //vertices[i] = new Combat;
                temp.setType("Combat");
            }
            //vertex indexes
            temp.setIndex(i);
            temp.setNextIndex1(nextI1);
            temp.setNextIndex2(nextI2);
            vertices[i] = temp;
        }
    }

    public void detectNextPossibleVertices(VertexNode vertex){
        //vertex.setAvailable(false);
        VertexNode[] paths = getPaths();
        for(int i = 0; i < 4; i++){
            for(VertexNode temp = paths[i]; temp != null; temp = temp.getNext()) {
                temp.setAvailable(false);
            }

        }

        if(vertex.getNext() != null){vertex.getNext().setAvailable(true);}
        if(vertex.getAlternativeNext() != null){vertex.getAlternativeNext().setAvailable(true);}
    }

    public void callTreasure(Vertex x) {
    }


    public void setCurrentVertex(VertexNode vertex){
        currentMainVertex = vertex;
        detectNextPossibleVertices(vertex);
    }


}
