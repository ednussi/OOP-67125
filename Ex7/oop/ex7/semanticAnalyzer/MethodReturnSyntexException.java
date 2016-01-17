package oop.ex7.semanticAnalyzer;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents a method
 * return staement with a syntax error in it
 * @author owner
 */
class MethodReturnSyntexException extends MasterTypeOneException {

	/**
	 * Constructor
	 * @param nodeLineNumber - the line number where the problem originated
	 * from
	 */
	public MethodReturnSyntexException(int nodeLineNumber) {
		super(nodeLineNumber, "wrong method syntax");
	}
}
