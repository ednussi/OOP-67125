package oop.ex6.filescript;

/**
 * Type II Errors are errors which resualt in crushing your program and used
 * in the next cases:
 * Invalid usage - like anything other than 2 program arguments, or args are
 * not what they are suppose to be.
 * I/O problems - errors occurring while accessing the command file
 * a bad subsection name - such as not FILTER/ORDER
 * Bad order of command file - like missing ORDER subsection
 * All TypeTwoErrors share that an error Message is printed and the program
 * stops from running
 * @author owner
 */
@SuppressWarnings("serial")
public class TypeTwoException extends Exception {
	
	/**
	 * Prints Error Message
	 */
	public void printErrorMessage(){
		System.err.println("ERROR");
	}
}
