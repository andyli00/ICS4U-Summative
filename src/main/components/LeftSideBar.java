package main.components;

import features.CarpalTunnelTimer;
import features.NoteManager;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import main.managers.ProjectManager;
import organization.Task;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * @author Andy Li
 * @since Dec 11, 2017
 */
public class LeftSideBar extends VBox {
	
	private GridPane optionsView;
	private Button toDoLists;
	private Button stickyNotes;
	private Button calendar;
	private Button timer;
	
	public LeftSideBar() {
		setPadding(new Insets(10, 10, 10, 10));
		setPrefSize(150, 750);
		
		optionsView = new GridPane();
		
		//todo go to https://docs.oracle.com/javafx/2/ui_controls/button.htm when you make icons
		toDoLists = new Button("To-do Lists");
		toDoLists.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> todoListsClicked());
		toDoLists.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		
		stickyNotes = new Button("Sticky Note");
		stickyNotes.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> stickyNotesClicked());
		
		calendar = new Button("Calendar");
		calendar.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> calendarClicked());
		
		timer = new Button("Timer");
		timer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> timerClicked());
		
		optionsView.add(toDoLists, 0, 0);
		optionsView.add(stickyNotes, 0, 1);
		optionsView.add(calendar, 1, 0);
		optionsView.add(timer, 1, 1);
	}
	
	//TODO finish these methods
	private void todoListsClicked() {
	
	}
	
	private void stickyNotesClicked() {
		new NoteManager();
	}
	
	private void calendarClicked() {
	
	}
	
	private void timerClicked() {
		new CarpalTunnelTimer(1);
	}
	
}
