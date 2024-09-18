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
	 * @throws IllegalArgumentException when when priority, or name are invalid. When the name is null,
	 * or empty string.
	 * @throws IndexOutOfBoundsException when the index is less than 1 or greater than size + 1.
	 */
	public void addTask(int priority, String name) {
		if (name == null || "".equals(name)) {
			throw new IllegalArgumentException("Invalid name for Task.");
		}
		if (priority < 1 || priority > size() + 1) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = tasks.size(); i > priority - 1; i--) {
			int transferPriority = getTask(i).getPriority() + 1;
			String transferName = getTask(i).getName();
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
		addTask(1, name);
	}
	/**
	 * Adds a Task to the front of the list with the lowest priority.
	 * @param name the name of the Task to be added
	 * @throws IllegalArgumentException when when priority, or name are invalid.
	 */
	public void addTaskLowestPriority(String name) {
		addTask(size() + 1, name);
	}
	/**
	 * Removes a Task from the list.
	 * @param priority the priority of the Task being removed
	 * @return the Task that was removed
	 * @throws IndexOutOfBoundsException when the priority is less than 1 or greater than size + 1.
	 */
	public Task removeTask(int priority) {
		if (priority < 1 || priority > tasks.size() + 1) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = priority; i < tasks.size(); i++) {
			String transferName = tasks.get(i).getName();
			tasks.set((i), new Task(i , transferName));
		}
		return tasks.remove(priority - 1);
	}
	/**
	 * Changes the Task priority to another priority.
	 * @param oldPriority the old priority
	 * @param newPriority the new priority
	 * @throws IllegalArgumentException when the oldPriority is the same as the newPriority.
	 * When the size is 0.
	 * @throws IndexOutOfBoundsException when the oldPriority and the newPriority are less than 1,
	 * or greater than size.
	 */
	public void changeTaskPriority(int oldPriority, int newPriority) {
		if (tasks.size() == 0) {
			throw new IllegalArgumentException("There are no Task yet.");
		}
		if (oldPriority == newPriority) {
			throw new IllegalArgumentException("The new priority can't be the same as the old priority.");
		}
		if (oldPriority < 1 || oldPriority > tasks.size()) {
			throw new IndexOutOfBoundsException();
		}
		if (newPriority < 1 || oldPriority > tasks.size()) {
			throw new IndexOutOfBoundsException();
		}
		String transferName = getTask(oldPriority).getName();
		removeTask(oldPriority);
		addTask(newPriority, transferName);
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
