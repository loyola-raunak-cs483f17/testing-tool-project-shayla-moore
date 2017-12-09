package vm.vending;

import vm.exceptions.*;
import vm.interfaces.Denomination;
import vm.interfaces.ICoin;
/**
 * The Coin implements ICoin and contains logic to set the denomination by char value.
 * @author Shmuel Lyss
 *
 */
public class Coin implements ICoin {

	Denomination _d = null;
	char charD = Character.UNASSIGNED;
	/**
	 * If this constructor is called, the setDenomination must be called to set the coin's denomination.
	 */
	public Coin(){
		_d = Denomination.None;
	}
	/**
	 * Instantiates an instance of a Coin with the set denomination that is resolved by the supplied character
	 * @param c A character that represents the denomination. Valid values are: h, q, d, n.
	 * @throws InvalidCoinException
	 */
	public Coin(char c) throws InvalidCoinException{
		setDenomination(c);
		charD = c;
	}
	/**
	 * Instantiates an instance of a Coin with the set denomination that is passed into the constructor.
	 * @param d A Denomination enumeration value
	 */
	public Coin(Denomination d){
		_d = d;
	}
	/**
	 * Returns the set denomination, or Denominatino.None if it is not yet set. 
	 */
	public Denomination getDenomination() {
		return _d;
	}
	/**
	 * Returns the char value that is resolved by the current denominiation.
	 * If the denomination is not yet set, it returns Character.UNASSIGNED.
	 */
	public char getCharValue(){
		char c = Character.UNASSIGNED;
		switch(getDenomination().Value()){//I have to use an integer value? Why doesn't Java let me use a switch statement directly on an enumeration?
			case 50:
				c = 'h';
				break;
			case 25:
				c = 'q';
				break;
			case 10:
				c = 'd';
				break;
			case 5:
				c = 'n';
				break;
		}//default: c=UNASSIGNED
		return c;
	}
	/**
	 * This sets the Denomination for the Coin.
	 * @param d A Denomination value. Setting this to Denomination.None may cause problems for yourself later.
	 */
	public void setDenomination(Denomination d) {
		_d = d;
	}
	/**
	 * This sets the denomination by taking in a character.
	 * @param c A character that will be resolved to a Denomination value or throw an InvalidCoinException.
	 * @throws InvalidCoinException
	 */
	public void setDenomination(char c) throws InvalidCoinException {
		switch(Character.toLowerCase(c)){
			case 'n':
				_d = Denomination.Nickel;
				break;
			case 'd':
				_d = Denomination.Dime;
				break;
			case 'q':
				_d = Denomination.Quarter;
				break;
			case 'h':
				_d = Denomination.Half;
				break;
			default:
					throw new InvalidCoinException();
		}
	}
	
	/**
	 * This returns an integer value of the current Denomination. Denomination.None will return 0. 
	 */
	public int getValue(){
		return (int)this.getDenomination().Value();
	}
	/**
	 * This makes sure the set Denomination is not Denominiatino.None (e.g. has a value greater than 0).
	 */
	public boolean isValid(){
		return this.getValue()>0;
	}

}
