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
		TaskManager tasker = new TaskManager();
		assertEquals("Tasker", tasker.getTitle());
		assertEquals(0, tasker.size());
	}
	/**
	 * Testing the add and remove methods.
	 */
	@Test
	public void testAddRemoveTask() {
		TaskManager tasker = new TaskManager();
		Exception e0 = assertThrows(IllegalArgumentException.class, () -> tasker.removeTask(1));
		assertEquals("No Tasks to remove.", e0.getMessage());
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> tasker.addTask(1, null));
		assertEquals("Invalid name for Task.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> tasker.addTask(1, ""));
		assertEquals("Invalid name for Task.", e2.getMessage());
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
		Exception e3 = assertThrows(IndexOutOfBoundsException.class, () -> tasker.addTask(0, "A task"));
		assertEquals("Priority has to be an integer from 1 to 4.", e3.getMessage());
		Exception e4 = assertThrows(IndexOutOfBoundsException.class, () -> tasker.addTask(5, "A task"));
		assertEquals("Priority has to be an integer from 1 to 4.", e4.getMessage());
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
		Exception e5 = assertThrows(IndexOutOfBoundsException.class, () -> tasker.removeTask(0));
		assertEquals("Priority has to be an integer from 1 to 6.", e5.getMessage());
		Exception e6 = assertThrows(IndexOutOfBoundsException.class, () -> tasker.removeTask(7));
		assertEquals("Priority has to be an integer from 1 to 6.", e6.getMessage());
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
	/**
	 * Testing the changeTaskPriority method.
	 */
	@Test
	public void testChangeTaskPriority() {
		TaskManager tasker = new TaskManager();
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> tasker.changeTaskPriority(1, 1));
		assertEquals("There are no Tasks yet.", e1.getMessage());
		tasker.addTask(1, "Homework 1");
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> tasker.changeTaskPriority(1, 1));
		assertEquals("The new priority can't be the same as the old priority.", e2.getMessage());
		Exception e3 = assertThrows(IndexOutOfBoundsException.class, () -> tasker.changeTaskPriority(2, 1));
		assertEquals("Priority has to be an integer from 1 to 1.", e3.getMessage());
		Exception e4 = assertThrows(IndexOutOfBoundsException.class, () -> tasker.changeTaskPriority(1, 2));
		assertEquals("Priority has to be an integer from 1 to 1.", e4.getMessage());
		tasker.addTask(2, "Homework 2");
		tasker.addTask(3, "Homework 3");
		tasker.addTask(4, "Homework 4"); 
		tasker.changeTaskPriority(1, 2);
		assertEquals(1, tasker.getTask(1).getPriority());
		assertEquals("Homework 2", tasker.getTask(1).getName());
		assertEquals(2, tasker.getTask(2).getPriority());
		assertEquals("Homework 1", tasker.getTask(2).getName());
		assertEquals(3, tasker.getTask(3).getPriority());
		assertEquals("Homework 3", tasker.getTask(3).getName());
		assertEquals(4, tasker.getTask(4).getPriority());
		assertEquals("Homework 4", tasker.getTask(4).getName());
	}
	/**
	 * Testing the setTitle method.
	 */
	@Test
	public void testSetTitle() {
		TaskManager tasker = new TaskManager();
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> tasker.setTitle(null));
		assertEquals("Null title!", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> tasker.setTitle(""));
		assertEquals("Title can't be empty.", e2.getMessage());
	}
}
