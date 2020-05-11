
public class Vertex {
    int index, nextIndex1, nextIndex2;
    String type;
    boolean lock;

    public Vertex()
    {
        lock = true;
    }

    public void setIndex(int x)
    {
        index = x;
    }
    public void setNextIndex1(int x)
    {
        nextIndex1 = x;
    }
    public void setNextIndex2(int x)
    {
        nextIndex2 = x;
    }
    public int getNextIndex1()
    {return nextIndex1;}
    public int getNextIndex2()
    {return nextIndex2;}
    public String getType()
    {
        return type;
    }
    public void setType(String temp)
    {
        type = temp;
    }
    public boolean getLock()
    {return lock;}

    public void changeLock()
    {
        if(lock == true)
            lock = false;
        else
            lock = true;
    }


}
