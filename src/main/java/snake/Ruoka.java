package snake;

import java.util.Random;

/**
 * Luokka edustaa Ruoka-olioita. Sillä on muutujat x ja y, jotka edustavat ruokapalan sijainti.
 * Konstruktori luo niille satunnaiset arvot. 
 */
public class Ruoka {
	private int x;
	private int y; 
	
	public Ruoka() {
		Random r = new Random();
		this.x = r.nextInt(10);
		this.y = r.nextInt(10);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}