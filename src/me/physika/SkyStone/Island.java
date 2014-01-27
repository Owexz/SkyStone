package me.physika.SkyStone;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class Island extends Schematic {
	
	/**
	 * Creates an island at the given chunk position
	 * @param gen - plugin to report all output to 
	 * @param world - world to generate in 
	 * @param random - random generator for generation 
	 * @param chunkX - chunk position
	 * @param chunkZ - chunk position
	 * @return
	 */
	public static Island createIsland(SkyStoneGenerator gen, World world, Random random, int chunkX, int chunkZ) {
		
		Biome mcBiome = world.getBiome(chunkX*16+8, chunkZ*16+8);
		BiomeDescription biome = BiomeDescription.getDescription(mcBiome);
		
		if(!biome.rollIsland(random) && !(chunkX == 0 && chunkZ == 0)) return null;
		Int3D size = null;
		Int3D position = null;
		if(chunkX == 0 && chunkZ == 0) {
			biome = BiomeDescription.getDescription(Biome.FOREST);
			size = new Int3D(128, 64, 128);
			position = new Int3D(-56, 48, -56);
			gen.spawn = new Int3D(0, (int) (position.y+size.y-biome.roughness), 0);
		} else {
			size = biome.getDimensions(random);
			position = biome.getPosition(random, chunkX, chunkZ, size);
		}
		
		return new Island(biome, random, position, size);
		
	}
	
	private static final byte grass = (byte) Material.GRASS.getId();
	private static final byte dirt = (byte) Material.DIRT.getId();
	private static final byte stone = (byte) Material.STONE.getId();
	private static final byte air = (byte) Material.AIR.getId();
	private static final byte snow = (byte) Material.SNOW.getId();
	private static final byte sand = (byte) Material.SAND.getId();
	private static final byte gravel = (byte) Material.GRAVEL.getId();
	private static final byte water = (byte) Material.WATER.getId();
	private static final byte lava = (byte) Material.LAVA.getId();
	private static final byte reed = (byte) Material.SUGAR_CANE_BLOCK.getId();
	private static final byte cactus = (byte) Material.CACTUS.getId();
	private static final byte flowerRed = (byte) Material.RED_ROSE.getId();
	private static final byte flowerYellow = (byte) Material.YELLOW_FLOWER.getId();
	private static final byte mushroomBrown = (byte) Material.BROWN_MUSHROOM.getId();
	private static final byte mushroomRed = (byte) Material.RED_MUSHROOM.getId();
	private static final byte pumpkin = (byte) Material.PUMPKIN.getId();
	private static final byte watermelon = (byte) Material.MELON_BLOCK.getId();
	private static final byte coalBlock = (byte) Material.COAL_BLOCK.getId();
	private static final byte mycelium = (byte) Material.MYCEL.getId();
		
	protected int centerX;
	protected int centerY;
	protected int centerZ;
	protected Random random;
	protected boolean[][] isLand;
	protected int[][] distanceFromEdge;
	protected int[][] heightMap;
	protected int[][] bottomMap;
	protected int[][] cliffMap;
	protected double[][] heightCoefficients;
	protected BiomeDescription biome;
	protected int islandMass = 0;
	
	/**
	 * Constructs and therefore generates an island. These parameters are hard to fill so
	 * use the Island.createIsland() method instead.
	 * @param biome - BiomeDescription returned from BiomeDescription.getDescription()
	 * @param random - random object used for generation (dependent on the world seed and chunk position)
	 * @param position - position of the island's center block
	 * @param size - size of the island  
	 */
	public Island(BiomeDescription biome, Random random, Int3D position, Int3D size) {
		super(position.x, position.y, position.z, size.x, size.y, size.z);
		
		this.biome = biome;
		
		this.random = random;
		
		this.centerX = this.length / 2;
		this.centerZ = this.bredth / 2;
		this.centerY = this.height - biome.getAirSpace();
		
		generateIslandForm();			//Generates topographical shape
		calculateHeightCoefficients();	//Calculates lots of useful data
		generateIslandTerrain();		//Main terrain generation
		erode();						//Roughens up the edges of things and makes them look nice
		addHollows();					//Caves and water/lava ponds
		supportFallingMaterials();		//Supports any flowing or falling materials
		populatePlants();				//Trees and shrubs
		addExtras();					//Lava falls, water falls, and ores
		
	}

	/**
	 * Generates a blob-like surface shape for the island using
	 * a technique that produces random blobs followed by an
	 * after-pass to clean up small holes.
	 */
	private void generateIslandForm() {
		
		//Array to describe the shape of the island when looking down on it
		this.isLand = new boolean[length][bredth];
		
		//Initialize a few points around the center of the island
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(centerX-1, centerZ));
		points.add(new Point(centerX+1, centerZ));
		points.add(new Point(centerX, centerZ-1));
		points.add(new Point(centerX, centerZ+1));
		isLand[centerX][centerZ] = true;
		isLand[centerX-1][centerZ] = true;
		isLand[centerX+1][centerZ] = true;
		isLand[centerX][centerZ-1] = true;
		isLand[centerX][centerZ+1] = true;
		
		//The number of times to iterate
		int num = (int) (length*bredth*0.5);
		
		//Odds of the island growing in the specified direction
		double xOdds = (double)length / (double)(length+bredth);
		double zOdds = 1.0-xOdds;
		
		for(int i = 0; i < num && points.size() > 0; i++) {
			
			//Choose a random point
			Point p = points.get(random.nextInt(points.size()));
			
			int x = (int) p.getX();
			int z = (int) p.getY();
			
			//If the roll is successful, add the neighbor to the x-1 direction to the list of points
			if(x > 0 && !isLand[x - 1][z] && random.nextDouble() < xOdds) {
				Point q = new Point(x - 1, z);
				if(!points.contains(q)) {
					if(x > 1) points.add(q);
					isLand[x - 1][z] = true;
				}
			}

			//If the roll is successful, add the neighbor to the x+1 direction to the list of points
			if(x < length - 1 && !isLand[x + 1][z] && random.nextDouble() < xOdds) {
				Point q = new Point(x + 1, z);
				if(!points.contains(q)) {
					if(x < length - 2) points.add(q);
					isLand[x + 1][z] = true;
				}
			}

			//If the roll is successful, add the neighbor to the z-1 direction to the list of points
			if(z > 0 && !isLand[x][z - 1] && random.nextDouble() < zOdds) {
				Point q = new Point(x, z - 1);
				if(!points.contains(q)) {
					if(z > 1) points.add(q);
					isLand[x][z - 1] = true;
				}
			}

			//If the roll is successful, add the neighbor to the z+1 direction to the list of points
			if(z < bredth - 1 && !isLand[x][z + 1] && random.nextDouble() < zOdds) {
				Point q = new Point(x, z + 1);
				if(!points.contains(q)) {
					if(z < bredth - 2) points.add(q);
					isLand[x][z + 1] = true;
				}
			}
			
			//If the point chosen originally has no more empty neighbors, throw it out
			if(isLand[x-1][z] && isLand[x+1][z] && isLand[x][z-1] && isLand[x][z+1]) {
				points.remove(p);
			}
			
		}

		points.clear();
		
		//Add the positon of adjacent air blocks
		for(int x = 1; x < length - 1; x++) {
			for(int z = 1; z < bredth - 1; z++) {
				if(!isLand[x][z]) {
					int n = 0;
					if(isLand[x - 1][z]) n++;
					if(isLand[x + 1][z]) n++;
					if(isLand[x][z - 1]) n++;
					if(isLand[x][z + 1]) n++;
					if(n > 1) points.add(new Point(x, z));
				}
			}
		}
		
		//Fill the adjacent air blocks so as to fill any small holes
		for(Point p : points) {
			int x = (int) p.getX();
			int z = (int) p.getY();
			if(x >= 0 && x < length && z >= 0 && z < bredth) {
				isLand[x][z] = true;
			}
		}
		
	}

	/**
	 * Calculates each of the island tiles' Manhattan distance from
	 * the nearest edge of the island, then normalizes and curves it
	 * to create a nice coefficient to multiply heights by to make an
	 * island with smooth edges.
	 */
	private void calculateHeightCoefficients() {
		
		this.heightCoefficients = new double[length][bredth];
		this.distanceFromEdge = new int[length][bredth];
		
		for(int x = 0; x < length; x++) {
			for(int z = 0; z < bredth; z++) {
				if(!isLand[x][z]) {
					heightCoefficients[x][z] = 0;
				} else {
					heightCoefficients[x][z] = 1;
				}
			}
		}
		
		int edge = (length + bredth) / 4;
		double max = 0;
		for(int i = 0; i < edge; i++) {
			for(int x = 1; x < length - 1; x++) {
				for(int z = 1; z < bredth - 1; z++) {
					if(heightCoefficients[x][z] > 0) {
						
						double lowNeighbor = 100;
						
						if(heightCoefficients[x - 1][z] < lowNeighbor) lowNeighbor = heightCoefficients[x - 1][z];
						if(heightCoefficients[x + 1][z] < lowNeighbor) lowNeighbor = heightCoefficients[x + 1][z];
						if(heightCoefficients[x][z - 1] < lowNeighbor) lowNeighbor = heightCoefficients[x][z - 1];
						if(heightCoefficients[x][z + 1] < lowNeighbor) lowNeighbor = heightCoefficients[x][z + 1];
						
						heightCoefficients[x][z] = lowNeighbor + 1;
						if(lowNeighbor + 1 > max) max = lowNeighbor + 1;
						
					}
				}
			}
		}
		
		for(int x = 0; x < length; x++) {
			for(int z = 0; z < bredth; z++) {
				distanceFromEdge[x][z] = (int) heightCoefficients[x][z];
				heightCoefficients[x][z] = heightCoefficients[x][z] / max;
				heightCoefficients[x][z] = 1.0 - Math.pow(1.0 - heightCoefficients[x][z], 2.5); //Power adjustment makes it look nicer
			}
		}
		
	}

	/**
	 * Generates the main island blocks. I've rewritten this so many times I'm not quite sure how
	 * it works any more and it doesn't help that I don't know the meaning of the scaling on the 
	 * Simplex noise generator.
	 */
	private void generateIslandTerrain() {
		
		//Does this island have lakes?
		boolean lake = false;
		if(biome.rollLake(random)) lake = true;
		
		//Roll the island's cliff height
		int cliffHeight = 0;
		if(biome.cliffs) {
			cliffHeight = biome.rollCliffHeight(random);
		}
		
		//Initialize the island height maps
		heightMap = new int[length][bredth];
		bottomMap = new int[length][bredth];
		cliffMap = new int[length][bredth];
		
		//Create a noise generator
		SimplexOctaveGenerator gen = new SimplexOctaveGenerator(random, 8);
		gen.setScale(1.0 / 64.0); //Not sure what this really means in terms of the numbers
		
		for(int x = 0; x < length; x++) {
			for(int z = 0; z < bredth; z++) {
				
				//If this column is part of the 2d shape generated in generateIslandForm();
				if(isLand[x][z]) {
					
					double distance = heightCoefficients[x][z]; 				//Multiplier to make the island curve in near the edges
					double noiseVal = gen.noise(x, z, biome.frequency, 0.25);	//Noise value from the noise generator
					
					//Complicated process of determining the surface height of the column
					double maxYd = noiseVal * Math.pow(distance, 0.2);
					if(lake && maxYd < 0.0) maxYd *= 3.0;
					if(lake && maxYd < 0.0) maxYd *= distance;
					maxYd += 0.5;
					if(maxYd < -0.5) maxYd = -0.5;
					int maxY = (int) (maxYd * biome.roughness);
					maxY += centerY;
					if(biome.snow && maxY > height - 2) maxY = height - 2;
					if(maxY > height - 1) {
						maxY = height - 1;
					}
					if(maxY < 0) maxY = 0;
					heightMap[x][z] = maxY;
					
					//Cliff noise
					int cliff = 0;
					if(biome.cliffs) {
						double cliffnoise = gen.noise(x, z, 0.4, 0.25);
						cliffnoise -= 0.5*biome.lakeChance*biome.lakeChance*biome.lakeChance;
						if(cliffnoise > 0 && distance > 0.9) cliff = cliffHeight;
						maxY += cliff;
					}
					cliffMap[x][z] = cliff;
					
					//Make sure the height map plus the cliff map is in bounds
					if(biome.snow && maxY > height - 2) maxY = height - 2;
					if(maxY > height - 1) {
						maxY = height - 1;
					}
					if(maxY < 0) maxY = 0;
					
					//Calculate the height of the bottom of the island
					int minY = (int) (centerY * (1.0-(0.8+gen.noise(x, z, 3.0, 0.25)*0.4)*distance));
					if(minY > centerY-4) minY = (int) (centerY-4+gen.noise(x, z, 3.0, 0.25)*2*distance);
					if(minY < 0) minY = 0;
					bottomMap[x][z] = minY;					
					
					//Replace the top biome.islandSoilDepth percentage of the column with biome.islandSoilMaterial
					//and the remaining lower part with biome.islandRockMaterial
					int range = maxY - minY;
					int soilDepth = (int) (range * (1.0-biome.islandSoilDepth));
					double sedimentNoise = gen.noise(x, z, 0.8, 0.25)+0.5;
					for(int y = minY; y < maxY; y++) {
						islandMass++;
						if((y - minY) < soilDepth || cliff > 0) {
							setBlock(x, y, z, biome.islandRockMaterial);
						} else {
							setBlock(x, y, z, biome.islandSoilMaterial);
						}
					}
					
					//Add snow if necessary
					if(biome.snow && !lake) setBlock(x, maxY + 1, z, biome.islandSnowMaterial);

					//Add water if there are lakes or biome.islandSurfaceMaterial
					int waterLevel = (int) (centerY+biome.roughness/2.0-1.0);
					if(lake && maxY < waterLevel) {
						if(sedimentNoise < 0.33) {
							setBlock(x, maxY, z, biome.islandLakeBedDirt);
						} else {
							setBlock(x, maxY, z, biome.islandLakeBedMaterial);
						}
						for(int y = maxY + 1; y < waterLevel; y++) {
							setBlock(x, y, z, biome.islandFluidMaterial);
						}
					} else {
						setBlock(x, maxY, z, biome.islandSurfaceMaterial);
					}
				}
			}
		}
	}
	
	/**
	 * Removes some of the blocks around the edges of the island and cliffs for a more natural look.
	 */
	private void erode() {
		for(int i = 1; i < length-1; i++) {
			for(int k = 1; k < bredth-1; k++) {
				
				//If the block is on the edge of the island...
				if(distanceFromEdge[i][k] == 1) {
					int jm = random.nextInt(heightMap[i][k] - bottomMap[i][k] + 1) + bottomMap[i][k];
					
					//...randomly remove the bottom blocks from that column 
					for(int j = 0; j < jm; j++) {
						byte block = getBlock(i, j, k);
						if(block == biome.islandRockMaterial || block == biome.islandSoilMaterial) {
							setBlock(i, j, k, air);
						}
					}
				}
				
				//If the block is at the edge of a cliff...
				if(cliffMap[i][k] > 0) {
					if(cliffMap[i-1][k] == 0 || cliffMap[i+1][k] == 0 || cliffMap[i][k-1] == 0 || cliffMap[i][k+1] == 0) {
						int jm = random.nextInt(cliffMap[i][k]*3/4) + heightMap[i][k];
						
						//...randomly remove the bottom blocks from a column between the island surface and the cliff top
						for(int j = heightMap[i][k]+1; j < jm; j++) {
							byte block = getBlock(i, j, k);
							
							//only the island's rock material is affected
							if(block == biome.islandRockMaterial) setBlock(i, j, k, air);
						}
					}
				}
				
			}
		}
	}
	
	/**
	 * Places stable blocks under all sand and gravel and around the bottom and edge blocks of lava and water.
	 * Secretly also replaces any dirt that has air above it with grass.
	 */
	private void supportFallingMaterials() {
		//Plant grass and support falling materials
		for(int i = 1; i < length-1; i++) {
			for(int k = 1; k < bredth-1; k++) {
				for(int j = 0; j < height; j++) {
					
					//Sand or gravel
					if(getBlock(i, j, k) == sand || getBlock(i, j, k) == gravel) {
						if(j > 0) {
							if(getBlock(i, j-1, k) == air) setBlock(i, j-1, k, biome.islandRockMaterial);
							if(getBlock(i, j-1, k) == caveSpace) setBlock(i, j-1, k, biome.islandRockMaterial);
						} else {
							setBlock(i, j, k, biome.islandRockMaterial);
						}
					}
					
					//Liquids
					if(getBlock(i, j, k) == water) {
						if(j > 0) {
							if(getBlock(i, j-1, k) == air) setBlock(i, j-1, k, biome.islandRockMaterial);
							if(getBlock(i-1, j, k) == air) setBlock(i-1, j, k, biome.islandSoilMaterial);
							if(getBlock(i+1, j, k) == air) setBlock(i+1, j, k, biome.islandSoilMaterial);
							if(getBlock(i, j, k-1) == air) setBlock(i, j, k-1, biome.islandSoilMaterial);
							if(getBlock(i, j, k+1) == air) setBlock(i, j, k+1, biome.islandSoilMaterial);
							if(getBlock(i, j-1, k) == caveSpace) setBlock(i, j-1, k, biome.islandRockMaterial);
							if(getBlock(i-1, j, k) == caveSpace) setBlock(i-1, j, k, biome.islandSoilMaterial);
							if(getBlock(i+1, j, k) == caveSpace) setBlock(i+1, j, k, biome.islandSoilMaterial);
							if(getBlock(i, j, k-1) == caveSpace) setBlock(i, j, k-1, biome.islandSoilMaterial);
							if(getBlock(i, j, k+1) == caveSpace) setBlock(i, j, k+1, biome.islandSoilMaterial);
						} else {
							setBlock(i, j, k, biome.islandRockMaterial);
						}
					}
					
					if(getBlock(i, j, k) == lava) {
						if(j > 0) {
							if(getBlock(i, j-1, k) == air) setBlock(i, j-1, k, biome.islandRockMaterial);
							if(getBlock(i-1, j, k) == air) setBlock(i-1, j, k, biome.islandRockMaterial);
							if(getBlock(i+1, j, k) == air) setBlock(i+1, j, k, biome.islandRockMaterial);
							if(getBlock(i, j, k-1) == air) setBlock(i, j, k-1, biome.islandRockMaterial);
							if(getBlock(i, j, k+1) == air) setBlock(i, j, k+1, biome.islandRockMaterial);
							if(getBlock(i, j-1, k) == caveSpace) setBlock(i, j-1, k, biome.islandRockMaterial);
							if(getBlock(i-1, j, k) == caveSpace) setBlock(i-1, j, k, biome.islandRockMaterial);
							if(getBlock(i+1, j, k) == caveSpace) setBlock(i+1, j, k, biome.islandRockMaterial);
							if(getBlock(i, j, k-1) == caveSpace) setBlock(i, j, k-1, biome.islandRockMaterial);
							if(getBlock(i, j, k+1) == caveSpace) setBlock(i, j, k+1, biome.islandRockMaterial);
						} else {
							setBlock(i, j, k, biome.islandRockMaterial);
						}
					}
					
					//Add grass to any dirt that has air above it
					if(biome.islandSurfaceMaterial == grass && j < height-1 && getBlock(i, j, k) == dirt && (getBlock(i, j+1, k) == air || getBlock(i, j+1, k) == caveSpace)) {
						setBlock(i, j, k, grass);
					}
				}
			}
		}
	}
	
	/**
	 * Generates some colorful plant-life including flowers, shrubs, cactuses, sugar cane, and trees.
	 * All statistics are controlled by the BiomeDescription.
	 */
	private void populatePlants() {
		for(int i = 1; i < length-1; i++) {
			for(int k = 1; k < bredth-1; k++) {
				int j = heightMap[i][k]+cliffMap[i][k];
				if(j < height-1 && getBlock(i, j+1, k) == air || getBlock(i, j+1, k) == snow) {
					
					//If the block is grass... (If any of the following succeed, the ones after them are ignored via the "continue" keyword)
					if(getBlock(i, j, k) == grass) {
						
						//Roll for a tree of a random type
						if(biome.rollTree(random)) {
							generateTree(i, j+1, k, biome.rollTreeType(random));
							continue;
						}
						
						//Roll for a reed
						if(getBlock(i+1, j, k) == water || getBlock(i-1, j, k) == water || getBlock(i, j, k+1) == water || getBlock(i, j, k-1) == water) {
							if(biome.rollReed(random)) {
								placeReed(i, j+1, k, random.nextInt(3)+1);
								continue;
							}
						}
						
						//Roll for a flower cluster
						if(biome.rollFlower(random)) {
							byte type = biome.rollFlowerType(random);
							if(type != 0) placeFlower(i, j+1, k, type);
							continue;
						}
						
						//Roll for a mushroom cluster (These should really be underground) [RIP Mushroom]
						if(biome.rollMushroom(random)) {
							if(biome.rollMushroomType(random)) {
								placePlantCluster(mushroomRed, i, k, biome.rollMushroomClusterSize(random), 5);
								continue;
							} else {
								placePlantCluster(mushroomBrown, i, k, biome.rollMushroomClusterSize(random), 5);			
								continue;					
							}
						}
						
						//Roll for a pumpkin cluster
						if(biome.rollPumpkin(random)) {
							placePlantCluster(pumpkin, i, k, biome.rollPumpkinClusterSize(random), 5);
							continue;
						}
						
						//Roll for a watermelon cluster
						if(biome.rollWatermelon(random)) {
							placePlantCluster(watermelon, i, k, biome.rollWatermelonClusterSize(random), 5);
							continue;
						}
						
						//Roll for a shrub (tall grass or "fern" only)
						if(biome.rollShrub(random)) {
							byte type = biome.rollShrubType(random);
							if(type != 0) placeShrub(i, j+1, k, type);
							continue;
						}
					}
					
					// (For Mega Taiga Biomes) If the block is Coal_Block (podzol).. (If any of the following succeed, the ones after them are ignored via the "continue" keyword)
					if(getBlock(i, j, k) == coalBlock) {
						
						//Roll for a tree of a random type
						if(biome.rollTree(random)) {
							generateTree(i, j+1, k, biome.rollTreeType(random));
							continue;
						}
						
						//Roll for a shrub (tall grass or "fern" only)
						if(biome.rollShrub(random)) {
							byte type = biome.rollShrubType(random);
							if(type != 0) placeShrub(i, j+1, k, type);
							continue;
						}
					}
					
					// (For Mushroom Biomes) If the block is Mycelium.. (If any of the following succeed, the ones after them are ignored via the "continue" keyword)
					if(getBlock(i, j, k) == mycelium) {
						
						//Roll for a tree of a random type
						if(biome.rollTree(random)) {
							generateTree(i, j+1, k, biome.rollTreeType(random));
							continue;
						}
						
						//Roll for a mushroom cluster [RIP Mushroom]
						if(biome.rollMushroom(random)) {
							if(biome.rollMushroomType(random)) {
								placePlantCluster(mushroomRed, i, k, biome.rollMushroomClusterSize(random), 5);
								continue;
							} else {
								placePlantCluster(mushroomBrown, i, k, biome.rollMushroomClusterSize(random), 5);			
								continue;					
							}
						}						
					}
					//If the block is sand... (again successes skip the latter populators)
					if(getBlock(i, j, k) == sand) {
						
						//Roll for a reed
						if(getBlock(i+1, j, k) == water || getBlock(i-1, j, k) == water || getBlock(i, j, k+1) == water || getBlock(i, j, k-1) == water) {
							if(biome.rollReed(random)) {
								placeReed(i, j+1, k, random.nextInt(3)+1);
								continue;
							}
						}
						
						//Roll for a cactus
						if(getBlock(i+1, j+1, k) == air || getBlock(i-1, j+1, k) == air || getBlock(i, j+1, k+1) == air || getBlock(i, j+1, k-1) == air) {
							if(biome.rollCactus(random)) {
								placeCactus(i, j+1, k, random.nextInt(3)+1);
								continue;
							}
						}
						
						//Roll for a shrub (dried bush only)
						if(biome.rollShrub(random)) {
							byte type = biome.rollShrubType(random);
							if(type == 0) placeShrub(i, j+1, k, type);
							continue;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Plants a tree of the TreeType given. The actual generation
	 * is done in the block populator, this method just adds a place holder.
	 * @param x - position of the bottom of the tree, not the block below it
	 * @param y - position of the bottom of the tree, not the block below it
	 * @param z - position of the bottom of the tree, not the block below it
	 */
	private void generateTree(int x, int y, int z, TreeType type) {
		switch(type) {
			case BIG_TREE: 
				generateBigTree(x, y, z);
				return;
			case BIRCH:
				generateBirchTree(x, y, z);
				return;
			case BROWN_MUSHROOM:
				generateTallBrownMushroom(x, y, z);
				return;
			case RED_MUSHROOM:
				generateTallRedMushroom(x, y, z);
				return;
			case REDWOOD:
				generateRedwoodTree(x, y, z);
				return;
			case TALL_REDWOOD:
				generateTallRedwoodTree(x, y, z);
				return;
			case TREE:
				generateTree(x, y, z);
				return;
			case MEGA_REDWOOD:
				generateMegaRedwoodTree(x, y, z);
				return;
			case ACACIA:
				generateAcaciaTree(x, y, z);
				return;
			case SWAMP:
				generateSwampTree(x, y, z);
				return;
			case DARK_OAK:
				generateDarkOakTree(x, y, z);
				return;
			case JUNGLE:
				generateJungleTree(x, y, z);
				return;	
			case SMALL_JUNGLE:
				generateSmallJungleTree(x, y, z);
				return;		
			case JUNGLE_BUSH:
				generateJungleBushTree(x, y, z);
				return;	
		}
	}
	/**
	 * Plants a jungle bush, as with all tree types, the actual generation
	 * is done in the block populator and this method just adds a place holder.
	 * @param x - position of the bottom of the tree, not the block below it
	 * @param y - position of the bottom of the tree, not the block below it
	 * @param z - position of the bottom of the tree, not the block below it
	 */
	private void generateJungleBushTree(int x, int y, int z) {
		setBlock(x, y, z, (byte) Material.BRICK_STAIRS.getId());
	}
	
	/**
	 * Plants a small jungle tree, as with all tree types, the actual generation
	 * is done in the block populator and this method just adds a place holder.
	 * @param x - position of the bottom of the tree, not the block below it
	 * @param y - position of the bottom of the tree, not the block below it
	 * @param z - position of the bottom of the tree, not the block below it
	 */
	private void generateSmallJungleTree(int x, int y, int z) {
		setBlock(x, y, z, (byte) Material.BOOKSHELF.getId());
	}
	
	/**
	 * Plants a large jungle tree, as with all tree types, the actual generation
	 * is done in the block populator and this method just adds a place holder.
	 * @param x - position of the bottom of the tree, not the block below it
	 * @param y - position of the bottom of the tree, not the block below it
	 * @param z - position of the bottom of the tree, not the block below it
	 */
	private void generateJungleTree(int x, int y, int z) {
		setBlock(x, y, z, (byte) Material.CAULDRON.getId());
	}
	
	/**
	 * Plants a swamp tree, as with all tree types, the actual generation
	 * is done in the block populator and this method just adds a place holder.
	 * @param x - position of the bottom of the tree, not the block below it
	 * @param y - position of the bottom of the tree, not the block below it
	 * @param z - position of the bottom of the tree, not the block below it
	 */
	private void generateDarkOakTree(int x, int y, int z) {
		setBlock(x, y, z, (byte) Material.JUKEBOX.getId());
	}
	
	/**
	 * Plants a swamp tree, as with all tree types, the actual generation
	 * is done in the block populator and this method just adds a place holder.
	 * @param x - position of the bottom of the tree, not the block below it
	 * @param y - position of the bottom of the tree, not the block below it
	 * @param z - position of the bottom of the tree, not the block below it
	 */
	private void generateSwampTree(int x, int y, int z) {
		setBlock(x, y, z, (byte) Material.WEB.getId());
	}
	
	/**
	 * Plants a acacia tree, as with all tree types, the actual generation
	 * is done in the block populator and this method just adds a place holder.
	 * @param x - position of the bottom of the tree, not the block below it
	 * @param y - position of the bottom of the tree, not the block below it
	 * @param z - position of the bottom of the tree, not the block below it
	 */
	private void generateAcaciaTree(int x, int y, int z) {
		setBlock(x, y, z, (byte) Material.ACACIA_STAIRS.getId());
	}
	
	/**
	 * Plants a mega redwood tree, as with all tree types, the actual generation
	 * is done in the block populator and this method just adds a place holder.
	 * @param x - position of the bottom of the tree, not the block below it
	 * @param y - position of the bottom of the tree, not the block below it
	 * @param z - position of the bottom of the tree, not the block below it
	 */
	private void generateMegaRedwoodTree(int x, int y, int z) {
		setBlock(x, y, z, (byte) Material.SPRUCE_WOOD_STAIRS.getId());
	}
		
	/**
	 * Plants a normal tree, as with all tree types, the actual generation
	 * is done in the block populator and this method just adds a place holder.
	 * @param x - position of the bottom of the tree, not the block below it
	 * @param y - position of the bottom of the tree, not the block below it
	 * @param z - position of the bottom of the tree, not the block below it
	 */
	private void generateTree(int x, int y, int z) {
		setBlock(x, y, z, (byte) Material.BRICK.getId());
	}
	
	/**
	 * Plants a big tree, as with all tree types, the actual generation
	 * is done in the block populator and this method just adds a place holder.
	 * @param x - position of the bottom of the tree, not the block below it
	 * @param y - position of the bottom of the tree, not the block below it
	 * @param z - position of the bottom of the tree, not the block below it
	 */
	private void generateBigTree(int x, int y, int z) {
		setBlock(x, y, z, (byte) Material.BEDROCK.getId());
	}
	
	/**
	 * Plants a tall birch tree, as with all tree types, the actual generation
	 * is done in the block populator and this method just adds a place holder.
	 * @param x - position of the bottom of the tree, not the block below it
	 * @param y - position of the bottom of the tree, not the block below it
	 * @param z - position of the bottom of the tree, not the block below it
	 */
	private void generateBirchTree(int x, int y, int z) {
		setBlock(x, y, z, (byte) Material.DIAMOND_BLOCK.getId());
	}
	
	/**
	 * Plants a red wood, as with all tree types, the actual generation
	 * is done in the block populator and this method just adds a place holder.
	 * @param x - position of the bottom of the tree, not the block below it
	 * @param y - position of the bottom of the tree, not the block below it
	 * @param z - position of the bottom of the tree, not the block below it
	 */
	private void generateRedwoodTree(int x, int y, int z) {
		setBlock(x, y, z, (byte) Material.LAPIS_BLOCK.getId());
	}
	
	/**
	 * Plants a tall redwood (pine tree), as with all tree types, the actual generation
	 * is done in the block populator and this method just adds a place holder.
	 * @param x - position of the bottom of the tree, not the block below it
	 * @param y - position of the bottom of the tree, not the block below it
	 * @param z - position of the bottom of the tree, not the block below it
	 */
	private void generateTallRedwoodTree(int x, int y, int z) {
		setBlock(x, y, z, (byte) Material.SPONGE.getId());
	}
	
	/**
	 * Plants a tall brown mushroom, as with all tree types, the actual generation
	 * is done in the block populator and this method just adds a place holder.
	 * @param x - position of the bottom of the tree, not the block below it
	 * @param y - position of the bottom of the tree, not the block below it
	 * @param z - position of the bottom of the tree, not the block below it
	 */
	private void generateTallBrownMushroom(int x, int y, int z) {
		setBlock(x, y, z, (byte) Material.GOLD_BLOCK.getId());
	}
	
	/**
	 * Plants a tall red mushroom, as with all tree types, the actual generation
	 * is done in the block populator and this method just adds a place holder.
	 * @param x - position of the bottom of the tree, not the block below it
	 * @param y - position of the bottom of the tree, not the block below it
	 * @param z - position of the bottom of the tree, not the block below it
	 */
	private void generateTallRedMushroom(int x, int y, int z) {
		setBlock(x, y, z, (byte) Material.IRON_BLOCK.getId());
	}
	
	/**
	 * Places a plant shrub. The type is not a material but a data value
	 * for the Long_Grass material. The values are as follows:	<br><p><code>
	 * 0 - dried bush	<br>
	 * 1 - tall grass	<br>
	 * 2 - "fern" as notch calls it (more like a pine tree shrub)	</code><br><p>
	 * Does not actually place the plant, instead places a block that is later
	 * replaced by the proper shrub since data values are not accessable from
	 * the initial generation step.
	 * @param x
	 * @param y
	 * @param z
	 * @param type
	 */
	private void placeShrub(int x, int y, int z, byte type) {
		switch(type) {
			case 0:
				setBlock(x, y, z, (byte) Material.CHEST.getId());
				return;
			case 1:
				setBlock(x, y, z, (byte) Material.WORKBENCH.getId());
				return;
			case 2:
				setBlock(x, y, z, (byte) Material.FURNACE.getId());
				return;
		}
	}
	
	/**
	 * Places a flower. The type is not a material but a data value
	 * for the flower material. The values are as follows:	<br><p><code>
	 * 1 - dandelion	<br>
	 * 2 - poppy	<br>
	 * 3 - blue orchid	<br>
	 * 4 - allium	<br>
	 * 5 - azure bluet	<br>
	 * 6 - red tulip	<br>
	 * 7 - orange tulip	<br>
	 * 8 - white tulip	<br>
	 * 9 - pink tulip	<br>
	 * 10 - oxeye daisy	<br>
	 * 11 - sunflower (Broken)	<br>
	 * 12 - lilac	(Broken)<br>
	 * 13 - rose bush	(Broken)<br>
	 * 14 - peony	(Broken)<br>
	 * Does not actually place the plant, instead places a block that is later
	 * replaced by the proper shrub since data values are not accessable from
	 * the initial generation step.
	 * @param x
	 * @param y
	 * @param z
	 * @param type
	 */
	private void placeFlower(int x, int y, int z, byte type) {
		switch(type) {
			case 1:
				setBlock(x, y, z, (byte) Material.BREWING_STAND.getId());
				return;
			case 2:
				setBlock(x, y, z, (byte) Material.GLASS.getId());
				return;
			case 3:
				setBlock(x, y, z, (byte) Material.QUARTZ_BLOCK.getId());
				return;
			case 4:
				setBlock(x, y, z, (byte) Material.QUARTZ_STAIRS.getId());
				return;
			case 5:
				setBlock(x, y, z, (byte) Material.COBBLESTONE_STAIRS.getId());
				return;
			case 6:
				setBlock(x, y, z, (byte) Material.COBBLE_WALL.getId());
				return;
			case 7:
				setBlock(x, y, z, (byte) Material.DAYLIGHT_DETECTOR.getId());
				return;
			case 8:
				setBlock(x, y, z, (byte) Material.FENCE.getId());
				return;
			case 9:
				setBlock(x, y, z, (byte) Material.FENCE_GATE.getId());
				return;
			case 10:
				setBlock(x, y, z, (byte) Material.FLOWER_POT.getId());
				return;
			case 11:
				setBlock(x, y, z, (byte) Material.GLASS.getId());
				return;
			case 12:
				setBlock(x, y, z, (byte) Material.HOPPER.getId());
				return;
			case 13:
				setBlock(x, y, z, (byte) Material.HAY_BLOCK.getId());
				return;
			case 14:
				setBlock(x, y, z, (byte) Material.JACK_O_LANTERN.getId());
				return;
		}
	}
	
	/**
	 * Places a sugar cane plant on the block given if it has room to grow. Does not verify that the block given is grass or sand.
	 * @param x - position of the block that the sugar cane should be planted on
	 * @param y - position of the block that the sugar cane should be planted on
	 * @param z - position of the block that the sugar cane should be planted on
	 * @param height - height of the sugar cane
	 */
	private void placeReed(int x, int y, int z, int height) {
		for(int j = 0; j < height; j++) {
			setBlockSafe(x, y+j, z, reed);
		}
	}
	
	/**
	 * Places a cactus on the block given if it has room to grow. Does not verify that the block given is sand.
	 * @param x - position of the sand block that the cactus should be planted on
	 * @param y - position of the sand block that the cactus should be planted on
	 * @param z - position of the sand block that the cactus should be planted on
	 * @param height - height of the cactus
	 */
	private void placeCactus(int x, int y, int z, int height) {
		for(int j = 0; j < height; j++) {
			if(getBlockSafe(x-1, y, z) == air && getBlockSafe(x+1, y, z) == air && getBlockSafe(x, y, z-1) == air && getBlockSafe(x, y, z+1) == air) {
				setBlockSafe(x, y+j, z, cactus);
			} else {
				break;
			}
		}
	}
	
	/**
	 * Rolls a random cluster blocks of the given material centered roughly on the (x, z) location
	 * provided. There will be a number of plants equal to numPlants.
	 * @param type - material of the plant (one of the strange blocks used as place holders for the block populator)
	 * @param x - position on the surface to place the plant cluster
	 * @param z - position on the surface to place the plant cluster
	 * @param numPlants - number of plants in the cluster
	 * @param radius - max radius of the cluster (technically the cluster is a square)
	 */
	private void placePlantCluster(byte type, int x, int z, int numPlants, int radius) {
		placePlant(type, x, z);
		for(int i = 1; i < numPlants; i++) {
			placePlant(type, x+random.nextInt(radius*2)-radius, z+random.nextInt(radius*2)-radius);
		}
	}

	/**
	 * Places a single plant on top of the given block if it is a grass block.
	 * The position is indexed as an (x, z) location and will automatically be put on the
	 * top layer of the island. If either block is out of bounds, nothing happens.
	 * @param type - material of the plant (one of the strange blocks used as place holders for the block populator)
	 * @param x - position on the surface to place the plant
	 * @param z - position on the surface to place the plant
	 */
	private void placePlant(byte type, int x, int z) {
		if(x < 0 || x >= length || z < 0 || z >= bredth) return;
		int y = heightMap[x][z]+cliffMap[x][z];
		if(y < 0 || y >= height-1) return;
		if(getBlock(x, y, z) == grass) setBlock(x, y+1, z, type);
	}
	
	/**
	 * Adds the cave system, a slow process, and then populates some lava and water lakes.
	 * Caves were a very complicated task so the code for them is in a different class.
	 * Because it was hard to get caves to look good at all, their shape is not affected by biome.
	 */
	private void addHollows() {
		
		if(biome.rollCave(random)) {
			//Create random object for cave generation
			Random random = new Random(this.random.nextLong());
			int x = length/2, y = height/2, z = bredth/2;
			
			//Create two systems moving in opposite directions somewhere in the middle of the island 
			double angle = random.nextDouble()*Math.PI*2.0;
			CaveNode nodeL = new CaveNode(x, y, z, angle, -0.2, biome.caveDescription.startRadius);
			CaveSystem systemL = new CaveSystem(this, random, nodeL, biome.getMaximumCaveSpace(islandMass)/2);
			CaveNode nodeR = new CaveNode(x, y, z, angle-Math.PI, -0.2, biome.caveDescription.startRadius);
			CaveSystem systemR = new CaveSystem(this, random, nodeR, biome.getMaximumCaveSpace(islandMass)/2);
			
			blocks = systemL.carve(blocks);
			blocks = systemR.carve(blocks);
			
		}
		
		//Randomly add some ponds to the underground and the surface. Lava ponds spawn underground, water on the surface.
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < height; j++) {
				for(int k = 0; k < bredth; k++) {
					if(getBlock(i, j, k) == biome.islandSurfaceMaterial && biome.rollWaterPond(random)) {
						addPond(i, j, k, biome.islandFluidMaterial);
					}
					if(getBlock(i, j, k) == biome.islandRockMaterial && biome.rollLavaPond(random)) {
						addPond(i, j, k, lava);
					}
				}
			}
		}
		
	}
	
	/**
	 * Adds in a pond at the given location. The max size is 5x5 and it is a rounded shape.
	 * Ponds always have a depth of 2 and are of the material given in the fluid parameter.
	 * @param x - coordinate of the middle of the pond.
	 * @param y - coordinate of the middle of the pond.
	 * @param z - coordinate of the middle of the pond.
	 * @param fluid - material to fill the pond with
	 */
	private void addPond(int x, int y, int z, byte fluid) {
		boolean[][] lake = new boolean[5][5];
		lake[2][2] = true;
		for(int j = 0; j < 4; j++) {
			for(int i = 0; i < 5; i++) {
				for(int k = 0; k < 5; k++) {
					if(i > 0 && lake[i-1][k] && random.nextDouble() < 0.5) {
						lake[i][k] = true;
						continue;
					}
					if(i < 4 && lake[i+1][k] && random.nextDouble() < 0.5) {
						lake[i][k] = true;
						continue;
					}
					if(k > 0 && lake[i][k-1] && random.nextDouble() < 0.5) {
						lake[i][k] = true;
						continue;
					}
					if(k < 4 && lake[i][k+1] && random.nextDouble() < 0.5) {
						lake[i][k] = true;
						continue;
					}
				}
			}
		}
		for(int i = 0; i < 5; i++) {
			for(int k = 0; k < 5; k++) {
				if(lake[i][k]) {
					setBlockSafe(x-2+i, y-1, z-2+k, fluid);
					setBlockSafe(x-2+i, y, z-2+k, fluid);
					setBlockSafe(x-2+i, y+1, z-2+k, air);
				}
			}
		}
	}

	/**
	 * Creates random water and lava falls like the ones you will find in caves in the normal generator
	 * and also adds ore to the ground. Odds for ores and liquid falls are in the BiomeDescription class.
	 */
	private void addExtras() {
		for(int i = 1; i < length-1; i++) {
			for(int k = 1; k < bredth-1; k++) {
				for(int j = bottomMap[i][k]; j <= heightMap[i][k]+cliffMap[i][k]; j++) {
					
					byte block = getBlock(i, j, k);
					
					if(block == biome.islandRockMaterial || block == biome.islandSoilMaterial) {
						
						//count how many adjacent spaces are or will be air 
						// - caveSpace is currently end stone and is replaced with air by a block populator, 
						//   this makes it so that caves can not be overwritten by other islands. While a little
						//   slower than just using air blocks, it makes cool situations.
						int n = 0;
						if(getBlockSafe(i, j-1, k) != caveSpace && getBlockSafe(i, j+1, k) != caveSpace) { 
							if(getBlock(i+1, j, k) == caveSpace) n++;
							if(getBlock(i-1, j, k) == caveSpace) n++;
							if(n < 2 && getBlock(i, j, k+1) == caveSpace) n++;
							if(n < 2 && getBlock(i, j, k-1) == caveSpace) n++;
							
							//If there is only one adjacent air block and the top and bottom are covered, roll for a liquid fall.
							if(n == 1 && random.nextDouble() < biome.liquidFallChance) {
								if(random.nextDouble() < biome.lavaFallChance) {
									setBlock(i, j, k, lava);
								} else {
									setBlock(i, j, k, water);
								}
								continue;
							}
						}
					}
					
					//Roll for an ore vein
					if(block == stone) {
						double depth = (double)(j-bottomMap[i][k]) / (double)(heightMap[i][k]+cliffMap[i][k]-bottomMap[i][k]);
						int oreVein = biome.rollOreVein(random, depth);
						if(oreVein > -1) {
							int amount = biome.rollOreVeinSize(random, oreVein);
							byte material = biome.getOreMaterial(oreVein);
							populateOreVein(i, j, k, material, amount);
						}
					}
					
				}
			}
		}
	}

	/**
	 * Creates a random vein of the given material at the position (i, j, k) with a number
	 * of ores equal to the amount given unless there are not enough stone blocks or the vein
	 * is positioned at the edge of the schematic.
	 * @param i - x coordinate of the initial ore node
	 * @param j - y coordinate of the initial ore node (depth)
	 * @param k - z coordinate of the initial ore node
	 * @param material - material to make the vein out of
	 * @param amount - max number of ores in the vein
	 */
	private void populateOreVein(int i, int j, int k, byte material, int amount) {
		//Create a list of nodes and a temporary list of neighbors
		ArrayList<Int3D> nodes = new ArrayList<Int3D>();
		ArrayList<Int3D> neighbors = new ArrayList<Int3D>();
		
		//Add an initial node
		nodes.add(new Int3D(i, j, k));
		int tries = 0;
		
		oreWhileLoop:
		while(nodes.size() < amount && tries < 10) {
			
			//Choose a node at random
			Int3D node = nodes.get(random.nextInt(nodes.size()));
			
			//Populate its list of neighbors
			neighbors.clear();
			neighbors.add(new Int3D(node.x+1, node.y, node.z));
			neighbors.add(new Int3D(node.x-1, node.y, node.z));
			neighbors.add(new Int3D(node.x, node.y+1, node.z));
			neighbors.add(new Int3D(node.x, node.y-1, node.z));
			neighbors.add(new Int3D(node.x, node.y, node.z+1));
			neighbors.add(new Int3D(node.x, node.y, node.z-1));
			
			//Shuffle the neighbors so that the same direction is not chosen every time
			Collections.shuffle(neighbors);
			
			//For each neighbor...
			for(Int3D n : neighbors) {
				if(!nodes.contains(n)) {
					
					//...check if it is stone and, if it is, add it to the node list
					if(getBlockSafe(n.x, n.y, n.z) == stone) {
						nodes.add(n);
						tries = 0;
						continue oreWhileLoop;
					}
				}
			}
			
			//If the node selected has no valid neighbors 10 times in a row, break out of the loop
			tries++;
		}
		
		//Place ore in each of the nodes' positions
		for(Int3D n : nodes) {
			setBlock(n.x, n.y, n.z, material);
		}
		
	}

	/**
	 * This method converts the Island object to a Schematic object and by doing
	 * so dumps all of the allocated memory used during generation.
	 * @return Schematic
	 */
	public Schematic asSchematic() {
		return (Schematic) this;
	}
	
}
