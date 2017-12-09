package vm.vending;
import java.io.*;
import java.util.*;

import vm.exceptions.*;
import vm.interfaces.IProductManager;

/**
 * This class handles the IO for the data.
 * @author Shmuel Lyss
 *
 */
public class ProductManager_DataFile implements IProductManager {
	private String _file= "";
	private Hashtable<String, Integer> data = null;
	
	/**
	 * Instantiates an instance of the Product manager, which deals primarily with handling the data access.
	 * @param file String path to where the data file resides
	 * @throws IOException
	 * @throws InvalidDataInProductFileException
	 */
	public ProductManager_DataFile(String file) throws IOException, InvalidDataInProductFileException{
		if(!new File(file).exists())
			throw new FileNotFoundException();
		else
			_file = file;
		data = getData(_file);
	}
	
	/** 
	 * @see Vending.IProductManager#getData()
	 */

	public Hashtable<String, Integer> getData(){
		return data;//I know this breaks OO best practices, but I'm too lazy to research Iterators in Java (and it's not a requirement for this homework)
	}
	
	/**
	 * @see Vending.IProductManager#productExists(java.lang.String)
	 */

	public boolean productExists(String product){
		return getData().get(product.toLowerCase())!=null;
	}
	/**
	 * This performs the actual IO on the file and retrieves the data to be put in the Hashtable.
	 * @param file String path to where the data file resides
	 * @return Hashtable<String, Integer>
	 * @throws IOException
	 * @throws InvalidDataInProductFileException
	 */
	private Hashtable<String, Integer> getData(String path) throws IOException, InvalidDataInProductFileException{
		Hashtable<String, Integer> table = new Hashtable<String, Integer>();
		DataInputStream in = new DataInputStream(new FileInputStream(path));
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		try{
			while ((strLine = br.readLine()) != null)
				table.putAll(getItemFromLine(strLine));
		}catch(InvalidDataInProductFileException e){
			in.close();
			throw new InvalidDataInProductFileException();
		}finally{
			in.close();
		}
		return table;
	}
	/**
	 * This takes in one line from the data file and converts it to a HashMap<String,Integer> item that 
	 * will be one element in the Hashmap data source.
	 * @param line One line in the data file (requires the format of: productName|productPriceInCents)
	 * @returnHashMap<String,Integer>
	 * @throws InvalidDataInProductFileException
	 */
	private Map<String, Integer> getItemFromLine(String line) throws InvalidDataInProductFileException{
		Map<String, Integer> m = new HashMap<String,Integer>();
		try{
			m.put(line.split("\\|")[0], Integer.valueOf(line.split("\\|")[1]));
		}catch(Exception ex){
			m = null;
			throw new InvalidDataInProductFileException();
		}
		return m;
	}
}
