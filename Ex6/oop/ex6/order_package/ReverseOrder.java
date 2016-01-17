package oop.ex6.order_package;

import java.io.File;

/**
 * An order decorator
 * Orders according to the reverse logic of the given order
 * @author owner
 */
public class ReverseOrder extends Order {
	
	private Order _orderToNeg;
	
	/**
	 * A ReverseOrder Constructor
	 * @param orderToNeg
	 */
	public ReverseOrder(Order orderToNeg){
		_orderToNeg = orderToNeg;
	}

	/**
	 * Returns the reverse logic by changing the file comparing place
	 */
	@Override
	public int compare(File file1, File file2) {
		int orderSign = _orderToNeg.compare(file2, file1);
			return orderSign;
	}

}
