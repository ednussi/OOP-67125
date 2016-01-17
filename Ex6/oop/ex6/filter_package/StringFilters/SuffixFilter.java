package oop.ex6.filter_package.StringFilters;

import java.io.File;

/**
 * Filters files with the given suffix
 * @author owner
 */
public class SuffixFilter extends StringFilters{

	/**
	 * SuffixFilter constructor
	 * @param suffix - the suffix to match
	 */
	public SuffixFilter(String suffix){
		super(suffix);
	}
	
	@Override
	public boolean isFiltered(File file) {
		if (file.getName().endsWith(_testString)){
			return true;
		} else {
			return false;
			}
		}
	}
