package oop.ex7.nodes.oneLineNodes.variabals;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.BadMemberNameDiscovered;
import oop.ex7.nodes.NodeTypes;
import oop.ex7.nodes.nodeFactories.DecelerationType;
import oop.ex7.nodes.oneLineNodes.VariableNode;
import oop.ex7.parser.LineSyntaxException;

/**
 * A class representing an int variable node
 * @author owner
 */
public class IntNode extends VariableNode {

	private static final String INT_VARIABLE = "(" + INT + ")(" + POSSIBLE_SPACE
			+ EMPTY_BRACKETS + ")?" + POSSIBLE_SPACE + "(" +
			VARIABLE_NAME_REGEX + ")" + POSSIBLE_SPACE + "("
			+ ASSIGNMENT_OPERATOR + POSSIBLE_SPACE + "(" + DecelerationType.INT
			.getVariableTypeRegex() + OR +
			VARIABLE_NAME_REGEX + OR + SJAVAC_MATH_OPERATION + OR +
			POSSIBLE_ASSIGNED_FOR_ALL_TYPES +
			OR + RETURNING_ARRAY + ")?)?" + POSSIBLE_SPACE + ";?";
	public static final String INT_REGEX = "-*\\d+";

	private final static String STRUCTURE_PATTERN = INT_VARIABLE;
	private static final int VARIABLE_NAME = 3;
	private static final int COMPARED_VARIABLE_NAME = 6;

	/**
	 * constructor
	 * @param lineData - the Line Strin Data
	 * @param lineNum - the number of the line
	 * @throws LineSyntaxException
	 * @throws BadMemberNameDiscovered
	 */
	public IntNode(String lineData, int lineNum) throws
			MasterTypeOneException {
		super(lineData, lineNum, DecelerationType.INT, STRUCTURE_PATTERN);
		this.setNodeType(NodeTypes.VARIABLE_INITIALIZATION_NODE);
		this.setNodeProperties(VARIABLE_NAME, COMPARED_VARIABLE_NAME);
	}


	/**
	 * @return the structurepattern
	 */
	public static String getStructurePattern() {
		return STRUCTURE_PATTERN;
	}

}
