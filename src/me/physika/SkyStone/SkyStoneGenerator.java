package me.physika.SkyStone;

import java.util.*;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

// Referenced classes of package net.owexz.skystone:
//            SchematicSpawnHandler, Int3D, ReplacePopulator, SkyStone

public class SkyStoneGenerator extends ChunkGenerator
{

    public SkyStoneGenerator(SkyStone plugin, String worldName, String worldUID)
    {
        this.worldName = "";
        this.worldUID = "";
        this.plugin = plugin;
        this.worldName = worldName;
        this.worldUID = worldUID;
        spawner = new SchematicSpawnHandler(this);
    }

    public Location getFixedSpawnLocation(World world, Random random)
    {
        if(spawn == null)
        {
            spawner.prepareChunk(world, 0, 0);
            if(spawn == null)
                spawn = new Int3D(8, 90, 8);
        }
        return new Location(world, (spawn.x + random.nextInt(32)) - 16, (spawn.y + random.nextInt(32)) - 16, (spawn.z + random.nextInt(32)) - 16);
    }

    public List getDefaultPopulators(World world)
    {
        ArrayList list = new ArrayList();
        list.add(new ReplacePopulator(plugin));
        return list;
    }

    public byte[] generate(World world, Random random, int chunkX, int chunkZ)
    {
        return spawner.generate(world, chunkX, chunkZ);
    }

    SkyStone plugin;
    String worldName;
    String worldUID;
    SchematicSpawnHandler spawner;
    public Int3D spawn;
}