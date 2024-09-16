package com.douglasojeda.tasker.manager;

import java.util.ArrayList;

import com.douglasojeda.tasker.task.Task;

/**
 * Class
 * @author douglasojeda
 */
public class TaskManager {
	/** Title of TaskManager. */
	private String title;
	/** TaskManagers List of Tasks. */
	private ArrayList<Task> tasks;
	/**
	 * Constructs a TaskManager with a title. An Empty list is created for tasks.
	 * @param title the title for TaskManager
	 * @throws IllegalArgumentException when title is invalid.
	 */
	public TaskManager(String title) {
		setTitle(title);
		tasks = new ArrayList<Task>();
	}
	/**
	 * Returns TaskManagers' title.
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Sets TaskManagers' title.
	 * @param title the title to set
	 * @throws IllegalArgumentException when the title is null, or empty.
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Null title!");
		}
		if ("".equals(title)) {
			throw new IllegalArgumentException("Name can't be empty.");
		}
		this.title = title;
	}
	/**
	 * Adds a Task to a index in the list, with a priority and name. It used the Task priority
	 * to tell where in the index it will be put. It adds the Task and move other Tasks accordingly.
	 * @param priority the priority of the Task to be added
	 * @param name the name of the Task to be added
	 * @throws IllegalArgumentException when when priority, or name are invalid.
	 * @throws IndexOutOfBoundsException when the index is less than 1 or greater than size + 1.
	 */
	public void addTaskAtIndex(int priority, String name) {
		if (priority < 1 || priority > size() + 1) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = tasks.size(); i > priority - 1; i--) {
			int transferPriority = tasks.get(i - 1).getPriority() + 1;
			String transferName = tasks.get(i - 1).getName();
			tasks.set(i - 1, new Task(transferPriority, transferName));
		}
		tasks.add(priority - 1, new Task(priority, name));
	}
	/**
	 * Adds a Task to the front of the list with the highest priority.
	 * @param name the name of the Task to be added
	 * @throws IllegalArgumentException when when priority, or name are invalid.
	 */
	public void addTaskHighestPriority(String name) {
		addTaskAtIndex(1, name);
	}
	/**
	 * Adds a Task to the front of the list with the lowest priority.
	 * @param name the name of the Task to be added
	 * @throws IllegalArgumentException when when priority, or name are invalid.
	 */
	public void addTaskLowestPriority(String name) {
		addTaskAtIndex(size() + 1, name);
	}
	/**
	 * Removes a Task from the list.
	 * @param idx the index of the Task being removed
	 * @return the Task that was removed
	 */
	public Task removeTask(int idx) {
		return null;
	}
	/**
	 * Returns the size of the Task list.
	 * @return size of Task list
	 */
	public int size() {
		return tasks.size();
	}
	/**
	 * Returns a Task with a given priority.
	 * @param priority the priority of the Task to get
	 * @return Task with give priority
	 */
	public Task getTask(int priority) {
		return tasks.get(priority - 1);
	}
}
