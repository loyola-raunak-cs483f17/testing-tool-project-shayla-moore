package vm.interfaces;
/**
 * This Denomination enumeration consists of the following American coins with corresponding values:
 * Half - $0.50
 * Quarter - $0.25
 * Dime - $0.10
 * Nickel - $0.05
 * None - $0.00 - this will invalidate your calculator if inserted
 * @author Shmuel Lyss
 *
 */
public enum Denomination {
	None,
	Nickel,
	Dime,
	Quarter,
	Half;
	public int Value(){
		switch(this){
		case Nickel:
			return 5;
		case Dime:
			return 10;
		case Quarter:
			return 25;
		case Half:
			return 50;
		default:
				return 0;
		}
	}
};
