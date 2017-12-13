package main.components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Vector;

/**
 * @author Andy Li
 * @since Dec 11, 2017
 */
public class TopToolBar extends HBox {
	
	public Vector<Button> buttons = new Vector<>(5, 1);
	
	public TopToolBar() {
		super();
		setPadding(new Insets(10, 10, 10, 0));
		Text text = new Text("Common Management System");
		text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		getChildren().add(text);
		setPrefSize(1600, 150);
		updateContents();
	}
	
	public void updateContents() {
		getChildren().remove(1, getChildren().size());
		for (Button b: buttons) {
			getChildren().addAll(new Separator(), b);
		}
	}
}
