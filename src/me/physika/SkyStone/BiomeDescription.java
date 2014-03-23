package me.physika.SkyStone;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Biome;

public class BiomeDescription {
	
	private static BiomeDescription standard = new BiomeDescription();
	private static BiomeDescription beach = new BiomeDescription();
	private static BiomeDescription birch_forest = new BiomeDescription();
	private static BiomeDescription birch_forest_hills = new BiomeDescription();
	private static BiomeDescription birch_forest_hills_mountains = new BiomeDescription();
	private static BiomeDescription birch_forest_mountains = new BiomeDescription();
	private static BiomeDescription cold_beach = new BiomeDescription();
	private static BiomeDescription cold_taiga = new BiomeDescription();
	private static BiomeDescription cold_taiga_hills = new BiomeDescription();
	private static BiomeDescription cold_taiga_mountains = new BiomeDescription();
	private static BiomeDescription deep_ocean = new BiomeDescription();
	private static BiomeDescription desert = new BiomeDescription();
	private static BiomeDescription desert_hills = new BiomeDescription();
	private static BiomeDescription desert_mountains = new BiomeDescription();
	private static BiomeDescription extreme_hills = new BiomeDescription();
	private static BiomeDescription extreme_hills_mountains = new BiomeDescription();
	private static BiomeDescription extreme_hills_plus = new BiomeDescription();
	private static BiomeDescription extreme_hills_plus_mountains = new BiomeDescription();
	private static BiomeDescription flower_forest = new BiomeDescription();
	private static BiomeDescription forest = new BiomeDescription();
	private static BiomeDescription forest_hills = new BiomeDescription();
	private static BiomeDescription frozen_ocean = new BiomeDescription();
	private static BiomeDescription frozen_river = new BiomeDescription();
	private static BiomeDescription hell = new BiomeDescription();
	private static BiomeDescription ice_mountains = new BiomeDescription();
	private static BiomeDescription ice_plains = new BiomeDescription();
	private static BiomeDescription ice_plains_spikes = new BiomeDescription();
	private static BiomeDescription jungle = new BiomeDescription();
	private static BiomeDescription jungle_edge = new BiomeDescription();
	private static BiomeDescription jungle_edge_mountains = new BiomeDescription();
	private static BiomeDescription jungle_hills = new BiomeDescription();
	private static BiomeDescription jungle_mountains = new BiomeDescription();
	private static BiomeDescription mega_spruce_taiga = new BiomeDescription();
	private static BiomeDescription mega_spruce_taiga_hills = new BiomeDescription();
	private static BiomeDescription mega_taiga = new BiomeDescription();
	private static BiomeDescription mega_taiga_hills = new BiomeDescription();
	private static BiomeDescription mesa = new BiomeDescription();
	private static BiomeDescription mesa_bryce = new BiomeDescription();
	private static BiomeDescription mesa_plateau = new BiomeDescription();
	private static BiomeDescription mesa_plateau_forest = new BiomeDescription();
	private static BiomeDescription mesa_plateau_forest_mountains = new BiomeDescription();
	private static BiomeDescription mesa_plateau_mountains = new BiomeDescription();
	private static BiomeDescription mushroom_island = new BiomeDescription();
	private static BiomeDescription mushroom_shore = new BiomeDescription();
	private static BiomeDescription ocean = new BiomeDescription();
	private static BiomeDescription plains = new BiomeDescription();
	private static BiomeDescription river = new BiomeDescription();
	private static BiomeDescription roofed_forest = new BiomeDescription();
	private static BiomeDescription roofed_forest_mountains = new BiomeDescription();
	private static BiomeDescription savanna = new BiomeDescription();
	private static BiomeDescription savanna_mountains = new BiomeDescription();
	private static BiomeDescription savanna_plateau = new BiomeDescription();
	private static BiomeDescription savanna_plateau_mountains = new BiomeDescription();
	private static BiomeDescription sky = new BiomeDescription();
	private static BiomeDescription small_mountains = new BiomeDescription();
	private static BiomeDescription stone_beach = new BiomeDescription();
	private static BiomeDescription sunflower_plains = new BiomeDescription();
	private static BiomeDescription swampland = new BiomeDescription();
	private static BiomeDescription swampland_mountains = new BiomeDescription();
	private static BiomeDescription taiga = new BiomeDescription();
	private static BiomeDescription taiga_hills = new BiomeDescription();
	private static BiomeDescription taiga_mountains = new BiomeDescription();
	
