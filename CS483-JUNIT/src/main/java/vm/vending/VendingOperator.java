package vm.vending;

import java.io.*;
import java.util.*;
import vm.interfaces.*;

/**
 * The VendingOperator class is the main runner for the vending machine.
 * This class deals with all the user interactions and outputs. 
 * @author Shmuel Lyss
 *
 */
public class VendingOperator {
	private static IProductManager p;
	private static Scanner in = new Scanner(System.in);
	/**
	 * The passed in arguments are not analyzed.
	 * @param args
	 */
	public static void main(String[] args) {
		p = getProductManager(GetDataFilePath());//instantiate an instance of an IProductManager
		if(p!=null)
			doWork();
	}
	/**
	 * This is where the main work/loop is performed.
	 * What? Do YOU have a better name for it?!?
	 */
	private static void doWork(){
		print("Hi, and welcome to the Buy-A-Beverage!\n");
		InputManager inputMgr = new InputManager(p);
		String strInput = "menu";
		do{
			if(strInput.equals("menu")){
				printProductList(p.getData().keySet());
			}else{
				try{
					inputMgr.setUserInput(strInput);//this does all the initial work
					if(inputMgr.MoneyShort()>0)
						print(String.valueOf(inputMgr.MoneyShort()));
					else//there is enough
						printChange(inputMgr.ProcessOrder());
				}
				catch(Exception e){//catches: InvalidSetOfInputsException, InvalidCoinException, InvalidItemException,InsufficientFundsException
					print(e.getMessage());//polymorphic exception handling. Is this acceptable in Java?
				}
			}
			print("\nPlease enter values in the form of (\"menu\" for product list, \"exit\" to exit) 2q 1d coke:\n");
			strInput = in.nextLine().toLowerCase().trim();
		}while(!strInput.toLowerCase().equals("exit"));
	}
	/**
	 * This uses Reflection (if that's what it's called in Java?) to get the current class' location and return a resource file in its context.
	 * @return String value with a full path to the products.txt file located in the src folder for this application.
	 */
	public static String GetDataFilePath(){
		//this will throw a null pointer exception if the file doesn't exist (although, the documentation says it will just return an empty string?)
		String path = "";
		try{
			path = VendingOperator.class.getClassLoader().getResource("products.txt").getPath();
			
			//String HugeString = "Hi, and welcome to the Buy-A-Beverage!\r\n\r\n\r\nThe following are our products and prices:\r\n\r\nProduct: \"pepsi\", cost: $0.75\r\nProduct: \"water\", cost: $0.25\r\nProduct: \"sprite\", cost: $0.50\r\nProduct: \"juice\", cost: $0.35\r\nProduct: \"coke\", cost: $0.60\r\n\r\nPlease enter values in the form of (\"menu\" for product list, \"exit\" to exit) 2q 1d coke:\r\n\r\n60\r\n\r\nPlease enter values in the form of (\"menu\" for product list, \"exit\" to exit) 2q 1d coke:";
			
			//System.out.println(HugeString);
		}catch(Exception e){
			//don't do anything. The empty path will throw an error when instantiating the ProductManager
		}
		return path;
	}
	/**
	 * This calls the ProductManagerFactory to get a concrete implementation of the IProductManager class
	 * @param path The full path to the products.txt data file.
	 * @return If the path is resolved, this returns a concrete implementation of the IProductManager class. If not, it returns null.
	 */
	private static IProductManager getProductManager(String path){
		IProductManager mgr = null;
		try {
			mgr = ProductManagerFactory.Manager(path);
		}
		catch(FileNotFoundException e){//for some reason this message for this exception is null
			print(e.getMessage());
		}
		catch(Exception e){//catches: IOException, InvalidDataInProductFileException
			print(e.getMessage()); //polymorphic exception handling. Is this acceptable in Java?
		}
		return mgr;
	}
	/**
	 * This prints the change out to the console in the proper format
	 * @param change
	 */
	private static void printChange(List<ICoin> change){
		if(change.size()==0)
			print("0");
		else{
			Map<Character, Integer> lstToPrint = new HashMap<Character, Integer>();
			Character tmpChar;
			String tmpStr = "";
			//First) Build a string that may look like: Xn Xq Xh and set the correct count of each coin
			for(int i=0;i<change.size();i++)
			{
				tmpChar = change.get(i).getCharValue();
				if(lstToPrint.containsKey(tmpChar))
					lstToPrint.put(tmpChar, lstToPrint.get(tmpChar)+1);//increment
				else{
					tmpStr+="X"+String.valueOf(tmpChar)+ " ";//the "x's" will be replaced later
					lstToPrint.put(tmpChar, 1);
				}
			}
			//Second) Replace the "X" values with the appropriate coin count
			for(Character c: lstToPrint.keySet())
				tmpStr = tmpStr.replaceAll("X"+String.valueOf(c), String.valueOf(lstToPrint.get(c))+ String.valueOf(c));
			print(tmpStr.trim());
		}
	}
	/**
	 * This prints out the menu with the available product list and associated price for each item.
	 * @param data A Set<String> of the names of each product
	 */
	private static void printProductList(Set<String> data){
		print("\nThe following are our products and prices:\n");
		for(String product: data)
			print("Product: \"" + product + "\", cost: $0."+ p.getData().get(product));
	}
	/**
	 * This is just a method that prints out a string to the console.
	 * It saves a few keystrokes every time I want to print something and it also allows 
	 * for a quick swap if I change the output device.
	 * @param str
	 */
	private static void print(String str){
		System.out.println(str);
	}
}
