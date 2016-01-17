package oop.ex6.filter_package.StringFilters;

import java.io.File;

/**
 * Filters files which match the given name
 * @author owner
 */
public class FileNameFilter extends StringFilters{

	/**
	 * FileNameFilter constructor
	 * @param fileName
	 */
	public FileNameFilter(String fileName){
		super(fileName);
	}
	
	@Override
	public boolean isFiltered(File file) {
		if (_testString.equals(file.getName())){
			return true;
		} else {
			return false;
			}
		}
	}
