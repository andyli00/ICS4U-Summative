package organization;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Basic class for task.
 * A task can either represent a single task or a todolist.
 * This is specified by using a constructor with or without the parameter size
 * A task can also be a project, which just means it shouldn't be an element
 * of another todolist
 *
 * @author Andy Li
 * @since Nov 1, 2017
 */
public class Task {
	
	private boolean completed = false;
	private String name;
	private int size;
	private Vector<Task> taskList;
	
	private String type;
	//some constants to help with indentification
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
	 * @param name Name of the task
	 */
	public Task(String name) {
		this.name = name;
		type = TASK;
	}
	
	/**
	 * Constructor for a TodoList
	 * Sets name as "untitled"
	 * @param size Size of isList
	 */
	public Task(int size, boolean isProject) {
		this.size = size;
		this.name = "untitled";
		type = isProject ? PROJECT : LIST;
		taskList = new Vector<>(this.size, 10);
	}
	
	/**
	 * Constructor for a TodoList
	 * @param size Size of isList
	 */
	public Task(String name, int size, boolean isProject) {
		this.size = size;
		this.name = name;
		type = isProject ? PROJECT : LIST;
		taskList = new Vector<>(this.size, 10);
	}
	
	/**
	 * @return If the task is completed or not
	 */
	public boolean isCompleted() {
		return completed;
	}
	
	/**
	 * @return If this object represents a isList of tasks or just one
	 */
	public boolean isList() {
		return type.equals(LIST);
	}
	
	/**
	 * @return If this object is a project (i.e. cannot be part of a task list)
	 */
	public boolean isProject() {
		return type.equals(PROJECT);
	}
	
	/**
	 * @return The type of this task object (only every the constants project, list, or task)
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @return The task's name
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
	 * @param t 	A task object to be added to the list
	 * @param index The index to add at
	 */
	public void addNewTask(Task t, int index) {
		taskList.set(index, t);
	}
	
	/**
	 * Marks a task complete
	 * @param index The index that the task located is
	 */
	public void markTaskComplete(int index) {
		taskList.get(index).markComplete();
	}
	
	/**
	 * @return This Task object's tasklist
	 */
	public Vector<Task> getTaskList() {
		return taskList;
	}
	
	/*
	 * The following few methods were mostly used for bug testing and shouldn't
	 * be called by the main application, but I'm keeping them here because
	 * it's better to have it and not need it than need it and not have it.
	 */
	
	/**
	 * Uses recursion to count the number of tasks/tasklists in a task object
	 * @param task A task object, usually a project
	 * @return The number of tasks/tasklists contained by
	 */
	public static int getNumChildren(Task task) {
		int count = 0;
		for (Task t: task.getTaskList()) {
			count++;
			if (t.isList())
				count += getNumChildren(t);
		}
		return count;
	}
	
	
	/**
	 * Gets all lists and tasks under a task object in a single vector
	 * @param task A task object
	 * @return A vector containing all the task objects
	 */
	public static Vector<Task> getAllTasks(Task task) {
		Vector<Task> list = new Vector<>(10, 1);
		for (Task t: task.getTaskList()) {
			if (t.isList())
				list.addAll(getAllTasks(t));
			else
				list.add(t);
		}
		list.trimToSize();
		return list;
	}
	
	/**
	 * Creates a String containing every task/list in a given task along with the type
	 * @param task A task object
	 * @return A String in the format
	 * 			TYPE: name
	 * 			TYPE: name... etc
	 */
	public static String toString(Task task) {
		StringBuilder temp = new StringBuilder(task.getType() + ": " + task.getName() + "\n");
		for (Task t: task.getTaskList()) {
			if (t.isList())
				temp.append(toString(t));
			else
				temp.append(t.getType()).append(": ").append(t.getName()).append("\n");
		}
		return temp.toString();
	}
}
