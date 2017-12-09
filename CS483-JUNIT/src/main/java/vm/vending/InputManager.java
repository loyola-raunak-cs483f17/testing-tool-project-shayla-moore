package vm.vending;
import java.util.*;

import vm.interfaces.*;
import vm.exceptions.*;

/**
 * This InputManager class acts as the main class that brings all other classes together.
 * It will act as the abstraction layer and call the necessary components under the hood.
 * Instantiation requires an instance of the ProductManager class, which handles products and costs.
 * @author Shmuel Lyss
 *
 */
public class InputManager{
	private IProductManager _mgr=null;
	private String strInput = "", strItem="";
	CoinCalculator calc = null;
	/**
	 * Instantiates the InputManager and sets the input from the user
	 * @param mgr ProductManager must be valid and instantiated. This follows a factory design pattern
	 * @param strUserInput The input read in from the user (e.g. 1h 1q pepsi). If empty, will throw InvalidSetOfInputsException
	 * @throws InvalidCoinException
	 * @throws InvalidItemException
	 * @throws InvalidSetOfInputsException 
	 */
	public InputManager(IProductManager mgr, String strUserInput) throws InvalidCoinException, InvalidItemException, InvalidSetOfInputsException{
		setObjects();
		_mgr = mgr;
		strInput = strUserInput;
		setInputs();
	}
	/**
	 * Instantiates the InputManager. This requires a call to the setUserInput method.
	 * @param mgr ProductManager must be valid and instantiated. This follows a factory design pattern
	 */
	public InputManager(IProductManager mgr){
		setObjects();
		_mgr = mgr;
	}
	/**
	 * This indicates the existence of the product in the data source. 
	 * @return Boolean value indicated the existence of the product in the data source
	 */
	public boolean ItemIsValid(){
		return _mgr.productExists(strItem);
	}
	/**
	 * This takes in the user input and sets the appropriate values. 
	 * @param strUserInput The input read in from the user (e.g. 1h 1q pepsi). If empty, will throw InvalidSetOfInputsException
	 * @throws InvalidCoinException
	 * @throws InvalidItemException
	 * @throws InvalidSetOfInputsException
	 */
	public void setUserInput(String strUserInput) throws InvalidCoinException, InvalidItemException, InvalidSetOfInputsException{
		strInput = strUserInput;
		setInputs();
	}
	/**
	 * If this returns a positive integer, it is how much more your owe for the given product.
	 * If this returns a negative integer, it is the change you will if your purchase this item.
	 * @return positive or negative integer indicationg ItemPrice - TotalInputValue
	 */
	public int MoneyShort() throws InvalidItemException{
		if(!ItemIsValid())
			throw new InvalidItemException();
		return _mgr.getData().get(strItem)-calc.TotalValue();
	}
	/**
	 * If supplied sufficient funds, this clears input values and returns a List of ICoins that contain the set of change.
	 * @return List<ICoin> containing the change for the processed order
	 * @throws InvalidItemException
	 * @throws InsufficientFundsException
	 */
	public List<ICoin> ProcessOrder() throws InvalidItemException, InsufficientFundsException{
		int moneyShort = MoneyShort();
		if(moneyShort>0)
			throw new InsufficientFundsException();
		calc.Clear();
		return getReversedBrokenDownChangeValues(-moneyShort);//the "-" will convert to a positive for change
	}
	/**
	 * This takes in the amount of change to process then calls the function to get the proper change and reverses 
	 * the list to be in ascending order.
	 * @param change A non-negative integer indicating the user's change form their processed order that is 
	 * to be broken down into the fewest amount of coins.
	 * @return
	 */
	private List<ICoin> getReversedBrokenDownChangeValues(int change){
		List<ICoin> lst = breakdownValue(change);
		Collections.reverse(lst);
		return lst;
	}
	/**
	 * Recursive function to get change in descending Denomination.
	 * @param value
	 * @return
	 */
	private List<ICoin> breakdownValue(int value){
		List<ICoin> change = new ArrayList<ICoin>();
		Denomination d = getNextDenomination(value);
		if(d != Denomination.None)
		{
			change.add(new Coin(d));
			value-=d.Value();
			change.addAll(breakdownValue(value));//recursion
		}
		return change;
	}
	/**
	 * This determines the next largest denomination for the given input.
	 * @param value Integer value indicating the desire to retrieve the largest coin Denominiation based on this value.
	 * @return Largest Denomination value for the given input 
	 */
	private Denomination getNextDenomination(int value){
		Denomination d;
		if(value>=50)
			d = Denomination.Half;
		else if(value>=25)
			d = Denomination.Quarter;
		else if(value>=10)
			d = Denomination.Dime;
		else if(value>=5)
			d = Denomination.Nickel;
		else
			d = Denomination.None;
		return d;
	}
	/**
	 * Central place for internal object instantiation.
	 */
	private void setObjects(){
		calc = new CoinCalculator();
	}
	/**
	 * Internal function that sets the appropriate values for  the user's input.
	 * If this class has already been used and instantiated, it will clear out all of the values.
	 * @throws InvalidCoinException
	 * @throws InvalidItemException
	 * @throws InvalidSetOfInputsException
	 */
	private void setInputs() throws InvalidCoinException, InvalidItemException, InvalidSetOfInputsException{
		if(strInput.trim().equals(""))
			throw new InvalidSetOfInputsException();
		calc.Clear();
		String[] inputs = cleanArray(strInput.split(" "));
		if(inputs.length>0){
			strItem = inputs[inputs.length-1];
			if(!_mgr.productExists(strItem))
				throw new InvalidItemException();
		}
		for(int i=0;i<inputs.length-1;i++)
			calc.AddCoin(setCoin(inputs[i]), getNumberFromInput(inputs[i]));
	}
	/**
	 * This takes in what should be one coin entry. E.g. "1q" or "2d"
	 * This reads the last character of a coin input and instantiates/returns an instance of a Coin.
	 * @param input
	 * @return Coin implementation of ICoin
	 * @throws InvalidCoinException
	 */
	private ICoin setCoin(String input) throws InvalidCoinException{
		return new Coin(input.toCharArray()[input.length()-1]);
	}
	/**
	 * This takes in an array and returns an array after removing all of
	 * the input's empty or null items.
	 * @param arr Array of strings that may contain null or empty values
	 * @return the same String array without all of the empty elements
	 */
	private String[] cleanArray(String[] arr){
		List<String> stringList = new ArrayList<String>();  
		for(String string : arr)    
			if(string != null && string.trim().length() > 0)       
				stringList.add(string);
		return stringList.toArray(new String[stringList.size()]); 
	}
	/**
	 * This takes in the string value of a coin and extracts the count for that coin.
	 * The count must be a valid integer greater than 0.
	 * @param input String value for a coin (e.g. 1q or 3d)
	 * @return If passed in "3d" this will return: 3
	 * @throws InvalidCoinException
	 */
	private Integer getNumberFromInput(String input) throws InvalidCoinException{
		Integer num = 0;
		try{
			num = Integer.parseInt(input.substring(0, input.length()-1));
		}catch(NumberFormatException e){
			throw new InvalidCoinException();
		}
		if(num.equals(0))
			throw new InvalidCoinException();
		return num;
	}
}