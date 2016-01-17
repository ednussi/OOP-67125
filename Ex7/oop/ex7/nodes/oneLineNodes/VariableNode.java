package oop.ex7.nodes.oneLineNodes;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.NodeTypes;
import oop.ex7.nodes.generalTypes.OneLinerNode;
import oop.ex7.nodes.mediator.NodeMediator;
import oop.ex7.nodes.nodeFactories.DecelerationType;

/**
 * A class representing a variable node
 * @author owner
 */
public abstract class VariableNode extends OneLinerNode {

	public static final String INT = "int";
	public static final String CHAR = "char";
	public static final String STRING = "String";
	public static final String DOUBLE = "double";
	public static final String BOOLEAN = "boolean";
	protected static final String POSSIBLE_ASSIGNED_FOR_ALL_TYPES =
			CallMethodNode.CALL_METHOD_REGEX + OR + CALLING_FROM_ARRAY_REGEX;
	private final static String STRUCTURE_PATTERN =
			POSSIBLE_INITIALIZE_TYPES_REGEX +
					POSSIBLE_SPACE + VARIABLE_NAME_REGEX +
					POSSIBLE_SPACE +
					"(" + ASSIGNMENT_OPERATOR + POSSIBLE_SPACE + "(([^;])+)"
					+ POSSIBLE_SPACE + ")?(?:(" +
					SEMICOLON_END_OF_LINE + ")?)";
	private final static NodeTypes NODE_TYPE
			= NodeTypes.VARIABLE_INITIALIZATION_NODE;
	protected static final String LEFT_VARIABLE_NODE_BASE_REGEX = ")("
			+ POSSIBLE_SPACE + EMPTY_BRACKETS + ")?" + POSSIBLE_SPACE + "(" +
			VARIABLE_NAME_REGEX + ")" + POSSIBLE_SPACE + "("
			+ ASSIGNMENT_OPERATOR + POSSIBLE_SPACE + "(";

	/**
	 * constructor
	 * @param lineData - the line data
	 * @param lineNum - the line's number
	 * @param decelerationType - it's Type
	 */
	protected VariableNode(
			String lineData, int lineNum, DecelerationType decelerationType,
			String structurePattern) {
		super(lineData, lineNum, structurePattern, decelerationType,
				NODE_TYPE);

	}

	/**
	 * @return the node pattern
	 */
	public static String getStructurePattern() {
		return STRUCTURE_PATTERN;
	}

	/**
	 * validates the node deceleration statement
	 * @throws MasterTypeOneException
	 */
	@Override
	public void isValid() throws MasterTypeOneException {

		if (this.checkAssignedVariableValidation()) {
			NodeMediator.storeVariableInParent(this.getNodeName(),
					this.getNodeDecelerationType(), this);
		} else {
			NodeMediator.deepValidationItemToDecleredItem(this,
					this.getNodeDecelerationType(),
					this.getAssignedVariableName());
		}
	}


}
