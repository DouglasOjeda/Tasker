package com.douglasojeda.tasker.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.douglasojeda.tasker.task.Task;

/**
 * Class
 * @author douglasojeda
 */
public class TaskReader {
	/**
	 * Reads Task records from a file. It parses through the file by each line
	 * reading in a Task and a adding it to a list.
	 * @param fileName the name of the file to be read
	 * @return list of Tasks read in
	 * @throws FileNotFoundException when the file could not be found or read.
	 */
	public static ArrayList<Task> readTaskFile(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		ArrayList<Task> tasks = new ArrayList<Task>();
		int lineCounter = 1;
		while (fileReader.hasNextLine()) {
			try {
				tasks.add(taskReader(fileReader.nextLine()));
				lineCounter++;
			} catch (IllegalArgumentException e) {
				fileReader.close();
				throw new IllegalArgumentException("Error reading file on line " + lineCounter + ".");
			}
		}
		fileReader.close();
		return tasks;
	}
	private static Task taskReader(String fileLine) {
		Scanner lineReader = new Scanner(fileLine);
		lineReader.useDelimiter(",");
		try {
			int priority = lineReader.nextInt();
			String name = lineReader.next();
			Task task = new Task(priority, name);
			lineReader.close();
			return task;
		} catch (NoSuchElementException e) {
			lineReader.close();
			throw new IllegalArgumentException();
		}
	}
}
