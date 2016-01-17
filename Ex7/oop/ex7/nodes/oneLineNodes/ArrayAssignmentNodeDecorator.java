package oop.ex7.nodes.oneLineNodes;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.NodeTypes;
import oop.ex7.nodes.generalTypes.OneLinerNode;
import oop.ex7.nodes.generalTypes.SimpleNode;
import oop.ex7.nodes.mediator.NodeMediator;
import oop.ex7.nodes.nodeFactories.DecelerationType;

/**
 * A Class that decorates a regular assignment statement to hold an array
 * assignment
 * @author owner
 */
public class ArrayAssignmentNodeDecorator extends OneLinerNode {

	public static final String ARRAY_ASSIGNMENT = ARRAY_ASSIGNING + ALL_VALUES;
	private static final String ARRAY_VARIABLE_ASSIGN_REGEX = "("
			+ VARIABLE_NAME_REGEX + POSSIBLE_SPACE + LEFT_BRACKET
			+ POSSIBLE_SPACE +
			POSSIBLE_ASSIGNED_VALUES_REGEX + POSSIBLE_SPACE +
			RIGHT_BRACKET + ")" +
			POSSIBLE_SPACE +
			"(;|" + ASSIGNMENT_OPERATOR + POSSIBLE_SPACE +
			POSSIBLE_ASSIGNED_VALUES_REGEX + POSSIBLE_SPACE + ";)";
	private final static String STRUCTURE_PATTERN =
			ARRAY_VARIABLE_ASSIGN_REGEX;

	private final static int VARIABLE_NAME = 2;
	private final static int VALUE_IN_BRACKETS = 3;
	private final static NodeTypes NODE_TYPE = NodeTypes.ARRAY_ASSIGNMENT_NODE;
	private SimpleNode decoratedNode;


	public ArrayAssignmentNodeDecorator(SimpleNode decoratedNode)
			throws MasterTypeOneException {
		super(decoratedNode.getNodeDeceleration(),
				decoratedNode.getNodeLineNumber(), STRUCTURE_PATTERN,
				decoratedNode.getNodeDecelerationType(), NODE_TYPE);
		this.decoratedNode = decoratedNode;
		this.setNodeProperties(VARIABLE_NAME,
				decoratedNode.getAssignedVariableName());
		checkIfArray();
	}

	/**
	 * checks if it's supposed to be an assign to the array or only one
	 * value of
	 * it.
	 */
	private void checkIfArray() {
		if (this.getNodeName().matches(ARRAY_ASSIGNMENT)) {
			this.setLeftSideParameterArray(true);
		}
	}

	/**
	 * @return the structure pattern of this node type
	 */
	public static String getStructurePattern() {
		return STRUCTURE_PATTERN;
	}

	@Override
	/**
	 *sets this node's parent node and it's decorated node
	 */
	public void setNodeParent(SimpleNode fatherNode) {
		this.decoratedNode.setNodeParent(fatherNode);
		super.setNodeParent(fatherNode);

	}

	@Override
	/**
	 * first checks if the boundary of the array is legal and then checks this
	 * as a a regular variable
	 */
	public void isValid() throws MasterTypeOneException {
		checkBoundaryInBrackets();
		checkAssignedVariable(this);
	}

	/**
	 * @throws MasterTypeOneException
	 */
	private void checkBoundaryInBrackets() throws MasterTypeOneException {
		String parameter = this.matcher.group(VALUE_IN_BRACKETS);
		NodeMediator.compareTypes(this, DecelerationType.INT, false,
				parameter);
	}


}
