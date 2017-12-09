package vm.vending;

import static org.junit.Assert.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import vm.vending.Coin;
import vm.exceptions.InsufficientFundsException;
import vm.exceptions.InvalidCoinException;
import vm.exceptions.InvalidDataInProductFileException;
import vm.exceptions.InvalidItemException;
import vm.exceptions.InvalidSetOfInputsException;
import vm.interfaces.IProductManager;
import vm.interfaces.Denomination;
import vm.interfaces.ICoin;
public class InputManagerTest {
	IProductManager _mgr;
	InputManager inputManager;
	String userInput, path;
	List<ICoin> coinList;
	@Test
	public void testInputManagerIProductManagerString() throws InvalidCoinException, InvalidItemException,
			InvalidSetOfInputsException, IOException, InvalidDataInProductFileException {

		userInput = "1h 1q pepsi";
		path = VendingOperator.class.getClassLoader().getResource("products.txt").getPath();
		_mgr = ProductManagerFactory.Manager(path);// instantiate an instance of an IProductManager

		inputManager = new InputManager(_mgr, userInput);
		assertNotNull(inputManager);
	}

	@Test(expected = InvalidItemException.class)
	public void testInputManagerIProductManagerStringInvalidItem() throws InvalidCoinException, InvalidItemException,
			InvalidSetOfInputsException, IOException, InvalidDataInProductFileException {

		userInput = "1h 1q bepsi";
		path = VendingOperator.class.getClassLoader().getResource("products.txt").getPath();
		_mgr = ProductManagerFactory.Manager(path);// instantiate an instance of an IProductManager

		inputManager = new InputManager(_mgr, userInput);
	}

	@Test(expected = InvalidCoinException.class)
	public void testInputManagerIProductManagerStringInvalidCoin() throws InvalidCoinException, InvalidItemException,
			InvalidSetOfInputsException, IOException, InvalidDataInProductFileException {

		userInput = "1h 1z pepsi";
		path = VendingOperator.class.getClassLoader().getResource("products.txt").getPath();
		_mgr = ProductManagerFactory.Manager(path);// instantiate an instance of an IProductManager

		inputManager = new InputManager(_mgr, userInput);
	}

	@Test(expected = InvalidSetOfInputsException.class)
	public void testInputManagerIProductManagerStringInvalidInput() throws InvalidCoinException, InvalidItemException,
			InvalidSetOfInputsException, IOException, InvalidDataInProductFileException {

		userInput = "";
		path = VendingOperator.class.getClassLoader().getResource("products.txt").getPath();
		_mgr = ProductManagerFactory.Manager(path);// instantiate an instance of an IProductManager

		inputManager = new InputManager(_mgr, userInput);
	}

	@Test(expected = InvalidDataInProductFileException.class)
	public void testInputManagerIProductManagerStringInvalidPFile() throws InvalidCoinException, InvalidItemException,
			InvalidSetOfInputsException, IOException, InvalidDataInProductFileException {
		
		userInput = "1h 1q pepsi";
		path = VendingOperator.class.getClassLoader().getResource("products2.txt").getPath();
		_mgr = ProductManagerFactory.Manager(path);// instantiate an instance of an IProductManager

		inputManager = new InputManager(_mgr, userInput);
	}

	@Test
	public void testInputManagerIProductManager() throws IOException, InvalidDataInProductFileException {
		path = VendingOperator.class.getClassLoader().getResource("products.txt").getPath();
		
		
			_mgr = ProductManagerFactory.Manager(path);
	
		
		inputManager = new InputManager(_mgr);
		assertNotNull(inputManager);		
	}

	@Test
	public void testItemIsValid() throws IOException, InvalidDataInProductFileException, InvalidCoinException, InvalidItemException, InvalidSetOfInputsException {
		userInput = "1h 1q pepsi";
		path = VendingOperator.class.getClassLoader().getResource("products.txt").getPath();
		_mgr = ProductManagerFactory.Manager(path);
		
		boolean testValid = _mgr.productExists("pepsi");
		
		inputManager = new InputManager(_mgr, userInput);
		boolean valid = inputManager.ItemIsValid();
		assertTrue(valid);
		assertTrue(testValid);
	}
	
