package triangle;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class TriangleTest {

	@Test
	public void TestTriangles() throws IOException {
		// This is the output gained from the ACTS csv file
		//THIS IS FOR THE
	
		String fName = "C:\\Users\\Shayla\\eclipse-workspace\\TriangleACTSTest\\src\\triangle\\TriangleTests-output.csv";
		parseACTSCSV(fName);
		
		/**
		String fName2 = "C:\\Users\\Shayla\\eclipse-workspace\\TriangleACTSTest\\src\\triangle\\TriangleTests-output2.csv";
		parseACTSCSV(fName2);
		**/
	}

	public void parseACTSCSV(String fName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fName));
		String[] line;
		String sCurrentLine;
		
		//Reads the Heading Portion of the CSV file
		int[] sides = new int[3];
		for(int i=0;i < 7;i++)
		{
		    br.readLine();
		}
		int i = 7;
		while ((sCurrentLine = br.readLine()) != null) {

			line = sCurrentLine.split(",");
			sides[0] = Integer.parseInt(line[0]);
			sides[1] = Integer.parseInt(line[1]);
			sides[2] = Integer.parseInt(line[2]);
			
			TriangleHelper(sides);
			
			System.out.println(i);
		}
		br.close();
	}

	public void TriangleHelper(int[] sides) {
		System.out.print("Testing Sides: ");

		for (int i = 0; i < sides.length; i++) {
			System.out.print(sides[i] + ", ");
		}
		System.out.println();

		Triangle triangle = new Triangle();
		if (triangle.hasValidSides(sides)) {
			assertTrue(triangle.hasValidSides(sides));
			System.out.println("It has Valid Sides");
		} else {
			assertFalse(triangle.hasValidSides(sides));
			System.out.println("It does not have Valid Sides");
		}
		if (triangle.isEquilateral(sides)) {
			assertTrue(triangle.isEquilateral(sides));
			System.out.println("It is Equilateral");
		} else {
			assertFalse(triangle.isEquilateral(sides));
			System.out.println("It is not Equilateral");
		}
		if (triangle.isIsosceles(sides)) {
			assertTrue(triangle.isIsosceles(sides));
			System.out.println("It is Isosceles");
		} else {
			assertFalse(triangle.isIsosceles(sides));
			System.out.println("It is not Isosceles");
		}
		if (triangle.isScalene(sides)) {
			assertTrue(triangle.isScalene(sides));
			System.out.println("It is Scalene");
		} else {
			assertFalse(triangle.isScalene(sides));
			System.out.println("It is not Scalene");
		}
		System.out.println();
	}

}
