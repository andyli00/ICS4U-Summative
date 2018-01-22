package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Application;

import main.components.*;

/**
 * GUI for the mainApplication application
 * @author Andy Li
 * @since Nov 30, 2017
 */
public class CommonManagement extends Application {
	
	private Stage window;
	private ProjectManager pm = new ProjectManager();
	private VBox mainApplication;
	private TopToolBar topBar = new TopToolBar();
	private ProjectTreeView treeView;
	
	
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		initializeComponents();
		window.setTitle("CMS");
		window.setScene(new Scene(mainApplication));
		window.getIcons().add(new Image("/resources/CMS.jpg"));
		
		window.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 *
	 */
	private void initializeComponents() {
		window.initStyle(StageStyle.DECORATED);
		
		Text title = new Text("Common Management System");
		title.setStyle("-fx-font: 18 western; -fx-fill: blue");
		
		treeView = new ProjectTreeView(ProjectManager.getProjects().get(0));
		
		mainApplication = new VBox(title, topBar, new ScrollPane(treeView));
		mainApplication.setAlignment(Pos.BASELINE_CENTER);
		mainApplication.setSpacing(10);
	}
}
