package main;

import organization.Task;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;


/**
 * A class to create and manage project and task objects
 * @author Andy Li
 * @since Dec 04, 2017
 */
public class ProjectManager {
	
	Stage window;
	private BorderPane pane;
	private Task project;
	
	public ProjectManager() {
		run();
	}
	
	private void run() {
		//nothing yet
	}
	
}
