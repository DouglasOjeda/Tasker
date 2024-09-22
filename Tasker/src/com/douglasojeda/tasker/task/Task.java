package com.douglasojeda.tasker.task;
/**
 * A Task knows its' priority and name. Task can validate and set its's fields.
 * Task can also check if it is equal to another Object and can generate a String
 * representation of itself.
 * @author douglasojeda
 */
public class Task {
	/** Priority of the Task. */
	private int priority;
	/** Name of the Task. */
	private String name;
	/**
	 * Constructs a Task using a priority and a name.
	 * @param priority the priority of the Task
	 * @param name the name of the Task
	 * @throws IllegalArgumentException when any of the fields are invalid.
	 */
	public Task(int priority, String name) {
		setPriority(priority);
		setName(name);
	}
	/**
	 * Returns the Task's priority.
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * Sets the Task's priority.
	 * @param priority the priority to set
	 * @throws IllegalArgumentException when the priority is less than 1.
	 */
	private void setPriority(int priority) {
		if (priority < 1) {
			throw new IllegalArgumentException("Priority can't be less 1.");
		}
		this.priority = priority;
	}
	/**
	 * Returns the Task's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the Task's name.
	 * @param name the name to set
	 * @throws IllegalArgumentException when the name is null, or an empty String.
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Null name!");
		}
		if ("".equals(name)) {
			throw new IllegalArgumentException("Name can't be empty.");
		}
		this.name = name;
	}
	/**
	 * Generates hash code for Task.
	 * @return hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + name.hashCode();
		result = prime * result + priority;
		return result;
	}
	/**
	 * Checks if an Object is equal to this Task.
	 * @param obj Object to be checked for equality
	 * @return whether two object are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Task))
			return false;
		Task other = (Task) obj;
		if (!name.equals(other.name))
			return false;
		if (priority != other.priority)
			return false;
		return true;
	}
	/**
	 * Return a String representation of Task.
	 * @return String representation of Task
	 */
	@Override
	public String toString() {
		return String.format("%s,%s", priority, name);
	}
	
}
