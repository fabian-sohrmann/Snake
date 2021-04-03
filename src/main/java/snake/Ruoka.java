package snake;

/**
 * This class creates the pieces of food the snake will eat.
 * @author fabia
 *
 */
public class Ruoka {
	private int x;
	private int y;
	
	public Ruoka(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
