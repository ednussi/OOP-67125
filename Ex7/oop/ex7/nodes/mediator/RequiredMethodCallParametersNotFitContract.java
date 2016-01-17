package oop.ex7.nodes.mediator;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents that the
 * method was not called with the correct parameters.
 * @author owner
 */
class RequiredMethodCallParametersNotFitContract
		extends MasterTypeOneException {
	
	/**
	 * Constructor
	 * @param nodeLineNumber - the line number where the problem originated
	 * from
	 */
	public RequiredMethodCallParametersNotFitContract(int nodeLineNumber) {
		super(nodeLineNumber, "");

	}
}
