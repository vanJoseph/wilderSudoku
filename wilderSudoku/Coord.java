package wilderSudoku;
/**
 * The Coord class holds x and y values. 
 * @author Donovan J. Wilder
 * @since 2016-07-17
 *
 */
public class Coord {
	public int x;
	public int y;
	
	/**
	 * Constructs a Coord while setting its x and y values
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Coord(int x, int y){
		this.x=x;
		this.y=y;
	}
	/**
	 * Returns a string with an ordered pair structure
	 */
	@Override
	public String toString(){
		return"(" + x+","+y+")";
	}
	/**
	 * Determines if two coords are equal by seeing if their
	 * x and y values are the equal.
	 * @param coord 
	 * @return true if the same, or false if not
	 *		
	 */
	@Override
	public boolean equals(Object coord	){
		if((((Coord)coord).x==this.x)&& (((Coord)coord).y==this.y)){
			return true;	
			
		}
		return false;
		
	}

}
