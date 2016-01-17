package oop.ex6.section_package;

import oop.ex6.filter_package.*;
import oop.ex6.order_package.*;

/**
 * Each command file is composed of different sections.
 * Each section has its own set of filters and orders - and thus
 * Section is a class which represent a single complete section.
 * @author owner
 */
public class Section {
	
	// Initializing parameters
	private Filter _filter;
	private Order _order;

	/**
	 * Default Constructor of Section
	 * @param filter - the filter of he section
	 * @param order - the order of the section
	 */
	public Section(Filter filter, Order order){
		_filter = filter;
		_order = order;
	}

	/**
	 * Filter Getter
	 * @return the section's filter
	 */
	public Filter get_filter() {
		return _filter;
	}
	
	/**
	 * Order Getter
	 * @return the section's order
	 */
	public Order get_order() {
		return _order;
	}

}
