package snake;

/**
 * Tästä luokasta muodostetaan maton kehoa edustavia Pala-olioita. Luokalla on muuttujat x ja y,
 * jotka edustavat palan sijainti.
 */
public class Pala {
	
	private int x;
	private int y;
	
	public Pala(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	

	public int getY() {
		return y;
	}
	
	public void setX(int i) {
		x = i;
	}
	
	public void setY(int i) {
		y = i;
	}
		
}
