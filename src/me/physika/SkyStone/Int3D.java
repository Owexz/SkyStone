package me.physika.SkyStone;


public class Int3D
{

    public Int3D()
    {
    }

    public Int3D(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getZ()
    {
        return z;
    }

    public void setZ(int z)
    {
        this.z = z;
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof Int3D))
            return false;
        Int3D c = (Int3D)o;
        return x == c.x && y == c.y && z == c.z;
    }

    public int x;
    public int y;
    public int z;
}