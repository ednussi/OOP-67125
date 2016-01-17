package oop.ex7.parser;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents that a
 * line ended in an incorrect manner
 * @author owner
 */
class WrongEndLine extends MasterTypeOneException {

	/**
	 * Constructor
	 * @param exceptionLineNumber - the line number where the problem
	 * originated from
	 */
	public WrongEndLine(int exceptionLineNumber) {
		super(exceptionLineNumber, "the line end with the wrong sign");
	}
}
