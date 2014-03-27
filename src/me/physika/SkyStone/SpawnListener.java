package me.physika.SkyStone;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import me.physika.SkyStone.SkyStoneGenerator;

// Referenced classes of package net.owexz.skystone:
//            SkyStone

public class SpawnListener
    implements Listener
{

    public SpawnListener(SkyStone skyStone)
    {
        plugin = skyStone;
    }

    public void onCreatureSpawn(CreatureSpawnEvent event)
    {
        Entity entity = event.getEntity();
        if(entity == null)
            return;
        if((entity instanceof Monster) && Math.random() > 0.125D && event.getWorld().getGenerator() instanceof SkyStoneGenerator)
            event.setCancelled(true);
    }

    SkyStone plugin;
}
