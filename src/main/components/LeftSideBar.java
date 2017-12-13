package main.components;

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
	
	public LeftSideBar() {
		setPadding(new Insets(10, 10, 10, 10));
		setPrefSize(150, 750);
		
	}
	
	public void updateContents() {
		getChildren().remove(0, getChildren().size());
		for (Task t: ProjectManager.getProjects()) {
			Text text = new Text(t.getName());
			text.setFont(Font.font("Arial", FontWeight.BLACK, 15));
			getChildren().add(text);
		}
	}
}
