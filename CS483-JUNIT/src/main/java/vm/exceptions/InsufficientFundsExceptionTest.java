package vm.exceptions;

import static org.junit.Assert.*;

import org.junit.Test;

public class InsufficientFundsExceptionTest {

	@Test
	public void testGetError() {
		InsufficientFundsException e = new InsufficientFundsException();
		String message = e.getError();
		String expected = "Insufficient funds";
		assertTrue(expected.equals(message));
	}

	@Test
	public void testGetMessage() {
		InsufficientFundsException e = new InsufficientFundsException();
		String message = e.getMessage();
		String expected = "Insufficient funds";
		assertTrue(expected.equals(message));
	}

}
