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
	 * Konstruktori lis‰‰ Pala-olion ja antaa suunnan madon keholle.
	 * L‰htˆpaikka on satunnaisesti keskell‰ kentt‰‰ ja suunta on myˆs satunnainen.
	 * 
	 */
	public Mato() {
		
		//satunnaisuus l‰htˆ‰ varten
		Random r = new Random();
		String[] suunnat = new String[] {"ylos", "alas", "oikea","vasen"};
		
		//lis‰t‰‰n p‰‰ ja sen paikka ja suunta
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
	 * Kasvattaa madon, lis‰‰ Pala-olion madon kehoon ja antaa sille liikesuuntaan n‰hden oikeat koordinaatit
	 */
	public void grow() {
		int x = 0;
		int y = 0;
		
		//jos vain p‰‰
		if(keho.size() == 1) {
			if(headDir.equals("vasen")) {
				x = keho.get(0).getX() +1;
				y = keho.get(0).getY();
			}
			if(headDir.equals("oikea")) {
				x = keho.get(0).getX()-1;
				y = keho.get(0).getY();
			}
			if(headDir.equals("ylos")) {
				x = keho.get(0).getX();
				y = keho.get(0).getY()+1;
			}
			if(headDir.equals("alas")) {
				x = keho.get(0).getX();
				y = keho.get(0).getY()-1;
			}
		}else{
			//jos 2 tai enemm‰n palaa: haetaan 2 viimeisint‰ palaa, verrataan niiden kulkusuunta
			//ja kasvatetaan h‰nt‰ menosuunnasta poisp‰in
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
			
		
			
			
		}	
		
		//lis‰t‰‰n oikeat koordinaatit uudelle h‰nn‰lle ja lis‰t‰‰n uusi h‰nt‰ listaan
		//keho.add(keho.size(), new Pala(x, y));
		keho.add(keho.size(), new Pala(x, y));
	}
	
	public void setDir(String s) {
		headDir = s;
	}
	
	public String getDir() {
		return headDir;
	}
	
	public void move() {
			
			if(keho.size() > 1) {
				//loppuosa siirtyy ensin ONGELMA TƒSSƒ JOSSAIN!!  
				for(int i = keho.size()-1; i >  0; i--) {
					Pala back = keho.get(i);    
					Pala front = keho.get(i-1);     
					back.setX(front.getX());
					back.setY(front.getY());
				}
				
				//p‰‰ siirtyy lopuksi
				if(headDir.equals("ylos")) {
					Pala paa = keho.get(0);
					int paaSijainti = paa.getX();
					paa.setX(paaSijainti-1);
				}
				if(headDir.equals("alas")) {
					Pala paa = keho.get(0);
					int paaSijainti = paa.getX();
					paa.setX(paaSijainti+1);
				}
				if(headDir.equals("vasen")) {
					Pala paa = keho.get(0);
					int paaSijainti = paa.getY();
					paa.setY(paaSijainti-1);
				}
				if(headDir.equals("oikea")) {
					Pala paa = keho.get(0);
					int paaSijainti = paa.getY();
					paa.setY(paaSijainti+1);
				}
			}else{
				// vain p‰‰ siirtyy TESTATTU JA OK!
				if(headDir.equals("ylos")) {
					Pala paa = keho.get(0);
					int paaSijainti = paa.getX();
					paa.setX(paaSijainti-1);
				}
				if(headDir.equals("alas")) {
					Pala paa = keho.get(0);
					int paaSijainti = paa.getX();
					paa.setX(paaSijainti+1);
				}
				if(headDir.equals("vasen")) {
					Pala paa = keho.get(0);
					int paaSijainti = paa.getY();
					paa.setY(paaSijainti-1);
				}
				if(headDir.equals("oikea")) {
					Pala paa = keho.get(0);
					int paaSijainti = paa.getY();
					paa.setY(paaSijainti+1);
				}
			}
	}
	
	public boolean osuukoReunaan() {
		
		//simuloidaan seuraava siirto ja tarkistetaan sen laillisuus
		boolean osuuReunaan = true;
		
		Pala paa = keho.get(0);
		int paaX = paa.getX();
		int paaY = paa.getY();
		
		if((paaX < 10) && (paaX >= 0) && (paaY < 10) && (paaY >= 0)) {
			osuuReunaan = false;
		}  
		
		return osuuReunaan;
	}
	
	public boolean osuukoHantaan() {
		
		//simuloidaan seuraava siirto ja tarkistetaan sen laillisuus
		boolean osuuHantaan = false;
		
		Pala paa = keho.get(0);
		int paaX = paa.getX();
		int paaY = paa.getY();
		
		for(int i = 1; i < keho.size(); i++) {
			if((paaX == keho.get(i).getX()) && (paaY == keho.get(i).getY())) {
				osuuHantaan = true;
				break;
			}
		}
		
		return osuuHantaan;
	}
	
	
	public boolean osuukoRuokaan(Ruoka r) {
		if((keho.get(0).getX() == r.getX()) && (keho.get(0).getY() == r.getY())){
			return true;
		}else {
			return false;
		}
	}


}
