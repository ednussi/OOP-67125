package oop.ex7.nodes.mediator;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents a
 * parameter which is missing the proper declaration
 * @author owner
 */
public class MissingParameterDeceleration extends MasterTypeOneException {

	/**
	 * Constructor
	 * @param nodeLineNumber - the line number where the problem originated
	 * from
	 */
	public MissingParameterDeceleration(int nodeLineNumber) {
		super(nodeLineNumber, "Missing Parameter Deceleration in line ");
	}
}
