package unused;

import main.components.ProjectTreeView;
import main.ProjectManager;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

/**
 * @author Andy Li
 * @since Dec 11, 2017
 */
public class SecondaryGridPane extends GridPane {
	
	private ScrollPane mainProjectScrollView;
	private TreeView<String> mainProjectView;
	
	public SecondaryGridPane() {
		super();
		initializeComponents();
	}
	
	private void initializeComponents() {
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(15, 15, 15 ,15));
		setGridLinesVisible(false);
		
		
		
		initializeProjectView();
		
		//add(mainProjectScrollView);
	}
	
	private void initializeProjectView() {
		mainProjectView = new ProjectTreeView(ProjectManager.getProjects().firstElement());
		
		mainProjectScrollView = new ScrollPane(mainProjectView);
	}
	
	/* Most of this code is now useless because I implemented
	 * context menus on the tree view in ProjectTreeView.java
	 * I'm too sentimental to delete it though.
	 */
	
	/*
	private Task currentProjectViewed;
	
	//GUI elements
	private VBox projectInfoBox;
	private TextField name;
	private TextField size;
	private Button addTaskButton;
	
	private VBox newButtons;
	private Button newProject;
	private Button newList;
	private Button newTask;
	
	private HBox sortLabels;
	//private ComboBox sort;
	private ComboBox filter;
	private TextField search;
	private Text invalidInput;
	
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
			Dialog.showError("Invalid Input", "");
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
		
		invalidInput = new Text("Invalid input. Try again.");
		invalidInput.setFill(Color.RED);
		invalidInput.setVisible(false);
		add(invalidInput, 0, 2);
		
		/*sort = new ComboBox();
		sort.setPromptText("Sort...");
		sort.getItems().addAll("Alphabetically", "Date", "Priority");
		sort.valueProperty().addListener(new ChangeListener<>() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				sort(String.valueOf(newValue));
			}
		});
		
		filter = new ComboBox();
		filter.setPromptText("Filter");
		filter.getItems().addAll("Completed", "Uncompleted");
		filter.valueProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				filter(newValue.equals("Completed"));
			}
		});
		
		search = new TextField();
		search.setPromptText("Search");
		search.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				changeInputStatus(!search.getText().isEmpty() && !(search.getText() == null));
			}
		});
		
		sortLabels.getChildren().addAll(sort, filter, search);
	}
	
	private void sort(String type) {
		switch (type) {
			case "Alphabetically":
				for (Node treeItem: mainProjectView.getChildrenUnmodifiable()) {
				
				}
				break;
				
			case "Date":
				
				break;
				
			case "Priority":
				
				break;
		}
	}
	
	private void filter(boolean isCompleted) {
		for (Node treeItem: mainProjectView.getChildrenUnmodifiable()) {
			if (isCompleted) {
				treeItem.setVisible(currentProjectViewed.isCompleted());
			}
		}
	}
	
	private void changeInputStatus(boolean isVisible) {
		invalidInput.setVisible(isVisible);
	}*/
}
