package organization;

import java.util.Vector;

/**
 * Essentially a list of task objects
 * @author Andy Li
 * @since Oct 30, 2017
 */
public class TodoList extends Task {
	
	private final int size;
	Vector<Task> taskList;
	//I used a vector because it's better for retrieving,
	//adding, or removing elements at specific indexes
	
	/**
	 * Constructor
	 * @param name name of the TodoList
	 * @param size how many tasks it can contain
	 */
	public TodoList(String name, int size) {
		super(name);
		this.size = size;
		taskList = new Vector<>(size, 10);
	}
	
	/**
	 * Constructor if no size is specified
	 * @param name name of the TodoList
	 */
	public TodoList(String name) {
		super(name);
		this.size = 10; //default to 10
		taskList = new Vector<>(size, 10);
	}
	
	/**
	 * Constructor if no name is specified
	 * @param size how many tasks it can contain
	 */
	public TodoList(int size) {
		super();
		this.size = size;
		taskList = new Vector<>(this.size, 10);
	}
	
	/**
	 * @return size of the TodoList
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Adds a new task at a specified index
	 * @param t 	a task object
	 * @param index	the index at which to add the task
	 */
	public void addNewTask(Task t, int index) 	{
		taskList.set(index, t);
	}
	
	/**
	 * marks a task as complete
	 * @param index index at which to mark task complete
	 */
	public void markTaskComplete(int index) {
		taskList.elementAt(index).markComplete();
	}
	
	/**
	 * removes a task from the list
	 * @param index index at which to remove the task
	 */
	public void removeTask(int index) {
		taskList.removeElementAt(index);
	}
}