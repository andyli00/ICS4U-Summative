package main.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import features.CarpalTunnelTimer;
import features.NoteManager;
import utilities.Dialog;

/**
 *
 * @author Andy Li
 * @since Dec 11, 2017
 */
public class TopToolBar extends HBox {
	
	public TopToolBar() {
		super();
		setPadding(new Insets(10, 10, 10, 10));
		setSpacing(10);
		setAlignment(Pos.CENTER);
		
		Button stickyNotes = new Button("Sticky Note");
		stickyNotes.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> stickyNotesClicked());
		stickyNotes.setStyle("-fx-font: 16 sans-serif");
		
		Button timer = new Button("Timer");
		timer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> timerClicked());
		timer.setStyle("-fx-font: 16 sans-serif");
		
		getChildren().addAll(stickyNotes, timer);
		
	}
	
	/**
	 * creates a note manager object
	 */
	private void stickyNotesClicked() {
		new NoteManager();
	}
	
	/**
	 * starts a timer
	 */
	//TODO implement a more user-friendly way of inputting time
	private void timerClicked() {
		new CarpalTunnelTimer(Integer.parseInt(Dialog.getInput("How long? (in seconds)")));
	}
}
