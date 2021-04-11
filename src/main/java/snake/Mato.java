package snake;
import java.util.ArrayList;
import java.util.Random;

public class Mato {
	
	/**
	 * Snakes body, consists of Pala-objects. 1. object is head of snake.
	 */
	private ArrayList<Pala> keho = new ArrayList<Pala>();
	
	//vaihtoehdot: ylos, alas, oikea, vasen
	String headDir;
	String tailDir;
	
	
	/**
	 * Konstruktori lisää Pala-olion ja antaa suunnan madon keholle.
	 * Lähtöpaikka on satunnaisesti keskellä kenttää ja suunta on myös satunnainen.
	 * 
	 */
	public Mato() {
		
		//satunnaisuus lähtöä varten
		Random r = new Random();
		String[] suunnat = new String[] {"ylos", "alas", "oikea","vasen"};
		
		//lisätään pää ja sen paikka ja suunta
		int lahtoX = (r.nextInt(2))+4;
		int lahtoY = (r.nextInt(2))+4;
		
		Pala paa = new Pala(lahtoX, lahtoY);
		keho.add(paa);
		headDir = suunnat[r.nextInt(4)];
	}
	
	public ArrayList<Pala> getKeho(){
		return keho;
	}
	
	/**
	 * Kasvattaa madon, lisää Pala-olion madon kehoon ja antaa sille liikesuuntaan nähden oikeat koordinaatit
	 */
	public void grow() {
		int x = 0;
		int y = 0;
		
		//jos vain pää
		if(keho.size() == 1) {
			if(headDir.equals("vasen")) {
				x = keho.get(0).getX();
				y = keho.get(0).getY() + 1;
			}
			if(headDir.equals("oikea")) {
				x = keho.get(0).getX();
				y = keho.get(0).getY() - 1;
			}
			if(headDir.equals("ylos")) {
				x = keho.get(0).getX() + 1;
				y = keho.get(0).getY();
			}
			if(headDir.equals("alas")) {
				x = keho.get(0).getX() - 1;
				y = keho.get(0).getY();
			}
		}else{
			//jos 2 tai enemmän palaa: haetaan 2 viimeisintä palaa, verrataan niiden kulkusuunta
			//ja kasvatetaan häntä menosuunnasta poispäin
			Pala vikaPala = keho.get(keho.size()-1);
			Pala vertPala = keho.get(keho.size()-2); 		
			int x2 = vertPala.getX();
			int y2 = vertPala.getY();
			int x1 = vikaPala.getX();
			int y1 = vikaPala.getY();
			
			if(x1>x2 ) {
				x = x1 + 1;
				y = y1;
			}
			if(x1<x2 ) {
				x = x1 - 1;
				y = y1;
			}
			if(y1<y2 ) {
				x = x1;
				y = y1 - 1;
			}
			if(y1>y2) {
				x = x1;
				y = y1 + 1;
			}
			
		//lisätään oikeat koordinaatit uudelle hännälle ja lisätään uusi häntä listaan
		keho.add(keho.size(), new Pala(x, y));
		}	
	}
	
	public void setDir(String s) {
		headDir = s;
	}
	
	public String getDir() {
		return headDir;
	}
	
	public void move() {
			System.out.println("Check1");
			if(keho.size() > 1) {
			//loppuosa siirtyy ensin
			for(int i = 1; i < keho.size(); i++) {
				Pala p2 = keho.get(i);
				Pala p1 = keho.get(i-1);
				p2.setX(p1.getX());
				p2.setY(p1.getY());
			}
			System.out.println("Check2");
			}
			//pää siirtyy lopuksi
			if(keho.size()==1) {
				System.out.println("Check3");
				if(headDir.equals("ylos")) {
					Pala paa = keho.get(0);
					int paaSijainti = paa.getY();
					paa.setY(paaSijainti-1);
					System.out.println("ylos");
				}
				if(headDir.equals("alas")) {
					Pala paa = keho.get(0);
					int paaSijainti = paa.getY();
					paa.setY(paaSijainti+1);
					System.out.println("alas");
				}
				if(headDir.equals("vasen")) {
					Pala paa = keho.get(0);
					int paaSijainti = paa.getX();
					System.out.println(paaSijainti);
					paa.setX(paaSijainti-1);
					System.out.println("vasen");
					System.out.println("Check2");
					System.out.println(paaSijainti);
				}
				if(headDir.equals("oikea")) {
					Pala paa = keho.get(0);
					int paaSijainti = paa.getX();
					paa.setX(paaSijainti+1);
					System.out.println("oikea");
				}
			}
	}
	
	public boolean legalToMove() {
		boolean osuuReunaan = false;
		boolean osuuHantaan = false;
		if((keho.get(0).getX() < 10) && (keho.get(0).getX() >= 0) && 
			(keho.get(0).getX() < 10) && (keho.get(0).getX() >= 0)) {
			osuuReunaan = false;
		}  
		int menossaX = keho.get(0).getX();
		int menossaY = keho.get(0).getY();
		
		if(headDir.equals("ylos")) {
			menossaY = keho.get(0).getY()-1;
		}
		if(headDir.equals("alas")) {
			menossaY = keho.get(0).getY()+1;
		}
		if(headDir.equals("vasen")) {
			menossaX = keho.get(0).getX()-1;
		}
		if(headDir.equals("oikea")) {
			menossaX = keho.get(0).getX()+1;
		}
		
		for(int i = 0; i < keho.size(); i++) {
			if((menossaX == keho.get(i).getX()) && (menossaY == keho.get(i).getX())) {
				osuuHantaan = true;
			}else {
				osuuHantaan = false;
			}
		}
		
		if(!osuuHantaan && !osuuReunaan) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean osuukoRuokaan(Ruoka r) {
		if((keho.get(0).getX() == r.getX()) && (keho.get(0).getY() == r.getY())){
			return true;
		}else {
			return false;
		}
	}


}
