package snake;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;

import org.junit.Test;

public class MatoTester {
	@Test
	public void growsMatoX() {
		
		/**
		 *  PP = madon pää, ## = kehon palat
		 *
		 *
		 *	00 01 02 03
		 *	10 11 PP 13
		 *	20 ## ## 23
		 *	30 31 32 33
		 *
		 * Luodaan maton kehoa edustava lista, jonka osilla on koordinaatit eli
		 * etäisyydet vasemmasta yl�kulmasta {21,22,12}
		 * Kasvattamisen j�lkeen pit�isi olla {21,22,12,02}. Tämä metodi testaa vain
		 * X-koordinaatit.
		
		*/
		
		Mato mato = new Mato();
		ArrayList<Pala> keho = mato.getKeho();
		keho.set(0, new Pala(2,1));
		keho.add(new Pala(2,2));
		keho.add(new Pala(1,2));
		mato.setDir(null);
		mato.grow();
		
		int[] Xkoordinaatit = new int[4];
		for(int i = 0; i < keho.size(); i++) {
			int x = keho.get(i).getX();
			Xkoordinaatit[i] = x;
		}
		
		int[] oikeatXKoordinaatit = new int[] {2,2,1,0};
		
		
		assertArrayEquals(oikeatXKoordinaatit, Xkoordinaatit);
	}
	
	@Test
	public void growsMatoY() {
		
		/**
		 *  PP = madon pää, ## = kehon palat
		 *
		 *
		 *	00 01 02 03
		 *	10 11 PP 13
		 *	20 ## ## 23
		 *	30 31 32 33
		 *
		 * Luodaan maton kehoa edustava lista, jonka osilla on koordinaatit eli
		 * etäisyydet vasemmasta yläkulmasta {21,22,12}
		 * Kasvattamisen jälkeen pitäisi olla {21,22,12,02}. Tämä metodi testaa vain
		 * Y-koordinaatit.
		
		*/
		
		Mato mato = new Mato();
		ArrayList<Pala> keho = mato.getKeho();
		keho.set(0, new Pala(2,1));
		keho.add(new Pala(2,2));
		keho.add(new Pala(1,2));
		mato.setDir(null);
		mato.grow();
		
		int[] Ykoordinaatit = new int[4];
		for(int i = 0; i < keho.size(); i++) {
			int y = keho.get(i).getY();
			Ykoordinaatit[i] = y;
		}
		
		int[] oikeatYKoordinaatit = new int[] {1, 2, 2, 2};
		
		
		assertArrayEquals(oikeatYKoordinaatit, Ykoordinaatit);
	}
	
	/**
	 *  PP = madon pää, ## = kehon palat
	 *
	 *
	 *	00 01 02 03
	 *	10 11 PP 13
	 *	## ## ## 23
	 *	30 31 32 33
	 *
	 * Luodaan maton kehoa edustava lista, jonka osilla on koordinaatit {21,22,12,02}
	 * liikuttamisen jälkeen (vasemmalle) pitäisi olla {11,21,22,12}. Tämä metodi testaa vain
	 * X-koordinaatit.
	
	*/
	
	@Test
	public void moveX() {
		
		
		Mato mato = new Mato();
		ArrayList<Pala> keho = mato.getKeho();
		keho.set(0, new Pala(2,1));
		keho.add(new Pala(2,2));
		keho.add(new Pala(1,2));
		keho.add(new Pala(0,2));
		mato.setDir("vasen");
		mato.move();
		
		int[] Xkoordinaatit = new int[4];
		for(int i = 0; i < keho.size(); i++) {
			int x = keho.get(i).getX();
			System.out.println(x);
			Xkoordinaatit[i] = x;
		}
		
		
		int[] oikeatXKoordinaatit = new int[] {1,2,2,1};
		
		assertArrayEquals(oikeatXKoordinaatit, Xkoordinaatit);
	}
	
	/**
	 *  PP = madon pää, ## = kehon palat
	 *
	 *
	 *	00 01 02 03
	 *	10 11 PP 13
	 *	## ## ## 23
	 *	30 31 32 33
	 *
	 * Luodaan maton kehoa edustava lista, jonka osilla on koordinaatit {21,22,12,02}
	 * liikuttamisen jälkeen (vasemmalle) pitäisi olla {11,21,22,12}. Tämä metodi testaa vain
	 * Y-koordinaatit.
	
	*/
	
	@Test
	public void moveY() {
		
		Mato mato = new Mato();
		ArrayList<Pala> keho = mato.getKeho();
		keho.set(0, new Pala(2,1));
		keho.add(new Pala(2,2));
		keho.add(new Pala(1,2));
		keho.add(new Pala(0,2));
		mato.setDir("vasen");
		mato.move();
		
		int[] Ykoordinaatit = new int[4];
		for(int i = 0; i < keho.size(); i++) {
			int y = keho.get(i).getY();
			Ykoordinaatit[i] = y;
		}
		
		int[] oikeatYKoordinaatit = new int[] {1,1,2,2};
		
		assertArrayEquals(oikeatYKoordinaatit, Ykoordinaatit);
	}
}
	
