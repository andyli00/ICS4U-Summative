package main.components;

import features.CarpalTunnelTimer;
import features.NoteManager;
import main.managers.ProjectManager;
import organization.Task;
import utilities.FxDialog;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * @author Andy Li
 * @since Dec 11, 2017
 */
public class SecondaryGridPane extends GridPane {
	
	private VBox projectInfoBox;
	private TextField name;
	private TextField size;
	private Button addTaskButton;
	
	private VBox newButtons;
	private Button newProject;
	private Button newList;
	private Button newTask;
	
	private HBox sortLabels;
	private ComboBox sort;
	private ComboBox filter;
	private TextField search;
	
	private ScrollPane mainProjectScrollView;
	private TreeView<HBox> mainProjectView;
	
	public SecondaryGridPane() {
		super();
		
		initializeComponents();
	}
	
	private void initializeComponents() {
		setPrefSize(1450, 750);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(15, 15, 15 ,15));
		
		initializeNewButtons();
		initializeProjectInfo();
		initializeSortLabels();
		initializeProjectView();
		
		add(projectInfoBox, 0, 0, 3, 2);
		add(newButtons, 3, 0, 2, 3);
		add(sortLabels, 0, 2, 3, 1);
		add(mainProjectScrollView, 0, 3, 5, 2);
	}
	
	private void initializeProjectInfo() {
		projectInfoBox = new VBox(10);
		
		name = new TextField();
		size = new TextField();
		
		addTaskButton = new Button("+ Add!");
		addTaskButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> addTaskButtonClicked());
		
		projectInfoBox.getChildren().addAll(name, size, addTaskButton);
	}
	
	private void addTaskButtonClicked() {
		int temp = 0;
		try {
			temp = Integer.parseInt(size.getText());
		} catch (Exception e) {
			FxDialog.showError("Invalid Input", "");
		}
		new Task(name.getText(), temp, false);
	}
	
	private void reloadProjectInfo(String type) {
		name.setPromptText("Name of the " + type.toLowerCase());
		size.setPromptText("Size of the " + type.toLowerCase());
	}
	
	private void initializeNewButtons() {
		newButtons = new VBox(10);
		
		newProject = new Button("+ New Project");
		newProject.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> newButtonClicked(Task.PROJECT));
		
		newList = new Button("+ New To-do List");
		newList.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> newButtonClicked(Task.LIST));
		
		newTask = new Button("+ New Task");
		newTask.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> newButtonClicked(Task.TASK));
		
		newButtons.getChildren().addAll(newProject, newList, newTask);
	}
	
	private void newButtonClicked(String type) {
		reloadProjectInfo(type);
	}
	
	@SuppressWarnings("unchecked")
	private void initializeSortLabels() {
		sortLabels = new HBox(10);
		//TODO add action listeners
		sort = new ComboBox();
		sort.setPromptText("Sort by...");
		sort.getItems().addAll("Alphabetically", "Completed", "Date", "Priority");
		
		filter = new ComboBox();
		filter.setPromptText("Filter");
		filter.getItems().addAll("Completed", "");
		
		search = new TextField();
		search.setPromptText("Search");
	}
	
	private void initializeProjectView() {
		mainProjectView = new TreeView<>();
		try {
			TreeItem<HBox> root = initializeTreeItems(ProjectManager.getProjects().get(0));
			mainProjectView.setRoot(root);
		} catch (NullPointerException e) {
			FxDialog.showError("error:", e.getMessage());
		}
		
		mainProjectScrollView = new ScrollPane(mainProjectView);
	}
	
	@SuppressWarnings("unchecked")
	private TreeItem<HBox> initializeTreeItems(Task t) {
		TreeItem<HBox> parent = new TreeItem<>();
		for (Task child: t.getTaskList()) {
			if (child.isList()) {
				parent.getChildren().addAll(new TreeItem<>(createTreeHBox(t)), initializeTreeItems(child));
			}
			else {
				parent.getChildren().add(new TreeItem<>(createTreeHBox(t)));
			}
		}
		return parent;
	}
	
	private HBox createTreeHBox(Task t) {
		HBox hBox = new HBox();
		Text text = new Text(t.getName());
		text.setFont(Font.font("Arial", FontWeight.BLACK, 12));
		
		Button markComplete = new Button("Mark Complete");
		markComplete.setAlignment(Pos.CENTER_RIGHT);
		markComplete.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> t.markComplete());
		
		Button delete = new Button("Delete");
		delete.setAlignment(Pos.CENTER_RIGHT);
		delete.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ProjectManager.getProjects().remove(t));
		
		hBox.getChildren().addAll(text, markComplete, delete);
		
		return hBox;
	}
}