	static {
		
	//Beach
	beach.islandRockMaterial = (byte)Material.STONE.getId();
	beach.islandSoilMaterial = (byte)Material.SAND.getId();
	beach.islandSurfaceMaterial = (byte)Material.SAND.getId();
	beach.lakeChance = 1.0;
	beach.treeDensity = 0.0;
	beach.cliffs = false;
	beach.roughness = 6D;
	beach.frequency = 0.3;
	beach.cactusDensity = 0.00;
        beach.shrubDensity = 0.02;
        beach.shrubType = new byte[1];
        beach.shrubTypeChance = (new double[] {1.0});
        beach.pondWaterChance = 0.0;
        beach.pondLavaChance = 0.0;
        beach.lavaFallChance = 0.0;
        beach.chanceOfCave = 0.0;
        
		//Birch birch_forest
        birch_forest.cliffs = false;
        birch_forest.roughness = 8;
        birch_forest.treeDensity = 0.08;
        birch_forest.treeType = (new TreeType[] {TreeType.BIRCH});
        birch_forest.treeTypeChance = (new double[] {0.1});
        birch_forest.lakeChance = 0.2;
        birch_forest.shrubDensity = 0.3;
        birch_forest.shrubType = (new byte[] {1, 2});
        birch_forest.shrubTypeChance = (new double[] {0.7, 0.3});
		
		//Birch Forest Hills
        birch_forest_hills.cliffs = true;
        birch_forest_hills.roughness = 12;
        birch_forest_hills.treeDensity = 0.08;
        birch_forest.treeType = (new TreeType[] {TreeType.BIRCH});
        birch_forest.treeTypeChance = (new double[] {0.1});
        birch_forest_hills.lakeChance = 0.2;
        birch_forest_hills.shrubDensity = 0.3;
        birch_forest_hills.shrubType = (new byte[] {1, 2});
        birch_forest_hills.shrubTypeChance = (new double[] {0.7, 0.3});
		
		//Birch Forest Hills Mountains
        birch_forest_hills_mountains.cliffs = true;
        birch_forest_hills_mountains.roughness = 20;
        birch_forest_hills_mountains.treeDensity = 0.08;
        birch_forest.treeType = (new TreeType[] {TreeType.BIRCH});
        birch_forest.treeTypeChance = (new double[] {0.1});
        birch_forest_hills_mountains.lakeChance = 0.2;
        birch_forest_hills_mountains.shrubDensity = 0.3;
        birch_forest_hills_mountains.shrubType = (new byte[] {1, 2});
        birch_forest_hills_mountains.shrubTypeChance = (new double[] {0.7, 0.3});
		
		//Birch Forest Mountains
        birch_forest_mountains.cliffs = false;
        birch_forest_mountains.roughness = 20;
        birch_forest_mountains.treeDensity = 0.08;
        birch_forest.treeType = (new TreeType[] {TreeType.BIRCH});
        birch_forest.treeTypeChance = (new double[] {0.1});
        birch_forest_mountains.lakeChance = 0.2;
        birch_forest_mountains.shrubDensity = 0.3;
        birch_forest_mountains.shrubType = (new byte[] {1, 2});
        birch_forest_mountains.shrubTypeChance = (new double[] {0.7, 0.3});
		
	//Cold Beach
	cold_beach.cliffs = true;
	cold_beach.lakeChance = 1.0;
	cold_beach.treeDensity = 0.002;
	cold_beach.treeType = frozen_ocean.treeType;
	cold_beach.treeTypeChance = frozen_ocean.treeTypeChance;
	cold_beach.roughness = 6.0;
	cold_beach.frequency = 0.3;
	cold_beach.snow = true;
	cold_beach.islandRockMaterial = (byte) Material.STONE.getId();
	cold_beach.islandSoilMaterial = (byte) Material.SAND.getId();
	cold_beach.islandSurfaceMaterial = (byte) Material.SAND.getId();
	cold_beach.islandSoilDepth = 0.5;
	cold_beach.chanceOfCave = 0.0;
		
	//Cold Taiga
	cold_taiga.cliffs = false;
	cold_taiga.lakeChance = 0.01;
	cold_taiga.treeDensity = 0.001;
	cold_taiga.treeType = (new TreeType[] {TreeType.REDWOOD, TreeType.TALL_REDWOOD});
	cold_taiga.treeTypeChance = (new double[] {0.2, 0.8});
	cold_taiga.roughness = 4.0;
	cold_taiga.frequency = 0.2;
	cold_taiga.shrubDensity = 0.6;
	cold_taiga.shrubType = new byte[] {1, 2};
	cold_taiga.shrubTypeChance = new double[] {0.9, 0.1};
	cold_taiga.snow = true;
		
	//Cold Taiga Hills
	cold_taiga_hills.cliffs = true;
	cold_taiga_hills.lakeChance = 0.01;
	cold_taiga_hills.treeDensity = 0.001;
	cold_taiga_hills.treeType = (new TreeType[] {TreeType.REDWOOD, TreeType.TALL_REDWOOD});
	cold_taiga_hills.treeTypeChance = (new double[] {0.2, 0.8});
	cold_taiga_hills.roughness = 16.0;
	cold_taiga_hills.frequency = 0.6;
	cold_taiga_hills.shrubDensity = 0.6;
	cold_taiga_hills.shrubType = new byte[] {1, 2};
	cold_taiga_hills.shrubTypeChance = new double[] {0.9, 0.1};
	cold_taiga.snow = true;
		
	//Cold Taiga Mountains
	cold_taiga_mountains.cliffs = false;
	cold_taiga_mountains.lakeChance = 0.01;
	cold_taiga_mountains.treeDensity = 0.001;
	cold_taiga_mountains.treeType = (new TreeType[] {TreeType.REDWOOD, TreeType.TALL_REDWOOD});
	cold_taiga_mountains.treeTypeChance = (new double[] {0.2, 0.8});
	cold_taiga_mountains.roughness = 20.0;
	cold_taiga_mountains.frequency = 0.8;
	cold_taiga_mountains.shrubDensity = 0.6;
	cold_taiga_mountains.shrubType = new byte[] {1, 2};
	cold_taiga_mountains.shrubTypeChance = new double[] {0.9, 0.1};
	cold_taiga.snow = true;
		
	//Deep Ocean
	deep_ocean.chanceOfIsland = 0.0; //Lakes are not generating in Oceans, hence no islands will generate for the time being.
	deep_ocean.islandRockMaterial = (byte)Material.STONE.getId();
	deep_ocean.islandSoilMaterial = (byte)Material.SAND.getId();
	deep_ocean.islandSurfaceMaterial = (byte)Material.SAND.getId();
	deep_ocean.lakeChance = 1.0;
	deep_ocean.treeDensity = 0.0;
	deep_ocean.cliffs = false;
	deep_ocean.roughness = 2.0;
	deep_ocean.frequency = 0.3;
	deep_ocean.cactusDensity = 0.0;
        deep_ocean.shrubDensity = 0.0;
        deep_ocean.pondWaterChance = 0.0;
        deep_ocean.pondLavaChance = 0.0;
        deep_ocean.lavaFallChance = 0.0;
        deep_ocean.chanceOfCave = 0.0;
		
		//Desert
	desert.islandRockMaterial = (byte)Material.SANDSTONE.getId();
        desert.islandSoilMaterial = (byte)Material.SAND.getId();
        desert.islandSurfaceMaterial = (byte)Material.SAND.getId();
        desert.lakeChance = 0.0;
        desert.treeDensity = 0.0;
        desert.cliffs = false;
        desert.roughness = 6;
        desert.frequency = 0.3;
        desert.cactusDensity = 0.01;
        desert.shrubDensity = 0.02;
        desert.shrubType = new byte[1];
        desert.shrubTypeChance = (new double[] {1.0});
        desert.pondWaterChance = 0.0;
        desert.pondLavaChance = 0.0;
        desert.lavaFallChance = 0.0;
        
		//Desert Hills
	desert.islandRockMaterial = (byte)Material.SANDSTONE.getId();
        desert.islandSoilMaterial = (byte)Material.SAND.getId();
        desert.islandSurfaceMaterial = (byte)Material.SAND.getId();
        desert.lakeChance = 0.0;
        desert.treeDensity = 0.0;
        desert.cliffs = true;
        desert.roughness = 10;
        desert.frequency = 0.5;
        desert.cactusDensity = 0.01;
        desert.shrubDensity = 0.02;
        desert.shrubType = new byte[1];
        desert.shrubTypeChance = (new double[] {1.0});
        desert.pondWaterChance = 0.0;
        desert.pondLavaChance = 0.0;
        desert.lavaFallChance = 0.0;
        
		//Desert Mountains
	desert.islandRockMaterial = (byte)Material.SANDSTONE.getId();
        desert.islandSoilMaterial = (byte)Material.SAND.getId();
        desert.islandSurfaceMaterial = (byte)Material.SAND.getId();
        desert.lakeChance = 0.0;
        desert.treeDensity = 0.0;
        desert.cliffs = false;
        desert.roughness = 20;
        desert.frequency = 0.6;
        desert.cactusDensity = 0.01;
        desert.shrubDensity = 0.02;
        desert.shrubType = new byte[1];
        desert.shrubTypeChance = (new double[] {1.0});
        desert.pondWaterChance = 0.0;
        desert.pondLavaChance = 0.0;
        desert.lavaFallChance = 0.0;
		
		//Extreme Hills
        extreme_hills.treeDensity = 0.01;
        extreme_hills.treeType = (new TreeType[] {TreeType.TREE, TreeType.REDWOOD});
        extreme_hills.treeTypeChance = (new double[] {0.8, 0.2});
        extreme_hills.lakeChance = 0.05;
        extreme_hills.roughness = 20;
        extreme_hills.frequency = 0.6;
        extreme_hills.islandSizeMin = 64;
        extreme_hills.islandSizeMax = 128;
        extreme_hills.shrubDensity = 0.3;
        extreme_hills.shrubType = (new byte[] {1, 2});
        extreme_hills.shrubTypeChance = (new double[] {0.7, 0.3});
        extreme_hills.pondWaterChance = 0.0;
        extreme_hills.pondLavaChance = 0.0;
        extreme_hills.oreTypeMaterial = new byte[] {13, 16, 15, 14, 73, 21, 56, 35, 23, 97};				//Ores	(in order: {gravel, coal, iron, gold, lapis, redstone, diamond, emerald(replaced), silverfish}
        extreme_hills.oreTypeChance = new double[] {0.002, 0.0012, 0.0005, 0.00025, 0.00025, 0.0005, 0.0001, 0.00005,  0.00005}; 
        extreme_hills.oreTypeMinDepth = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        extreme_hills.oreTypeVeinMin = new int[] {5, 6, 4, 3, 5, 3, 2, 1, 1};	
        extreme_hills.oreTypeVeinMax = new int[] {16, 22, 24, 6, 7, 6, 5, 1, 1};
        
		//Extreme Hills Mountains
        extreme_hills_mountains.treeDensity = 0.01;
        extreme_hills_mountains.treeType = (new TreeType[] {TreeType.TREE, TreeType.REDWOOD});
        extreme_hills_mountains.treeTypeChance = (new double[] {0.8, 0.2});
        extreme_hills_mountains.lakeChance = 0.05;
        extreme_hills_mountains.roughness = 20;
        extreme_hills_mountains.frequency = 0.6;
        extreme_hills_mountains.islandSizeMin = 64;
        extreme_hills_mountains.islandSizeMax = 128;
        extreme_hills_mountains.shrubDensity = 0.3;
        extreme_hills_mountains.shrubType = (new byte[] {1, 2});
        extreme_hills_mountains.shrubTypeChance = (new double[] {0.7, 0.3});
        extreme_hills_mountains.pondWaterChance = 0.0;
        extreme_hills_mountains.pondLavaChance = 0.0;
        extreme_hills_mountains.islandSoilMaterial = (byte)Material.STONE.getId();
        extreme_hills_mountains.islandSurfaceMaterial = (byte)Material.STONE.getId();
        extreme_hills_mountains.oreTypeMaterial = new byte[] {13, 16, 15, 14, 73, 21, 56, 35, 23, 97};				//Ores	(in order: {gravel, coal, iron, gold, lapis, redstone, diamond, emerald(replaced), silverfish}
        extreme_hills_mountains.oreTypeChance = new double[] {0.002, 0.0012, 0.0005, 0.00025, 0.00025, 0.0005, 0.0001, 0.00005,  0.00005}; 
        extreme_hills_mountains.oreTypeMinDepth = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        extreme_hills_mountains.oreTypeVeinMin = new int[] {5, 6, 4, 3, 5, 3, 2, 1, 1};	
        extreme_hills_mountains.oreTypeVeinMax = new int[] {16, 22, 24, 6, 7, 6, 5, 1, 1};
        
		//Extreme Hills Plus
        extreme_hills_plus.treeDensity = 0.03;
        extreme_hills_plus.treeType = (new TreeType[] {TreeType.TREE, TreeType.REDWOOD});
        extreme_hills_plus.treeTypeChance = (new double[] {0.8, 0.2});
        extreme_hills_plus.lakeChance = 0.05;
        extreme_hills_plus.roughness = 20;
        extreme_hills_plus.frequency = 0.6;
        extreme_hills_plus.islandSizeMin = 64;
        extreme_hills_plus.islandSizeMax = 128;
        extreme_hills_plus.shrubDensity = 0.3;
        extreme_hills_plus.shrubType = (new byte[] {1, 2});
        extreme_hills_plus.shrubTypeChance = (new double[] {0.7, 0.3});
        extreme_hills_plus.pondWaterChance = 0.0;
        extreme_hills_plus.pondLavaChance = 0.0;
        extreme_hills_plus.oreTypeMaterial = new byte[] {13, 16, 15, 14, 73, 21, 56, 35, 23, 97};				//Ores	(in order: {gravel, coal, iron, gold, lapis, redstone, diamond, emerald(replaced), silverfish}
        extreme_hills_plus.oreTypeChance = new double[] {0.002, 0.0012, 0.0005, 0.00025, 0.00025, 0.0005, 0.0001, 0.00005,  0.00005}; 
        extreme_hills_plus.oreTypeMinDepth = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        extreme_hills_plus.oreTypeVeinMin = new int[] {5, 6, 4, 3, 5, 3, 2, 1, 1};	
        extreme_hills_plus.oreTypeVeinMax = new int[] {16, 22, 24, 6, 7, 6, 5, 1, 1};
        
		//Extreme Hills Plus Mountains
        extreme_hills_plus_mountains.treeDensity = 0.03;
        extreme_hills_plus_mountains.treeType = (new TreeType[] {TreeType.TREE, TreeType.REDWOOD});
        extreme_hills_plus_mountains.treeTypeChance = (new double[] {0.8, 0.2});
        extreme_hills_plus_mountains.lakeChance = 0.05;
        extreme_hills_plus_mountains.roughness = 20;
        extreme_hills_plus_mountains.frequency = 0.6;
        extreme_hills_plus_mountains.islandSizeMin = 64;
        extreme_hills_plus_mountains.islandSizeMax = 128;
        extreme_hills_plus_mountains.shrubDensity = 0.3;
        extreme_hills_plus_mountains.shrubType = (new byte[] {1, 2});
        extreme_hills_plus_mountains.shrubTypeChance = (new double[] {0.7, 0.3});
        extreme_hills_plus_mountains.pondWaterChance = 0.0;
        extreme_hills_plus_mountains.pondLavaChance = 0.0;
        extreme_hills_plus_mountains.islandSoilMaterial = (byte)Material.STONE.getId();
        extreme_hills_plus_mountains.islandSurfaceMaterial = (byte)Material.GRAVEL.getId();
        extreme_hills_plus_mountains.oreTypeMaterial = new byte[] {13, 16, 15, 14, 73, 21, 56, 35, 23, 97};				//Ores	(in order: {gravel, coal, iron, gold, lapis, redstone, diamond, emerald(replaced), silverfish}
        extreme_hills_plus_mountains.oreTypeChance = new double[] {0.002, 0.0012, 0.0005, 0.00025, 0.00025, 0.0005, 0.0001, 0.00005,  0.00005};  
        extreme_hills_plus_mountains.oreTypeMinDepth = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        extreme_hills_plus_mountains.oreTypeVeinMin = new int[] {5, 6, 4, 3, 5, 3, 2, 1, 1};	
        extreme_hills_plus_mountains.oreTypeVeinMax = new int[] {16, 22, 24, 6, 7, 6, 5, 1, 1};
		
		//Flower Forest
        flower_forest.cliffs = false;
        flower_forest.roughness = 8;
        flower_forest.treeDensity = 0.08;
        flower_forest.treeType = (new TreeType[] {TreeType.TREE, TreeType.BIRCH});
        flower_forest.treeTypeChance = (new double[] {0.5, 0.5});
        flower_forest.lakeChance = 0.2;
        flower_forest.shrubDensity = 0.3;
        flower_forest.shrubType = (new byte[] {1, 2});
        flower_forest.shrubTypeChance = (new double[] {0.7, 0.3});
	flower_forest.flowerDensity = 0.2;
	flower_forest.flowerType = new byte[] {1, 2, 4, 5, 6, 7, 8, 9, 10};
	flower_forest.flowerTypeChance = new double[] {0.2, 0.05, 0.05, 0.05, 0.15, 0.15, 0.15, 0.15, 0.05};
        
		//Forest
        forest.cliffs = false;
        forest.roughness = 8;
        forest.treeDensity = 0.08;
        forest.treeType = (new TreeType[] {TreeType.TREE, TreeType.BIRCH});
        forest.treeTypeChance = (new double[] {0.5, 0.5});
        forest.lakeChance = 0.2;
        forest.shrubDensity = 0.3;
        forest.shrubType = (new byte[] {1, 2});
        forest.shrubTypeChance = (new double[] {0.7, 0.3});
		
		//Forest Hills
        forest_hills.cliffs = true;
        forest_hills.roughness = 12;
        forest_hills.treeDensity = 0.08;
        forest_hills.treeType = (new TreeType[] {TreeType.TREE, TreeType.BIRCH});
        forest_hills.treeTypeChance = (new double[] {0.5, 0.5});
        forest_hills.lakeChance = 0.2;
        forest_hills.shrubDensity = 0.3;
        forest_hills.shrubType = (new byte[] {1, 2});
        forest_hills.shrubTypeChance = (new double[] {0.7, 0.3});
		
	//Frozen Ocean
	frozen_ocean.cliffs = false;
	frozen_ocean.lakeChance = 1.0;
	frozen_ocean.treeDensity = 0.001;
	frozen_ocean.treeType = new TreeType[] {TreeType.TALL_REDWOOD};
	frozen_ocean.treeTypeChance = new double[] {1.0};
	frozen_ocean.roughness = 0.0;
	frozen_ocean.snow = true;
	frozen_ocean.islandFluidMaterial = (byte) Material.ICE.getId();
	frozen_ocean.chanceOfIsland = 0.001;
	
	//Frozen River
	frozen_river.cliffs = false;
	frozen_river.lakeChance = 0.9;
	frozen_river.treeDensity = 0.002;
	frozen_river.treeType = frozen_ocean.treeType;
	frozen_river.treeTypeChance = frozen_ocean.treeTypeChance;
	frozen_river.roughness = 3.0;
	frozen_river.frequency = 0.3;
	frozen_river.snow = true;
	frozen_river.islandFluidMaterial = (byte) Material.ICE.getId();
		
	//Hell		
	hell.cliffs = true;
	hell.cliffHeightMin = 5;
	hell.cliffHeightMax = 10;
	hell.lakeChance = 0.7;
	hell.treeDensity = 0.0;
	hell.roughness = 10.0;
	hell.frequency = 4.0;
	hell.islandFluidMaterial = (byte) Material.LAVA.getId();
	hell.islandLakeBedMaterial = (byte) Material.SOUL_SAND.getId();
	hell.islandSoilMaterial = (byte) Material.NETHERRACK.getId();
	hell.islandRockMaterial = (byte) Material.NETHERRACK.getId();
	hell.islandSurfaceMaterial = (byte) Material.NETHERRACK.getId();
	hell.islandAltitudeMax = 64;
	hell.islandAltitudeMax = 24;		
	hell.shrubDensity = 0.002;
	hell.shrubType = new byte[] {0};
	hell.shrubTypeChance = new double[] {1.0};
		
	//Ice Mountains
	ice_mountains.treeDensity = 0.003;
	ice_mountains.treeType = frozen_ocean.treeType;
	ice_mountains.treeTypeChance = frozen_ocean.treeTypeChance;
	ice_mountains.lakeChance = 0.05;
	ice_mountains.roughness = 20.0;
	ice_mountains.frequency = 0.4;
	ice_mountains.islandSizeMin = 64;
	ice_mountains.islandSizeMax = 128;
	ice_mountains.islandFluidMaterial = (byte) Material.ICE.getId();
	ice_mountains.islandSurfaceMaterial = (byte) Material.SNOW_BLOCK.getId();
	ice_mountains.islandSoilMaterial = (byte) Material.PACKED_ICE.getId();
	ice_mountains.islandRockMaterial = (byte) Material.STONE.getId();
	ice_mountains.snow = true;
	ice_mountains.pondWaterChance = 0.0;
		
	//Ice Plains
	ice_plains.cliffs = true;
	ice_plains.lakeChance = 0.01;
	ice_plains.treeDensity = 0.001;
	ice_plains.treeType = new TreeType[] {TreeType.TALL_REDWOOD};
	ice_plains.treeTypeChance = new double[] {1.0};
	ice_plains.roughness = 2.0;
	ice_plains.frequency = 0.3;
	ice_plains.snow = true;		
	ice_plains.islandFluidMaterial = (byte) Material.ICE.getId();
	ice_plains.islandSurfaceMaterial = (byte) Material.SNOW_BLOCK.getId();
	ice_plains.islandSoilMaterial = (byte) Material.PACKED_ICE.getId();
	ice_plains.islandRockMaterial = (byte) Material.STONE.getId();
	
	//Ice Plains Spikes
	ice_plains_spikes.cliffs = true;
	ice_plains_spikes.lakeChance = 0.01;
	ice_plains_spikes.treeDensity = 0.001;
	ice_plains_spikes.treeType = new TreeType[] {TreeType.TALL_REDWOOD};
	ice_plains_spikes.treeTypeChance = new double[] {1.0};
	ice_plains_spikes.roughness = 2.0;
	ice_plains_spikes.frequency = 0.3;
	ice_plains_spikes.snow = true;
	ice_plains_spikes.islandFluidMaterial = (byte) Material.ICE.getId();
	ice_plains_spikes.islandSurfaceMaterial = (byte) Material.SNOW_BLOCK.getId();
	ice_plains_spikes.islandSoilMaterial = (byte) Material.PACKED_ICE.getId();
	ice_plains_spikes.islandRockMaterial = (byte) Material.STONE.getId();
		
	//Jungle
	jungle.cliffs = false;
	jungle.frequency = 0.2;
        jungle.roughness = 8;
        jungle.treeDensity = 0.1;
        jungle.treeType = (new TreeType[] {TreeType.TREE, TreeType.JUNGLE, TreeType.SMALL_JUNGLE});
        jungle.treeTypeChance = (new double[] {0.2, 0.5, 0.3});
        jungle.lakeChance = 0.2;
        jungle.shrubDensity = 0.3;
        jungle.shrubType = (new byte[] {1, 2});
        jungle.shrubTypeChance = (new double[] {0.7, 0.3});
	jungle.pumpkinDensity = 0.0;
	jungle.watermelonDensity = 0.0;
		
		//Jungle Edge
		jungle_edge.cliffs = false;
        jungle_edge.roughness = 8;
        jungle_edge.treeDensity = 0.1;
        jungle_edge.treeType = (new TreeType[] {TreeType.TREE, TreeType.SMALL_JUNGLE, TreeType.JUNGLE_BUSH});
        jungle_edge.treeTypeChance = (new double[] {0.2, 0.4, 0.4});
        jungle_edge.lakeChance = 0.2;
        jungle_edge.shrubDensity = 0.3;
        jungle_edge.shrubType = (new byte[] {1, 2});
        jungle_edge.shrubTypeChance = (new double[] {0.7, 0.3});
	jungle_edge.pumpkinDensity = 0.0;
	jungle_edge.watermelonDensity = 0.0;
		
		//Jungle Hills
		jungle_hills.cliffs = true;
        jungle_hills.roughness = 12;
        jungle_hills.treeDensity = 0.1;
        jungle_hills.treeType = (new TreeType[] {TreeType.TREE, TreeType.JUNGLE, TreeType.SMALL_JUNGLE});
        jungle_hills.treeTypeChance = (new double[] {0.2, 0.5, 0.3});
        jungle_hills.lakeChance = 0.2;
        jungle_hills.shrubDensity = 0.3;
        jungle_hills.shrubType = (new byte[] {1, 2});
        jungle_hills.shrubTypeChance = (new double[] {0.7, 0.3});
	jungle_hills.pumpkinDensity = 0.0;
	jungle_hills.watermelonDensity = 0.0;
		
		//Jungle Mountain
	jungle_mountains.cliffs = false;
        jungle_mountains.roughness = 20;
        jungle_mountains.treeDensity = 0.1;
        jungle_mountains.treeType = (new TreeType[] {TreeType.TREE, TreeType.JUNGLE, TreeType.SMALL_JUNGLE});
        jungle_mountains.treeTypeChance = (new double[] {0.2, 0.5, 0.3});
        jungle_mountains.lakeChance = 0.2;
        jungle_mountains.shrubDensity = 0.3;
        jungle_mountains.shrubType = (new byte[] {1, 2});
        jungle_mountains.shrubTypeChance = (new double[] {0.7, 0.3});
	jungle_mountains.pumpkinDensity = 0.0;
	jungle_mountains.watermelonDensity = 0.0;
	
	//Mega Spruce Taiga
	mega_spruce_taiga.cliffs = false;
	mega_spruce_taiga.lakeChance = 0.01;
	mega_spruce_taiga.treeDensity = 0.06;
	mega_spruce_taiga.treeType = (new TreeType[] {TreeType.REDWOOD, TreeType.MEGA_REDWOOD});
	mega_spruce_taiga.treeTypeChance = (new double[] {0.6, 0.4});
	mega_spruce_taiga.roughness = 4.0;
	mega_spruce_taiga.frequency = 0.2;
	mega_spruce_taiga.shrubDensity = 0.6;
	mega_spruce_taiga.shrubType = new byte[] {1, 2};
	mega_spruce_taiga.shrubTypeChance = new double[] {0.9, 0.1};
	mega_spruce_taiga.snow = false;
	
	//Mega Spruce Taiga Hills
	mega_spruce_taiga_hills.cliffs = true;
	mega_spruce_taiga_hills.lakeChance = 0.06;
	mega_spruce_taiga_hills.treeDensity = 0.001;
	mega_spruce_taiga_hills.treeType = (new TreeType[] {TreeType.REDWOOD, TreeType.MEGA_REDWOOD});
	mega_spruce_taiga_hills.treeTypeChance = (new double[] {0.6, 0.4});
	mega_spruce_taiga_hills.roughness = 12.0;
	mega_spruce_taiga_hills.frequency = 0.2;
	mega_spruce_taiga_hills.shrubDensity = 0.6;
	mega_spruce_taiga_hills.shrubType = new byte[] {1, 2};
	mega_spruce_taiga_hills.shrubTypeChance = new double[] {0.9, 0.1};
	mega_spruce_taiga_hills.snow = false;
		
	//Mega Taiga
	mega_taiga.cliffs = false;
	mega_taiga.lakeChance = 0.06;
	mega_taiga.treeDensity = 0.12;
	mega_taiga.treeType = (new TreeType[] {TreeType.REDWOOD, TreeType.MEGA_REDWOOD});
	mega_taiga.treeTypeChance = (new double[] {0.3, 0.7});
	mega_taiga.roughness = 4.0;
	mega_taiga.frequency = 0.2;
	mega_taiga.shrubDensity = 0.3;
	mega_taiga.shrubType = new byte[] {1, 2};
	mega_taiga.shrubTypeChance = new double[] {0.6, 0.4};
	mega_taiga.snow = false;
	mega_taiga.pumpkinDensity = 0.00002;
	mega_taiga.boulderDensity = 0.02;
	mega_taiga.islandSurfaceMaterial = (byte)Material.COAL_BLOCK.getId();
	
	//Mega Taiga Hills
	mega_taiga_hills.cliffs = true;
	mega_taiga_hills.lakeChance = 0.01;
	mega_taiga_hills.treeDensity = 0.12;
	mega_taiga_hills.treeType = (new TreeType[] {TreeType.REDWOOD, TreeType.MEGA_REDWOOD});
	mega_taiga_hills.treeTypeChance = (new double[] {0.3, 0.7});
	mega_taiga_hills.roughness = 12.0;
	mega_taiga_hills.frequency = 0.2;
	mega_taiga_hills.shrubDensity = 0.15;
	mega_taiga_hills.shrubType = new byte[] {1, 2};
	mega_taiga_hills.shrubTypeChance = new double[] {0.6, 0.4};
	mega_taiga_hills.snow = false;
	mega_taiga_hills.pumpkinDensity = 0.00002;
	mega_taiga_hills.boulderDensity = 0.02;
	mega_taiga_hills.islandSurfaceMaterial = (byte)Material.COAL_BLOCK.getId(); //Replaced by Podzol
		
	//Mesa
	mesa.islandSurfaceMaterial = (byte)Material.NOTE_BLOCK.getId(); //Replaced by Red Sand
	mesa.islandSoilMaterial = (byte)Material.STAINED_CLAY.getId();
        mesa.islandRockMaterial = (byte)Material.HARD_CLAY.getId();
        mesa.lakeChance = 0.0;
        mesa.treeDensity = 0.0;
        mesa.cliffs = false;
        mesa.roughness = 6;
        mesa.frequency = 0.3;
        mesa.pondWaterChance = 0.0;
        mesa.pondLavaChance = 0.0;
        mesa.lavaFallChance = 0.0;
        
	//Mesa Bryce
	mesa_bryce.islandRockMaterial = (byte)Material.HARD_CLAY.getId();
	mesa_bryce.islandSoilMaterial = (byte)Material.STAINED_CLAY.getId();
        mesa_bryce.islandSurfaceMaterial = (byte)Material.HARD_CLAY.getId();
        mesa_bryce.lakeChance = 0.0;
        mesa_bryce.treeDensity = 0.0;
        mesa_bryce.cliffs = true;
        mesa_bryce.roughness = 6;
        mesa_bryce.frequency = 0.3;
        mesa_bryce.pondWaterChance = 0.0;
        mesa_bryce.pondLavaChance = 0.0;
        mesa_bryce.lavaFallChance = 0.0;
		
	//Mesa Plateau
	mesa_plateau.islandRockMaterial = (byte)Material.HARD_CLAY.getId();
	mesa_plateau.islandSoilMaterial = (byte)Material.STAINED_CLAY.getId();
        mesa_plateau.islandSurfaceMaterial = (byte)Material.HARD_CLAY.getId();
        mesa_plateau.lakeChance = 0.0;
        mesa_plateau.treeDensity = 0.0;
        mesa_plateau.cliffs = false;
        mesa_plateau.roughness = 1;
        mesa_plateau.frequency = 0.1;
        mesa_plateau.pondWaterChance = 0.0;
        mesa_plateau.pondLavaChance = 0.0;
        mesa_plateau.lavaFallChance = 0.0;
        
	//Mesa Plateau Forest
	mesa_plateau_forest.islandRockMaterial = (byte)Material.HARD_CLAY.getId();
	mesa_plateau_forest.islandSoilMaterial = (byte)Material.STAINED_CLAY.getId();
        mesa_plateau_forest.islandSurfaceMaterial = (byte)Material.HARD_CLAY.getId();
        mesa_plateau_forest.lakeChance = 0.0;
        mesa_plateau_forest.treeDensity = 0.0;
        mesa_plateau_forest.cliffs = false;
        mesa_plateau_forest.roughness = 1;
        mesa_plateau_forest.frequency = 0.1;
        mesa_plateau_forest.pondWaterChance = 0.0;
        mesa_plateau_forest.pondLavaChance = 0.0;
        mesa_plateau_forest.lavaFallChance = 0.0;
        
	//Mesa Plateau Forest Mountains
	mesa_plateau_forest_mountains.islandRockMaterial = (byte)Material.HARD_CLAY.getId();
	mesa_plateau_forest_mountains.islandSoilMaterial = (byte)Material.STAINED_CLAY.getId();
        mesa_plateau_forest_mountains.islandSurfaceMaterial = (byte)Material.HARD_CLAY.getId();
        mesa_plateau_forest_mountains.lakeChance = 0.0;
        mesa_plateau_forest_mountains.treeDensity = 0.0;
        mesa_plateau_forest_mountains.cliffs = false;
        mesa_plateau_forest_mountains.roughness = 1;
        mesa_plateau_forest_mountains.frequency = 0.1;
        mesa_plateau_forest_mountains.pondWaterChance = 0.0;
        mesa_plateau_forest_mountains.pondLavaChance = 0.0;
        mesa_plateau_forest_mountains.lavaFallChance = 0.0;
        mesa_plateau_forest_mountains.treeDensity = 0.01;
        mesa_plateau_forest_mountains.treeType = (new TreeType[] {TreeType.TREE});
        mesa_plateau_forest_mountains.treeTypeChance = (new double[] {1.0});
        
	//Mesa Plateau Mountains
	mesa_plateau_mountains.islandRockMaterial = (byte)Material.HARD_CLAY.getId();
	mesa_plateau_mountains.islandSoilMaterial = (byte)Material.STAINED_CLAY.getId();
        mesa_plateau_mountains.islandSurfaceMaterial = (byte)Material.HARD_CLAY.getId();
        mesa_plateau_mountains.lakeChance = 0.0;
        mesa_plateau_mountains.treeDensity = 0.0;
        mesa_plateau_mountains.cliffs = false;
        mesa_plateau_mountains.roughness = 1;
        mesa_plateau_mountains.frequency = 0.1;
        mesa_plateau_mountains.pondWaterChance = 0.0;
        mesa_plateau_mountains.pondLavaChance = 0.0;
        mesa_plateau_mountains.lavaFallChance = 0.0;
        
	//Mushroom Island
	mushroom_island.cliffs = true;
	mushroom_island.lakeChance = 0.2;
	mushroom_island.treeDensity = 0.04;
	mushroom_island.roughness = 8.0;
	mushroom_island.frequency = 0.3;
	mushroom_island.islandSurfaceMaterial = (byte) Material.MYCEL.getId();
	mushroom_island.treeType = new TreeType[] {TreeType.BROWN_MUSHROOM, TreeType.RED_MUSHROOM};
	mushroom_island.treeTypeChance = new double[] {0.5, 0.5};
	mushroom_island.mushroomDensity = 0.05;
		
	//Mushroom Shore
	mushroom_shore.cliffs = false;
	mushroom_shore.lakeChance = 0.7;
	mushroom_shore.treeDensity = 0.04;
	mushroom_shore.roughness = 4.0;
	mushroom_shore.frequency = 0.3;
	mushroom_shore.islandSurfaceMaterial = (byte) Material.MYCEL.getId();
	mushroom_shore.treeType = mushroom_island.treeType;
	mushroom_shore.treeTypeChance = mushroom_island.treeTypeChance;
	mushroom_shore.mushroomDensity = 0.01;
		
		//Ocean
	ocean.chanceOfIsland = 0.0; //Lakes are not generating in Oceans, hence no islands will generate for the time being.
	ocean.islandRockMaterial = (byte)Material.STONE.getId();
	ocean.islandSoilMaterial = (byte)Material.SAND.getId();
	ocean.islandSurfaceMaterial = (byte)Material.SAND.getId();
	ocean.lakeChance = 1.0;
	ocean.treeDensity = 0.0;
	ocean.cliffs = false;
	ocean.roughness = 2.0;
	ocean.frequency = 0.3;
	ocean.cactusDensity = 0.0;
        ocean.shrubDensity = 0.0;
        ocean.pondWaterChance = 0.0;
        ocean.pondLavaChance = 0.0;
        ocean.lavaFallChance = 0.0;
        ocean.chanceOfCave = 0.0;
		
	//Plains
	plains.lakeChance = 0.01;
	plains.treeDensity = 0.001;
	plains.roughness = 3.0;
	plains.frequency = 0.6;
	plains.shrubDensity = 0.3;
	plains.flowerDensity = 0.04;
	plains.flowerType = new byte[] {1, 2, 4, 5, 6, 7, 8, 9, 10};
	plains.flowerTypeChance = new double[] {0.2, 0.15, 0.15, 0.15, 0.5, 0.5, 0.5, 0.5, 0.15};
			
	//River
	river.cliffs = false;
	river.lakeChance = 1.0;
	river.treeDensity = 0.001;
	river.roughness = 6.0;
	river.frequency = 0.6;
	river.shrubDensity = 0.3;
	river.shrubType = new byte[] {1, 2};
	river.shrubTypeChance = new double[] {0.4, 0.6};
	river.chanceOfCave = 0.0;
		
	//Roofed Forest
        roofed_forest.cliffs = false;
        roofed_forest.roughness = 8;
        roofed_forest.treeDensity = 0.09;
        roofed_forest.treeType = (new TreeType[] {TreeType.DARK_OAK, TreeType.RED_MUSHROOM, TreeType.BROWN_MUSHROOM});
        roofed_forest.treeTypeChance = (new double[] {0.6, 0.2, 0.2});
        roofed_forest.lakeChance = 0.2;
        roofed_forest.shrubDensity = 0.3;
        roofed_forest.shrubType = (new byte[] {1, 2});
        roofed_forest.shrubTypeChance = (new double[] {0.7, 0.3});
	roofed_forest.mushroomDensity = 0.04;
		
	//Roofed Forest Mountains
        roofed_forest_mountains.cliffs = false;
        roofed_forest_mountains.roughness = 16;
        roofed_forest_mountains.treeDensity = 0.09;
        roofed_forest_mountains.treeType = (new TreeType[] {TreeType.DARK_OAK, TreeType.RED_MUSHROOM, TreeType.BROWN_MUSHROOM});
        roofed_forest_mountains.treeTypeChance = (new double[] {0.6, 0.2, 0.2});
        roofed_forest_mountains.lakeChance = 0.2;
        roofed_forest_mountains.shrubDensity = 0.3;
        roofed_forest_mountains.shrubType = (new byte[] {1, 2});
        roofed_forest_mountains.shrubTypeChance = (new double[] {0.7, 0.3});
	roofed_forest_mountains.mushroomDensity = 0.04;
	
	//Savanna
	savanna.cliffs = false;
	savanna.lakeChance = 0.01;
	savanna.treeDensity = 0.003;
	savanna.treeType = new TreeType[] {TreeType.ACACIA};
	savanna.treeTypeChance = new double[] {0.1};
	savanna.roughness = 4.0;
	savanna.frequency = 0.2;
	savanna.shrubDensity = 0.6;
	savanna.shrubType = new byte[] {1};
	savanna.shrubTypeChance = new double[] {1};
	savanna.pumpkinDensity = 0.0;
	savanna.watermelonDensity = 0.0;
	
	//Savanna Mountains
	savanna_mountains.cliffs = false;
	savanna_mountains.lakeChance = 0.01;
	savanna_mountains.treeDensity = 0.003;
	savanna_mountains.treeType = new TreeType[] {TreeType.ACACIA};
	savanna_mountains.treeTypeChance = new double[] {0.1};
	savanna_mountains.roughness = 12.0;
	savanna_mountains.frequency = 0.2;
	savanna_mountains.shrubDensity = 0.6;
	savanna_mountains.shrubType = new byte[] {1};
	savanna_mountains.shrubTypeChance = new double[] {1};
	savanna_mountains.pumpkinDensity = 0.0;
	savanna_mountains.watermelonDensity = 0.0;
		
	//Savanna Plateau
	savanna_plateau.cliffs = false;
	savanna_plateau.lakeChance = 0.01;
	savanna_plateau.treeDensity = 0.003;
	savanna_plateau.treeType = new TreeType[] {TreeType.ACACIA};
	savanna_plateau.treeTypeChance = new double[] {0.1};
	savanna_plateau.roughness = 4.0;
	savanna_plateau.frequency = 0.2;
	savanna_plateau.shrubDensity = 0.6;
	savanna_plateau.shrubType = new byte[] {1};
	savanna_plateau.shrubTypeChance = new double[] {1};
	savanna_plateau.pumpkinDensity = 0.0;
	savanna_plateau.watermelonDensity = 0.0;
	
	//Savanna Plateau Mountains
	savanna_plateau_mountains.cliffs = false;
	savanna_plateau_mountains.lakeChance = 0.01;
	savanna_plateau_mountains.treeDensity = 0.003;
	savanna_plateau_mountains.treeType = new TreeType[] {TreeType.ACACIA};
	savanna_plateau_mountains.treeTypeChance = new double[] {0.1};
	savanna_plateau_mountains.roughness = 12.0;
	savanna_plateau_mountains.frequency = 0.2;
	savanna_plateau_mountains.shrubDensity = 0.6;
	savanna_plateau_mountains.shrubType = new byte[] {1};
	savanna_plateau_mountains.shrubTypeChance = new double[] {1};
	savanna_plateau_mountains.pumpkinDensity = 0.0;
	savanna_plateau_mountains.watermelonDensity = 0.0;
		
	//Small Mountains
        small_mountains.treeDensity = 0.01;
        small_mountains.treeType = (new TreeType[] {TreeType.TREE, TreeType.REDWOOD});
        small_mountains.treeTypeChance = (new double[] {0.8, 0.2});
        small_mountains.lakeChance = 0.05;
        small_mountains.roughness = 16;
        small_mountains.frequency = 0.6;
        small_mountains.islandSizeMin = 64;
        small_mountains.islandSizeMax = 128;
        small_mountains.shrubDensity = 0.3;
        small_mountains.shrubType = (new byte[] {1, 2});
        small_mountains.shrubTypeChance = (new double[] {0.7, 0.3});
        small_mountains.pondWaterChance = 0.0;
        small_mountains.pondLavaChance = 0.0;
		
	//Stone Beach
	stone_beach.islandRockMaterial = (byte)Material.STONE.getId();
	stone_beach.islandSoilMaterial = (byte)Material.STONE.getId();
	stone_beach.islandSurfaceMaterial = (byte)Material.STONE.getId();
	stone_beach.lakeChance = 1.0;
	stone_beach.treeDensity = 0.0;
	stone_beach.cliffs = true;
	stone_beach.roughness = 6;
	stone_beach.frequency = 0.3;
	stone_beach.cactusDensity = 0.01;
        stone_beach.shrubDensity = 0.02;
        stone_beach.shrubType = new byte[1];
        stone_beach.shrubTypeChance = (new double[] {1.0});
        stone_beach.pondWaterChance = 0.0;
        stone_beach.pondLavaChance = 0.0;
        stone_beach.lavaFallChance = 0.0;
        stone_beach.chanceOfCave = 0.0;
        
	//Sunflower Plains (Sunflowers do not work at present, biome will act as "Flower Plains")
	sunflower_plains.lakeChance = 0.01;
	sunflower_plains.treeDensity = 0.001;
	sunflower_plains.roughness = 2.0;
	sunflower_plains.frequency = 0.3;
	sunflower_plains.shrubDensity = 0.3;
	sunflower_plains.flowerDensity = 0.2;
	sunflower_plains.flowerType = new byte[] {1, 2, 4, 5, 6, 7, 8, 9, 10};
	sunflower_plains.flowerTypeChance = new double[] {0.2, 0.15, 0.15, 0.15, 0.5, 0.5, 0.5, 0.5, 0.15};

	//Swampland
	swampland.cliffs = false;
	swampland.lakeChance = 0.9;
	swampland.treeDensity = 0.01;
	swampland.treeType = (new TreeType[] {TreeType.SWAMP});
	swampland.treeTypeChance = (new double[] {1.0});
	swampland.roughness = 4.0;
	swampland.frequency = 0.2;
	swampland.islandLakeBedDirt = (byte) Material.DIRT.getId();
	swampland.islandLakeBedMaterial = (byte) Material.CLAY.getId();
	swampland.shrubDensity = 0.3;
	swampland.shrubType = new byte[] {1};
	swampland.shrubTypeChance = new double[] {1.0};
	swampland.flowerDensity = 0.03;
	swampland.flowerType = new byte[] {1, 2, 3};
	swampland.flowerTypeChance = new double[] {0.3, 0.3, 0.4};
	
	//Swampland Mountains
	swampland_mountains.cliffs = true;
	swampland_mountains.lakeChance = 0.9;
	swampland_mountains.treeDensity = 0.01;
	swampland_mountains.treeType = (new TreeType[] {TreeType.SWAMP});
	swampland_mountains.treeTypeChance = (new double[] {1.0});
	swampland_mountains.roughness = 8.0;
	swampland_mountains.frequency = 0.2;
	swampland_mountains.islandLakeBedDirt = (byte) Material.DIRT.getId();
	swampland_mountains.islandLakeBedMaterial = (byte) Material.CLAY.getId();
	swampland_mountains.shrubDensity = 0.3;
	swampland_mountains.shrubType = new byte[] {1};
	swampland_mountains.shrubTypeChance = new double[] {1.0};
	swampland_mountains.flowerDensity = 0.03;
	swampland_mountains.flowerType = new byte[] {1, 2, 3};
	swampland_mountains.flowerTypeChance = new double[] {0.3, 0.3, 0.4};
	
	//Taiga
	taiga.cliffs = true;
	taiga.lakeChance = 0.01;
	taiga.treeDensity = 0.06;
	taiga.treeType = (new TreeType[] {TreeType.REDWOOD});
	taiga.treeTypeChance = (new double[] {1.0});
	taiga.roughness = 4.0;
	taiga.frequency = 0.2;
	taiga.shrubDensity = 0.6;
	taiga.shrubType = new byte[] {1, 2};
	taiga.shrubTypeChance = new double[] {0.9, 0.1};
	
	//Taiga Mountains
	taiga_mountains.cliffs = true;
	taiga_mountains.lakeChance = 0.01;
	taiga_mountains.treeDensity = 0.06;
	taiga_mountains.treeType = (new TreeType[] {TreeType.REDWOOD});
	taiga_mountains.treeTypeChance = (new double[] {1.0});
	taiga_mountains.roughness = 12.0;
	taiga_mountains.frequency = 0.2;
	taiga_mountains.shrubDensity = 0.6;
	taiga_mountains.shrubType = new byte[] {1, 2};
	taiga_mountains.shrubTypeChance = new double[] {0.9, 0.1};
		
	}
	
	
	public static BiomeDescription getDescription(Biome mcBiome) {
		switch(mcBiome) {
			case BEACH: return beach;
			case BIRCH_FOREST: return birch_forest;
			case BIRCH_FOREST_HILLS: return birch_forest_hills;
			case BIRCH_FOREST_HILLS_MOUNTAINS: return birch_forest_hills_mountains;
			case BIRCH_FOREST_MOUNTAINS: return birch_forest_mountains;
			case COLD_BEACH: return cold_beach;
			case COLD_TAIGA: return cold_taiga;
			case COLD_TAIGA_HILLS: return cold_taiga_hills;
			case COLD_TAIGA_MOUNTAINS: return cold_taiga_mountains;
			case DEEP_OCEAN: return deep_ocean;
			case DESERT: return desert;
			case DESERT_HILLS: return desert_hills;
			case DESERT_MOUNTAINS: return desert_mountains;
			case EXTREME_HILLS: return extreme_hills;
			case EXTREME_HILLS_MOUNTAINS: return extreme_hills_mountains;
			case EXTREME_HILLS_PLUS: return extreme_hills_plus;
			case EXTREME_HILLS_PLUS_MOUNTAINS: return extreme_hills_plus_mountains;
			case FLOWER_FOREST: return flower_forest;
			case FOREST: return forest;
			case FOREST_HILLS: return forest_hills;
			case FROZEN_OCEAN: return frozen_ocean;
			case FROZEN_RIVER: return frozen_river;
			case HELL: return hell;
			case ICE_MOUNTAINS: return ice_mountains;
			case ICE_PLAINS: return ice_plains;
			case ICE_PLAINS_SPIKES: return ice_plains_spikes;
			case JUNGLE: return jungle;
			case JUNGLE_EDGE: return jungle_edge;
			case JUNGLE_EDGE_MOUNTAINS: return jungle_edge_mountains;
			case JUNGLE_HILLS: return jungle_hills;
			case JUNGLE_MOUNTAINS: return jungle_mountains;
			case MEGA_SPRUCE_TAIGA: return mega_spruce_taiga;
			case MEGA_SPRUCE_TAIGA_HILLS: return mega_spruce_taiga_hills;
			case MEGA_TAIGA: return mega_taiga;
			case MEGA_TAIGA_HILLS: return mega_taiga_hills;
			case MESA: return mesa;
			case MESA_BRYCE: return mesa_bryce;
			case MESA_PLATEAU: return mesa_plateau;
			case MESA_PLATEAU_FOREST: return mesa_plateau_forest;
			case MESA_PLATEAU_FOREST_MOUNTAINS: return mesa_plateau_forest_mountains;
			case MESA_PLATEAU_MOUNTAINS: return mesa_plateau_mountains;
			case MUSHROOM_ISLAND: return mushroom_island;
			case MUSHROOM_SHORE: return mushroom_shore;
			case OCEAN: return ocean;
			case PLAINS: return plains;
			case RIVER: return river;
			case ROOFED_FOREST: return roofed_forest;
			case ROOFED_FOREST_MOUNTAINS: return roofed_forest_mountains;
			case SAVANNA: return savanna;
			case SAVANNA_PLATEAU: return savanna_plateau;
			case SAVANNA_PLATEAU_MOUNTAINS: return savanna_plateau_mountains;
			case SKY: return sky;
			case SMALL_MOUNTAINS: return small_mountains;
			case STONE_BEACH: return stone_beach;
			case SUNFLOWER_PLAINS: return sunflower_plains;
			case SWAMPLAND: return swampland;
			case SWAMPLAND_MOUNTAINS: return swampland_mountains;
			case TAIGA: return taiga;
			case TAIGA_HILLS: return taiga_hills;
			case TAIGA_MOUNTAINS: return taiga_mountains;
			default : return standard;
		}
	}
	
