package oop.ex6.order_package;

import java.io.File;

/**
 * Orders according to the size of the files starting from the biggest
 * In case are of same size - orders by default comparing method - 
 * by absolute path
 * @author owner
 */
public class SizeOrder extends Order {
	
	/**
	 * SizeOrder constructor
	 */
	public SizeOrder(){
	}
	
	@Override
	public int compare(File file1, File file2) {
		double file1Size = file1.length();
		double file2Size = file2.length();
		if (file1Size == file2Size){
			return defaultCompare(file1, file2);
		} else if (file1Size > file2Size){
			return DEFAULT_BIGGER_SIGN;
		} else {
			return DEFAULT_SMALLER_SIGN;
		}
	}
}
