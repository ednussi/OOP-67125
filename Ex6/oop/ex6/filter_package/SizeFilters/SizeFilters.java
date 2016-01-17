package oop.ex6.filter_package.SizeFilters;

import java.io.File;

import oop.ex6.filter_package.Filter;
import oop.ex6.filter_package.FilterExceptions;

/**
 * Abstract Class for all filters which use a value comparison logic in order
 * to filter by size.
 * @author owner
 */
public abstract class SizeFilters implements Filter {
	
	protected double _upperBound;
	protected double _lowerBound;
	protected double _comparingSize;

	/**
	 * SizeFilter constructor
	 * @param comparingSize - the size to compare to
	 * throws if comparingSizes are non negative doubles
	 * @throws FilterExceptions
	 */
	protected SizeFilters(double comparingSize) throws FilterExceptions{
		if (comparingSize < 0){
			throw new FilterExceptions();
		}
		_comparingSize = comparingSize;
	}
	
	/**
	 * SizeFilter constructor
	 * @param lowerBound - the lower boundary size limit
	 * @param upperBound - the upper boundary size limit
	 * throws if bounds are non negative doubles
	 * @throws FilterExceptions
	 */
	protected SizeFilters(double lowerBound, double upperBound) 
			throws FilterExceptions{
		if (lowerBound < 0 || upperBound < 0 || 
				lowerBound > upperBound){
			throw new FilterExceptions();
		}
		_upperBound = upperBound;
		_lowerBound = lowerBound;
	}
	
	@Override
	public abstract boolean isFiltered(File file);
}