	/********************************************************************************************************\
	 *********** ALL OF THE ATTRIBUTES ARE BASED PER BIOME, BELOW ARE DEFAULTS, ABOVE ARE SPECIFICS *********
	\********************************************************************************************************/
	
	//Basic Island Appearance
	public double chanceOfIsland = 0.15; 			//Chance of this type of island to appear centered in any given chunk
	public int islandSizeMin = 70;					//Minimum lateral size of the island
	public int islandSizeMax = 120;					//Maximum lateral size of the island
	public double islandSquareRegularity = 0.5;		//Coefficient of squareness (totally made this up but it works)
	public int islandHeightMin = 16;				//Minimum vertical size of the island
	public int islandHeightMax = 128;				//Maximum vertical size of the island
	public double islandHeightRatio = 0.4;			//Ratio of height attributed to size 
	public double islandHeightRegularity = 1.0;		//Coefficient of height according to the calculated ratio
	public int islandAltitudeMin = 16;				//Minimum spawn altitude (adjusted by height of the island)
	public int islandAltitudeMax = 128;				//Maximum spawn altitude (adjusted by height of the island)
	
	//Island Composition
	public byte islandRockMaterial = (byte) Material.STONE.getId();		//Material to fill the bottom of the island with (stone)
	public byte islandSoilMaterial = (byte) Material.DIRT.getId();		//Material to fill the top layers of the island with (dirt)
	public byte islandSurfaceMaterial = (byte) Material.GRASS.getId(); 	//Material to put on the surface of the island (grass)
	public byte islandLakeBedMaterial = (byte) Material.SAND.getId();	//Material to use as the beach (sand)
	public byte islandLakeBedDirt = (byte) Material.SAND.getId();		//Material to mix into the beach material in swamps mostly (dirt)
	public byte islandFluidMaterial = (byte) Material.WATER.getId();	//Material to use for lakes
	public byte islandSnowMaterial = (byte) Material.SNOW.getId();		//Material to use for snow (always snow?)
	
