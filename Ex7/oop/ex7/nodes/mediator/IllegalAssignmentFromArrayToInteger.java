package oop.ex7.nodes.mediator;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents an
 * illegal assignemnt from array to integer
 * @author owner
 */
class IllegalAssignmentFromArrayToInteger
		extends MasterTypeOneException {
	
	/**
	 * Constructor
	 * @param nodeLineNumber - the line number where the problem originated
	 * from
	 */
	public IllegalAssignmentFromArrayToInteger(int nodeLineNumber) {
		super(nodeLineNumber, "illegal assignment operation between an array "
				+ "and regular variable");

	}
}
