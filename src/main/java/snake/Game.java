package snake;

import java.io.FileInputStream;
import java.io.IOException;
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

public class Game extends Application {
	
	GameLoop gl;
	Mato mato = new Mato();
	
	@Override
    public void start(Stage stage) throws IOException, InterruptedException {
		
		//luodaan tarpeelliset elementit
		ArrayList<Ruutu> ruudukko = new ArrayList<Ruutu>();
		BorderPane borderPane = new BorderPane();
		Canvas canvas = new Canvas(200, 200);
		Button startButton = new Button("Start");
		Button stopButton = new Button("End Game");
		Label title = new Label("Snake");
		Image tausta = new Image(new FileInputStream("src/main/resources/snake/background_fill.png"));
		Image osa = new Image(new FileInputStream("src/main/resources/snake/snake_fill.png"));
		Image syotava = new Image(new FileInputStream("src/main/resources/snake/food_fill.png"));
		
		HBox hbox = new HBox();
		hbox.getChildren().add(startButton);
		hbox.getChildren().add(stopButton);
		
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
		
		gl = new GameLoop(gc, tausta, osa, syotava, ruudukko, mato, ruoka);
	
				
		/*//tapahtumakäsittely
		EventHandler<ActionEvent> stopHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				gl.stop();
			}
		};*/
		
		EventHandler<ActionEvent> startHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				gl.start();
			}
		};
		
		EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>(){
			
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.W) {
					mato.setDir("ylos");
					System.out.println(mato.getDir());
				}
				if(event.getCode() == KeyCode.S) {
					mato.setDir("alas");
					System.out.println(mato.getDir());
				}
				if(event.getCode() == KeyCode.A) {
					mato.setDir("vasen");
					System.out.println(mato.getDir());
				}
				if(event.getCode() == KeyCode.D) {
					mato.setDir("oikea");
					System.out.println(mato.getDir());
				}
				event.consume();
				
			}
		};
		
		borderPane.setOnKeyPressed(keyHandler);
		startButton.setOnAction(startHandler);
		//stopButton.setOnAction(stopHandler);
		
		stage.show();

	
	}
	

	
}
