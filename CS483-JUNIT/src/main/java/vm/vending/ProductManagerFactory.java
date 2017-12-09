/**
 * 
 */
package vm.vending;
import java.io.IOException;

import vm.interfaces.*;
import vm.exceptions.*;

/**
 * This is the factory that returns an instantiated ProductManager
 * @author Shmuel Lyss
 *
 */
public class ProductManagerFactory {
	private static boolean pathChanged=false; 
	private static IProductManager mgr = null;
	private static String _path = "";
	/**
	 * This returns a singleton of the same instantiation of an IProductManager class.
	 * This method can only be called if the setDataFilePath(String) method was called first, or
	 * the last time this factory was invoked in the current memory context it returned a valid class.
	 * @return IProductManager implementation
	 * @throws IOException
	 * @throws InvalidDataInProductFileException
	 */
	public static IProductManager Manager() throws IOException, InvalidDataInProductFileException{
		if(mgr==null||pathChanged)
			mgr = new ProductManager_DataFile(_path);
		pathChanged=false;//if it was true, it already served its purpose above.
		return mgr;
	}
	
	public static IProductManager Manager(String path) throws IOException, InvalidDataInProductFileException{
		setDataFilePath(path);
		return Manager();
	}
	public static void setDataFilePath(String path){
		pathChanged = _path.toLowerCase().trim()!=path.toLowerCase().trim();
		_path = path.trim();
	}
}
