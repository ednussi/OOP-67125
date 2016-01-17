package oop.ex7.nodes.oneLineNodes;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.NodeTypes;
import oop.ex7.nodes.generalTypes.OneLinerNode;
import oop.ex7.nodes.generalTypes.SimpleNode;
import oop.ex7.nodes.mediator.NodeMediator;

/**
 * A node that marks an array deceleration in to code with or without
 * initialization.
 */
public class ArrayNodeDecorator extends OneLinerNode {

	private OneLinerNode decoratedNode;

	private static final String POSSIBLE_INITIALIZE_ARRAY_TYPES_REGEX =
			POSSIBLE_BASIC_TYPES_REGEX + POSSIBLE_SPACE + "(" + EMPTY_BRACKETS
					+ ")";
	private static final String ARRAY_VARIABLE_REGEX =
			POSSIBLE_INITIALIZE_ARRAY_TYPES_REGEX + POSSIBLE_SPACE
					+ VARIABLE_NAME_REGEX + POSSIBLE_SPACE + "(;?|"
					+ ASSIGNMENT_OPERATOR + POSSIBLE_SPACE
					+ POSSIBLE_ASSIGNED_VALUES_REGEX + POSSIBLE_SPACE + ";)";

	private final static String STRUCTURE_PATTERN = ARRAY_VARIABLE_REGEX;
	private final static int VARIABLE_NAME = 3;
	private final static int COMPARED_VARIABLE_NAME = 5;
	private final static NodeTypes NODE_TYPE = NodeTypes.ARRAY_NODE;

	/**
	 * The node array constructor
	 * @throws oop.ex7.nodes.BadMemberNameDiscovered in case the node
	 * identifier
	 * is illegal
	 */
	public ArrayNodeDecorator(OneLinerNode decoratedNode)
			throws MasterTypeOneException {
		super(decoratedNode.getNodeDeceleration(),
				decoratedNode.getNodeLineNumber(), STRUCTURE_PATTERN,
				decoratedNode.getNodeDecelerationType(), NODE_TYPE);
		this.decoratedNode = decoratedNode;
		this.setNodeProperties(VARIABLE_NAME, COMPARED_VARIABLE_NAME);
		this.setLeftSideParameterArray(true);
		// assigned set to true by default - since it's the requirmnet of the
		// exercise to consider decleration for array as intillized
		this.setAssigned(true);
	}

	/**
	 * @return the node pattern
	 */
	public static String getStructurePattern() {
		return STRUCTURE_PATTERN;
	}

	/**
	 * the node checking method
	 * @throws oop.ex7.main.MasterTypeOneException
	 */
	@Override
	public void isValid() throws MasterTypeOneException {
		if (this.checkAssignedVariableValidation()) {
			NodeMediator.storeVariableInParent(this.getNodeName(),
					this.getNodeDecelerationType(), this);
		}
	}

	@Override
	/**
	 * Node parent setter
	 */
	public void setNodeParent(SimpleNode fatherNode) {
		this.decoratedNode.setNodeParent(fatherNode);
		super.setNodeParent(fatherNode);

	}


}
