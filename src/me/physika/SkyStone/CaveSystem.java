package me.physika.SkyStone;

import java.util.*;

// Referenced classes of package net.owexz.skystone:
//            Island, BiomeDescription, CaveDescription, CaveNode

public class CaveSystem
{

    public CaveSystem(Island island, Random random, CaveNode start, int maxCaveSpace)
    {
        splits = 1.0D;
        segments = 0;
        startingNode = start;
        currentEnds = new ArrayList();
        allNodes = new ArrayList();
        this.island = island;
        length = island.length;
        bredth = island.bredth;
        height = island.height;
        description = island.biome.caveDescription;
        stayBelow = (int)((double)island.centerY + island.biome.roughness / 2D);
        this.random = random;
        currentEnds.add(start);
        allNodes.add(start);
        for(int minSegments = (int)(((double)maxCaveSpace / (3.1415926535897931D * description.startRadius * description.startRadius)) * 2D); segments < minSegments && currentEnds.size() > 0; expand());
    }

    public void expand()
    {
        int selectedIndex = random.nextInt(currentEnds.size());
        CaveNode selectedEnd = (CaveNode)currentEnds.get(selectedIndex);
        currentEnds.remove(selectedIndex);
        int number;
        for(number = 1; number < description.maxSplit;)
        {
            if(random.nextDouble() >= description.splitChance / splits)
                break;
            number++;
            splits++;
        }

        double swayDX = description.angleDX * (double)number * (double)number;
        double swayDY = description.angleDY * (double)number * (double)number;
        for(int i = 0; i < number; i++)
        {
            double x = selectedEnd.x + Math.cos(selectedEnd.angleX) * Math.cos(selectedEnd.angleY) * 2D;
            double z = selectedEnd.z + Math.sin(selectedEnd.angleX) * Math.cos(selectedEnd.angleY) * 2D;
            double y = selectedEnd.y + Math.sin(selectedEnd.angleY) * 2D;
            if(x >= 0.0D && y >= 0.0D && z >= 0.0D && x < length && y < height && y < (double)stayBelow && z < bredth && island.getBlockSafe((int)x, (int)y, (int)z) != 0)
            {
                double radius = selectedEnd.radius + (random.nextDouble() * 2D - 1.0D) * description.maxDeltaRadius;
                if(radius < description.minRadius)
                    radius = description.minRadius;
                if(radius > description.maxRadius)
                    radius = description.maxRadius;
                double angleX;
                double angleY;
                if(number > 1)
                {
                    double a = random.nextDouble() * 3.1415926535897931D * 2D;
                    angleX = selectedEnd.angleX + Math.cos(a) * swayDX;
                    angleY = selectedEnd.angleY + Math.sin(a) * swayDY;
                } else
                {
                    angleX = selectedEnd.angleX + (random.nextDouble() * 2D - 1.0D) * swayDX;
                    angleY = selectedEnd.angleY + (random.nextDouble() * 2D - 1.0D) * swayDY;
                }
                if(angleY < -description.maxAngleY)
                    angleY = -description.maxAngleY;
                if(angleY > description.maxAngleY)
                    angleY = description.maxAngleY;
                CaveNode node = new CaveNode(x, y, z, angleX, angleY, radius);
                currentEnds.add(node);
                allNodes.add(node);
                segments++;
            }
        }

    }

    public byte[] carve(byte blocks[])
    {
        int length = (int)this.length;
        int bredth = (int)this.bredth;
        int height = (int)this.height;
        for(Iterator iterator = allNodes.iterator(); iterator.hasNext();)
        {
            CaveNode n = (CaveNode)iterator.next();
            int xmin = (int)(n.x - n.radius);
            int ymin = (int)(n.y - n.radius);
            int zmin = (int)(n.z - n.radius);
            int xmax = (int)(n.x + n.radius);
            int ymax = (int)(n.y + n.radius);
            int zmax = (int)(n.z + n.radius);
            if(xmin < 0)
                xmin = 0;
            if(ymin < 0)
                ymin = 0;
            if(zmin < 0)
                zmin = 0;
            if(xmax > length)
                xmax = length;
            if(ymax > height)
                ymax = height;
            if(zmax > bredth)
                zmax = bredth;
            for(int i = xmin; i < xmax; i++)
            {
                for(int j = ymin; j < ymax; j++)
                {
                    for(int k = zmin; k < zmax; k++)
                        if(isInRange(i, j, k, n.x, n.y, n.z, n.radius))
                            blocks[(i * bredth + k) * height + j] = Island.caveSpace;

                }

            }

        }

        return blocks;
    }

    public boolean isInRange(double node1X, double node1Y, double node1Z, double node2X, double node2Y, double node2Z, double radius)
    {
        double distX = Math.abs(node1X - node2X);
        double distY = Math.abs(node1Y - node2Y);
        double distZ = Math.abs(node1Z - node2Z);
        if(distX > radius || distY > radius || distZ > radius)
            return false;
        if(distX + distY + distZ < radius)
            return true;
        return distX * distX + distY * distY + distZ * distZ < radius * radius;
    }

    ArrayList currentEnds;
    ArrayList allNodes;
    CaveNode startingNode;
    CaveDescription description;
    double length;
    double height;
    double bredth;
    double splits;
    int segments;
    Random random;
    int stayBelow;
    Island island;
}