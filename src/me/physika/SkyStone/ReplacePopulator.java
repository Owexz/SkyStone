package me.physika.SkyStone;

import java.util.Random;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

// Referenced classes of package net.owexz.skystone:
//            SkyStone

public class ReplacePopulator extends BlockPopulator
{

    public ReplacePopulator(SkyStone plugin)
    {
        this.plugin = plugin;
    }

    public void populate(World world, Random random, Chunk chunk)
    {
        for(int x = 0; x < 16; x++)
        {
            for(int y = 0; y < 128; y++)
            {
                for(int z = 0; z < 16; z++)
                {
                    Block block = chunk.getBlock(x, y, z);
                    if(block.getType() == Material.SPONGE)
                    {
                        block.setType(Material.AIR);
                        world.generateTree(block.getLocation(), TreeType.REDWOOD);
                    } else
                    if(block.getType() == Material.BEDROCK)
                    {
                        block.setType(Material.AIR);
                        world.generateTree(block.getLocation(), TreeType.BIRCH);
                    } else
                    if(block.getType() == Material.CAULDRON)
                    {
                        block.setType(Material.AIR);
                        world.generateTree(block.getLocation(), TreeType.JUNGLE);
                    } else
                    if(block.getType() == Material.BOOKSHELF)
                    {
                        block.setType(Material.AIR);
                        world.generateTree(block.getLocation(), TreeType.SMALL_JUNGLE);
                    } else
                    if(block.getType() == Material.BRICK_STAIRS)
                    {
                    block.setType(Material.AIR);
                    world.generateTree(block.getLocation(), TreeType.JUNGLE_BUSH);
                    } else
                    if(block.getType() == Material.BRICK)
                    {
                        block.setType(Material.AIR);
                        world.generateTree(block.getLocation(), TreeType.TREE);
                    } else
                    if(block.getType() == Material.DIAMOND_BLOCK)
                    {
                        block.setType(Material.AIR);
                        world.generateTree(block.getLocation(), TreeType.BIRCH);
                    } else
                    if(block.getType() == Material.LAPIS_BLOCK)
                    {
                        block.setType(Material.AIR);
                        world.generateTree(block.getLocation(), TreeType.REDWOOD);
                    } else
                    if(block.getType() == Material.SPRUCE_WOOD_STAIRS)
                    {
                    block.setType(Material.AIR);
                    world.generateTree(block.getLocation(), TreeType.MEGA_REDWOOD);
                    } else
                  	if(block.getType() == Material.ACACIA_STAIRS)
                    {
                    block.setType(Material.AIR);
                    world.generateTree(block.getLocation(), TreeType.ACACIA);
                    } else
                    if(block.getType() == Material.WEB)
                    {
                        block.setType(Material.AIR);
                        world.generateTree(block.getLocation(), TreeType.SWAMP);
                    } else
                    if(block.getType() == Material.JUKEBOX)
                    {
                    block.setType(Material.AIR);
                    world.generateTree(block.getLocation(), TreeType.DARK_OAK);
                    } else
                    if(block.getType() == Material.GOLD_BLOCK)
                    {
                        block.setType(Material.AIR);
                        world.generateTree(block.getLocation(), TreeType.BROWN_MUSHROOM);
                    } else
                    if(block.getType() == Material.IRON_BLOCK)
                    {
                        block.setType(Material.AIR);
                        world.generateTree(block.getLocation(), TreeType.RED_MUSHROOM);
                    } else                    
                    if(block.getType() == Material.DISPENSER)
                    {
                        block.setType(Material.EMERALD_ORE);
                    } else
                    if(block.getType() == Material.CHEST)
                    {
                        block.setType(Material.LONG_GRASS);
                        block.setData((byte)0);
                    } else
                    if(block.getType() == Material.WORKBENCH)
                    {
                        block.setType(Material.LONG_GRASS);
                        block.setData((byte)1);
                    } else
                    if(block.getType() == Material.FURNACE)
                    {
                        block.setType(Material.LONG_GRASS);
                        block.setData((byte)2);
                    } else                    
                    if(block.getType() == Material.BREWING_STAND)
                    {
                        block.setType(Material.YELLOW_FLOWER);
                    } else	                   
                    if(block.getType() == Material.GLASS)
                    {
                        block.setType(Material.RED_ROSE);
                    } else                   
                    if(block.getType() == Material.QUARTZ_BLOCK)
                    {
                        block.setType(Material.RED_ROSE);
                        block.setData((byte)1);
                    } else
                    if(block.getType() == Material.QUARTZ_STAIRS)
                    {
                        block.setType(Material.RED_ROSE);
                        block.setData((byte)2);
                    } else	                    
                    if(block.getType() == Material.COBBLESTONE_STAIRS)
                    {
                        block.setType(Material.RED_ROSE);
                        block.setData((byte)3);
                    } else	                    
                    if(block.getType() == Material.COBBLE_WALL)
                    {
                        block.setType(Material.RED_ROSE);
                        block.setData((byte)4);
                    } else	                   
                    if(block.getType() == Material.DAYLIGHT_DETECTOR)
                    {
                        block.setType(Material.RED_ROSE);
                        block.setData((byte)5);
                    } else	                    
                    if(block.getType() == Material.FENCE)
                    {
                        block.setType(Material.RED_ROSE);
                        block.setData((byte)6);
                    } else	                    
                    if(block.getType() == Material.FENCE_GATE)
                    {
                        block.setType(Material.RED_ROSE);
                        block.setData((byte)7);
                    } else	                    
                    if(block.getType() == Material.FLOWER_POT)
                    {
                        block.setType(Material.RED_ROSE);
                        block.setData((byte)8);
                    } else	                   
                    if(block.getType() == Material.GLASS)
                    {
                        block.setType(Material.DOUBLE_PLANT);
                    } else	                    
                    if(block.getType() == Material.HOPPER)
                    {
                        block.setType(Material.DOUBLE_PLANT);
                        block.setData((byte)1);
                    } else	                   
                    if(block.getType() == Material.HAY_BLOCK)
                    {
                        block.setType(Material.DOUBLE_PLANT);
                        block.setData((byte)4);
                    } else	                    
                    if(block.getType() == Material.JACK_O_LANTERN)
                    {
                        block.setType(Material.DOUBLE_PLANT);
                        block.setData((byte)5);
                    } else	
                    if(block.getType() == Material.COAL_BLOCK)
                    {
                        block.setType(Material.DIRT);
                        block.setData((byte)2);
                    } else                    
                    if(block.getType() == Material.NOTE_BLOCK)
                    {
                        block.setType(Material.SAND);
                        block.setData((byte)1);
                    } else 
                    if(block.getType() == Material.EMERALD_BLOCK)
                        block.setType(Material.AIR);

                }

            }

        }

    }

    SkyStone plugin;
}