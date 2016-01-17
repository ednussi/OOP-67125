package oop.ex6.filter_package.BooleanFilters;

import java.io.File;
import oop.ex6.filter_package.Filter;
import oop.ex6.filter_package.FilterExceptions;

/**
 * Abstract Class for all filters which use a boolean logic of meeting a 
 * criteria
 * @author owner
 */
public abstract class BooleanFilters implements Filter {
	
	protected final int YES = 1, NO = 2;
	protected int _flag;

	/**
	 * BooleanFilters constructor 
	 * @throws FilterExceptions 
	 */
	public BooleanFilters(String flag) throws FilterExceptions {
		_flag = checkFlag(flag);
	}

	@Override
	public abstract boolean isFiltered(File file);
	
	/**
	 * Checks if the string enters is "YES" or "NO" and return if so - 
	 * otherwise throw filterException
	 * @param flag - the checked String
	 * @return what the flag contained
	 * @throws FilterExceptions
	 */
	protected int checkFlag(String flag) throws FilterExceptions{
		if (flag.equals("YES")){
			return YES;
		} else if (flag.equals("NO")){
			return NO;
		} else {
			throw new FilterExceptions();
		}
	}
}
