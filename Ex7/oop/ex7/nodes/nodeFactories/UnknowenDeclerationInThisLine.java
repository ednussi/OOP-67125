package oop.ex7.nodes.nodeFactories;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents an unknown
 * variable type declaration
 * @author owner
 */
class UnknowenDeclerationInThisLine extends MasterTypeOneException {

	/**
	 * Constructor
	 * @param nodeLineNumber - the line number where the problem originated
	 * from
	 */
	public UnknowenDeclerationInThisLine(int nodeLineNumber) {
		super(nodeLineNumber, "Unidentified code pattern: This line contains "
				+ "an unknown code ");
	}
}
