package oop.ex7.main;

/**
 * Master excepton class used in order to be the basic exception which holds the
 * joined use methods Created by fimak on 20/06/14.
 */
abstract class MasterException extends Exception {
	private int generalExceptionType;
	private int exceptionLineNumber;
	private String exceptionMessage;

	/**
	 * regular constructor
	 * @param nodeLineNumber the exception line number
	 * @param wantedErrorMessage the wanted exception messege to be printed
	 * @param exceptionType the type of the exception (1/2?)
	 */
	MasterException(
			int nodeLineNumber, String wantedErrorMessage, int exceptionType) {
		this.exceptionLineNumber = nodeLineNumber;
		this.exceptionMessage = wantedErrorMessage;
		this.generalExceptionType = exceptionType;
	}

	/**
	 * prints the stored exception message and it's line number.
	 */
	public void printExceptionMessage() {
		System.err.println(exceptionMessage + " at line: " +
				exceptionLineNumber);
		System.out.println(generalExceptionType);
	}
}
