package me.physika.SkyStone;

import java.util.logging.Logger;
import org.bukkit.Server;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

// Referenced classes of package net.owexz.skystone: SkyStoneGenerator

public class SkyStone extends JavaPlugin
{

    public SkyStone()
    {
        log = Logger.getLogger("Minecraft");
        generator = null;
    }

    public static void log(String msg)
    {
        instance.logMessage(msg);
    }

    public void onEnable()
    {
        instance = this;
        logMessage("SkyStone Enabled.");
        PluginManager pm = getServer().getPluginManager();
    }

    public void onDisable()
    {
        logMessage("SkyStone Disabled.");
    }

    public void logMessage(String msg)
    {
        PluginDescriptionFile pdFile = getDescription();
        log.info((new StringBuilder(String.valueOf(pdFile.getName()))).append(" ").append(pdFile.getVersion()).append(": ").append(msg).toString());
    }

    public ChunkGenerator getDefaultWorldGenerator(String worldName, String worldUID)
    {
        if(generator == null)
            generator = new SkyStoneGenerator(this, worldName, worldUID);
        return generator;
    }

    public static SkyStone instance;
    public Logger log;
    private SkyStoneGenerator generator;
}