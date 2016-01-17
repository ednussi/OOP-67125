package oop.ex6.filter_package.BooleanFilters;

import java.io.File;

import oop.ex6.filter_package.FilterExceptions;

/**
 * Filters files which are writable
 * @author owner
 */
public class WritableFilter extends BooleanFilters{
	
	/**
	 * WritableFilter constructor
	 * @param flag - "YES" or "NO" - if wants all the hidden or otherwise
	 * @throws FilterExceptions
	 */
	public WritableFilter(String flag) throws FilterExceptions{
		super(flag);
	}
	
	@Override
	public boolean isFiltered(File file){
		if (_flag == YES){
			return file.canWrite();
		} else {
			return !file.canWrite();
		}
	}
}