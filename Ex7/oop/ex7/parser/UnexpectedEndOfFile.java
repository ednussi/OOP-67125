package oop.ex7.parser;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents that the
 * end of the file reached before all closers part needed by the file
 * @author owner
 */
class UnexpectedEndOfFile extends MasterTypeOneException {

	/**
	 * Constructor
	 * @param nodeLineNumber - the line number where the problem originated
	 * from
	 */

	public UnexpectedEndOfFile(int nodeLineNumber) {
		super(nodeLineNumber,
				"File reached it's end unexpectedly! (buy more " + "files)");
	}
}
