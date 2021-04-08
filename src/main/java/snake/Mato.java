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
	 * Konstruktori lis‰‰ ja suunnan madon keholle
	 */
	public Mato() {
		Pala paa = new Pala(5,5);
		keho.add(paa);
		headDir = "vasen";
	}
	
	public ArrayList<Pala> getKeho(){
		return keho;
	}
	
	
	public void grow() {
		int x = 0;
		int y = 0;
		
		
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
			//haetaan 2 viimeisint‰ palaa, verrataan niiden kulkusuunta
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
				y = y1 + 1;
			}
			if(y1>y2) {
				x = x1;
				y = y1-1;
			}
			
		//lis‰t‰‰n oikeat koordinaatit uudelle h‰nn‰lle ja lis‰t‰‰n uusi h‰nt‰ listaan
		keho.add(keho.size(), new Pala(x, y));
		}	
	}
	
	
}
