package com.douglasojeda.tasker.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for TaskReader and TaskWriter classes.
 * @author douglasojeda
 */
public class TaskReaderWriterTest {
	/** Valid Tasks file. */
	private final String VALID_TASK_FILE = "";
	/** Invalid Tasks file 1. */
	private final String INVALID_TASK_FILE_1 = "";
	/** Valid Task 1 - on line 1 */
	private final String VALID_TASK_1 = "";
	/** Valid Task 2 - on line 2 */
	private final String VALID_TASK_2 = "";
	/** Valid Task 3 - on line 3 */
	private final String VALID_TASK_3 = "";
	/** Valid Task 4 - on line 4 */
	private final String VALID_TASK_4 = "";
	/** Valid Task 5 - on line 5 */
	private final String VALID_TASK_5 = "";
	/** Valid Task 6 - on line 6 */
	private final String VALID_TASK_6 = "";
	/** Valid Task 7 - on line 7 */
	private final String VALID_TASK_7 = "";
	/** Valid Task 8 - on line 8 */
	private final String VALID_TASK_8 = "";
	/** Valid Task 9 - on line 9 */
	private final String VALID_TASK_9 = "";
	/** Valid Task 10 - on line 10 */
	private final String VALID_TASK_10 = "";
	/**
	 * Resets valid and invalid Task files.
	 */
	@Before
	public void setUp() {
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "valid_tasks.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "tasks.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	/**
	 * Testing readTaskFile for valid Tasks.
	 */
	@Test
	public void validReadTaskFileTest() {
		try {
			
		}
	}
}