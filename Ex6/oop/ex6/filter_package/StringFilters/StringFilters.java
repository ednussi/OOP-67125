package oop.ex6.filter_package.StringFilters;

import java.io.File;

import oop.ex6.filter_package.Filter;

/**
 * An abstract Class for all string comparison based on strings
 * @author owner
 */
public abstract class StringFilters implements Filter {
	
	protected String _testString;

	/**
	 * StringFilters constructor
	 * @param testString - the String to be used for comparison
	 */
	public StringFilters(String testString) {
		_testString = testString;
	}

	@Override
	public abstract boolean isFiltered(File file);

}
