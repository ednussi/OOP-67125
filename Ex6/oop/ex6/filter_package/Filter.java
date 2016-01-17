package oop.ex6.filter_package;

import java.io.File;

/**
 * A Basic Filter interface for all filters.
 * Has one shared method to all filters which is isFiltered?
 * @author ednussi
 */
public interface Filter {
	
	/**
	 * Checks If the file given is or is not of the filter criteria
	 * @param file - the file to check if meets the criteria
	 * @return true/false accordingly
	 */
	boolean isFiltered(File file);
}
