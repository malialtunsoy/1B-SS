
public class Map {
    int vertexSize = 50;
    int currentPosition;
    Vertex[] vertices = new Vertex[vertexSize];
    Player p;

    public Map(Player p)
    {
        createVertex();
        this.p = p;
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

    public void chooseVertex( int index )
    {
        System.out.println(vertices[index].getType());
        if( vertices[index].getLock())
        {
            String type = vertices[index].getType();
            if (type.equals("Merchant")) {
                callMerchant(vertices[index]);
            } else if (type.equals("Treasure")) {
                callTreasure(vertices[index]);
            } else if (type.equals("Rest")) {
                Vertex temp = vertices[index];
                callRest( (Rest) temp );

            } else {
                callCombat(vertices[index]);
            }
            System.out.println("Your vertex is " + type);
            System.out.println(index + " choosed");
            System.out.println("Next vertices are: " + vertices[index].getNextIndex1() + " and " + vertices[index].getNextIndex2());
        }
        else
            System.out.println("Locked");
    }
    public void updateLocks(int opt1,int opt2,int opt3,int opt4)
    {
        vertices[vertices[opt1].getNextIndex1()].changeLock();
        vertices[vertices[opt1].getNextIndex2()].changeLock();
        vertices[opt2].changeLock();
        vertices[opt3].changeLock();
        vertices[opt4].changeLock();
    }
    public void updateLocks(int opt1, int opt2)
    {
        vertices[vertices[opt1].getNextIndex1()].changeLock();
        vertices[vertices[opt1].getNextIndex2()].changeLock();
        vertices[opt2].changeLock();
    }

    //will give needed properties to these vertices
    public void callMerchant( Vertex x )
    {

    }
    public void callTreasure(Vertex x)
    {

    }
    public void callRest(Rest x)
    {
        System.out.println("1 for rest, 2 for upgrade.");
        x.healHP(p);
    }
    public void callCombat(Vertex x)
    {

    }
}
