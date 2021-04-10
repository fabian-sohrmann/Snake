package snake;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game extends Application {
	
	ArrayList<ImageView> tiles = new ArrayList<ImageView>();

	@Override
    public void start(Stage stage) throws IOException, InterruptedException {
		
		//luodaan tarpeelliset elementit
		ArrayList<Ruutu> ruudukko = new ArrayList<Ruutu>();
		BorderPane borderPane = new BorderPane();
		Canvas canvas = new Canvas(200, 200);
		Button startButton = new Button("Start");
		Label title = new Label("Snake");
		Image tausta = new Image(new FileInputStream("src/main/resources/snake/background_fill.png"));
		Image osa = new Image(new FileInputStream("src/main/resources/snake/snake_fill.png"));
		Image syotava = new Image(new FileInputStream("src/main/resources/snake/food_fill.png"));
		
		borderPane.setTop(title);
		borderPane.setBottom(startButton);
		borderPane.setCenter(canvas);
		
		Scene scene = new Scene(borderPane);
		stage.setScene(scene);
		stage.setTitle("Snake");
		stage.setResizable(false);
		
		Mato mato = new Mato();
		Ruoka ruoka = new Ruoka();
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				ruudukko.add(new Ruutu(tausta, i, j));
			}
		}
		
		GameLoop gl = new GameLoop(gc, tausta, osa, syotava, ruudukko, mato, ruoka);
		gl.start();
		
		
		
		
		
			/*EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
			}
		};
		
		//startButton.setOnAction(actionHandler);*/
		
		/*

			
			
			
			//tarkistetaan onko madon seuraava liike laillinen
			if(!(mato.legalToMove())) {
				peliJatkuu = false;
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
			}
			
			
		}
	
	*/
				
		//tapahtumakäsittely
		/*
		EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>() {
			
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.W) {
					mato.setDir("ylos");
				}
				if(event.getCode() == KeyCode.S) {
					mato.setDir("alas");
				}
				if(event.getCode() == KeyCode.A) {
					mato.setDir("vasen");
				}
				if(event.getCode() == KeyCode.D) {
					mato.setDir("oikea");
				}
				event.consume();
				
			}
		};*/
		
		//startButton.setOnAction(actionHandler);
		//borderPane.setOnKeyPressed(keyHandler);
		
		
		stage.show();

	
	}
	

	
}
