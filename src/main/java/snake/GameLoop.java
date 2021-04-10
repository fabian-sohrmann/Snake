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
	long lastUpdateTime = 0;
	
	GameLoop(GraphicsContext gc, Image tausta, Image osa, Image syotava, 
			ArrayList<Ruutu> ruudukko, Mato mato){
		this.gc = gc; 
		this.tausta = tausta;
		this.osa = osa;
		this.syotava = syotava;
		this.ruudukko = ruudukko;
		this.mato = mato;
	}
	
	
	boolean noWorm = true;
	@Override
	public void handle(long time) {
		
		
		mato.move();
		
		for(int i = 0; i < 100; i++) {
			Ruutu r = ruudukko.get(i);
			int ruutuX = r.getX();
			int ruutuY = r.getY();

			for(int j = 0; j < mato.getKeho().size(); j++) {
				Pala pala = mato.getKeho().get(j);
				int matoX = pala.getX();
				int matoY = pala.getY();
				
				if(ruutuX == matoX && ruutuY == matoY) {
					r.setImage(osa);
				}
			}
		}
		
		
		/*
		Ruoka ruoka = null;
		if(ruoka == null) {
			ruoka = new Ruoka();
		}*/
		
		/*//Päivitä ruutujen kuvat listassa sen mukaan, onko samoissa koordinaateissa mato tai ruoka.
		for(int i = 0; i < 100; i++) {
			Ruutu r = ruudukko.get(i);
			int ruutuX = r.getX();
			int ruutuY = r.getY();

			for(int j = 0; j < mato.getKeho().size(); j++) {
				Pala pala = mato.getKeho().get(j);
				int matoX = pala.getX();
				int matoY = pala.getY();
				
				if(ruutuX == matoX && ruutuY == matoY) {
					r.setImage(osa);
				}
				if(ruutuX == ruoka.getX() && ruutuY == ruoka.getY()) {
					r.setImage(syotava);
				}
			}
			
		}*/
		
		/*//tarkistetaan onko madon seuraava liike laillinen
		if(!(mato.legalToMove())) {
			//TARKISTA ONKO VOITTO VAI HÄVIÖ 
			if(mato.getKeho().size() == 100) {
				//VOITTO
			}else {
				//HÄVIÖ
			}
		}*/
		
		/*//liikutetaan mato
		mato.move();
		if(mato.osuukoRuokaan(ruoka)) {
			mato.grow();
			ruoka = null;
		}*/
		
		
		//1 000 000 000 nanoseconds = 1 second
		if((time/1000000000 - lastUpdateTime/1000000000)>=1) {
			for(int i = 0; i < 100; i++) {
				Image kuva = ruudukko.get(i).getKuva();
				int x = ruudukko.get(i).getX();
				int y = ruudukko.get(i).getY();
				gc.drawImage(kuva, x, y);
			}
			//
			lastUpdateTime = time;
		}
		
		
		//System.out.println(time);
		//System.out.println(lastUpdateTime);
		
		
		
		
		
	}
	
}
