package organization;

import java.util.Vector;

/**
 * Basic class for task. This is the ancestor of Project
 * A task can either represent a single task or a todolist.
 * This is specified by using a constructor with or without the parameter size
 * A task can also be a project, which just means it shouldn't be an element
 * of another todolist
 * @author Andy Li
 * @since Nov 1, 2017
 */
public class Task extends TreeNode {
	
	private boolean completed = false;
	private final boolean isList;
	private final boolean isProject;
	private String name;
	private int size;
	private Vector<Task> taskList;
	
	private TreeNode tree;
	
	/**
	 * Constructor for a plain task
	 * Sets name as "untitled"
	 */
	public Task() {
		name = "untitled";
		isList = false;
		isProject = false;
		init();
	}
	
	/**
	 * Constructor for a plain task
	 * @param name name of the task
	 */
	public Task(String name) {
		this.name = name;
		isList = false;
		isProject = false;
		init();
	}
	
	/**
	 * Constructor for a TodoList
	 * Sets name as "untitled"
	 * @param size size of isList
	 */
	public Task(int size, boolean isProject) {
		this.size = size;
		this.name = "untitled";
		isList = true;
		this.isProject = isProject;
		taskList = new Vector<>(this.size, 10);
		init();
	}
	
	/**
	 * Constructor for a TodoList
	 * @param size size of isList
	 */
	public Task(String name, int size, boolean isProject) {
		this.size = size;
		this.name = name;
		isList = true;
		this.isProject = isProject;
		taskList = new Vector<>(this.size, 10);
		init();
	}
	
	private void init() {
		try {
			tree = new TreeNode(this);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @return if the task is completed or not
	 */
	public boolean isCompleted() {
		return completed;
	}
	
	/**
	 * @return if this object represents a isList of tasks or just one
	 */
	public boolean isList() {
		return isList;
	}
	
	/**
	 * @return if this object is a project (i.e. cannot be part of a task list)
	 */
	public boolean isProject() {
		return isProject;
	}
	
	/**
	 * @return the task's name
	 */
	public String getName() {
		return name;
	}
	
	public TreeNode getTreeNode() {
		return this.tree;
	}
	
	/**
	 * changes this task's name
	 * @param newName the new name
	 */
	public void setName(String newName) {
		name = newName;
	}
	
	/**
	 * marks the task complete
	 */
	public void markComplete() {
		completed = true;
	}
	
	/**
	 * Adds a new task to the taskList at the specified index
	 * This task object could also be a todolist
	 * @param t 	a task object
	 * @param index the index to add at
	 */
	public void addNewTask(Task t, int index) {
		taskList.set(index, t);
		if (t.isList())
			this.addChildren(t.getTaskList());
		else
			this.addChild(t);
	}
	
	/**
	 * Marks a task complete
	 * @param index the index that the task located is
	 */
	public void markTaskComplete(int index) {
		taskList.get(index).markComplete();
	}
	
	/**
	 * Returns a task
	 * @param index the index of the task
	 * @return a task object
	 */
	public Task getTask(int index) {
		return taskList.get(index);
	}
	
	/**
	 * @return this object's tasklist
	 */
	public Vector<Task> getTaskList() {
		if (this.isList())
			return taskList;
		else
			return null;
	}
}
