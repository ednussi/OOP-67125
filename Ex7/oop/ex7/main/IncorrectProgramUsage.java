package oop.ex7.main;

/**
 * Exception to annotate a case in which the program was launched with wrong
 * parameters. Created by fimak on 20/06/14.
 */
public class IncorrectProgramUsage extends MasterTypeTwoException {
	/**
	 * Constructor
	 */
	private final static String MESSEGE =
			"The program was launced with bad arguments please refine the" +
					" arguments , supply correct directory and files and try" +
					" again";

	public IncorrectProgramUsage(int nodeLineNumber) {
		super(nodeLineNumber, MESSEGE);
	}
}
