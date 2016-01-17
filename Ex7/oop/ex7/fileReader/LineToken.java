package oop.ex7.fileReader;

/**
 * A class representing a class token which volds a read line form the the
 * source file and it's line number
 * @author owner
 */
public class LineToken {
	private String tokenLine;

	private int tokenLineNumber;

	/**
	 * constructor
	 * @param tokenLine - the line string
	 * @param tokenLineNumber - the line's number
	 */
	public LineToken(String tokenLine, int tokenLineNumber) {
		super();
		this.tokenLineNumber = tokenLineNumber;
		this.tokenLine = tokenLine;
	}

	/**
	 * Token Line Getter
	 * @return the tokenLine
	 */
	public String getTokenLine() {
		return this.tokenLine;
	}

	/**
	 * Token Line Number Getter
	 * @return the tokenLineNumber
	 */
	public int getTokenLineNumber() {
		return this.tokenLineNumber;
	}

}
