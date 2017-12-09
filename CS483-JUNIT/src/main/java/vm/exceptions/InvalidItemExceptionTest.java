package vm.exceptions;

import static org.junit.Assert.*;

import org.junit.Test;

public class InvalidItemExceptionTest {

	@Test
	public void testGetError() {
		InvalidItemException e = new InvalidItemException();
		String value = e.getError();
		String expected = "Invalid item";

		assertTrue(expected.equals(value));
	}

	@Test
	public void testGetMessage() {
		InvalidItemException e = new InvalidItemException();
		String value = e.getMessage();
		String expected = "Invalid item";

		assertTrue(expected.equals(value));
	}

}
