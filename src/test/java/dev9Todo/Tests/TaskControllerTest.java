/**
 * Tests for TaskController class methods
 */
package dev9Todo.Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dev9Todo.Priority;
import dev9Todo.Task;
import dev9Todo.TaskController;

/**
 * @author David Krumm
 *
 */
public class TaskControllerTest {

	private TaskController controller;

	/*
	 * Initialize / set up data for tests
	 */
	@Before
	public void setUp() throws Exception {

		// create new controller with no data
		controller = new TaskController();

		// add first task for validation
		Task t = new Task(0, "Test task", Priority.LOW, false, "This is only a test");

		controller.putTask(t);
	}

	/**
	 * Test method for {@link dev9Todo.TaskController#getTask(long)}. Validates
	 * getting a Task by id
	 * 
	 */
	@Test
	public void testGetTask() {

		Task task = controller.getTask(1);
		assertNotNull(task);
		assertSame(task.getName(), "Test task", task.getName());
		assertEquals(task.getId(), 1);
		assertFalse(task.getIsComplete());
		assertSame(task.getPriority(), Priority.LOW, task.getPriority());
		assertSame(task.getNotes(), "This is only a test");

	}

	/**
	 * Test method for {@link dev9Todo.TaskController#putTask(dev9Todo.Task)}.
	 */
	@Test
	public void testPutTask() {

		Task t = new Task(0, "Get groceries", Priority.HIGH, false, "Pick up groceries on the ways home");

		long taskId = controller.putTask(t);

		Task taskToVerify = controller.getTask(taskId);
		assertNotNull(taskToVerify);
		assertSame(taskToVerify.getName(), "Get groceries", taskToVerify.getName());
		assertEquals(taskToVerify.getId(), 1);
		assertFalse(taskToVerify.getIsComplete());
		assertSame(taskToVerify.getPriority(), Priority.HIGH, taskToVerify.getPriority());
		assertSame(taskToVerify.getNotes(), "Pick up groceries on the ways home");

	}

	/*
	 * Test method for {@link
	 * dev9Todo.TaskController#deleteTask(long)}.
	 */
	@Test
	public void testDeleteTask() {

		Task t = new Task(0, "Task To Delete", Priority.LOW, false, "This will be deleted after test");
		long taskId = controller.putTask(t);
		Task deletedTask = controller.getTask(taskId);
		assertNotNull(deletedTask); //that we actually got a task back
		
		controller.deleteTask(taskId);
		deletedTask = controller.getTask(taskId);
		assertNull(deletedTask);  //that the task has been removed

	}
	
	/*
	 * Test method for (@link dev9Todo.TaskController#updateTask(long, boolean)
	 */
	@Test
	public void testUpdateTask(){
		Task t = new Task(0, "Task To Delete", Priority.LOW, false, "This will be deleted after test");
		long taskId = controller.putTask(t);
		
		controller.updateTask(taskId, true);
		Task updatedTask = controller.getTask(taskId);
		assertTrue(updatedTask.getIsComplete());

	}
}
