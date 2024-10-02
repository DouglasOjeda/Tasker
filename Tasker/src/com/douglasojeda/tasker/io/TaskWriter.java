package com.douglasojeda.tasker.io;

import java.io.PrintStream;
import java.io.File;
import java.io.IOException;

import com.douglasojeda.tasker.manager.TaskManager;

public class TaskWriter {
	/**
	 * Writes Tasks onto a file from a TaskManager.
	 * @param fileName the file name of the File that the Tasks will be written to.
	 * @param tasker the TaskManager containing Tasks that will be written into the file
	 */
	public static void writeTaskFile(String fileName, TaskManager tasker) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < tasker.size(); i++) {
			fileWriter.println(tasker.getTask(i + 1).toString());
		}
		fileWriter.close();
	}
}
