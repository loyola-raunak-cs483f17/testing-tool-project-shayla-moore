package vm.exceptions;

/**
 * This error should be thrown when an item is not in the current list of items or is not entered when coins are entered.
 * @author Shmuel Lyss
 *
 */
@SuppressWarnings("serial")
public class InvalidItemException extends Exception {
	public String getError(){
		return "Invalid item";
	}
	public String getMessage(){
		return getError();
	}
}
