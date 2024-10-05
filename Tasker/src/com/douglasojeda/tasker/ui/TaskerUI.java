package com.douglasojeda.tasker.ui;

import com.douglasojeda.tasker.manager.TaskManager;

import java.time.ZonedDateTime;
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
	private static final int PERFORM_MAIN_MENU_OPTION = 1;
	/** Constant for back to menu option */
	private static final int BACK_TO_MENU_OPTION = 2;
	/**
	 * Main method start the program.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		TaskManager tasker = new TaskManager();
		//I hard coded my load and save destination, but you could prompt the user for destination
		tasker.loadTasks("./program_files/Tasker_files/personal_tasks.txt");
		int menuInput = 0;
		//Main menu loop
		clearConsole();
		do {
			boolean isMainMenuInputInteger = false;
			menuDisplay(tasker);
			while (!isMainMenuInputInteger) {
				try {
					menuInput = Integer.parseInt(keyboard.nextLine());
					isMainMenuInputInteger = true;
				} catch (NumberFormatException e) {
					System.out.println(TEXT_DIVIDER);
					clearConsole();
					System.out.println("Enter a single integer from 1 to 5");
					System.out.println(TEXT_DIVIDER);
					menuDisplay(tasker);
				}
			}
			System.out.println(TEXT_DIVIDER);
			clearConsole();
			//Logic for Menu
			switch (menuInput) {
			case MENU_OPTION_ADD:
				int addSubMenuInput = PERFORM_MAIN_MENU_OPTION;
				do {
					switch (addSubMenuInput) {
					case PERFORM_MAIN_MENU_OPTION:
						String taskAdded = addTask(tasker, keyboard);
						if (taskAdded == null) {
							//User no longer wants to add, goes back to main menu
							addSubMenuInput = BACK_TO_MENU_OPTION;
							break;
						}
						System.out.println("Task " + taskAdded + " was added!");
						System.out.println(TEXT_DIVIDER);
						addSubMenuInput = subMenu("Add another Task", keyboard);
						break;
					case BACK_TO_MENU_OPTION:
						break;
					default:
						System.out.println("Enter an Enterger from 1 to 2");
						System.out.println(TEXT_DIVIDER);
						addSubMenuInput = subMenu("Add another Task", keyboard);
						break;
					}
				} while (addSubMenuInput != BACK_TO_MENU_OPTION);
				tasker.saveTasks("./program_files/Tasker_files/personal_tasks.txt");
				break;
			case MENU_OPTION_REMOVE:
				int removeSubMenuInput = PERFORM_MAIN_MENU_OPTION;
				do {
					switch (removeSubMenuInput) {
					case PERFORM_MAIN_MENU_OPTION:
						String taskRemoved = removeTask(tasker, keyboard);
						if (taskRemoved == null) {
							//User no longer wants to remove, goes back to main menu
							removeSubMenuInput = BACK_TO_MENU_OPTION;
							break;
						}
						System.out.println("Task " + taskRemoved + " was removed!");
						System.out.println(TEXT_DIVIDER);
						removeSubMenuInput = subMenu("Remove another Task", keyboard);
						break;
					case BACK_TO_MENU_OPTION:
						break;
					default:
						System.out.println("Enter an Enterger from 1 to 2");
						System.out.println(TEXT_DIVIDER);
						removeSubMenuInput = subMenu("Remove another Task", keyboard);
						break;
					}
				} while (removeSubMenuInput != BACK_TO_MENU_OPTION);
				tasker.saveTasks("./program_files/Tasker_files/personal_tasks.txt");
				break;
			case MENU_OPTION_CHANGE:
				System.out.println("Not implemented yet.");
				System.out.println(TEXT_DIVIDER);
				System.out.print("Hit (Return/Enter) to go back to main menu: ");
				keyboard.nextLine();
				System.out.println(TEXT_DIVIDER);
				clearConsole();
				break;
			case MENU_OPTION_LIST:
				listTasks(tasker);
				System.out.print("Hit (Return/Enter) to go back to main menu: ");
				keyboard.nextLine();
				System.out.println(TEXT_DIVIDER);
				clearConsole();
				break;
			case MENU_OPTION_EXIT:
				break;
			default:
				System.out.println("Enter a single integer from 1 to 5");
				System.out.println(tasker.getClass().getSimpleName());
				System.out.println(TEXT_DIVIDER);
				break;
			}
		} while (menuInput != MENU_OPTION_EXIT);
		keyboard.close();
	}
	
	/**
	 * Displays the UI main menu.
	 * @param tasker the TaskManager used for getting name.
	 */
	public static void menuDisplay(TaskManager tasker) {
		ZonedDateTime date = ZonedDateTime.now();
		System.out.format("%02d/%02d/%d\n", date.getMonthValue(), date.getDayOfMonth(), date.getYear());
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
	 * main menu. Then user input for the menu option is validated and returned.
	 * @param firstMenuOption the String for sub menu option to perform the same option again
	 * @param keyboard Scanner that will take in the keyboard input
	 * @return the subMenuInput
	 */
	public static int subMenu(String firstMenuOption, Scanner keyboard) {
		int subMenuInput = 0;
		boolean isSubMenuInputInteger = false;
		while (!isSubMenuInputInteger) {
			try {
				System.out.println("1. " + firstMenuOption);
				System.out.println("2. Back to menu");
				System.out.println(TEXT_DIVIDER);
				System.out.print("Choose an option: ");
				subMenuInput = Integer.parseInt(keyboard.nextLine());
				isSubMenuInputInteger = true;
			} catch (NumberFormatException e) {
				System.out.println(TEXT_DIVIDER);
				clearConsole();
				System.out.println("Enter an Enterger from 1 to 2");
				System.out.println(TEXT_DIVIDER);
			}
		}
		System.out.println(TEXT_DIVIDER);
		clearConsole();
		return subMenuInput;
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
	 * @param tasker the TaskManager where the Task will be added to
	 * @param keyboard the Scanner that will take in the keyboard input
	 * @return the Task name that was added
	 */
	public static String addTask(TaskManager tasker, Scanner keyboard) {
		boolean sucessfulTask = false;
		int subMenuInput = 0;
		int taskPriority = 0;
		while (!sucessfulTask && subMenuInput != BACK_TO_MENU_OPTION) {
			try {
				listTasks(tasker);
				System.out.print("What is the name of this Task? ");
				String taskName = keyboard.nextLine();
				System.out.print("What is the priority of " + taskName + "? ");
				boolean isTaskPriorityValid = false;
				while (!isTaskPriorityValid) {
					try {
						taskPriority = Integer.parseInt(keyboard.nextLine());
						isTaskPriorityValid = true;
					} catch (NumberFormatException e) {
						System.out.println(TEXT_DIVIDER);
						clearConsole();
						System.out.println("Priority has to be an integer.");
						System.out.println(TEXT_DIVIDER);
						System.out.print("What is the priority of " + taskName + "? ");
					}
				}
				tasker.addTask(taskPriority, taskName);
				sucessfulTask = true;
				System.out.println(TEXT_DIVIDER);
				clearConsole();
			} catch (IndexOutOfBoundsException | IllegalArgumentException e) {
				//setting priority to 0 
				System.out.println(TEXT_DIVIDER);
				clearConsole();
				System.out.println(e.getMessage());
				System.out.println(TEXT_DIVIDER);
				//Asking user if they want to try again or exit to main menu
				subMenuInput = subMenu("Add Task", keyboard);
				while (subMenuInput != PERFORM_MAIN_MENU_OPTION && subMenuInput != BACK_TO_MENU_OPTION) {
					System.out.println("Enter an Enterger from 1 to 2");
					System.out.println(TEXT_DIVIDER);
					subMenuInput = subMenu("Add Task", keyboard);
				}
			}
		}
			if (sucessfulTask) {
				return tasker.getTask(taskPriority).getName();
			}
			return null;
	}
	/**
	 * Prompts the user for a Task priority to remove a Task.
	 * @param tasker the TaskManager where the Task will be removed from
	 * @param keyboard the Scanner that will take in the keyboard input
	 * @return the Task name that was removed
	 */
	public static String removeTask(TaskManager tasker, Scanner keyboard) {
		boolean sucessfulTask = false;
		int subMenuInput = 0;
		while (!sucessfulTask && subMenuInput != BACK_TO_MENU_OPTION) {
			try {
				listTasks(tasker);
				System.out.print("What is the priority of the Task you want to remove? ");
				int taskPriority = 0;
				boolean isTaskPriorityValid = false;
				while (!isTaskPriorityValid) {
					try {
						taskPriority = Integer.parseInt(keyboard.nextLine());
						isTaskPriorityValid = true;
					} catch (NumberFormatException e) {
						System.out.println(TEXT_DIVIDER);
						clearConsole();
						System.out.println("Priority has to be an integer.");
						System.out.println(TEXT_DIVIDER);
						System.out.print("What is the priority of the Task you want to remove? ");
					}
				}
				String taskRemoved = tasker.removeTask(taskPriority).getName();
				sucessfulTask = true;
				System.out.println(TEXT_DIVIDER);
				clearConsole();
				return taskRemoved;
			} catch (IndexOutOfBoundsException | IllegalArgumentException e) {
				System.out.println(TEXT_DIVIDER);
				clearConsole();
				System.out.println(e.getMessage());
				System.out.println(TEXT_DIVIDER);
				//Asking user if they want to try again or exit to main menu
				subMenuInput = subMenu("Remove Task", keyboard);
				while (subMenuInput != PERFORM_MAIN_MENU_OPTION && subMenuInput != BACK_TO_MENU_OPTION) {
					System.out.println("Enter an Enterger from 1 to 2");
					System.out.println(TEXT_DIVIDER);
					subMenuInput = subMenu("Remove Task", keyboard);
				}
			}
		}
		return null;
	}
}
