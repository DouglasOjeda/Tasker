package com.douglasojeda.tasker.ui;

import com.douglasojeda.tasker.manager.TaskManager;
import java.util.Scanner;

public class TaskerUI {
	/** Constant for String text divider */
	private static final String TEXT_DIVIDER = "------------------------";
	/** Constant for add task menu option */
	private static final int MENU_OPTION_ADD = 1;
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		TaskManager tasker = new TaskManager();
		int input = 5;
		do {
			menuDisplay(tasker);
			while (!keyboard.hasNextInt()) {
				keyboard.next();
				System.out.println();
				System.out.println("Enter a single integer from 1-5: ");
				System.out.println();
				menuDisplay(tasker);
			}
			input = keyboard.nextInt();
			System.out.println(TEXT_DIVIDER);
			switch (input) {
			case MENU_OPTION_ADD:
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
					} catch (IndexOutOfBoundsException | IllegalArgumentException e) {
						System.out.println(TEXT_DIVIDER);
						System.out.println(e.getMessage());
						System.out.println(TEXT_DIVIDER);
					}
				}
				break;
			case 2:
				listTasks(tasker);
				System.out.println(TEXT_DIVIDER);
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
				System.out.println("5");
				break;
			default:
				System.out.println("Error");
				break;
			}
		} while (input != 5);
		keyboard.close();
	}
	/**
	 * Displays the UI menu.
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
	 * Lists the tasks from TaskManager.
	 * @param tasker the TaskManager that will be used to retrieve tasks.
	 */
	public static void listTasks(TaskManager tasker) {
		System.out.println(tasker.getTitle() + " Tasks");
		System.out.println(TEXT_DIVIDER);
		for (int i = 0; i < tasker.size(); i++) {
			System.out.printf("%d- %s\n", tasker.getTask(i + 1).getPriority(), tasker.getTask(i + 1).getName());
		}
		System.out.println(TEXT_DIVIDER);
	}
}
