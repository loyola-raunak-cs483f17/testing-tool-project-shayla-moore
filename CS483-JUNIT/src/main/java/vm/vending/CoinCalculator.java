package vm.vending;
import java.util.*;

import vm.interfaces.ICoin;

/**
 * The CoinCalculator simply takes in coins, can verify that each coin has a set denomination and calculates 
 * the total value of all of the coins in the calculator.
 * @author Shmuel Lyss
 *
 */
public class CoinCalculator {
	List<ICoin> list = null;
	
	public CoinCalculator(){
		list = new ArrayList<ICoin>();
	}
	/**
	 * Adds a Coin to the calculator.
	 * @param coin Any instantiated implementation of the ICoin interface
	 */
	public void AddCoin(ICoin coin){
		list.add(coin);
	}
	/**
	 * Add 1-N amount of coins to the calculator.
	 * @param coin Any instantiated implementation of the ICoin interface
	 * @param count the number of the supplied coins you would like to add to the calculator
	 */
	public void AddCoin(ICoin coin, int count){
		for(int i=0;i<count;i++)
			this.AddCoin(coin);
	}
    /**
     * This returns the total value of all the coins in the calculator.
     * @return Integer value of all coins in calculator
     */
    public int TotalValue(){
    	int val = 0;
    	for(int i=0;i<list.size();i++)
    		val+=list.get(i).getValue();
    	return val;
    }
    /**
     * This indicates whether all of the coins in the calculator have a value greater than 0.
     * @return boolean value indicating that all coins in the calculator are valid
     */
    public boolean IsValid(){
    	boolean valid = true;
    	int ndx = 0;
    	while(valid && ndx < list.size())
    		valid = list.get(ndx++).isValid();
    	return valid;
    }
	/**
	 * This empties all coins from the calculator.
	 */
    public void Clear(){
    	list.clear();
    }
}
