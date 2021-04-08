package snake;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Kentta {
	private int width = 10;
	private int height  = 10;
	/**
	 * Konstruktori luo listan ArrayList<ImageView>, joka sis‰lt‰‰ kuvia. Kuvilla on absoluttinen sijainti. 
	 * @throws FileNotFoundException
	 */
	public Kentta() throws FileNotFoundException {
		
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
