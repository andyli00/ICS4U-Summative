package features;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * A GUI class that creates a desktop sticky note
 * @author Andy Li
 * @since Nov 10, 2017
 */
class Note {
	
	Stage window; //package-private
	private BorderPane pane;
	private ToolBar toolBar;
	private ScrollPane scrollPane;
	
	private TextArea bodyText;
	private Button newButton;
	private Button showAll;
	private Button hide;
	private Button deleteButton;
	private NoteManager manager;
	
	
	Note(String content, NoteManager manager) {
		initializeComponents();
		loadText(content);
		this.manager = manager;
	}
	
	private void initializeComponents() {
		window = new Stage(StageStyle.DECORATED);
		pane = new BorderPane();
		Font f = Font.getDefault();
		
		newButton = new Button("New");
		newButton.setFont(f);
		newButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> newButtonClicked());
				
		showAll = new Button("Show all");
		showAll.setFont(f);
		showAll.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> showAllClicked());
		
		hide = new Button("Hide");
		hide.setFont(f);
		hide.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> hideButtonClicked());
		
		deleteButton = new Button("Delete");
		deleteButton.setFont(f);
		deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> deleteButtonClicked());
		
		toolBar = new ToolBar();
		toolBar.getItems().addAll(
				new Separator(),
				newButton,
				deleteButton,
				new Separator(),
				showAll,
				hide,
				new Separator());
		
		bodyText = new TextArea();
		bodyText.setFont(f);
		bodyText.setWrapText(true);
		bodyText.setPrefRowCount(5);
		bodyText.setPrefColumnCount(20);
		
		scrollPane = new ScrollPane(bodyText);
		
		pane.setTop(toolBar);
		pane.setCenter(scrollPane);
		pane.setBottom(null);
		
		window.initModality(Modality.WINDOW_MODAL);
		window.setMinWidth(265);
		window.setMinHeight(125);
		window.setScene(new Scene(pane));
		window.show();
	}
	
	private void delete() {
		this.manager.delete(this);
	}
	
	private void loadText(String bodyText) {
		this.bodyText.setText(bodyText);
	}
	
	String getText() {
		return bodyText.getText();
	}
	
	private void newButtonClicked() {
		manager.create("");
	}
	
	private void showAllClicked() {
		manager.showAll();
	}
	
	private void hideButtonClicked() {
		window.hide();
		if (manager.allHidden())
			window.close();
	}
	
	private void deleteButtonClicked() {
		delete();
	}
}
