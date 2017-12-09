package vm.exceptions;

import static org.junit.Assert.*;

import org.junit.Test;

public class InvalidDataInProductFileExceptionTest {

	@Test
	public void testGetError() {
		InvalidDataInProductFileException e = new InvalidDataInProductFileException();
		String value = e.getError();
		String expected = "Invalid data in product file.";
		
		assertTrue(expected.equals(value));
	}

	@Test
	public void testGetMessage() {
		InvalidDataInProductFileException e = new InvalidDataInProductFileException();
		String value = e.getMessage();
		String expected = "Invalid data in product file.";
		
		assertTrue(expected.equals(value));
	}

}
