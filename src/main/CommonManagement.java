package main;

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
		initializeComponents();
		primaryStage = window;
		primaryStage.setMaxHeight(900);
		primaryStage.setMinHeight(720);
		primaryStage.setMaxWidth(1600);
		primaryStage.setMaxWidth(1280);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void initializeComponents() {
		window = new Stage(StageStyle.DECORATED);
		
		topBar = new TopToolBar();
		topBar.updateContents();
		
		leftSideBar = new LeftSideBar();
		leftSideBar.updateContents();
		
		mainGridPane = new MainGridPane();
		
		secondaryGridPane = new SecondaryGridPane();
	}
}
