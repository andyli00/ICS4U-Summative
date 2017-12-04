package organization;

/**
 * Essentially a list of task or todolist objects
 * @author Andy Li
 * @since Nov 1, 2017
 */
public class Project extends TodoList {
	
	/**
	 * Constructor
	 * @param name 	name of the project
	 * @param size	how many todolists or tasks can be contained
	 */
	public Project(String name, int size) {
		super(name, size);
	}
	
	/**
	 * Constructor if no name is specified
	 * @param size how many todolists or tasks can be contained
	 */
	public Project(int size) {
		super(size);
	}
	
	/**
	 * Adds a todolist to the project
	 * @param list	the todolist to be added
	 * @param index	the index at which to add the todolist
	 */
	public void addNewTodoList(TodoList list, int index) {
		taskList.set(index, list);
	}
}