	//Terrain Features
	public double lakeChance = 0.1;			//Chance of a lake occurring
	public boolean snow = false;			//Snow?
	public boolean cliffs = false;			//Cliffs?
	public int cliffHeightMin = 4;			//Minimum cliff height
	public int cliffHeightMax = 8;			//Maximum cliff height 
	public double roughness = 8.0;			//Roughness, double amplitude of surface noise
	public double frequency = 0.5;			//Frequency of surface noise
	public double islandSoilDepth = 0.3;	//Top percentage of the island to be made of soil
	public double pondWaterChance = 0.0002;	//Water pond chance, rolled for each block on the surface of the island
	public double pondLavaChance = 0.0001;	//Lava pond chance, rolled for each stone block with a caveSpace material above it
	
	//Plant life
	public double treeDensity = 0.03;		//Chance of a tree, rolled for each surface grass
	
	public TreeType[] treeType = new TreeType[] {TreeType.TREE, TreeType.BIG_TREE};	//Type of tree
	public double[] treeTypeChance = new double[] {0.8, 0.2};						//Chance for each type (same size as treeType[])
	
	public double reedDensity = 0.1;		//Reed density, rolled only for sand or grass blocks near water
	public int readClusterMin = 2;			//Minimum cluster size for reeds
	public int readClusterMax = 5;			//Maximum cluster size for reeds
	
