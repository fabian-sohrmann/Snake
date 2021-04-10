package snake;

import java.util.Random;

/**
 * Luokka luo Ruoka-olioita. Konstruktori luo sille satunnaisen sijainnin. 
 * @author fabia
 *
 */
public class Ruoka {
	private int x;
	private int y; 
	
	public Ruoka() {
		int[] arrX = new int[] {0,20,40,60,80,100,120,140,160,180};
		int[] arrY = new int[] {0,20,40,60,80,100,120,140,160,180};
		
		Random r = new Random();
		this.x = arrX[r.nextInt(10)];
		this.y = arrY[r.nextInt(10)];
	}
	
	public int getX() {
		return x*20;
	}
	
	public int getY() {
		return y*20;
	}
}
