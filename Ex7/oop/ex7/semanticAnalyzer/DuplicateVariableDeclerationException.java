package oop.ex7.semanticAnalyzer;

import oop.ex7.main.MasterTypeOneException;

/**
 * An Exception Class extending MasterTypeOneException and represents duplicates
 * of the variable already exist
 * @author owner
 */
public class DuplicateVariableDeclerationException
		extends MasterTypeOneException {

	/**
	 * Constructor
	 * @param nodeLineNumber - the line number where the problem originated
	 * from
	 */
	public DuplicateVariableDeclerationException(int nodeLineNumber) {
		super(nodeLineNumber, "Attempt to declare an already declared " +
				"variable in the " + "same scope!");
	}
}
