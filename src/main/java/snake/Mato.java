package snake;
import java.util.ArrayList;
import java.util.Random;


	/**
	 * Tämä luokka edustaa matoa. Sillä on muuttujat ArrayList&stPala&lt, joka edustaa madon kehoa.
	 * Listassa on Pala-olioita, jotka tietävät oman sijainnin pelikentällä. Lisäksi String-tyyppinen
	 * muutuja headDir tietää nykyisen kulkusuunnan.
	 */
public class Mato {
	
	private ArrayList<Pala> keho = new ArrayList<Pala>();
	
	//Saa vaihtoehdot: ylos, alas, oikea, vasen
	String headDir;
	
	
	/**
	 * Konstruktori luo uuden madon, lisää Pala-olion listaan ja antaa suunnan madon keholle.
	 * Lähtöpaikka on satunnaisesti keskellä kenttää ja suunta on myös satunnainen.
	 * 
	 */
	public Mato() {
		
		Random r = new Random();
		String[] suunnat = new String[] {"ylos", "alas", "oikea","vasen"};
		
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
	 * <h1>Mato</h1>
	 * Kasvattaa madon lisäämällä Pala-olio madon kehoon (listaan) ja antaa sille liikesuuntaan 
	 * nähden oikeat koordinaatit. Jos maton pituus on 1, metodi laskee kulkusuunnan ja nykyisen
	 * sijainnin avulla uudelle palalle oikea paikka. Jos maton pituus >= 2, metodi hakee kaksi
	 * viimeistä palaa ja laskee niiden sijaintien mukaan uudelle palalle oikea paikka.
	 */
	public void grow() {
		int x = 0;
		int y = 0;
		
		//jos madon pituus == 1
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
			//pituus >= 2
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
		
		keho.add(keho.size(), new Pala(x, y));
	}
	
	public void setDir(String s) {
		headDir = s;
	}
	
	public String getDir() {
		return headDir;
	}
	
	/**
	 * Metodi liikuttaa matoa, eli se pävittää Pala-olioiden koordinaatit oikealla tavalla
	 * kulkusuuntaan nähden. Käytännössä tapahtuu siten, että kaikki koordinaatit siirtyvät 
	 * paloissa yhden askeleen taaksepäin (2. viimeinen -> viimeinen, ..., 1. -> 2.) Pää-pala
	 * päivitetään erikseen: lasketaan uusi sijainti maton kulkusuunnan avulla. Jos maton 
	 * pituus = 1, vain pää siirtyy.  
	 */
	public void move() {
			
			if(keho.size() > 1) {
				//loppuosa siirtyy ensin 
				for(int i = keho.size()-1; i >  0; i--) {
					Pala back = keho.get(i);    
					Pala front = keho.get(i-1);     
					back.setX(front.getX());
					back.setY(front.getY());
				}
				
				//pää siirtyy lopuksi
				if(headDir.equals("ylos")) {
					Pala paa = keho.get(0);
					int nykSijaintiX = paa.getX();
					int nykSijaintiY = paa.getY();
					paa.setX(nykSijaintiX);
					paa.setY(nykSijaintiY-1);
				}
				if(headDir.equals("alas")) {
					Pala paa = keho.get(0);
					int nykSijaintiX = paa.getX();
					int nykSijaintiY = paa.getY();
					paa.setX(nykSijaintiX);
					paa.setY(nykSijaintiY+1);
				}
				if(headDir.equals("vasen")) {
					Pala paa = keho.get(0);
					int nykSijaintiX = paa.getX();
					int nykSijaintiY = paa.getY();
					paa.setX(nykSijaintiX-1);
					paa.setY(nykSijaintiY);
				}
				if(headDir.equals("oikea")) {
					Pala paa = keho.get(0);
					int nykSijaintiX = paa.getX();
					int nykSijaintiY = paa.getY();
					paa.setX(nykSijaintiX+1);
					paa.setY(nykSijaintiY);
				}
			}else{
				// vain pää siirtyy
				if(headDir.equals("ylos")) {
					Pala paa = keho.get(0);
					int nykSijaintiX = paa.getX();
					int nykSijaintiY = paa.getY();
					paa.setX(nykSijaintiX);
					paa.setY(nykSijaintiY-1);
				}
				if(headDir.equals("alas")) {
					Pala paa = keho.get(0);
					int nykSijaintiX = paa.getX();
					int nykSijaintiY = paa.getY();
					paa.setX(nykSijaintiX);
					paa.setY(nykSijaintiY+1);
				}
				if(headDir.equals("vasen")) {
					Pala paa = keho.get(0);
					int nykSijaintiX = paa.getX();
					int nykSijaintiY = paa.getY();
					paa.setX(nykSijaintiX-1);
					paa.setY(nykSijaintiY);
				}
				if(headDir.equals("oikea")) {
					Pala paa = keho.get(0);
					int nykSijaintiX = paa.getX();
					int nykSijaintiY = paa.getY();
					paa.setX(nykSijaintiX+1);
					paa.setY(nykSijaintiY);
				}
			
			}
	}
	
	
	/**
	 * Metodi tarkistaa, osuuko mato kentän reunaan. Kentän mitat ovat ennalta määrätyt 10*10.
	 * Palauttaa true tai false tilanteen mukaan.
	 */
	public boolean osuukoReunaan() {
		
		boolean osuuReunaan = true;
		Pala paa = keho.get(0);
		int paaX = paa.getX();
		int paaY = paa.getY();
		
		if((paaX < 10) && (paaX >= 0) && (paaY < 10) && (paaY >= 0)) {
			osuuReunaan = false;
		}  
		
		return osuuReunaan;
	}
	
	
	/**
	 * Metodi tarkistaa, osuuko mato omaan häntään. Se vertaa pään sijanti kehon muiden osien
	 * siajintiin ja palauttaa true tai false tilanteen mukaan.
	 */
	public boolean osuukoHantaan() {
		
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
	
	
	/*'
	 * Metodi tarkistaa osuuko maton pää ruokaan vertaamalla niiden sijainnit.
	 */
	public boolean osuukoRuokaan(Ruoka r) {
		if((keho.get(0).getX() == r.getX()) && (keho.get(0).getY() == r.getY())){
			return true;
		}else {
			return false;
		}
	}


}
