package oop.ex7.nodes.oneLineNodes;


import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.BadMemberNameDiscovered;
import oop.ex7.nodes.NodeTypes;
import oop.ex7.nodes.generalTypes.OneLinerNode;
import oop.ex7.nodes.generalTypes.SimpleNode;
import oop.ex7.nodes.mediator.NodeMediator;
import oop.ex7.nodes.nodeFactories.DecelerationType;

/**
 * A class representing a Call Method node i.e. foo()
 * @author owner
 */
public class CallMethodNode extends OneLinerNode {

	private static final String CALL_METHOD_NODE_THE_NODE_REGEX =
			VARIABLE_NAME_REGEX +
					POSSIBLE_SPACE + LEFT_PANTERNESES + POSSIBLE_SPACE + "(" +
					POSSIBLE_ASSIGNED_VALUES_REGEX + "(" + POSSIBLE_SPACE
					+ COMMA + POSSIBLE_SPACE +
					POSSIBLE_ASSIGNED_VALUES_REGEX + ")*)?" + POSSIBLE_SPACE
					+ RIGHT_PATERNESES + POSSIBLE_SPACE + ";?";
	public static final String CALL_METHOD_REGEX = "\\w+\\(.*\\)";
	private final static String STRUCTURE_PATTERN
			= CALL_METHOD_NODE_THE_NODE_REGEX;
	private final static int METHOD_NAME = 1;

	/**
	 * The node call method constructor
	 * @param lineData - the deceleration line
	 * @param lineNum - the file line number
	 * @param decelerationType - the checked deceleration type
	 * @throws BadMemberNameDiscovered in case the node identifier is illegal
	 */
	public CallMethodNode(
			String lineData, int lineNum, DecelerationType decelerationType)
			throws MasterTypeOneException {
		super(lineData, lineNum, STRUCTURE_PATTERN, decelerationType,
				NodeTypes.ARRAY_NODE);
		this.setNodeType(NodeTypes.METHOD_CALL_NODE);
		this.setNodeProperties(METHOD_NAME, SimpleNode.NO_ASSIGNED_VALUE_FLAG);
	}

	public static String getStructurePattern() {
		return STRUCTURE_PATTERN;
	}

	/**
	 * the node checking method
	 * @throws MasterTypeOneException
	 */
	@Override
	public void isValid() throws MasterTypeOneException {
		NodeMediator.calledMethodCorrectly(this, this.getNodeDeceleration());
	}


}
