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
	
	public int getX() {
		return x*20;
	}
	

	public int getY() {
		return y*20;
	}
	
	public void setX(int i) {
		x = i;
	}
	
	public void setY(int i) {
		y = i;
	}
		
}
