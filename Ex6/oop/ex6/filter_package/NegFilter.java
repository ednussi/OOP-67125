package oop.ex6.filter_package;

import java.io.File;

/**
 * A filter decorator 
 * Filters according to the opposite logic of the given filter
 * @author owner
 */
public class NegFilter implements Filter{

	private Filter _filterToNeg;
	
	/**
	 * NegFilter constructor
	 * @param filterToNeg - the filter to negate its logic
	 */
	public NegFilter(Filter filterToNeg){
		_filterToNeg = filterToNeg;
	}
	
	@Override
	public boolean isFiltered(File file){
		return !_filterToNeg.isFiltered(file);
		}
	}
