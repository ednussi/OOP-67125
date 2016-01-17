package oop.ex7.nodes.mediator;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents an
 * illegal math operation
 * @author owner
 */
class IllegalMathOperation extends MasterTypeOneException {
	
	/**
	 * Constructor
	 * @param nodeLineNumber - the line number where the problem originated
	 * from
	 */
	public IllegalMathOperation(int nodeLineNumber) {
		super(nodeLineNumber, "Longer then supposed too math operation");

	}
}
