package oop.ex6.order_package;

import java.io.File;

/**
 * Orders according to the type of the file by name
 * In case are of same size - orders by default comparing method - 
 * by absolute path
 * @author owner
 */
public class TypeOrder extends Order {
	
	/**
	 * TypeOrder Constructor
	 */
	public TypeOrder(){
	}

	@Override
	public int compare(File file1, File file2) {
		int compareVal = getType(file1).compareTo(getType(file2));
		if (compareVal == ARE_EQUAL){
			return defaultCompare(file1, file2);
		} else {
			return compareVal;
		}
	}
	
	/**
	 * A method which gets a file path and returns its type - using the logic
	 * that a file type is whatever is written after the last '.' in the name
	 * @param file - the file path
	 * @return - the file's type
	 */
	private String getType(File file){
		String[] fileName = file.getName().split("\\.");
		return fileName[fileName.length - 1];
	}

}
