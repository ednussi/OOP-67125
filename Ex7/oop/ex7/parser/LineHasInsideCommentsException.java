package oop.ex7.parser;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents that a
 * line has inside comments within
 * @author owner
 */
class LineHasInsideCommentsException extends MasterTypeOneException {

	/**
	 * Constructor
	 * @param tokenLineNumber - the line number where the problem originated
	 * from
	 */

	public LineHasInsideCommentsException(int tokenLineNumber) {
		super(tokenLineNumber, "There is illeagal comments sign inside the "
				+ "code line....");
	}
}
