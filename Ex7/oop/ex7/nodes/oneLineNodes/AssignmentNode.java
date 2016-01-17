package oop.ex7.nodes.oneLineNodes;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.NodeTypes;
import oop.ex7.nodes.generalTypes.OneLinerNode;
import oop.ex7.nodes.nodeFactories.DecelerationType;

/**
 * A class representing an Assignment node (i.e a=b,a=b[1],a={})
 * @author owner
 */
public class AssignmentNode extends OneLinerNode {


	private static final String ARRAY_AND_REGULAR_ASSIGNMENT_NODE_REGEX = "(?:("
			+ VARIABLE_NAME_REGEX + OR + ARRAY_ASSIGNING +
			"))" + POSSIBLE_SPACE + "(" + ASSIGNMENT_OPERATOR + POSSIBLE_SPACE
			+ "(" + POSSIBLE_ASSIGNED_VALUES_REGEX +
			OR + POSSIBLE_SPACE + "))" + POSSIBLE_SPACE + "(?:;)";


	private final static String STRUCTURE_PATTERN
			= ARRAY_AND_REGULAR_ASSIGNMENT_NODE_REGEX;
	private static final int VARIABLE_NAME = 1;
	private static final int COMPARED_VARIABLE_NAME = 14;
	private final static NodeTypes NODE_TYPE
			= NodeTypes.VARIABLE_ASSIGNMENT_NODE;

	/**
	 * constructor of the Assignment node
	 * @param lineData - the line data
	 * @param lineNumber - the line's number
	 * @param decelerationType - it's Type
	 */
	public AssignmentNode(
			String lineData, int lineNumber, DecelerationType decelerationType)
			throws MasterTypeOneException {
		super(lineData, lineNumber, STRUCTURE_PATTERN, decelerationType,
				NODE_TYPE);
		this.setNodeProperties(VARIABLE_NAME, COMPARED_VARIABLE_NAME);
	}

	/**
	 * @return the node pattern
	 */
	public static String getStructurePattern() {
		return STRUCTURE_PATTERN;
	}

	/**
	 * the node checking method
	 * @throws MasterTypeOneException
	 */
	@Override
	public void isValid() throws MasterTypeOneException {
		checkAssignedVariable(this);
		this.setAssigned(true);
	}


}
