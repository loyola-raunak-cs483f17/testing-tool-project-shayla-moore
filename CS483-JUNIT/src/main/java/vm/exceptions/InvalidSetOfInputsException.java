package vm.exceptions;

/**
 * This should be thrown if someone doesn't enter any text when trying to use the vending machine.
 * @author Shmuel Lyss
 *
 */
@SuppressWarnings("serial")
public class InvalidSetOfInputsException extends Exception {
	public String getError(){
		return "Invalid set of inputs";
	}
	public String getMessage(){
		return getError();
	}
}
