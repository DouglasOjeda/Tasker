package com.douglasojeda.tasker.ui;

import com.douglasojeda.tasker.manager.TaskManager;
import com.douglasojeda.tasker.task.Task;

import java.util.Scanner;

public class TaskerUI {
	/** Constant for String text divider. */
	private static final String TEXT_DIVIDER = "------------------------";
	/** Constant for add task menu option. */
	private static final int MENU_OPTION_ADD = 1;
	/** Constant for remove task menu option. */
	private static final int MENU_OPTION_REMOVE = 2;
	/** Constant for change task menu option. */
	private static final int MENU_OPTION_CHANGE = 3;
	/** Constant for list task menu option. */
	private static final int MENU_OPTION_LIST = 4;
	/** Constant for exit task menu option. */
	private static final int MENU_OPTION_EXIT = 5;
	/** Constant for repeating option again. */
	private static final int REPEAT_OPTION = 1;
	/** Constant for back to menu option */
	private static final int BACK_TO_MENU_OPTION = 2;
	/**
	 * Main method start the program.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		TaskManager tasker = new TaskManager();
		int menuInput = 5;
		do {
			menuDisplay(tasker);
			while (!keyboard.hasNextInt()) {
				keyboard.next();
				System.out.println(TEXT_DIVIDER);
				clearConsole();
				System.out.println("Enter a single integer from 1 to 5");
				System.out.println(TEXT_DIVIDER);
				menuDisplay(tasker);
			}
			menuInput = keyboard.nextInt();
			System.out.println(TEXT_DIVIDER);
			System.out.print("\033[H\033[2J");
			switch (menuInput) {
			case MENU_OPTION_ADD:
				int subMenuInput = 2;
				do {
					addTask(tasker, keyboard);
					System.out.print("\033[H\033[2J");
					System.out.println("Task was added!");
					System.out.println(TEXT_DIVIDER);
					subMenuAnother("Add another Task");
					subMenuInput = keyboard.nextInt();
					while (keyboard.hasNextInt() && subMenuInput != 1 && subMenuInput != 2) {
						System.out.println(TEXT_DIVIDER);
						System.out.println("Enter an Enterger from 1 to 2: ");
						System.out.println(TEXT_DIVIDER);
					}
					System.out.println(TEXT_DIVIDER);
					System.out.print("\033[H\033[2J");
				} while (subMenuInput != 2);
				break;
			case 2:
				removeTask(tasker, keyboard);
				break;
			case 3:
				listTasks(tasker);
				System.out.println(TEXT_DIVIDER);
				break;
			case 4:
				listTasks(tasker);
				System.out.println(TEXT_DIVIDER);
				break;
			case 5:
				break;
			default:
				System.out.println("Enter a single integer from 1 to 5: ");
				break;
			}
		} while (menuInput != 5);
		keyboard.close();
	}
	/**
	 * Displays the UI main menu.
	 * @param tasker the TaskManager used for getting name.
	 */
	public static void menuDisplay(TaskManager tasker) {
		System.out.println("Welcome to " + tasker.getTitle());
		System.out.println(TEXT_DIVIDER);
		System.out.println("1. Add Task");
		System.out.println("2. Remove Task");
		System.out.println("3. Change Task");
		System.out.println("4. List Tasks");
		System.out.println("5. Exit");
		System.out.println(TEXT_DIVIDER);
		System.out.print("Choose an option: ");
	}
	/**
	 * Displays a UI sub menu for performing the same menu option again, or returning back to the
	 * main menu.
	 * @param firstMenuOption the String for sub menu option to perform the same option again
	 */
	public static void subMenuAnother(String firstMenuOption) {
		System.out.println("1. Add another Task");
		System.out.println("2. Back to menu");
		System.out.println(TEXT_DIVIDER);
		System.out.print("Choose an option: ");
	}
	/**
	 * Lists the tasks from TaskManager.
	 * @param tasker the TaskManager that will be used to retrieve tasks.
	 */
	public static void listTasks(TaskManager tasker) {
		System.out.println("Tasks");
		System.out.println(TEXT_DIVIDER);
		for (int i = 0; i < tasker.size(); i++) {
			System.out.printf("%d) %s\n", tasker.getTask(i + 1).getPriority(), tasker.getTask(i + 1).getName());
		}
		System.out.println(TEXT_DIVIDER);
	}
	/**
	 * Clears the console.
	 */
	public static void clearConsole() {
		System.out.print("\033[H\033[2J");
	}
	/**
	 * Prompts the user for a Task name and a Task priority to Add a Task.
	 * @param tasker the TaskManager where the Task wil be added to
	 * @param keyboard the Scanner that will take in the keyboard input
	 */
	public static void addTask(TaskManager tasker, Scanner keyboard) {
		boolean sucessfulTask = false;
		while (!sucessfulTask) {
			try {
				listTasks(tasker);
				System.out.print("What is the name of this Task? ");
				keyboard.nextLine();
				String taskName = keyboard.nextLine();
				System.out.print("What is the priority of " + taskName + "? ");
				while (!keyboard.hasNextInt()) {
					keyboard.next();
					System.out.println(TEXT_DIVIDER);
					System.out.println("Priority has to be an integer.");
					System.out.println(TEXT_DIVIDER);
					System.out.print("What is the priority of " + taskName + "? ");
				}
				int taskPriority = keyboard.nextInt();
				tasker.addTask(taskPriority, taskName);
				sucessfulTask = true;
				System.out.println(TEXT_DIVIDER);
			} catch (IndexOutOfBoundsException | IllegalArgumentException e) {
				System.out.println(TEXT_DIVIDER);
				System.out.println(e.getMessage());
				System.out.println(TEXT_DIVIDER);
			}
		}
	}
	/**
	 * Prompts the user for a Task priority to remove a Task.
	 * @param tasker the TaskManager where the Task will be removed from
	 * @param keyboard the Scanner that will take in the keyboard input
	 * @return the Task that was removed
	 */
	public static Task removeTask(TaskManager tasker, Scanner keyboard) {
		boolean sucessfulTask = false;
		while (!sucessfulTask) {
			try {
				listTasks(tasker);
				System.out.print("What is the priority of the Task you want to remove? ");
				while (!keyboard.hasNextInt()) {
					keyboard.next();
					System.out.println(TEXT_DIVIDER);
					System.out.println("Priority has to be an integer.");
					System.out.println(TEXT_DIVIDER);
					System.out.print("What is the priority of the Task you want to remove? ");
				}
				int taskPriority = keyboard.nextInt();
				Task taskRemoved = tasker.removeTask(taskPriority);
				sucessfulTask = true;
				System.out.println(TEXT_DIVIDER);
				return taskRemoved;
			} catch (IndexOutOfBoundsException e) {
				System.out.println(TEXT_DIVIDER);
				System.out.println(e.getMessage());
				System.out.println(TEXT_DIVIDER);
			}
		}
		return null;
	}
}
