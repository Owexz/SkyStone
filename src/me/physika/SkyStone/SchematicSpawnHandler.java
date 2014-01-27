package me.physika.SkyStone;

import java.util.*;
import org.bukkit.World;

// Referenced classes of package net.owexz.skystone:
//            Schematic, SkyStone, Island, SkyStoneGenerator

public class SchematicSpawnHandler
{

    public SchematicSpawnHandler(SkyStoneGenerator gen)
    {
        schematics = new ArrayList();
        remove = new ArrayList();
        lastUpdate = System.currentTimeMillis() - 5000L;
        memory = 0;
        random = new Random();
        chunkLoaded = null;
        chunkPrepared = null;
        this.gen = gen;
    }

    public byte[] generate(World world, int chunkX, int chunkZ)
    {
        loadChunk(world, chunkX, chunkZ);
        byte blocks[] = new byte[32768];
        remove.clear();
        for(Iterator iterator = schematics.iterator(); iterator.hasNext();)
        {
            Schematic scheme = (Schematic)iterator.next();
            boolean schemeComplete = scheme.addToChunk(blocks, chunkX, chunkZ);
            if(schemeComplete)
                remove.add(scheme);
        }

        Schematic scheme;
        for(Iterator iterator1 = remove.iterator(); iterator1.hasNext(); removeSchematic(scheme))
            scheme = (Schematic)iterator1.next();

        return blocks;
    }

    private void loadChunk(World world, int chunkX, int chunkZ)
    {
        for(int i = chunkX - Schematic.maxSchematicChunkSize; i <= chunkX + Schematic.maxSchematicChunkSize; i++)
        {
            for(int k = chunkZ - Schematic.maxSchematicChunkSize; k <= chunkZ + Schematic.maxSchematicChunkSize; k++)
                prepareChunk(world, i, k);

        }

        chunkLoaded[chunkX - chunkMinX][chunkZ - chunkMinZ] = true;
    }

    private void addSchematic(Schematic scheme)
    {
        schematics.add(scheme);
        memory += scheme.length * scheme.bredth * scheme.height;
        if(System.currentTimeMillis() - lastUpdate > 5000000L)
        {
            SkyStone.log((new StringBuilder("Schematic memory usage: ")).append(getMemory()).toString());
            lastUpdate = System.currentTimeMillis();
        }
    }

    private String getMemory()
    {
        if(memory < 1024)
            return (new StringBuilder(String.valueOf(memory))).append(" bytes").toString();
        if(memory < 0x100000)
        {
            double mem = (double)memory / 1024D;
            String amount = String.valueOf(mem);
            amount = amount.substring(0, amount.indexOf(".") + 2);
            return (new StringBuilder(String.valueOf(amount))).append(" KB").toString();
        }
        if(memory < 0x40000000)
        {
            double mem = (double)memory / 1048576D;
            String amount = String.valueOf(mem);
            amount = amount.substring(0, amount.indexOf(".") + 2);
            return (new StringBuilder(String.valueOf(amount))).append(" MB").toString();
        } else
        {
            double mem = (double)memory / 1073741824D;
            String amount = String.valueOf(mem);
            amount = amount.substring(0, amount.indexOf(".") + 2);
            return (new StringBuilder(String.valueOf(amount))).append(" GB").toString();
        }
    }

    private void removeSchematic(Schematic scheme)
    {
        schematics.remove(scheme);
        memory -= scheme.length * scheme.bredth * scheme.height;
        if(System.currentTimeMillis() - lastUpdate > 5000000L)
        {
            SkyStone.log((new StringBuilder("Schematic memory usage: ")).append(getMemory()).toString());
            lastUpdate = System.currentTimeMillis();
        }
    }

    public boolean isChunkLoaded(int chunkX, int chunkZ)
    {
        if(chunkLoaded == null)
            return false;
        if(chunkX < chunkMinX || chunkX > chunkMaxX || chunkZ < chunkMinZ || chunkZ > chunkMaxZ)
            return false;
        else
            return chunkLoaded[chunkX][chunkZ];
    }

