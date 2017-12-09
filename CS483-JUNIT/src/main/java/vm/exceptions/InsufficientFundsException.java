package vm.exceptions;

/**
 * This error should be thrown when someone tries to process an order with insufficient funds.
 * @author Shmuel Lyss
 *
 */
@SuppressWarnings("serial")
public class InsufficientFundsException extends Exception {
	public String getError(){
		return "Insufficient funds";
	}
	public String getMessage(){
		return getError();
	}
}
