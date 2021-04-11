package snake;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameLoop extends AnimationTimer{
	
	GraphicsContext gc;
	Image tausta;
	Image osa;
	Image syotava;
	ArrayList<Ruutu> ruudukko;
	Mato mato;
	Ruoka ruoka;
	
	long lastUpdateTime = 0;
	
	GameLoop(GraphicsContext gc, Image tausta, Image osa, Image syotava, 
			ArrayList<Ruutu> ruudukko, Mato mato, Ruoka ruoka){
		this.gc = gc; 
		this.tausta = tausta;
		this.osa = osa;
		this.syotava = syotava;
		this.ruudukko = ruudukko;
		this.mato = mato;
		this.ruoka = ruoka;
	}
	
	@Override
	public void handle(long time) {
		
		
		/*
		//tarkistetaan onko madon seuraava liike laillinen
		if(!(mato.legalToMove())) {
			//TARKISTA ONKO VOITTO VAI HÄVIÖ 
			if(mato.getKeho().size() == 100) {
				//VOITTO
			}else {
				//HÄVIÖ
			}
		}
		
		//liikutetaan mato
		mato.move();
		if(mato.osuukoRuokaan(ruoka)) {
			mato.grow();
			ruoka = null;
		}*/
		
		
		//1 000 000 000 nanoseconds = 1 second
		if((time/1000000000 - lastUpdateTime/1000000000)>=1) {
			paivitaRuudukko(ruudukko, mato, ruoka);
			piirraRuudukko(ruudukko);
			mato.move();
			mato.grow();
			
			lastUpdateTime = time;
		}
		
		
	
		
	}
	
	private void piirraRuudukko(ArrayList<Ruutu> ruudukko) {
		Ruutu ruutu;
		for(int i = 0; i < 100; i++){
			ruutu = ruudukko.get(i);
			gc.drawImage(ruutu.getKuva(), 20*ruutu.getX(), 20*ruutu.getY());
		}
	}
	
	//Päivittää ruudukon kuvat sivuvaikutuksena.
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
