package organization;

import java.util.Vector;

/**
 * An implementation of a tree data type.
 * Only works for the Task class because its not necessary
 * to generalize for this project's purposes.
 * @author Andy Li
 * @since Dec 04, 2017
 */
public class TreeNode {
	
	private Task task;
	private Vector<Task> children = new Vector<>(10, 10);
	private Task parent;
	
	TreeNode() {
		try {
			this.task = new Task();
		} catch (StackOverflowError e) {
			e.printStackTrace();
		}
	}
	
	TreeNode(Task data) {
		this.task = data;
	}
	
	public void addChild(Task data) {
		data.setParent(this.getTask());
		children.add(data);
	}
	
	public void addChildren(Vector<Task> children) {
		for (Task t: children)
			t.setParent(this.getTask());
		this.children.addAll(children);
	}
	
	public int getNumChildren() {
		return children.size();
	}
	
	public Vector<Task> getChildren() {
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
