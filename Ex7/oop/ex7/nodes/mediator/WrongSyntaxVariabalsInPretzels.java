package oop.ex7.nodes.mediator;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents that a
 * wrond snytax appeared within the barackets
 * @author owner
 */
class WrongSyntaxVariabalsInPretzels extends MasterTypeOneException {

	/**
	 * Constructor
	 * @param nodeLineNumber - the line number where the problem originated
	 * from
	 */
	public WrongSyntaxVariabalsInPretzels(int nodeLineNumber) {
		super(nodeLineNumber, "WrongSyntaxVariabalsInPretzels");

	}

}
