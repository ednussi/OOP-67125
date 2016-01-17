package oop.ex6.filter_package.BooleanFilters;

import java.io.File;

import oop.ex6.filter_package.FilterExceptions;

/**
 * Filters files which are hidden
 * @author owner
 */
public class HiddenFilter extends BooleanFilters{
	
	/**
	 * HiddenFilter constructor
	 * @param flag - "YES" or "NO" - if wants all the hidden or otherwise
	 * @throws FilterExceptions
	 */
	public HiddenFilter(String flag) throws FilterExceptions{
		super(flag);
	}
	
	@Override
	public boolean isFiltered(File file){
		if (_flag == YES){
			return file.isHidden();
		} else {
			return !file.isHidden();
		}
	}
}