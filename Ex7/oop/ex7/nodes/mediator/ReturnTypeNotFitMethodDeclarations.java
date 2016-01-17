package oop.ex7.nodes.mediator;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents that for a
 * method the return type - doesn't not fit the method declaration
 * @author owner
 */
class ReturnTypeNotFitMethodDeclarations extends MasterTypeOneException {
	
	/**
	 * Constructor
	 * @param nodeLineNumber - the line number where the problem originated
	 * from
	 */
	public ReturnTypeNotFitMethodDeclarations(int nodeLineNumber) {
		super(nodeLineNumber,
				"the returned veriable doesnt fit the methods " + "declared " +
						"type");
	}
}
