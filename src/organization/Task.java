package organization;

/**
 * Basic class for task. This is the ancestor of TodoList and Project
 * @author Andy Li
 * @since Nov 1, 2017
 */
public class Task {
	
	private boolean completed = false;
	private final String name;
	
	/**
	 * Default constructor that gives a name "untitled"
	 */
	public Task() { this.name = "untitled";}
	
	/**
	 * Constructor
	 * @param name name of the task
	 */
	public Task(String name) { this.name = name; }
	
	/**
	 * @return if the task is completed or not
	 */
	public boolean isCompleted() { return completed; }
	
	/**
	 * @return the task's name
	 */
	public String getName() 	 { return name; }
	
	/**
	 * marks the task complete
	 */
	public void markComplete()   { completed = true; }
}
