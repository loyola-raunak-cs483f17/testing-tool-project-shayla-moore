package triangle;


/****
 * A java program to to check the validity of a triangle from its 3 given sides
 * 
 * Triangle provides methods validating the triangle.
 * 		- hasValidSides()
 * 		- isValidTriangle
 * 		- isEquilateral()
 * 		- isisosceles()
 * 		- isScalene()
 **/


public class Triangle {

	static int theSide = 0;		// tracks a particular side of a triangle
	
	public Triangle()  {
		// an empty constructor
	}
	
	
	/****
	 * hasValidSides()
	 * checks to see if the inputs provided for the sides if triangle are valid:
	 * 		i.e. all sides greater than 0
	 * @param int[] triangleSides; array holding the sides of the triangle
	 * @return true if the sides are valid; otherwise false
	 **/
	public boolean hasValidSides( int[] triangleSides )  {
		
		boolean valid = true;	// set "valid" triangle to true
		
		// check to see if any side of the triangle <= 0; if so, set valid to false
		for( theSide=0; theSide < triangleSides.length; theSide++ ) {
			if( triangleSides[theSide] <= 0 )
				valid = false;
		}
		
		return valid;
	}  // end hasValidSides()
	
	
	/****
	 * isValidTriangle()
	 * checks to see if the inputs create a valid triangle
	 * 		i.e. the sum of the lengths of the two smaller sides > then the largest side
	 *           (side1 + side2) > side3
	 * @param int[] triangleSides; array holding the sides of the triangle
	 * @return true if the triangle is valid; otherwise false
	 **/
	public boolean isValidTriangle( int[] triangleSides )  {
		
		boolean validTriangle = false;		// set validTriangle to false
		
		// check to see if the sum of the lengths of the two smaller 
		// sides > length of the largest side; if so, set validTriangle to true
		for( theSide=0; theSide<3 ; theSide++ ) {
			if( triangleSides[theSide] <= 0 )  {
				validTriangle = false;
				break;
			}  else  {	
				if( ( ( triangleSides[theSide%3] + triangleSides[(theSide+1)%3] ) 
						> triangleSides[(theSide+2)%3] ) )
					validTriangle = true;
				//The follow else statement was added to fix the program as it would accept any sides as valid
				else {
					validTriangle = false;
					break;
				}
			}
		}	
		
		return validTriangle;
	}  // end isValidTriangle()

	
	/****
	 * isEquilateral()
	 * checks to see if the triangle is an equilateral triangle
	 * 		i.e. all sides of the triangle are equal 
	 * 			 side1 = side2 = side3
	 * 		it can be induces that
	 * 			if side1 = side2 AND side2 = side3 => side1 = side3
	 @param int[] triangleSides; array holding the sides of the triangle
	 * @return true if the triangle is an equilateral; otherwise false
	 **/
	public boolean isEquilateral( int[] triangleSides )  {
		
		boolean equilateral = false;
		
		// check to see if all three sides of the triangle are of equal length
		// if so, set equilateral to true
		if( triangleSides[0] == triangleSides[1] && 
				triangleSides[1] == triangleSides[2] )
			equilateral = true;
		
		return equilateral;
	} // end isEquilateral()
	

	/****
	 * isisosceles()
	 * checks to see if the triangle is an isosceles triangle
	 * 		i.e. exactly two sides of the triangle are equal 
	 *           side1 = side2 OR side2 = side3 OR side3 = side1
	 * @param int[] triangleSides; array holding the sides of the triangle
	 * @return true if the triangle is an isosceles; otherwise false
	 **/
	public boolean isIsosceles( int[] triangleSides )  {
		
		boolean isosceles = false;
		
		// check to see if exactly two sides of the triangle are of equal length
		// if so, set isosceles to true
		for( theSide=0; theSide<3 ; theSide++ ) {
			if( !isEquilateral( triangleSides ) && ( triangleSides[theSide%3] 
					== triangleSides[(theSide+1)%3] ) )
				isosceles = true;
		}
		
		return isosceles;
	} // end isosceles()

	
	/****
	 * isScalene()
	 * checks to see if the triangle is an scalene triangle
	 * 		i.e. all sides of the triangle are of different lengths
	 * 			 side1 != side2 AND side1 != side3 AND side2 != side3
	 * @param int[] triangleSides; array holding the sides of the triangle
	 * @return true if the triangle is a scalene; otherwise false
	 **/
	public boolean isScalene( int[] triangleSides )  {

		boolean scalene = false;
		
		// check to see if all sides of the triangle are of different length
		// if so, set scalene to true
		if( (triangleSides[0] != triangleSides[1] ) && 
				( triangleSides[0] != triangleSides[2] ) && 		//This branch seems unreachable for me
					( triangleSides[1] != triangleSides[2] ) )
			scalene = true;
				
		return scalene;
	} // end isScalene()
	
	
}  // end chkTriangle class