	public double cactusDensity = 0.0;		//Cactus density, rolled for any sand block on the surface
	
	public double shrubDensity = 0.02;							//Chance of a shrub, rolled for each surface grass or sand block
	public byte[] shrubType = new byte[] {1, 2};				//Type of shrub (0 is dried bush, 1 is grass, 2 is fern)
	public double[] shrubTypeChance = new double[] {0.7, 0.3};	//Chance for the given type, rolled after shrub is guaranteed
	
	public double flowerDensity = 0.02;				//Flower cluster density, roll per surface grass block
	public byte[] flowerType = new byte[] {1, 2};		 //1-dandelion, 2-poppy, 3-blue orchid,  4-allium, 5-azure bluet, 6-red tulip, 7-orange tulip, 8-white tulip, 9-pink tulip, 10-oxeye daisy
	public double[] flowerTypeChance = new double[] {0.5, 0.5};	//Chance for the given type, rolled after flower is guaranteed
	public int flowerClusterMin = 3;				//Flower cluster minimum size
	public int flowerClusterMax = 7;				//Flower cluster maximum size
	
	public double mushroomDensity = 0.0;			//Mushroom cluster density, roll per surface grass block
	public double mushroomClusterIsRedChance = 0.5;	//Chance for cluster to be red, rolled after cluster is guaranteed
	public int mushroomClusterMin = 3;				//Mushroom cluster minimum size
	public int mushroomClusterMax = 5;				//Mushroom cluster maximum size
	
