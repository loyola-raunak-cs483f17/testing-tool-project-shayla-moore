package vm.exceptions;

import static org.junit.Assert.*;

import org.junit.Test;

public class InvalidCoinExceptionTest {

	@Test
	public void testGetError() {
		InvalidCoinException e = new InvalidCoinException();
		String value = e.getError();
		String expected = "Invalid coins";

		assertTrue(expected.equals(value));
	}

	@Test
	public void testGetMessage() {
		InvalidCoinException e = new InvalidCoinException();
		String value = e.getMessage();
		String expected = "Invalid coins";

		assertTrue(expected.equals(value));
	}

}
