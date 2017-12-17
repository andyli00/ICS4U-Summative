package main;

import javafx.scene.Scene;
import javafx.stage.Modality;
import main.components.*;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Application;

/**
 * GUI for the main application
 * @author Andy Li
 * @since Nov 30, 2017
 */
public class CommonManagement extends Application {
	
	private Stage window;
	//no pane, no gain (heh)
	public static MainGridPane mainGridPane;
	public static SecondaryGridPane secondaryGridPane;
	public static TopToolBar topBar;
	public static LeftSideBar leftSideBar;
	
	
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		initializeComponents();
		window.setScene(new Scene(mainGridPane));
		window.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void initializeComponents() {
		window.initStyle(StageStyle.DECORATED);
		
		topBar = new TopToolBar();
		topBar.updateContents();
		leftSideBar = new LeftSideBar();
		secondaryGridPane = new SecondaryGridPane();
		mainGridPane = new MainGridPane();
		
		mainGridPane.add(topBar, 0, 0);
		mainGridPane.add(leftSideBar, 0, 1);
		mainGridPane.add(secondaryGridPane, 1, 1);
		
		/*window.initModality(Modality.APPLICATION_MODAL);
		window.setMaxHeight(900);
		window.setMinHeight(720);
		window.setMaxWidth(1600);
		window.setMaxWidth(1280);*/
	}
}
