package oop.ex7.parser;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents that there
 * is a syntax problem within the line
 * @author owner
 */

public class LineSyntaxException extends MasterTypeOneException {

	/**
	 * Constructor
	 * @param exceptionLineNumber - the line number where the problem 
	 * originated
	 * from
	 */
	public LineSyntaxException(int exceptionLineNumber) {
		super(exceptionLineNumber, ("A syntax error occur on line"));
	}
}
