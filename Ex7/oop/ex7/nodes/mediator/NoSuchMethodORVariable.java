package oop.ex7.nodes.mediator;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents that the
 * looked up method or variable do not exist
 * @author owner
 */
class NoSuchMethodORVariable extends MasterTypeOneException {

	/**
	 * Constructor
	 * @param nodeLineNumber - the line number where the problem originated
	 * from
	 */
	public NoSuchMethodORVariable(int nodeLineNumber) {
		super(nodeLineNumber, "NoSuchMethodORVariable");
	}
}
