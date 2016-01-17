package oop.ex7.fileReader;

import oop.ex7.main.IncorrectProgramUsage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * reads the file line by line - thus creating an iterator to be used by the
 * parser, so the file can be read line by line and analyzed.
 */
public class FileReader {
	
	private final static int FIRST_LINE = 0;
	
	public static final LineToken EOF_FLAG = new LineToken("EOF", FIRST_LINE);
	private static Scanner scanner;
	private static int lineNumber;

	/**
	 * The only exposed method besides the intillizing method (readFile). this
	 * method simply reads one line from the file and returnes it in case there
	 * are no more lines return the constant.
	 */
	public static LineToken getNextLine() {
		LineToken returnedToken = EOF_FLAG;
		if (hasNext()) {
			String currentReadLine = next();
			returnedToken = new LineToken(currentReadLine, lineNumber);
		}
		return returnedToken;
	}

	/**
	 * checks of more lines are left to read in the file
	 * @return true if the scanner haven't reached the end of the file.
	 */
	private static boolean hasNext() {
		return scanner.hasNextLine();
	}

	/**
	 * checks of more lines are left to read in the file
	 * @return new line from the file after it's extra spaces were trimmed
	 */
	private static String next() {
		String newLine = scanner.nextLine().trim();
		newLine.trim();
		lineNumber++;
		return newLine;
	}

	/**
	 * Intillize the scanner with the provided file
	 * @param source the file to read
	 * @throws FileNotFoundException - in case the file is not found will throw
	 * an
	 */
	public static void readFile(File source) throws IncorrectProgramUsage {
		lineNumber = FIRST_LINE;
		try{
			scanner = new Scanner(source);
		} catch (FileNotFoundException e){
			throw new IncorrectProgramUsage(0);
		}
	}
}
