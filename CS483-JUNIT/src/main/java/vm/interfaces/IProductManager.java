package vm.interfaces;

import java.util.Hashtable;
/**
 * This interface should be used to abstract the data source from the concrete manager and implementation.
 * @author Shmuel Lyss
 */
public interface IProductManager {
	/**
	 * Hashtable set of productName/productPrice 
	 * @return Hashtable<String, Integer>
	 */
	public abstract Hashtable<String, Integer> getData();
	/**
	 * This is a convenient way of determining whether the product exists, without requiring the user's
	 * to handle any return objects
	 * @param product String value of a product name that may or may not be in the data set
	 * @return Boolean value indicating the product's existence in the data set
	 */
	public abstract boolean productExists(String product);

}