	public double watermelonDensity = 0.0;	//Water melon cluster density, rolled per surface grass block
	public int watermelonClusterMin = 3;	//Cluster minimum size
	public int watermelonClusterMax = 5;	//Cluster maximum size
	
	public double pumpkinDensity = 0.0;		//Pumpkin cluster density, rolled per surface grass block
	public int pumpkinClusterMin = 3;		//Cluster minimum size
	public int pumpkinClusterMax = 5;		//Cluster maximum size
	
	//Cave features (don't mess with these and expect it to look good)
	public CaveDescription caveDescription = new CaveDescription();
	private double chanceOfCave = 1.0;
	
	//Extras
	public double liquidFallChance = 0.001;	//Chance of a lava or water fall, rolled per underground stone with exactly one air adjacent
	public double lavaFallChance = 0.0;		//Chance that the liquid fall is lava, rolled after liquid fall is guaranteed
	
	public double boulderDensity = 0.0;	//Mossy Cobblestone cluster density, rolled per surface podzol block (Mega Taiga only)
	public int boulderClusterMin = 3;	//Cluster minimum size
	public int boulderClusterMax = 5;	//Cluster maximum size
	
	//Ores	(in order: {gravel, coal, iron, gold, lapis, redstone, diamond, granite, dirorite, andesite}
	public byte[] oreTypeMaterial = new byte[] {13, 16, 15, 14, 73, 21, 56, 43, 95, 35};							//Had to use block IDs because it was being stupid (see above list)
	public double[] oreTypeChance = new double[] {0.002, 0.0012, 0.0005, 0.00025, 0.00025, 0.0005, 0.0001, 0.002, 0.002, 0.002}; //Chance of each ore spawning
	public double[] oreTypeMinDepth = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};					//Minimum percentage depth to spawn each ore
	public int[] oreTypeVeinMin = new int[] {5, 6, 4, 3, 5, 3, 2, 5, 5, 5};										//Minimum vein size of each ore
	public int[] oreTypeVeinMax = new int[] {16, 22, 24, 6, 7, 6, 5, 16, 16, 16};									//Maximum vein size of each ore
	
	
	public boolean rollIsland(Random random) {
		return random.nextDouble() < chanceOfIsland;
	}
	
	public Int3D getPosition(Random random, int chunkX, int chunkZ, Int3D size) {
		int x = chunkX*16 + random.nextInt(16)-size.x/2;
		int y = islandAltitudeMin + random.nextInt(islandAltitudeMax-size.y-islandAltitudeMin+1);
		int z = chunkZ*16 + random.nextInt(16)-size.z/2;
		return new Int3D(x, y, z);
	}
	
	public Int3D getDimensions(Random random) {
		int airSpace = getAirSpace();
		int length = islandSizeMin + weightedRand(random, islandSizeMax-islandSizeMin+1, 6.0);
		int bredth = islandSizeMin + weightedRand(random, islandSizeMax-islandSizeMin+1, 6.0);
		bredth = (int) (islandSquareRegularity*(double)length + (1.0-islandSquareRegularity)*(double)bredth);
		int height = islandHeightMin + airSpace + weightedRand(random, islandHeightMax-islandHeightMin+1, 6.0);
		height = (int) (islandHeightRegularity*(double)(length+bredth)/2.0*islandHeightRatio + (1.0-islandHeightRegularity)*(double)height);
		if(height < airSpace + islandHeightMin) height = airSpace + islandHeightMin;
		return new Int3D(length, height, bredth);
	}

	/**
	 * Calculate require air space, offset for the centerY coordinate of the island
	 * @return int - number of blocks that need to be open above the centerY coordinate in the schematic
	 */
	public int getAirSpace() {
		int airSpace = (int) roughness;
		if(cliffs) airSpace += cliffHeightMax;
		if(treeDensity > 0) airSpace += 10;
		return airSpace;
	}

	/**
	 * Non linear random makes lots of small islands and some rare, really big ones
	 */
	private int weightedRand(Random random, int i, double d) {
		return (int) (Math.pow(random.nextDouble(), d)*(i+1));
	}

	public boolean rollLake(Random random) {
		return random.nextDouble() < lakeChance;
	}

	public int rollCliffHeight(Random random) {
		return random.nextInt(cliffHeightMax-cliffHeightMin+1)+cliffHeightMin;
	}

	public boolean rollTree(Random random) {
		return random.nextDouble() < treeDensity;
	}

	public TreeType rollTreeType(Random random) {
		double total = 0.0;
		for(int i = 0; i < treeTypeChance.length; i++) {
			total += treeTypeChance[i];
		}
		double d = random.nextDouble()*total;
		double chance = 0.0;
		for(int i = 0; i < treeType.length; i++) {
			if(d < chance+treeTypeChance[i]) {
				return treeType[i];
			} else {
				chance += treeTypeChance[i];
			}
		}
		return TreeType.TREE;
	}

	public boolean rollShrub(Random random) {
		return random.nextDouble() < shrubDensity;
	}	
	
	public byte rollShrubType(Random random) {
		double total = 0.0;
		for(int i = 0; i < shrubTypeChance.length; i++) {
			total += shrubTypeChance[i];
		}
		double d = random.nextDouble()*total;
		double chance = 0.0;
		for(int i = 0; i < shrubType.length; i++) {
			if(d < chance+shrubTypeChance[i]) {
				return shrubType[i];
			} else {
				chance += shrubTypeChance[i];
			}
		}
		return (byte) 1;
	}
	
	public byte rollFlowerType(Random random) {
		double total = 0.0;
		for(int i = 0; i < flowerTypeChance.length; i++) {
			total += flowerTypeChance[i];
		}
		double d = random.nextDouble()*total;
		double chance = 0.0;
		for(int i = 0; i < flowerType.length; i++) {
			if(d < chance+flowerTypeChance[i]) {
				return flowerType[i];
			} else {
				chance += flowerTypeChance[i];
			}
		}
		return (byte) 1;
	}

	public boolean rollReed(Random random) {
		return random.nextDouble() < reedDensity;
	}

	public boolean rollCactus(Random random) {
		return random.nextDouble() < cactusDensity;
	}

	public boolean rollFlower(Random random) {
		return random.nextDouble() < flowerDensity;
	}
	
	public int rollFlowerClusterSize(Random random) {
		return random.nextInt(flowerClusterMax-flowerClusterMin+1) + flowerClusterMin;
	}
	
	public boolean rollMushroom(Random random) {
		return random.nextDouble() < mushroomDensity;
	}
	
	public boolean rollMushroomType(Random random) {
		return random.nextDouble() < mushroomClusterIsRedChance;
	}
	
	public int rollMushroomClusterSize(Random random) {
		return random.nextInt(mushroomClusterMax-mushroomClusterMin+1) + mushroomClusterMin;
	}

	public boolean rollPumpkin(Random random) {
		return random.nextDouble() < pumpkinDensity;
	}
	
	public int rollPumpkinClusterSize(Random random) {
		return random.nextInt(pumpkinClusterMax-pumpkinClusterMin+1) + pumpkinClusterMin;
	}
	
	public boolean rollWatermelon(Random random) {
		return random.nextDouble() < watermelonDensity;
	}
	
	public int rollWatermelonClusterSize(Random random) {
		return random.nextInt(watermelonClusterMax-watermelonClusterMin+1) + watermelonClusterMin;
	}
	
	//Boulders are broken
	public boolean rollBoulder(Random random) {
		return random.nextDouble() < boulderDensity;
	}	

	public int rollBoulderClusterSize(Random random) {
		return random.nextInt(boulderClusterMax-boulderClusterMin+1) + boulderClusterMin;
	}
		
	public int getMaximumCaveSpace(int islandMass) {
		return (int) (caveDescription.cavePercentage * islandMass);
	}

	public int rollOreVein(Random random, double depth) {
		for(int i = 0; i < oreTypeChance.length; i++) {
			if(depth < 1.0-oreTypeMinDepth[i] && random.nextDouble() < oreTypeChance[i]) {
				return i;
			}
		}
		return -1;
	}

	public int rollOreVeinSize(Random random, int type) {
		return random.nextInt(oreTypeVeinMax[type]-oreTypeVeinMin[type]+1)+oreTypeVeinMin[type];
	}

	public byte getOreMaterial(int type) {
		return oreTypeMaterial[type];
	}

	public boolean rollWaterPond(Random random) {
		return random.nextDouble() < pondWaterChance;
	}
	
	public boolean rollLavaPond(Random random) {
		return random.nextDouble() < pondLavaChance;
	}

	public boolean rollCave(Random random) {
		return random.nextDouble() < chanceOfCave;
	}
	
}
