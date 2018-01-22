package main.components;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;

import organization.Task;

/**
 * A TreeView that contains the details of a project, as well as
 * options to add, remove, and check completion of tasks.
 * @author Andy Li
 * @since Jan 04, 2018
 */
public class ProjectTreeView extends TreeView<String> {
	
	/**
	 * Constructor initializes items and sets the Cell Factory
	 * @param rootTask The task object that the root node of
	 *                 the tree view to build off of.
	 */
	public ProjectTreeView(Task rootTask) {
		TreeItem<String> rootItem = initializeTreeItems(rootTask);
		rootItem.setExpanded(true);
		
		setRoot(rootItem);
		setEditable(true);
		setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
			@Override
			public TreeCell<String> call(TreeView<String> param) {
				return new TreeViewCell();
			}
		});
	}
	
	/**
	 * Private class used for the cell factory. A TreeCell is
	 * created for every TreeItem and is essentially just styling
	 * for that TreeItem. This is necessary because TreeItem does
	 * not extend the Node class.
	 */
	private class TreeViewCell extends TreeCell<String> {
		private TextField textField;
		private ContextMenu addMenu = new ContextMenu();
		
		/**
		 * Initializes a context menu with options to add, remove,
		 * and check completion of tasks and lists
		 */
		TreeViewCell() {
			setStyle("-fx-font: 16 western");
			
			MenuItem addTask = new MenuItem("Add Task");
			addTask.setOnAction(event -> {
				TreeItem<String> newTask = new TreeItem<>("untitled");
				getTreeItem().getChildren().add(newTask);
			});
			
			MenuItem removeTask = new MenuItem("Remove Task");
			removeTask.setOnAction(event -> {
				TreeItem<String> temp = getSelectionModel().getSelectedItem();
				temp.getParent().getChildren().remove(temp);
			});
			
			CheckMenuItem markComplete = new CheckMenuItem("Mark Complete");
			markComplete.setSelected(false);
			markComplete.selectedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if (newValue)
						setStyle("-fx-font: 16 western; -fx-text-fill: green");
					else
						setStyle("-fx-font: 16 western");
				}
			});
			
			addMenu.getItems().addAll(addTask, removeTask, markComplete);
		}
		
		/**
		 * The following few methods control the renaming of TreeItems.
		 * When an item is double-clicked, a text box appears and the
		 * user can edit the name. ESC to cancel and ENTER to confirm.
		 */
		
		@Override
		public void startEdit() {
			super.startEdit();
			if (textField == null)
				createTextField();
			
			setText(null);
			setGraphic(textField);
			textField.selectAll();
		}
		
		@Override
		public void cancelEdit() {
			super.cancelEdit();
			
			setText(getItem());
			setGraphic(getTreeItem().getGraphic());
		}
		
		@Override
		public void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				if (isEditing()) {
					if (textField != null)
						textField.setText(getItem());
					
					setText(null);
					setGraphic(textField);
				} else {
					setText(getItem());
					setGraphic(getTreeItem().getGraphic());
					if (!getTreeItem().isLeaf() && getTreeItem().getParent() != null)
						setContextMenu(addMenu);
				}
			}
		}
		
		private void createTextField() {
			textField = new TextField(getText());
			textField.setOnKeyReleased(t -> {
				if (t.getCode() == KeyCode.ENTER)
					commitEdit(textField.getText());
				else if (t.getCode() == KeyCode.ESCAPE)
					cancelEdit();
			});
		}
	}
	
	/**
	 * Creates TreeItems using task names.
	 * @param t The task to start creating TreeItems
	 * @return A TreeItem with children matching the children of the task used
	 */
	private TreeItem<String> initializeTreeItems(Task t) {
		TreeItem<String> current = new TreeItem<>(t.getName());
		for (Task child: t.getTaskList()) {
			if (child.isList())
				//recursion begins
				current.getChildren().add(initializeTreeItems(child));
			else
				current.getChildren().add(new TreeItem<>(child.getName()));
		}
		return current;
	}
}
