package oop.ex7.main;

/**
 * An exception Class extending the java exception class which represent the
 * father of all type two exceptions  = serious erros
 * @author owner
 */
public abstract class MasterTypeTwoException extends MasterException {

	private static final int GENERAL_EXCEPTION_TYPE_NUM = 2;

	/**
	 * Constructor
	 * @param nodeLineNumber - the line number where the problem originated
	 * from
	 * @param wantedErrorMessage - the error message to be print
	 */
	MasterTypeTwoException(
			int nodeLineNumber, String wantedErrorMessage) {
		super(nodeLineNumber, wantedErrorMessage, GENERAL_EXCEPTION_TYPE_NUM);
	}
}