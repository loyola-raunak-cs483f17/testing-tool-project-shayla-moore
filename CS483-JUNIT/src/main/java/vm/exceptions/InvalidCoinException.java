package vm.exceptions;

/**
 * This error should be thrown when someone inputs coins that do not exist or are in an incorrect format.
 * @author Shmuel Lyss
 *
 */
@SuppressWarnings("serial")
public class InvalidCoinException extends Exception {
	public String getError(){
		return "Invalid coins";
	}
	public String getMessage(){
		return getError();
	}
}
