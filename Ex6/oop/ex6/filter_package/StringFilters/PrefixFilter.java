package oop.ex6.filter_package.StringFilters;

import java.io.File;

/**
 * Filters files with the given prefix
 * @author owner
 */
public class PrefixFilter extends StringFilters{

	/**
	 * PrefixFilter constructor
	 * @param prefix - the prefix to match
	 */
	public PrefixFilter(String prefix){
		super(prefix);
	}
	
	@Override
	public boolean isFiltered(File file) {
		if (file.getName().startsWith(_testString)){
			return true;
		} else {
			return false;
			}
		}
	}
