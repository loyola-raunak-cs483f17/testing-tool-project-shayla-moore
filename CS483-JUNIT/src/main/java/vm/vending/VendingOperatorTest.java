package vm.vending;

import org.junit.Rule;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.core.Is.is;

import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import vm.vending.VendingOperator;

public class VendingOperatorTest {

	@Test
	public void testMain() throws IOException {
		String fName = "C:\\Users\\Shayla\\eclipse-workspace\\CS483-JUNIT\\src\\main\\resources\\VendingMachine-output.csv";
		parseACTSCSV(fName);
	}

	public void parseACTSCSV(String fName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fName));
		String[] line;
		String sCurrentLine;

		// Reads the Heading Portion of the CSV file
		String[] vendMachine = new String[5];
		for (int i = 0; i < 7; i++) {
			br.readLine();
		}
		int i = 7;
		while ((sCurrentLine = br.readLine()) != null) {

			line = sCurrentLine.split(",");
			vendMachine[0] = line[0];
			vendMachine[1] = line[1];
			vendMachine[2] = line[2];
			vendMachine[3] = line[3];
			vendMachine[4] = line[4];

			System.out.println(vendMachine[0] + " " + vendMachine[1] + " " + vendMachine[2] + " " + vendMachine[3] + " "
					+ vendMachine[4]);
			

			
		}
		br.close();
	}

	@Test
	public void testGetDataFilePath() {
		// this will throw a null pointer exception if the file doesn't exist (although,
		// the documentation says it will just return an empty string?)
		String path = "";
		try {
			path = VendingOperator.class.getClassLoader().getResource("products.txt").getPath();
		} catch (Exception e) {
			// don't do anything. The empty path will throw an error when instantiating the
			// ProductManager
		}
		String expected = "/C:/Users/Shayla/eclipse-workspace/CS483-JUNIT/target/classes/products.txt";
		boolean isEqual = expected.equals(path);
		assertTrue(isEqual);

	}

}
