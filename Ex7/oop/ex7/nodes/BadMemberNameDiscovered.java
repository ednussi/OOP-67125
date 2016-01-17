package oop.ex7.nodes;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents a bad
 * naming of a member.
 * @author owner
 */
public class BadMemberNameDiscovered extends MasterTypeOneException {
	/**
	 * Constructor
	 * @param nodeLineNumber - the line number where the problem originated
	 * from
	 */
	public BadMemberNameDiscovered(int nodeLineNumber) {
		super(nodeLineNumber, "Bad name");
	}

}