    public void prepareChunk(World world, int chunkX, int chunkZ)
    {
        if(chunkPrepared == null)
            createChunkArray(chunkX, chunkZ);
        while(chunkX < chunkMinX || chunkX > chunkMaxX || chunkZ < chunkMinZ || chunkZ > chunkMaxZ) 
            expandChunkArray(chunkX, chunkZ);
        if(chunkPrepared[chunkX - chunkMinX][chunkZ - chunkMinZ])
            return;
        long seed = ((long)chunkX * world.getSeed()) % 0x3fffffffffffffffL;
        seed += (long)chunkZ * (world.getSeed() % 0x10000L) * 512L;
        random.setSeed(seed);
        Island island = Island.createIsland(gen, world, random, chunkX, chunkZ);
        if(island != null)
        {
            checkCompletedChunks(island);
            addSchematic(island.asSchematic());
        }
        chunkPrepared[chunkX - chunkMinX][chunkZ - chunkMinZ] = true;
    }

    private void checkCompletedChunks(Schematic scheme)
    {
        for(int i = chunkMinX; i <= chunkMaxX; i++)
        {
            for(int k = chunkMinZ; k <= chunkMaxZ; k++)
                if(chunkLoaded[i - chunkMinX][k - chunkMinZ])
                    scheme.completeChunk(i, k);

        }

    }

    private void createChunkArray(int chunkX, int chunkZ)
    {
        chunkMinX = chunkX;
        chunkMinZ = chunkZ;
        chunkMaxX = chunkX;
        chunkMaxZ = chunkZ;
        chunkLoaded = new boolean[1][1];
        chunkPrepared = new boolean[1][1];
    }

    private void expandChunkArray(int x, int y)
    {
        int xOffset = 0;
        int zOffset = 0;
        int xSize = (chunkMaxX - chunkMinX) + 1;
        int zSize = (chunkMaxZ - chunkMinZ) + 1;
        int newMinX = chunkMinX;
        int newMaxX = chunkMaxX;
        int newMinZ = chunkMinZ;
        int newMaxZ = chunkMaxZ;
        if(x < chunkMinX)
        {
            xOffset = xSize;
            newMinX -= xSize;
        }
        if(y < chunkMinZ)
        {
            zOffset = zSize;
            newMinZ -= zSize;
        }
        if(x > chunkMaxX)
            newMaxX += xSize;
        if(y > chunkMaxZ)
            newMaxZ += zSize;
        boolean newChunkArray[][] = new boolean[(newMaxX - newMinX) + 1][(newMaxZ - newMinZ) + 1];
        for(int copyX = chunkMinX; copyX <= chunkMaxX; copyX++)
            System.arraycopy(chunkLoaded[copyX - chunkMinX], 0, newChunkArray[(copyX - chunkMinX) + xOffset], zOffset, zSize);

        boolean newChunkArrayP[][] = new boolean[(newMaxX - newMinX) + 1][(newMaxZ - newMinZ) + 1];
        for(int copyX = chunkMinX; copyX <= chunkMaxX; copyX++)
            System.arraycopy(chunkPrepared[copyX - chunkMinX], 0, newChunkArrayP[(copyX - chunkMinX) + xOffset], zOffset, zSize);

        chunkLoaded = newChunkArray;
        chunkPrepared = newChunkArrayP;
        chunkMinX = newMinX;
        chunkMinZ = newMinZ;
        chunkMaxX = newMaxX;
        chunkMaxZ = newMaxZ;
    }

    private ArrayList schematics;
    private ArrayList remove;
    private long lastUpdate;
    private int memory;
    private Random random;
    private int chunkMinX;
    private int chunkMaxX;
    private int chunkMinZ;
    private int chunkMaxZ;
    private boolean chunkLoaded[][];
    private boolean chunkPrepared[][];
    private SkyStoneGenerator gen;
}