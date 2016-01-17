package oop.ex7.semanticAnalyzer;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents more than
 * return statement was within the method
 * @author owner
 */
class MoreThenOneReturnStatement extends MasterTypeOneException {

	/**
	 * Constructor
	 * @param nodeLineNumber - the line number where the problem originated
	 * from
	 */
	public MoreThenOneReturnStatement(int nodeLineNumber) {
		super(nodeLineNumber, "Method has too many return statements!");
	}
}
