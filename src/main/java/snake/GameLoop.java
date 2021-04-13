package snake;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * <h1>GameLoop</h1>s
 * GameLoop perii AnimationTimer-luokan ja korvaa sen handle(long l) metodi. Tämä metodi ajetaan 
 * jokaisen framen yhteydessä. GameLoopille on tehty korvaava konstruktori, joka saa Game-luokasta
 * tarpeelliset oliot pelilogiikan luomiseen. Se tallentaa niitä omiin muuttujiin. Muita 
 * muuttujia ovat kokonaislukumuuttuja pisteet, joka käytetään pelin pistetilanteen seuraamiseen, ja 
 * long-tyyppinen muuttuja lastUpdateTime, jolle annetaan lähtöarvoksi 0. Tämä luokka hoitaa siis pelilogiikan.
 */
public class GameLoop extends AnimationTimer{
	
	GraphicsContext gc;
	Image tausta;
	Image osa;
	Image syotava;
	ArrayList<Ruutu> ruudukko;
	Mato mato;
	Ruoka ruoka;
	Image background;
	Label title;
	Label points;
	int pisteet = 0;
	long lastUpdateTime = 0;
	
	GameLoop(GraphicsContext gc, Image tausta, Image osa, Image syotava, Image background, 
			ArrayList<Ruutu> ruudukko, Mato mato, Ruoka ruoka, Label title, Label points){
		this.gc = gc; 
		this.tausta = tausta;
		this.osa = osa;
		this.syotava = syotava;
		this.ruudukko = ruudukko;
		this.mato = mato;
		this.ruoka = ruoka;
		this.background = background;
		this.title = title;
		this.points = points;
	}
	
	
	/**
	 * Korvaa AnimationTimerin handle()-metodi.Metodi ajetaan jokaisen kehyksen yhteydessä. Tämän
	 * avulla piirretään pelikenttä aina uusiksi. Metodi saa automaattisesti parametriksi nykyisen 
	 * ajan. Tämän ja muuttujuan lastUpdateTime avulla on pelikentän piirtämisen saatu hidastettu
	 * noin yhteen kertaan sekunnissa. Se ajaa siis vaadittavan koodin tähän vain, jos edellisestä
	 * kehyksestä on kulunut noin sekunti. Käyttää pelilogiikassa omia metodeja piirraRuudukko() ja 
	 * paivitaRuudukko() sekä Mato-luokan metodeja.
	 */
	@Override
	public void handle(long time) {
		
		paivitaRuudukko(ruudukko, mato, ruoka);
		
		//1 000 000 000 nanoseconds = 1 seconds
		if((time/1000000000 - lastUpdateTime/1000000000)>=1) {
			
			
			piirraRuudukko(ruudukko);
			mato.move();
			//tarkistetaan onko madon seuraava liike laillinen
			if(mato.osuukoReunaan() || mato.osuukoHantaan()){
				System.out.println("Peli ohi!");
				
				
				if(mato.getKeho().size()==100) {
					System.out.println("Voitit!");
					this.stop();
					title.setText("You won the game! Congratulations! \n\n");
				}else {
					System.out.println("H�visit!");
					this.stop();
					title.setText("You lost the game!\n\n");
					
				}
			}else {
				System.out.println("Pelijatkuu");
				if(mato.osuukoRuokaan(ruoka)) {
					mato.grow();
					pisteet++;
					points.setText("" + pisteet);
					ruoka = new Ruoka();
				}
			}
			
			lastUpdateTime = time;
		}
		
		
	}
	
	/**
	 * Metodi saa parametrina listan Ruutu-olioita. Pelikenttä piirretään käymällä koko lista
	 * läpi ja hakemalla ja piirtämällä jokaisen ruudun koordinaatit ja näytettävät kuva.  
	 */
	private void piirraRuudukko(ArrayList<Ruutu> ruudukko) {
		Ruutu ruutu;
		for(int i = 0; i < 100; i++){
			ruutu = ruudukko.get(i);
			gc.drawImage(ruutu.getKuva(), 20*ruutu.getX(), 20*ruutu.getY());
		}
	}
	
	/**
	 * Metodi äivittää saatun ruudukon kuvat sivuvaikutuksena. Se saa maton, ruuan ja listan
	 * parametreina ja päivittää ruudukko-listaa vastaamaan madon ja ruuan sijainteja. Se käy 
	 * jokaisen ruudukon ruudun läpi ja vertailee sitä madon ja ruuan koordinaatteihin ja päivittää
	 * ruudukon kuvat sen mukaisesti.
	 */
	private void paivitaRuudukko(ArrayList<Ruutu> ruudukko, Mato m, Ruoka r) {
		Ruutu ruutu;
		for(int i = 0; i < 100; i++){
			ruutu = ruudukko.get(i);
			ruutu.setImage(tausta);
			int ruutuX = ruutu.getX();
			int ruutuY = ruutu.getY();
			for(int j = 0; j < m.getKeho().size(); j++) {
				Pala pala = m.getKeho().get(j);
				int palaX = pala.getX();
				int palaY = pala.getY();
				if((ruutuX == palaX) && (ruutuY == palaY)) {
					ruutu.setImage(osa);
				}
			}
			int ruokaX = r.getX();
			int ruokaY = r.getY();
			if((ruokaX == ruutuX) && (ruokaY == ruutuY)) {
				ruutu.setImage(syotava);
			} 		
		}
	}

	
}
