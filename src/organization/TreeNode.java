package organization;

import java.util.ArrayList;

/**
 * An implementation of a tree data type.
 * Only works for the Task class because its not necessary
 * to generalize for this project's purposes.
 * @author Andy Li
 * @since Dec 04, 2017
 */
public class TreeNode {
	
	private Task task;
	private ArrayList<Task> children = new ArrayList<>();
	private Task parent;
	
	TreeNode() {
		this.task = new Task();
	}
	
	TreeNode(Task data) {
		this.task = data;
	}
	
	void addChild(Task data) {
		data.setParent(this.getTask());
		children.add(data);
	}
	
	void addChildren(ArrayList<Task> children) {
		for (Task t: children)
			t.setParent(this.getTask());
		this.children.addAll(children);
	}
	
	public ArrayList<Task> getChildren() {
		return children;
	}
	
	public Task getTask() {
		return task;
	}
	
	public void setTask(Task task) {
		this.task = task;
	}
	
	public void setParent(Task parent) {
		this.parent = parent;
	}
	
	public Task getParent() {
		return parent;
	}
}
