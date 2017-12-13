package organization;

import java.util.ArrayList;
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
public class Task  {
	
	private boolean completed = false;
	private String type;
	private String name;
	private int size;
	private Vector<Task> taskList;
	
	public static final String TASK = "TASK";
	public static final String LIST = "LIST";
	public static final String PROJECT = "PROJECT";
	
	/**
	 * Constructor for a plain task
	 * Sets name as "untitled"
	 */
	public Task() {
		name = "untitled";
		type = TASK;
	}
	
	/**
	 * Constructor for a plain task
	 * @param name name of the task
	 */
	public Task(String name) {
		this.name = name;
		type = TASK;
	}
	
	/**
	 * Constructor for a TodoList
	 * Sets name as "untitled"
	 * @param size size of isList
	 */
	public Task(int size, boolean isProject) {
		this.size = size;
		this.name = "untitled";
		if (isProject)
			type = PROJECT;
		else
			type = LIST;
		taskList = new Vector<>(this.size, 10);
	}
	
	/**
	 * Constructor for a TodoList
	 * @param size size of isList
	 */
	public Task(String name, int size, boolean isProject) {
		this.size = size;
		this.name = name;
		if (isProject)
			type = PROJECT;
		else
			type = LIST;
		taskList = new Vector<>(this.size, 10);
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
		return type.equals(LIST);
	}
	
	/**
	 * @return if this object is a project (i.e. cannot be part of a task list)
	 */
	public boolean isProject() {
		return type.equals(PROJECT);
	}
	
	/**
	 * @return the type of this task object (project, list, or task)
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @return the task's name
	 */
	public String getName() {
		return name;
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
	}
	
	/**
	 * Marks a task complete
	 * @param index the index that the task located is
	 */
	public void markTaskComplete(int index) {
		taskList.get(index).markComplete();
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
	
	/**
	 * Uses recursion to count the number of tasks/tasklists in a task object
	 * @param task a task object, usually a project
	 * @return the number of tasks/tasklists
	 */
	public int getNumChildren(Task task) {
		int count = 0;
		for (Task t: task.getTaskList()) {
			count += 1;
			if (t.isList()) {
				count += getNumChildren(t);
			}
		}
		return count;
	}
	
	//TODO finish this method using recursion when implementation of saving project data is continued
	public ArrayList<Task> getAllTasks(Task task) {
		return null;
	}
}
