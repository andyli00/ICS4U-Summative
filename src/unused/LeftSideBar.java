package unused;

import features.CarpalTunnelTimer;
import features.NoteManager;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import utilities.Dialog;

/**
 * @author Andy Li
 * @since Dec 11, 2017
 */
public class LeftSideBar extends VBox {
	public LeftSideBar() {
		setPadding(new Insets(10, 10, 10, 10));
		setSpacing(10);
		
		Button stickyNotes = new Button("Sticky Note");
		stickyNotes.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> stickyNotesClicked());
		stickyNotes.setStyle("-fx-font: 22 sans-serif");
		
		Button timer = new Button("Timer");
		timer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> timerClicked());
		timer.setStyle("-fx-font: 22 sans-serif");
		
		getChildren().add(stickyNotes);
		getChildren().add(timer);
	}
	
	private void stickyNotesClicked() {
		new NoteManager();
	}
	
	private void timerClicked() {
		int time = Integer.parseInt(Dialog.getInput("How long? (in seconds)"));
		new CarpalTunnelTimer(time);
	}
	
}
