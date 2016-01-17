package oop.ex6.filter_package;

import oop.ex6.filter_package.BooleanFilters.ExecutableFilter;
import oop.ex6.filter_package.BooleanFilters.HiddenFilter;
import oop.ex6.filter_package.BooleanFilters.WritableFilter;
import oop.ex6.filter_package.SizeFilters.BetweenFilter;
import oop.ex6.filter_package.SizeFilters.GreaterThanFilter;
import oop.ex6.filter_package.SizeFilters.SmallerThanFilter;
import oop.ex6.filter_package.StringFilters.FileNameContainsFilter;
import oop.ex6.filter_package.StringFilters.FileNameFilter;
import oop.ex6.filter_package.StringFilters.PrefixFilter;
import oop.ex6.filter_package.StringFilters.SuffixFilter;

/**
 * A FilterFactory which create a filter according to the filter data given
 * @author owner
 */
public class FilterFactory {
	
	private static final int FILTERTYPE = 0, DATA = 1, DATA2 = 2;
	private static Filter _filter;
	private static final double BYTE_TO_KBYTE_CONVERSATION = 1024;
	
	/**
	 * The main method which matches the data and creates a order accordingly
	 * @param filterData - the filter's data in the next form:
	 * Index 0 - the filter's type
	 * Index 1 - data needed by the certain type or empty
	 * Index 2 - data needed by the certain type or empty
	 * Last Index - contains "NOT" - if entered
	 * @return the filter 
	 * @throws FilterExceptions
	 */
	public static Filter createFilter(String[] filterData) 
			throws FilterExceptions {
		switch(filterData[FILTERTYPE]){
		
		case "greater_than":
			_filter = new GreaterThanFilter(kbyteToByte(filterData[DATA]));
			break;
			
		case "between":
			_filter = new BetweenFilter(kbyteToByte(filterData[DATA]),
					kbyteToByte(filterData[DATA2]));
			break;
			
		case "smaller_than":
			_filter = new SmallerThanFilter(kbyteToByte(filterData[DATA]));
			break;
			
		case "file":
			_filter = new FileNameFilter(filterData[DATA]);
			break;
			
		case "contains":
			_filter = new FileNameContainsFilter(filterData[DATA]);
			break;
			
		case "prefix":
			_filter = new PrefixFilter(filterData[DATA]);
			break;
			
		case "suffix":
			_filter = new SuffixFilter(filterData[DATA]);
			break;
			
		case "writable":
			_filter = new WritableFilter(filterData[DATA]);
			break;
			
		case "executable":
			_filter = new ExecutableFilter(filterData[DATA]);
			break;
			
		case "hidden":
			_filter = new HiddenFilter(filterData[DATA]);
			break;
			
		case "all":
			_filter = new AllFilter();
			break;
		default:
			throw new FilterExceptions();
		}
		
		// in case needed the negated filter
		if (filterData[toNegIndex(filterData)].equals("NOT")){
			return new NegFilter(_filter);
		} else {
			return _filter;
		}
		
	}
	
	/**
	 * Finds the last index in the String[] - which the NOT phrase 
	 * is suppose to be located if such one exists
	 * @param filterData - the String[] containing the filter's data
	 * @return an int - the last index of the String[]
	 */
	private static int toNegIndex(String[] filterData){
		return filterData.length - 1;
	}
	
	/**
	 * Turns KBytes size to Bytes
	 * @param kbyteValue - the value in KByte
	 * @return the size in Bytes
	 */
	private static Double kbyteToByte(String kbyteValue){
		return Double.parseDouble(kbyteValue)
				* BYTE_TO_KBYTE_CONVERSATION;
	}
}
