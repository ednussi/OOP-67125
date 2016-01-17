package oop.ex7.nodes.mediator;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents an
 * assigned variable which exists, yet is not initialized
 * @author owner
 */
class AssignedVariableExistsButNotInitialized extends MasterTypeOneException {

	/**
	 * Constructor
	 * @param nodeLineNumber - the line number where the problem originated
	 * from
	 */
	public AssignedVariableExistsButNotInitialized(int nodeLineNumber) {
		super(nodeLineNumber, "assigned Variable Exists But Not Initialized");
	}
}
