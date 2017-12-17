package main.components;

import main.CommonManagement;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.geometry.HPos;

/**
 *
 * @author Andy Li
 * @since Dec 11, 2017
 */
public class MainGridPane extends GridPane {
	
	public MainGridPane() {
		super();
		setPadding(new Insets(10, 10, 10, 10));
		setGridLinesVisible(true);
		
		Text title = new Text("Common Management System");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		setHalignment(title, HPos.LEFT);
		add(title, 0, 0);
		
		//setHalignment(CommonManagement.topBar, HPos.LEFT);
		ScrollPane topBarScroll = new ScrollPane(CommonManagement.topBar);
		add(topBarScroll, 1, 0, 4, 1);
		
		ScrollPane sideBarScroll = new ScrollPane(CommonManagement.leftSideBar);
		add(sideBarScroll, 0, 1, 1, 4);
		
		add(CommonManagement.secondaryGridPane, 1, 1, 4, 4);
	}
}
