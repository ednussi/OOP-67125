package oop.ex7.parser;

import oop.ex7.fileReader.FileReader;
import oop.ex7.fileReader.LineToken;
import oop.ex7.main.MasterTypeOneException;
import oop.ex7.main.MasterTypeTwoException;
import oop.ex7.nodes.generalTypes.AdvancedNode;
import oop.ex7.nodes.generalTypes.SimpleNode;
import oop.ex7.nodes.mediator.NodeMediator;
import oop.ex7.nodes.nodeFactories.AdvancedNodeFactory;
import oop.ex7.nodes.nodeFactories.OneLinerNodeFactory;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class representing the parser which analyze the line from the file he gets,
 * and creates using the approuate factories nodes.
 * @author owner
 */
public class LineParser {
	private static final String BLOCK_START_SIGNAL = ".*\\{$";
	private static final String BLOCK_END_SIGNAL = "^\\}$";
	private static final String REGULAR_END_OF_LINE = ".*;$";
	private static final String ILLEAGAL_INSIDE_COMMENTS_REGEX = "(.+//|.*"
			+ "(/\\*|\\*/|/\\*\\*)).*";
	private static final String SINGLE_LINE_COMMENT = "^(//).*";
	private static LineToken currentLine;
	private static final String END_OF_ARRAY_SIGN = "}";
	private static final String LONG_SPACES = "\\s+";
	private static final String SPACE_IN_ARRAY = "\\[s*\\]";
	private static final String SPACE_BETWEEN_PARAMTERS = "\\{s*\\}";
	/**
	 * Analyzes the given token and creates the approate node.
	 * @param currentToken - the current text line and line number.
	 * @return new node
	 * @throws MasterTypeOneException in case there's syntax error
	 */
	private static SimpleNode analyzeToken(LineToken currentToken)
			throws MasterTypeOneException {
		checkLineCorrectness(currentToken);
		String lineData = currentToken.getTokenLine();
		lineData = consumeUnneededSpaced(lineData);
		if (checkLineEnding(lineData, BLOCK_START_SIGNAL)) {
			return buildAdvancedNode(currentToken);
		} else if (checkLineEnding(lineData, REGULAR_END_OF_LINE)) {
			return buildSimpleNode(currentToken);
		} else {
			throw new WrongEndLine(currentToken.getTokenLineNumber());
		}
	}

	/**
	 * Holds the procedure to build a simple node. calls the factory.
	 * @param currentToken
	 * @return a new node
	 * @throws MasterTypeOneException in case of a a syntax problem
	 */
	private static SimpleNode buildSimpleNode(LineToken currentToken)
			throws MasterTypeOneException {
		return OneLinerNodeFactory.createOneLineNode(currentToken);
	}

	/**
	 * Creates and advanced node which is a scope node or method node runs on a
	 * section block reads the code and connects child node to the father node
	 * -which is either a scope node or method node.
	 * @param currentToken
	 * @return a new advanced node
	 * @throws MasterTypeOneException
	 */
	private static SimpleNode buildAdvancedNode(LineToken currentToken)
			throws MasterTypeOneException {
		AdvancedNode fatherNode = AdvancedNodeFactory.produceNode
				(currentToken);
		getNewLine();
		while (! checkLineEnding(currentLine.getTokenLine(),
				BLOCK_END_SIGNAL)) {
			if (isCurrentLineEndOfFile()) {
				throw new UnexpectedEndOfFile(currentLine.getTokenLineNumber
						());
			}
			SimpleNode childNode = analyzeToken(currentLine);
			NodeMediator.connectChildToParent(fatherNode, childNode);
			getNewLine();
		}
		return fatherNode;
	}


	/**
	 * Checks if the line is correct by the given laws - no inside comments and
	 * line has allowed end symbol
	 * @param currentLine - the line
	 * @throws WrongEndLine
	 * @throws LineHasInsideCommentsException
	 */
	private static void checkLineCorrectness(LineToken currentLine)
			throws LineHasInsideCommentsException {
		hasInsideComments(currentLine);
	}

	/**
	 * Checks if the line matches the following pattern
	 * @param lineData - the line
	 * @param endSignal - the requested ending
	 * @return true iff it matches the given pattern - ends with the given
	 * symbol.
	 */
	private static boolean checkLineEnding(String lineData, String endSignal) {
		return lineData.matches(endSignal);
	}

	/**
	 * gets a new line and runs aloop to ignore comments and empty lines.
	 * @throws MasterTypeOneException in case of syntax exceptions
	 */
	private static void getNewLine() {
		do {
			currentLine = FileReader.getNextLine();
		} while (isIgnoredLine(currentLine.getTokenLine()));
	}

	/**
	 * check if line is ok to be processed to allow the parser to ignore a
	 * comment line and empty line
	 * @param lineCursor - the line
	 * @return truee iff it does
	 */
	private static boolean isIgnoredLine(String lineCursor) {
		if (isCommentLine(lineCursor)) {
			return true;
		}
		if (lineCursor.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * checks if the line contains iillegalinner comments
	 * @param line
	 * @throws LineHasInsideCommentsException thrown in case the line has
	 * unwanted inside comments
	 */
	private static void hasInsideComments(LineToken line)
			throws LineHasInsideCommentsException {
		Matcher matcher = Pattern.compile(ILLEAGAL_INSIDE_COMMENTS_REGEX)
				.matcher(line.getTokenLine());
		if (matcher.matches()) {
			throw new LineHasInsideCommentsException(line.getTokenLineNumber
					());
		}
	}

	/**
	 * checks if is a comment line
	 * @param lineData - the line
	 * @return true iff it does
	 */
	private static boolean isCommentLine(String lineData) {
		return lineData.matches(SINGLE_LINE_COMMENT);
	}

	/**
	 * THe main method which starts the scan of the file and calls the token
	 * analyzer.
	 * @return an array list of nodes representing the text
	 * @throws MasterTypeOneException
	 * @throws MasterTypeTwoException
	 */

	public static ArrayList<SimpleNode> scanLines()
			throws MasterTypeOneException {
		getNewLine();
		ArrayList<SimpleNode> nodeList = new ArrayList<>();
		while (! isCurrentLineEndOfFile()) {
			if (! currentLine.getTokenLine().endsWith(END_OF_ARRAY_SIGN)) {
				SimpleNode tempNode = analyzeToken(currentLine);
				nodeList.add(tempNode);
			}
			getNewLine();
		}
		return nodeList;
	}

	/**
	 * checks if the current line is the end of the file
	 * @return truee iff it does
	 */
	private static boolean isCurrentLineEndOfFile() {
		return currentLine.equals(FileReader.EOF_FLAG);
	}

	/**
	 * consumes all unneeded spaces within a line
	 * @param targetLine - the string line
	 * @return the string without the unneeded spaces
	 */
	public static String consumeUnneededSpaced(String targetLine) {
		targetLine = targetLine.replaceAll(LONG_SPACES, " ");
		targetLine = targetLine.replaceAll(SPACE_IN_ARRAY, "[]");
		targetLine = targetLine.replaceAll(SPACE_BETWEEN_PARAMTERS, "{}");
		return targetLine;
	}

}
