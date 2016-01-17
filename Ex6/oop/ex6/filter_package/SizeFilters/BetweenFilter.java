package oop.ex6.filter_package.SizeFilters;

import java.io.File;
import oop.ex6.filter_package.FilterExceptions;

/**
 * Filters files which are between or equal size to the values given
 * @author owner
 */
public class BetweenFilter extends SizeFilters{

	/**
	 * BetweenFilter constructor
	 * @param value1 - first value
	 * @param value2 - second value
	 * @throws FilterExceptions
	 */
	public BetweenFilter(double lowerBound, double upperBound)
			throws FilterExceptions{
		super(lowerBound, upperBound);
	}
	
	@Override
	public boolean isFiltered(File file){
		double fileLength = file.length();
		if (fileLength <= _upperBound && fileLength >= _lowerBound){
			return true;
		} else {
			return false;
			}
		}
	}
