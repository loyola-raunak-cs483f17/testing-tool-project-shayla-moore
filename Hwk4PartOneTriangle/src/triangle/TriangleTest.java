package triangle;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TriangleTest {

	Triangle negativeTri, lessSidesTri, equilateralTri, isocelesTri, scaleneTri, emptyTri, InvalidTri, triangle;
	


	@Before
	public void setUpBeforeClass() throws Exception {
		triangle = new Triangle();
		negativeTri = new Triangle();
		
		emptyTri = new Triangle();
		
		lessSidesTri = new Triangle();
		
		equilateralTri = new Triangle();
		
		isocelesTri = new Triangle();
		
		scaleneTri = new Triangle();
	}

	@After
	public void tearDownAfterClass() throws Exception {

		
		lessSidesTri = null;
		
		equilateralTri = null;
		
		isocelesTri = null;
		
		scaleneTri = null;
	}
	


	
	@Test
	public void testTriangle() {
		
		assertNotNull(emptyTri);
		assertNotNull(lessSidesTri);
		assertNotNull(equilateralTri);
		assertNotNull(isocelesTri);
		assertNotNull(scaleneTri);
		
	}

	@Test
	public void testHasValidSides() {
		
	int[] invalidSidesZero = {0, 0, 0};
	int[] invalidSidesNeg = {1, -2, 3};
	int[] validSides = {1, 1, 2};
	
	assertFalse(triangle.hasValidSides(invalidSidesZero));
	assertEquals(false, triangle.hasValidSides(invalidSidesNeg));
	assertEquals(true, triangle.hasValidSides(validSides));
	}

	@Test
	public void testIsValidTriangle() {
	
		int[] goodRatio = {1, 1, 1};
		int[] badRatio = {1, 2, 7};
		int[] negRatio = {-1, -2, 3};

		assertFalse(triangle.isValidTriangle(badRatio)); 
		assertTrue(triangle.isValidTriangle(goodRatio));
		assertFalse(triangle.isValidTriangle(negRatio)); 
	}

	@Test
	public void testIsEquilateral() {
		int[] equilateralTri = {1, 1, 1};
		int[] nonEquilateralTri = {3, 4, 5};
		
		assertTrue(triangle.isEquilateral(equilateralTri));
		assertFalse(triangle.isEquilateral(nonEquilateralTri));
	}

	@Test
	public void testIsIsosceles() {
		int[] isoscelesTri = {2, 2, 3};
		int[] nonisoscelesTri = {2, 2, 2};
		
		assertTrue(triangle.isIsosceles(isoscelesTri));
		assertFalse(triangle.isIsosceles(nonisoscelesTri));
	}

	@Test
	public void testIsScalene() {
		int[] scaleneTri = {4, 8, 12};
		int[] nonscaleneTri = {2, 2, 2};
		
		int[] nonscaleneTri2 = {1, 2, 2};
		

		
		assertTrue(triangle.isScalene(scaleneTri));
		assertFalse(triangle.isScalene(nonscaleneTri));
		
		assertFalse(triangle.isScalene(nonscaleneTri2));	//necessary for branch coverage
	}

}
