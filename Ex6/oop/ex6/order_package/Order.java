package oop.ex6.order_package;

import java.io.File;
import java.util.Comparator;

/**
 * An abstract class which represent a basic order.
 * An order is a way to arrange the given files according to a requested logic
 * Builds order in the way such that could be used by "Collections.sort" method
 * @author ednussi
 */
public abstract class Order implements Comparator<File>{
	
	// Initializing parameters
	protected final int DEFAULT_EQUAL_SIGN = 0, DEFAULT_BIGGER_SIGN = 1, 
			DEFAULT_SMALLER_SIGN = -1, ARE_EQUAL = 0;
	
	/**
	 * Default Constructor of an Order
	 */
	public Order(){
	}
	
	/**
	 * A Method which is the logic of the ordering to be used by 
	 * Collections.sort - return 0 if meets the same criteria, positive number
	 * if the first is bigger than the other, and negative if otherwise.
	 */
	public abstract int compare(File file1, File file2);
	
	/**
	 * Default comparing methods - 
	 * which compares by the absolute path of two given files
	 * @param file1 - first given file
	 * @param file2 - second given file
	 * @return 0 if equal, positive number if greater, 
	 * negative number if smaller
	 */
	protected int defaultCompare(File file1, File file2) {
		String file1Path = file1.getAbsolutePath();
		String file2Path = file2.getAbsolutePath();
		return file1Path.compareTo(file2Path);
	}	
}
