package oop.ex6.filter_package.BooleanFilters;

import java.io.File;

import oop.ex6.filter_package.FilterExceptions;

/**
 * Filters files which has executable property
 * @author owner
 */
public class ExecutableFilter extends BooleanFilters{
	
	/**
	 * ExecutableFilter constructor
	 * @param flag - "YES" or "NO" - if wants all the hidden or otherwise
	 * @throws FilterExceptions
	 */
	public ExecutableFilter(String flag) throws FilterExceptions{
		super(flag);
	}

	@Override
	public boolean isFiltered(File file){
		if (_flag == YES){
			return file.canExecute();
		} else {
			return !file.canExecute();
		}
	}
}