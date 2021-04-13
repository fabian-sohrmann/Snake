package snake;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * <h1>Game</h1>
 * Luokaa Game sisältää vaadittavat osat grafiikka ja tapahtumien käsittelyä varten. Koska kyseessä
 * on JavaFX ohjelma, se perii Application luokkaa ja korvaa sen metodin start(). Luokassa
 * on kaksi muuttujaa GameLoop gl ja Mato mato, jotka on sijoitettu sinne, jotta start()-metodissa
 * olevat tapahtumienkäsittelijät pääsevät niihin myös käsiksi.
 */
public class Game extends Application {
	
	private GameLoop gl;
	private Mato mato = new Mato();
	
	/**
	 * <h1>start(Stage stage)</h1>
	 * Pakollinen start()-metodi, joka korvaa Application luokan start()-metodin. Tässä metodissa
	 * luodaan kaikki tarpeelliset osat grafiikan piirtämistä varten. Elementit ovat seuraavat:
	 * <b>ArrayList&ltRuutu&gt ruudukko</b>
	 * Tämä on lista, joka sisältää Ruutu olioita. Ruutuja käytetään grafiikan piirtämiseen, ja
	 * jokainen ruutu osaa kertoa oman sijaintinsa ja mikä kuva siihen sijaintiin on piirrettävä.
	 * Grafiikan piirtovaiheessa silmukka piirtää pelikentän näiden tietojen perusteella peli-ikkunaan.
	 * <b>Label title, highscore ja points</b> 
	 * Nämä sisältävät peliohjeet ja pisteet, ja ne piirtyvät myös ikkunaan.
	 * <b>Image tausta, osa, syotava, background</b>
	 * Kuvat, joilla piirretään pelikenttä. Background kuva piirretään alkukuvaksi kentälle.
	 * Muista kuvista muodostetaan pelikenttä. Jokainen kuva piirretään joka kierroksella aina
	 * oikeaan paikkaan. Pelikenttä koostuu siis 100 kappaleesta kuvia, jotka muodostavat 10*10
	 * ruudukon.
	 * <b>Button startButton</b>
	 * Tämä nappi käynnistää pelisilmukan, kun siitä klikataan.
	 * <b><Grafiikan piirto</b> 
	 * Pelikenttä piirretään käyttämällä luokat Canvas ja GraphicsContext. Canvas-olio on kooltaan
	 * 200*200 pikseliä, ja kuvat jotka sinne piirretään ovat kooltaan 20*20, eli mahtuu juuri
	 * sopivasti 10*10 kuvaa. GraphicsContext-oliolla piirretään kuvat canvakseen. Ennen pelisilmukkaa
	 * canvakseen piirretään background-kuva.
	 * <b>Pelisilmukka</b>
	 * Pelisilmukka on toteutettu käyttämällä luokkaa AnimationTimer, jonka perii luokka
	 * GameLoop. start()-metodissa luodaan uusi GameLoop olio, joka hoitaa pelisilmukan. Sille
	 * annetaan kaikki tarpeelliset elementit sen konstruktorissa. GameLoop hoitaa pelilogiikat.
	 * ruudkko-listalle annetaan vielä alkuasetelma Ruutu-olioita. Kaikki saavat oman sijainnin
	 * pelikentällä ja saman tausta-kuvan.
	 * <b>Tapahtumankäsittelijät</b>
	 * Start metodissa luodaan kaksi taphtumankäsittelijää: startHandler ja keyHandler.
	 * startHandler annetaan startButtonille ja se käynnistää pelisilmukan kutsumalla GameLoop-olion 
	 * start()-metodia. keyHandler annetaan BorderPane-oliolle, joka kuuntelee näin näppäimistön painalluksia.
	 * Kun käyttäjä painaa W, A, S tai D, niin keyHandlerin handle()-metodi muuttaa Mato-olion
	 * tiedossa oleva kulkusuunta. Tämä tieto tarvitaan maton liikkumisessa ja kasvamsiessa.
	 */
	@Override
    public void start(Stage stage) throws InterruptedException, FileNotFoundException {
		
		//Luodaan tarpeelliset elementit.
		ArrayList<Ruutu> ruudukko = new ArrayList<Ruutu>();
		BorderPane borderPane = new BorderPane();
		Canvas canvas = new Canvas(200, 200);
		Button startButton = new Button("Start");
		Image tausta = new Image(new FileInputStream("src/main/resources/snake/background_fill.png"));
		Image osa = new Image(new FileInputStream("src/main/resources/snake/snake_fill.png"));
		Image syotava = new Image(new FileInputStream("src/main/resources/snake/food_fill.png"));
		Label title = new Label("Welcome to the Game! \nUse W,A,S,D keys to move! \nClick Start to begin!");
		Image background = new Image(new FileInputStream("src/main/resources/snake/welcome.png"));
		Label highscore = new Label("Score: ");
		Label points = new Label("0");
		
		HBox hbox = new HBox();
		hbox.getChildren().add(startButton);
		hbox.getChildren().add(highscore);
		hbox.getChildren().add(points);
		
		borderPane.setTop(title);
		borderPane.setBottom(hbox);
		borderPane.setCenter(canvas);
		
		Scene scene = new Scene(borderPane);
		stage.setScene(scene);
		stage.setTitle("Snake");
		stage.setResizable(false);
		
		
		Ruoka ruoka = new Ruoka();
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				ruudukko.add(new Ruutu(tausta, i, j));
			}
		}
		
		gc.drawImage(background, 0, 0);
		
		gl = new GameLoop(gc, tausta, osa, syotava, background, ruudukko, mato, ruoka, title, points);
	
		//Luodaan tapahtumankasittelijat pelisilmukan aloittamista ja madon ohjaamista varten.
		EventHandler<ActionEvent> startHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				gl.start();
			}
		};
		
		EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>(){
			
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.W && !(mato.getDir().equals("alas"))) {
					mato.setDir("ylos");
					System.out.println(mato.getDir());
				}
				if(event.getCode() == KeyCode.S && !(mato.getDir().equals("ylos"))) {
					mato.setDir("alas");
					System.out.println(mato.getDir());
				}
				if(event.getCode() == KeyCode.A && !(mato.getDir().equals("oikea"))) {
					mato.setDir("vasen");
					System.out.println(mato.getDir());
				}
				if(event.getCode() == KeyCode.D && !(mato.getDir().equals("vasen"))) {
					mato.setDir("oikea");
					System.out.println(mato.getDir());
				}
				event.consume();
				
			}
		};
		
		borderPane.setOnKeyPressed(keyHandler);
		startButton.setOnAction(startHandler);
		
		stage.show();
		
	}
	

	
}
