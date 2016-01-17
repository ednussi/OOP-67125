package oop.ex6.filter_package;

import java.io.File;

/**
 * Filters all files
 * @author owner
 */
public class AllFilter implements Filter{
	
	/**
	 * AllFilter constructor
	 */
	public AllFilter(){
	}
	
	@Override
	public boolean isFiltered(File file) {
			return true;
	}
}
