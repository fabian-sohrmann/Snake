package snake;

import javafx.stage.Stage;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Game extends Application {
	
	@Override
    public void start(Stage stage) throws IOException {
		
		
		
		
		
		
		
		
		
		//graphics start here
		Button startButton = new Button("Start");
		Label title = new Label("Snake");
		BorderPane pane = new BorderPane();
		pane.setTop(title);
		pane.setBottom(startButton);
		
		
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("Testrun");
		stage.show();
		
	}
	
	public void startGame() {
		Kentta kentta = new Kentta(10, 10);
		
	}
	
}