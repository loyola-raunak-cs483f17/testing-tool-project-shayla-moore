package vm.exceptions;

import static org.junit.Assert.*;

import org.junit.Test;

public class InvalidSetOfInputsExceptionTest {

	@Test
	public void testGetError() {
		InvalidSetOfInputsException e = new InvalidSetOfInputsException();
		String value = e.getError();
		String expected = "Invalid set of inputs";

		assertTrue(expected.equals(value));
	}

	@Test
	public void testGetMessage() {
		InvalidSetOfInputsException e = new InvalidSetOfInputsException();
		String value = e.getMessage();
		String expected = "Invalid set of inputs";

		assertTrue(expected.equals(value));
	}

}
