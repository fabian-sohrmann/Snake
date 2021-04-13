package snake;

import javafx.scene.image.Image;

/**
 * Tämä luokka käytetään peliruudun piirtämiseen. Jokaisella Ruutu-oliolla annetaan konstruktorissa sijainti 
 * x ja y sekä kuva eli Image-olio.
 *
 */
public class Ruutu {
	
	private Image kuva;
	private int x;
	private int y;
	
	public Ruutu(Image kuva, int x, int y){
		this.kuva = kuva;
		this.x = x;
		this.y = y;
	}
	
	public Image getKuva() {
		return kuva;
	}
	
	public void setImage(Image img) {
		kuva = img;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int i) {
		this.x = i;
	}
	
	public void setY(int i) {
		this.y = i;
	}
	
}
