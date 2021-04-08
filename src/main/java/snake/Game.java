package snake;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {
	
	ArrayList<ImageView> tiles = new ArrayList<ImageView>();

	@Override
    public void start(Stage stage) throws IOException {
		
		//luodaan tarpeelliset elementit
		Button startButton = new Button("Start");
		Label title = new Label("Snake");
		Pane field = new Pane();
		Image tausta = new Image(new FileInputStream("src/main/resources/snake/background_fill.png"));
		Image madonKeho = new Image(new FileInputStream("src/main/resources/snake/snake_fill.png"));
		Image ruokaKuva = new Image(new FileInputStream("src/main/resources/snake/food_fill.png"));
		
		//lis‰t‰‰n kuvat listaan
		for(int i = 0; i <  200 ; i=i+20) {
			for(int j = 0; j < 200; j = j+20) {
				ImageView img = new ImageView(tausta);
				img.setX(i);
				img.setY(j);
				tiles.add(img);
			}
		}
		
		//piirret‰‰n kentt‰
		for(int i = 0; i < 100; i++) {
			field.getChildren().add(tiles.get(i));
		}
		
		//luodaan mato
		Mato mato = new Mato();
		
		//pelisilmukka
		boolean peliJatkuu = true;
		while(peliJatkuu) {
			Ruoka ruoka = new Ruoka();
			
			//p‰ivitet‰‰n pelikentt‰
			for(int i = 0; i < 100; i++) {
				int kuvaX = (int) tiles.get(i).getX();
				int kuvaY = (int) tiles.get(i).getY();
				
				for(int j = 0; j < mato.getKeho().size(); j++) {
					int matoX = mato.getKeho().get(j).getX();
					int matoY = mato.getKeho().get(j).getY();
					if(kuvaX == matoX && kuvaY == matoY) {
						tiles.get(i).setImage(madonKeho);
					}
					if(kuvaX == ruoka.getX() && kuvaY == ruoka.getY()) {
						tiles.get(i).setImage(ruokaKuva);
					}else {
						tiles.get(i).setImage(tausta);
					}
				}
			}
			
			//tarkistetaan onko madon seuraava liike laillinen
			if(mato.legalToMove()) {
				continue;
			}else {
				//PELIHƒVITTY
			}
			
			
			//liikutetaan mato
			mato.move();
			
			//
		}
		
		
		
			
			
			
		
			
			
			
			
			
			
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(title);
		borderPane.setBottom(startButton);
		borderPane.setCenter(field);
		
		Scene scene = new Scene(borderPane);

		stage.setScene(scene);
		stage.setTitle("Testrun");
		stage.setResizable(false);
		stage.show();
		
	}
	
		
		
		
}
