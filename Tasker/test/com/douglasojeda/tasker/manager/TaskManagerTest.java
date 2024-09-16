package com.douglasojeda.tasker.manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * Tests for the TaskManager class.
 * @author douglasojeda
 */
public class TaskManagerTest {
	/**
	 * Testing the TaskManagers' constructor.
	 */
	@Test
	public void testConstructor() {
		TaskManager tasker = new TaskManager("Tasker");
		assertEquals("Tasker", tasker.getTitle());
		assertEquals(0, tasker.size());
		assertThrows(IllegalArgumentException.class, () -> new TaskManager(null));
		assertThrows(IllegalArgumentException.class, () -> new TaskManager(""));
	}
	/**
	 * Testing the add and remove methods.
	 */
	@Test
	public void testAddRemoveTask() {
		TaskManager tasker = new TaskManager("Tasker");
		//Adding first Task
		tasker.addTask(1, "Homework 1");
		assertEquals(1, tasker.size());
		assertEquals(1, tasker.getTask(1).getPriority());
		assertEquals("Homework 1", tasker.getTask(1).getName());
		//Adding second Task
		tasker.addTask(2, "Homework 2");
		assertEquals(2, tasker.size());
		assertEquals(2, tasker.getTask(2).getPriority());
		assertEquals("Homework 2", tasker.getTask(2).getName());
		//Adding third Task
		tasker.addTask(1, "Reading 1");
		assertEquals(3, tasker.size());
		assertEquals(1, tasker.getTask(1).getPriority());
		assertEquals("Reading 1", tasker.getTask(1).getName());
		assertEquals(2, tasker.getTask(2).getPriority());
		assertEquals("Homework 1", tasker.getTask(2).getName());
		assertEquals(3, tasker.getTask(3).getPriority());
		assertEquals("Homework 2", tasker.getTask(3).getName());
		//Adding fourth Task
		tasker.addTask(2, "Reading 2");
		assertEquals(4, tasker.size());
		assertEquals(1, tasker.getTask(1).getPriority());
		assertEquals("Reading 1", tasker.getTask(1).getName());
		assertEquals(2, tasker.getTask(2).getPriority());
		assertEquals("Reading 2", tasker.getTask(2).getName());
		assertEquals(3, tasker.getTask(3).getPriority());
		assertEquals("Homework 1", tasker.getTask(3).getName());
		assertEquals(4, tasker.getTask(4).getPriority());
		assertEquals("Homework 2", tasker.getTask(4).getName());
		//Adding with highest priority method
		tasker.addTaskHighestPriority("Pay Bills");
		assertEquals(5, tasker.size());
		assertEquals(1, tasker.getTask(1).getPriority());
		assertEquals("Pay Bills", tasker.getTask(1).getName());
		assertEquals(2, tasker.getTask(2).getPriority());
		assertEquals("Reading 1", tasker.getTask(2).getName());
		assertEquals(3, tasker.getTask(3).getPriority());
		assertEquals("Reading 2", tasker.getTask(3).getName());
		assertEquals(4, tasker.getTask(4).getPriority());
		assertEquals("Homework 1", tasker.getTask(4).getName());
		assertEquals(5, tasker.getTask(5).getPriority());
		assertEquals("Homework 2", tasker.getTask(5).getName());
		//Adding with the lowest priority method
		tasker.addTaskLowestPriority("Assignment");
		assertEquals(6, tasker.size());
		assertEquals(1, tasker.getTask(1).getPriority());
		assertEquals("Pay Bills", tasker.getTask(1).getName());
		assertEquals(2, tasker.getTask(2).getPriority());
		assertEquals("Reading 1", tasker.getTask(2).getName());
		assertEquals(3, tasker.getTask(3).getPriority());
		assertEquals("Reading 2", tasker.getTask(3).getName());
		assertEquals(4, tasker.getTask(4).getPriority());
		assertEquals("Homework 1", tasker.getTask(4).getName());
		assertEquals(5, tasker.getTask(5).getPriority());
		assertEquals("Homework 2", tasker.getTask(5).getName());
		assertEquals(6, tasker.getTask(6).getPriority());
		assertEquals("Assignment", tasker.getTask(6).getName());
		//Removing a Task from last
		tasker.removeTask(6);
		assertEquals(5, tasker.size());
		assertEquals(1, tasker.getTask(1).getPriority());
		assertEquals("Pay Bills", tasker.getTask(1).getName());
		assertEquals(2, tasker.getTask(2).getPriority());
		assertEquals("Reading 1", tasker.getTask(2).getName());
		assertEquals(3, tasker.getTask(3).getPriority());
		assertEquals("Reading 2", tasker.getTask(3).getName());
		assertEquals(4, tasker.getTask(4).getPriority());
		assertEquals("Homework 1", tasker.getTask(4).getName());
		assertEquals(5, tasker.getTask(5).getPriority());
		assertEquals("Homework 2", tasker.getTask(5).getName());
		//Removing second Task from second to last
		tasker.removeTask(4);
		assertEquals(4, tasker.size());
		assertEquals(1, tasker.getTask(1).getPriority());
		assertEquals("Pay Bills", tasker.getTask(1).getName());
		assertEquals(2, tasker.getTask(2).getPriority());
		assertEquals("Reading 1", tasker.getTask(2).getName());
		assertEquals(3, tasker.getTask(3).getPriority());
		assertEquals("Reading 2", tasker.getTask(3).getName());
		assertEquals(4, tasker.getTask(4).getPriority());
		assertEquals("Homework 2", tasker.getTask(4).getName());
		//Removing third Task from the front
		tasker.removeTask(1);
		assertEquals(3, tasker.size());
		assertEquals(1, tasker.getTask(1).getPriority());
		assertEquals("Reading 1", tasker.getTask(1).getName());
		assertEquals(2, tasker.getTask(2).getPriority());
		assertEquals("Reading 2", tasker.getTask(2).getName());
		assertEquals(3, tasker.getTask(3).getPriority());
		assertEquals("Homework 2", tasker.getTask(3).getName());
	}
}
