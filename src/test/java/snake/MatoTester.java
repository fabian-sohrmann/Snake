package snake;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;

import org.junit.Test;

public class MatoTester {
	@Test
	public void growsMatoX() {
		
		/**
		 *  PP = madon p‰‰, ## = kehon palat
		 *
		 *
		 *	00 01 02 03
		 *	10 11 PP 13
		 *	20 ## ## 23
		 *	30 31 32 33
		 *
		 * Luodaan maton kehoa edustava lista, jonka osilla on koordinaatit eli
		 * et‰isyydet vasemmasta yl‰kulmasta {21,22,12}
		 * Kasvattamisen j‰lkeen pit‰isi olla {21,22,12,13}. T‰m‰ metodi testaa vain
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
		
		int[] oikeatXKoordinaatit = new int[] {2,2,1,1};
		
		
		assertArrayEquals(oikeatXKoordinaatit, Xkoordinaatit);
	}
	
	@Test
	public void growsMatoY() {
		
		/**
		 *  PP = madon p‰‰, ## = kehon palat
		 *
		 *
		 *	00 01 02 03
		 *	10 11 PP 13
		 *	20 ## ## 23
		 *	30 31 32 33
		 *
		 * Luodaan maton kehoa edustava lista, jonka osilla on koordinaatit {12,22,21}
		 * Kasvattamisen j‰lkeen pit‰isi olla {12,22,21,20}. T‰m‰ metodi testaa vain
		 * Y-koordinaatit.
		
		*/
		
		Mato mato = new Mato();
		ArrayList<Pala> keho = mato.getKeho();
		keho.set(0, new Pala(1,2));
		keho.add(new Pala(2,2));
		keho.add(new Pala(2,1));
		mato.setDir(null);
		mato.grow();
		
		int[] Ykoordinaatit = new int[4];
		for(int i = 0; i < keho.size(); i++) {
			int y = keho.get(i).getY();
			Ykoordinaatit[i] = y;
		}
		
		int[] oikeatYKoordinaatit = new int[] {2, 2, 1, 0};
		
		
		assertArrayEquals(oikeatYKoordinaatit, Ykoordinaatit);
	}
	
	/**
	 *  PP = madon p‰‰, ## = kehon palat
	 *
	 *
	 *	00 01 02 03
	 *	10 11 PP 13
	 *	20 ## ## 23
	 *	30 31 32 33
	 *
	 * Luodaan maton kehoa edustava lista, jonka osilla on koordinaatit {12,22,21}
	 * liikuttamisen j‰lkeen (ylos) pit‰isi olla {02,12,22}. T‰m‰ metodi testaa vain
	 * X-koordinaatit.
	
	*/
	
	@Test
	public void moveX() {
		
		
		Mato mato = new Mato();
		ArrayList<Pala> keho = mato.getKeho();
		keho.set(0, new Pala(1,3));
		keho.add(new Pala(1,2));
		keho.add(new Pala(1,1));
		keho.add(new Pala(2,1));
		mato.setDir("alas");
		mato.move();
		
		int[] Xkoordinaatit = new int[4];
		for(int i = 0; i < keho.size(); i++) {
			int x = keho.get(i).getX();
			System.out.println(x);
			Xkoordinaatit[i] = x;
		}
		
		
		int[] oikeatXKoordinaatit = new int[] {2,1,1,1};
		
		assertArrayEquals(oikeatXKoordinaatit, Xkoordinaatit);
	}
	
	/**
	 *  PP = madon p‰‰, ## = kehon palat
	 *
	 *
	 *	00 01 02 03
	 *	10 ## ## PP
	 *	20 ## 22 23
	 *	30 31 32 33
	 *
	 * Luodaan maton kehoa edustava lista, jonka osilla on koordinaatit {12,22,21}
	 * liikuttamisen j‰lkeen (ylos) pit‰isi olla {02,12,22}. T‰m‰ metodi testaa vain
	 * Y-koordinaatit.
	
	*/
	
	@Test
	public void moveY() {
		
		Mato mato = new Mato();
		ArrayList<Pala> keho = mato.getKeho();
		keho.set(0, new Pala(1,3));
		keho.add(new Pala(1,2));
		keho.add(new Pala(1,1));
		keho.add(new Pala(2,1));
		mato.setDir("alas");
		mato.move();
		
		int[] Ykoordinaatit = new int[4];
		for(int i = 0; i < keho.size(); i++) {
			int y = keho.get(i).getY();
			Ykoordinaatit[i] = y;
		}
		
		int[] oikeatYKoordinaatit = new int[] {3,3,2,1};
		
		assertArrayEquals(oikeatYKoordinaatit, Ykoordinaatit);
	}
}
	
