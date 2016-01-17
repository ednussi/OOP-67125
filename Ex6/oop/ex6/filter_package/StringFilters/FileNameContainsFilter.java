package oop.ex6.filter_package.StringFilters;

import java.io.File;

/**
 * Filters Files which contains the given string
 * @author owner
 */
public class FileNameContainsFilter extends StringFilters{

	/**
	 * FileNameContainsFilter constructor
	 * @param stringContained
	 */
	public FileNameContainsFilter(String stringContained){
		super(stringContained);
	}
	
	@Override
	public boolean isFiltered(File file) {
		if (file.getName().contains(_testString)){
			return true;
		} else {
			return false;
			}
		}
	}
