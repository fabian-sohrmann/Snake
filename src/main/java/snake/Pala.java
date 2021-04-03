package snake;

/**
 * This class creates the body parts for the snake. Each part has its own coordinates.
 * @author fabia
 *
 */
public class Pala {
	
	private int x;
	private int y;
	
	public Pala(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 *Method returns x coordinate 
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Method returns y coordinates
	 * @return
	 */
	public int getY() {
		return y;
	}
		
}
