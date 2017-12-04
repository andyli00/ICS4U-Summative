package main;

import features.NoteManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * GUI for the main application
 * @author Andy Li
 * @since Nov 30, 2017
 */
public class CommonManagement extends Application {
	
	ProjectManager projectManager;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setScene(new Scene(new StackPane()));
		//primaryStage.show();
		new NoteManager();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
