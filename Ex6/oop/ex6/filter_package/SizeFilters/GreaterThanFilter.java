package oop.ex6.filter_package.SizeFilters;

import java.io.File;
import oop.ex6.filter_package.FilterExceptions;

/**
 * Filters files which are of greater size than the given value
 * @author owner
 */
public class GreaterThanFilter extends SizeFilters{

	/**
	 * GraterThanFilter constructor
	 * @param length - length to compare to
	 * @throws FilterExceptions
	 */
	public GreaterThanFilter(double length) throws FilterExceptions{
		super(length);
	}
	
	@Override
	public boolean isFiltered(File file){
		if (file.length() > _comparingSize){
			return true;//make sure length is in k-bytes
		} else {
			return false;
			}
		}
	}
