package oop.ex6.order_package;

/**
 * An OrderFactory which creates the orders according to the order data
 * @author owner
 */
public class OrderFactory {
	
	
	private static final int ORDER_TYPE = 0;
	private static Order _order;
	
	/**
	 * The main method which matches the data input and creates an order 
	 * accordingly
	 * @param orderData - The data of the order - in the next form:
	 * Index 0 - the name of the Order
	 * Index - last index in String[] - "REVERSE" or nothing 
	 * according to wanting the basic logic or its reverse form
	 * @return An Order from the given data
	 * @throws OrderExceptions
	 */
	public static Order createOrder(String[] orderData) throws OrderExceptions {
		switch(orderData[ORDER_TYPE]){
		
		case "abs":
			_order = new AbsOrder();
			break;
			
		case "type":
			_order = new TypeOrder();
			break;
			
		case "size":
			_order = new SizeOrder();
			break;
		default:
			throw new OrderExceptions();
		}
		
		// in case needed to reverse order
		if (orderData[toReverseIndex(orderData)].equals("REVERSE")){
			return new ReverseOrder(_order);
		} else {
			return _order;
		}
	}
	
	/**
	 * Finds the last index in the String[] - which the REVERSE phrase 
	 * is suppose to be located if such one exists
	 * @param orderData - the String[] containing the order's data
	 * @return an int - the last index of the String[]
	 */
	private static int toReverseIndex(String[] orderData){
		return orderData.length - 1;
	}

}
