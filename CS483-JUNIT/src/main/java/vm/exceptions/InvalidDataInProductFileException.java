package vm.exceptions;

/**
 * This error should be thrown when the data in the product file is in an invalid format.
 * @author Shmuel Lyss
 *
 */
@SuppressWarnings("serial")
public class InvalidDataInProductFileException extends Exception {
	/*public InvalidDataInProductFileException(){
		super();
	}*/
	public String getError(){
		return "Invalid data in product file.";
	}
	public String getMessage(){
		return getError();
	}
}
