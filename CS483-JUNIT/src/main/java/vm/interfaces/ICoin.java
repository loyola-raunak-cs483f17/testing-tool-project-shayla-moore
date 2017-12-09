package vm.interfaces;
import vm.exceptions.*;

/**
 * This interface defines how coins must work.
 * The calculator should work correctly if you want to use a different currency.
 * @author Shmuel Lyss
 *
 */
public interface ICoin {
	Denomination getDenomination();
	char getCharValue();
	void setDenomination(Denomination d);
	void setDenomination(char d) throws InvalidCoinException;
	int getValue();
	boolean isValid();
}