	@Test
	public void testSetUserInput() throws InvalidCoinException, InvalidItemException, InvalidSetOfInputsException, IOException, InvalidDataInProductFileException{
		userInput = "1h 1q pepsi";
		path = VendingOperator.class.getClassLoader().getResource("products.txt").getPath();
		_mgr = ProductManagerFactory.Manager(path);
		inputManager = new InputManager(_mgr);
		inputManager.setUserInput(userInput);
		
	}

	/**
	@throws InsufficientFundsException 
	 * @throws InvalidItemException 
	 * @throws InvalidSetOfInputsException 
	 * @throws InvalidCoinException 
	 * @throws InvalidDataInProductFileException 
	 * @throws IOException 
	 * @Test
	public void testSetUserInput() {
		fail("Not yet implemented");
	}
	**/

	@Test
	public void testMoneyShort() throws InvalidItemException, InsufficientFundsException, InvalidCoinException, InvalidSetOfInputsException, IOException, InvalidDataInProductFileException {
		userInput = "2q pepsi";
		path = VendingOperator.class.getClassLoader().getResource("products.txt").getPath();
		_mgr = ProductManagerFactory.Manager(path);
		

		
		inputManager = new InputManager(_mgr, userInput);
		
		int oweMoney = inputManager.MoneyShort();
		int oweMoneyTest = 25;
		assertEquals(oweMoney, oweMoneyTest);
		
		
	}

	@Test(expected = InvalidItemException.class)
	public void testMoneyShortInvalidItem() throws InvalidItemException, InsufficientFundsException, InvalidCoinException, InvalidSetOfInputsException, IOException, InvalidDataInProductFileException {
		userInput = "1h 1q bepsi";
		path = VendingOperator.class.getClassLoader().getResource("products.txt").getPath();
		_mgr = ProductManagerFactory.Manager(path);
		
	
		
		inputManager = new InputManager(_mgr, userInput);
		
		int oweMoney = inputManager.MoneyShort();

		
	}
	
	@Test
	public void testProcessOrder() throws IOException, InvalidDataInProductFileException, InvalidItemException, InsufficientFundsException, InvalidCoinException, InvalidSetOfInputsException {
		userInput = "2h 1q 1d 1n pepsi";
		path = VendingOperator.class.getClassLoader().getResource("products.txt").getPath();
		_mgr = ProductManagerFactory.Manager(path);
		inputManager = new InputManager(_mgr, userInput);

		coinList = inputManager.ProcessOrder();
	
		
		int val = 0;
    	for(int i=0;i<coinList.size();i++)
    	{
    		val+=coinList.get(i).getValue();
    		
    	}
    	int expected = 65;
		
		assertEquals(val, expected);

		String userInput2 = "2h pepsi";
		
		_mgr = ProductManagerFactory.Manager(path);
		inputManager = new InputManager(_mgr, userInput2);
	
		coinList = inputManager.ProcessOrder();
	
		
		int val2 = 0;
    	for(int i=0;i<coinList.size();i++)
    	{
    		val2+=coinList.get(i).getValue();
    		
    	}
    	int expected2 = 25;
		
		assertEquals(val2, expected2);
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void testProcessOrderInsufficientFunds() throws IOException, InvalidDataInProductFileException, InvalidItemException, InsufficientFundsException, InvalidCoinException, InvalidSetOfInputsException {
		userInput = "2q pepsi";
		path = VendingOperator.class.getClassLoader().getResource("products.txt").getPath();
		_mgr = ProductManagerFactory.Manager(path);
		inputManager = new InputManager(_mgr, userInput);
		coinList = inputManager.ProcessOrder();
		
	}


}
