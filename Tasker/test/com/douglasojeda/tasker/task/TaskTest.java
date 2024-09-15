package com.douglasojeda.tasker.task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * Tests for the Task class.
 * @author douglasojeda
 */
public class TaskTest {
	/**
	 * Testing the Task constructor.
	 */
	@Test
	public void testConstructor() {
		//Checking normal flow
		Task t1 = new Task(1, "Read Chapter 1");
		assertEquals(1, t1.getPriority());
		assertEquals("Read Chapter 1", t1.getName());
		Task t2 = new Task(563, "Homework 34");
		assertEquals(563, t2.getPriority());
		assertEquals("Homework 34", t2.getName());
		//Checking error state
		assertThrows(IllegalArgumentException.class, () -> new Task(0, "Read Chapter 1"));
		assertThrows(IllegalArgumentException.class, () -> new Task(-1, "Read Chapter 1"));
		assertThrows(IllegalArgumentException.class, () -> new Task(-5, "Read Chapter 1"));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, null));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, ""));
	}
	/**
	 * Testing the equals and hashCode methods.
	 */
	@Test
	public void testEquals() {
		//Checking equals
		Task t1 = new Task(1, "Read Chapter 1");
		assertEquals(1, t1.getPriority());
		Task t2 = new Task(1, "Read Chapter 1");
		assertEquals(1, t1.getPriority());
		Task t3 = new Task(2, "Read Chapter 1");
		assertEquals(1, t1.getPriority());
		Task t4 = new Task(1, "Read Chapter 2");
		assertEquals(1, t1.getPriority());
		Task t5 = new Task(3, "Read Chapter 4");
		assertEquals(1, t1.getPriority());
		assertTrue(t1.equals(t1));
		assertTrue(t1.equals(t2));
		assertTrue(t2.equals(t1));
		assertFalse(t1.equals(t3));
		assertFalse(t1.equals(t4));
		assertFalse(t1.equals(t5));
		//Checking hash code
		assertEquals(t1.hashCode(), t2.hashCode());
		assertNotEquals(t1.hashCode(), t3.hashCode());
		assertNotEquals(t1.hashCode(), t4.hashCode());
		assertNotEquals(t1.hashCode(), t5.hashCode());
	}
	/**
	 * Testing the toString method.
	 */
	@Test
	public void testToString() {
		//Checking toString
		Task t1 = new Task(1, "Read Chapter 1");
		assertEquals("1,Read Chapter 1", t1.toString());
	}
}
