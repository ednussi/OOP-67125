package oop.ex7.main;

/**
 * An exception Class extending the java exception class which represent the
 * father of all type one exceptions
 * @author owner
 */
public class MasterTypeOneException extends MasterException {
	private static final int GENERAL_EXCEPTION_TYPE_NUM = 1;

	/**
	 * Constructor
	 * @param nodeLineNumber - the line number where the problem originated
	 * from
	 * @param wantedErrorMessage - the error message to be print
	 */
	protected MasterTypeOneException(
			int nodeLineNumber, String wantedErrorMessage) {
		super(nodeLineNumber, wantedErrorMessage, GENERAL_EXCEPTION_TYPE_NUM);
	}

}
