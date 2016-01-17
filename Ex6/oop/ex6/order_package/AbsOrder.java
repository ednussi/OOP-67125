package oop.ex6.order_package;

import java.io.File;

/**
 * Orders according to the absolute path
 * @author owner
 */
public class AbsOrder extends Order {
	
	/**
	 * AbsOrder Constructor
	 */
	public AbsOrder(){
	}

	@Override
	public int compare(File file1, File file2) {
		return defaultCompare(file1, file2);
	}
}
