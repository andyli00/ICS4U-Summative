package utilities;

import javafx.scene.control.*;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Andy Li
 * @since Dec 04, 2017
 */
public class Dialog {
	
	/**
	 * shows an error message
	 * @param title title of message
	 * @param message the message to be shown
	 */
	public static void showError(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.initStyle(StageStyle.UNDECORATED);
		alert.setTitle("Error");
		alert.setHeaderText(title);
		alert.setContentText(message);
		
		alert.showAndWait();
	}
	
	public static final String OK = "OK";
	public static final String CANCEL = "Cancel";
	
	/**
	 * shows a confirmation box
	 * @param title title of message
	 * @param message the message to be shown
	 * @return "OK" or "Cancel" depending on which button the user pressed
	 */
	public static String showConfirmation(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.initStyle(StageStyle.UNDECORATED);
		alert.setTitle("Confirm");
		alert.setHeaderText(title);
		alert.setContentText(message);
		
		String[] options = { OK, CANCEL };
		
		ArrayList<ButtonType> buttons = new ArrayList<>();
		for (String option: options) {
			buttons.add(new ButtonType(option));
		}
		
		alert.getButtonTypes().setAll(buttons);
		
		Optional<ButtonType> result = alert.showAndWait();
		if (!result.isPresent())
			return CANCEL;
		else
			return result.get().getText();
	}
	
	/**
	 * Shows an information box
	 * @param info A string to be shown in the info box
	 */
	public static void showInformation(String info) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(info);
		
		alert.showAndWait();
	}
	
	/**
	 * Gets a string input
	 * @param prompt A string prompt for the user
	 * @return The user's string input
	 */
	public static String getInput(String prompt) {
		TextInputDialog textInputDialog = new TextInputDialog();
		textInputDialog.setTitle("Input");
		textInputDialog.setHeaderText(null);
		textInputDialog.setContentText(prompt);
		
		Optional<String> result = textInputDialog.showAndWait();
		return result.orElse(null);
	}
}
