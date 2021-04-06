package snake;

import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Game extends Application {
	
	@Override
    public void start(Stage stage) throws IOException {
		
		/**
		 * Loading the FXML file
		 */
		String form = "snake.fxml";
		FXMLLoader loader = new FXMLLoader(getClass().getResource(form));
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Testrun");
		stage.show();
	}
	
}
