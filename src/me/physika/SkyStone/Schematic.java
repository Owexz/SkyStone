package me.physika.SkyStone;

import org.bukkit.Material;

public class Schematic
{

    public Schematic(int x, int y, int z, int length, int height, int bredth)
    {
        this.length = 0;
        this.bredth = 0;
        this.height = 0;
        this.length = length;
        this.bredth = bredth;
        this.height = height;
        this.x = x;
        this.y = y;
        this.z = z;
        blocks = new byte[length * bredth * height];
        chunkXMin = floor((double)x / 16D) - 1;
        chunkZMin = floor((double)z / 16D) - 1;
        chunkXMax = floor((double)(x + length) / 16D);
        chunkZMax = floor((double)(z + bredth) / 16D);
        chunkComplete = new boolean[(chunkXMax - chunkXMin) + 1][(chunkZMax - chunkZMin) + 1];
    }

    public void setBlock(int x, int y, int z, byte material)
    {
        blocks[(x * bredth + z) * height + y] = material;
    }

    public byte getBlock(int x, int y, int z)
    {
        return blocks[(x * bredth + z) * height + y];
    }

    public void setBlockSafe(int x, int y, int z, byte material)
    {
        if(x >= 0 && x < length && z >= 0 && z < bredth && y >= 0 && y < height)
            blocks[(x * bredth + z) * height + y] = material;
    }

    public byte getBlockSafe(int x, int y, int z)
    {
        if(x >= 0 && x < length && z >= 0 && z < bredth && y >= 0 && y < height)
            return blocks[(x * bredth + z) * height + y];
        else
            return 0;
    }

    public int getLength()
    {
        return length;
    }

    public int getBredth()
    {
        return bredth;
    }

    public int getHeight()
    {
        return height;
    }

    public byte[] getBlocks()
    {
        return blocks;
    }

    public int floor(double d)
    {
        if(d >= 0.0D)
            return (int)d;
        else
            return (int)d - 1;
    }

    public int ceil(double d)
    {
        if(d >= 0.0D)
            return (int)d + 1;
        else
            return (int)d;
    }

    public boolean addToChunk(byte chunk[], int chunkX, int chunkZ)
    {
        if(chunkX >= chunkXMin && chunkX <= chunkXMax && chunkZ >= chunkZMin && chunkZ <= chunkZMax)
        {
            int minX = this.x - chunkX * 16;
            if(minX < 0)
                minX = 0;
            int minZ = this.z - chunkZ * 16;
            if(minZ < 0)
                minZ = 0;
            int maxX = (this.x + length) - chunkX * 16;
            if(maxX > 16)
                maxX = 16;
            int maxZ = (this.z + bredth) - chunkZ * 16;
            if(maxZ > 16)
                maxZ = 16;
            int initX = chunkX * 16 - this.x;
            int initZ = chunkZ * 16 - this.z;
            for(int i = minX; i < maxX; i++)
            {
                for(int k = minZ; k < maxZ; k++)
                {
                    int x = initX + i;
                    int z = initZ + k;
                    int maxY = 128 - y;
                    if(maxY > height)
                        maxY = height;
                    int minY = 0 - y;
                    if(minY < 0)
                        minY = 0;
                    int blockIndex = (x * bredth + z) * height;
                    int chunkIndex = (i * 16 + k) * 128 + y;
                    for(int j = minY; j < maxY; j++)
                        if(blocks[blockIndex + j] != 0 && chunk[chunkIndex + j] != caveSpace)
                            chunk[chunkIndex + j] = blocks[blockIndex + j];

                }

            }

            chunkComplete[chunkX - chunkXMin][chunkZ - chunkZMin] = true;
        }
        for(int i = 0; i <= chunkXMax - chunkXMin; i++)
        {
            for(int k = 0; k <= chunkZMax - chunkZMin; k++)
                if(!chunkComplete[i][k])
                    return false;

        }

        return true;
    }

    public void completeChunk(int chunkX, int chunkZ)
    {
        if(chunkX >= chunkXMin && chunkX <= chunkXMax && chunkZ >= chunkZMin && chunkZ <= chunkZMax)
            chunkComplete[chunkX - chunkXMin][chunkZ - chunkZMin] = true;
    }

    protected static final byte caveSpace;
    public static int maxSchematicChunkSize = 16;
    protected int length;
    protected int bredth;
    protected int height;
    private int x;
    private int y;
    private int z;
    private int chunkXMin;
    private int chunkXMax;
    private int chunkZMin;
    private int chunkZMax;
    private boolean chunkComplete[][];
    protected byte blocks[];

    static 
    {
        caveSpace = (byte)Material.EMERALD_BLOCK.getId();
    }
}