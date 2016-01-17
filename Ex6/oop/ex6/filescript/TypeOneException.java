package oop.ex6.filescript;

/**
 * Type I Errors are Warnings - it is a class which extends Exception and
 * used in the next cases:
 * A bad FILTER/ORDER - name is case-sensative, or illegal name resualt in error
 * Bad parameters or illegal values for a FILTER/ORDER
 * TypeOneErrors Share a method which prints warnning message with the line
 * the error resualted from
 * @author owner
 */
@SuppressWarnings("serial")
public class TypeOneException extends Exception {
	
	/**
	 * Prints a warning message of where the error originated from
	 * @param lineNum - the line number the error originated from
	 */
	public void printError(String lineNum){
		System.out.println("Warning in line " + lineNum);
	}
}
