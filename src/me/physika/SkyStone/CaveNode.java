package me.physika.SkyStone;

public class CaveNode implements Comparable<CaveNode> {

	public static int currentComparisonDimension = 0;
	
	double x, y, z;
	double angleX, angleY;
	double radius, radius2;

	public CaveNode(double x, double y, double z, double angleX, double angleY, double radius) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.angleX = angleX;
		this.angleY = angleY;
		this.radius = radius;
		this.radius2 = radius*radius;
	}

	public int compareTo(CaveNode o) {
		switch(currentComparisonDimension) {
			case 0:
				if(this.x < o.x) return -1;
				if(this.x == o.x) return 0;
				return 1;
			case 1:
				if(this.y < o.y) return -1;
				if(this.y == o.y) return 0;
				return 1;
			case 2:
				if(this.z < o.z) return -1;
				if(this.z == o.z) return 0;
				return 1;
			default:
				return 0;
		}
	}
	
	public double differenceFrom(CaveNode o) {
		switch(currentComparisonDimension) {
			case 0:
				return Math.abs(this.x-o.x);
			case 1:
				return Math.abs(this.y-o.y);
			case 2:
				return Math.abs(this.z-o.z);
			default:
				return 0;
		}
	}
	
	public String toString() {
		return "("+x+", "+y+", "+z+")";
	}
	
}
