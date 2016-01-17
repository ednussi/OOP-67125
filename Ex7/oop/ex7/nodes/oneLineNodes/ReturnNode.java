package oop.ex7.nodes.oneLineNodes;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.BadMemberNameDiscovered;
import oop.ex7.nodes.NodeTypes;
import oop.ex7.nodes.generalTypes.OneLinerNode;
import oop.ex7.nodes.nodeFactories.DecelerationType;

/**
 * A class representing a return statement node
 * @author owner
 */
public class ReturnNode extends OneLinerNode {

	public static final String EMPTY_RETURN_STATEMENT = "NO RETURN VAL";

	private static final String RETURN_POSSIBILITIES = "("
			+ POSSIBLE_ASSIGNED_VALUES_REGEX +
			OR + SJAVAC_MATH_OPERATION + OR + RETURNING_ARRAY + ")";

	private static final String RETURN_NODE_REGEX = RETURN + POSSIBLE_SPACE +
			"(" + RETURN_POSSIBILITIES + ")?" + SEMICOLON_END_OF_LINE;

	private final static String STRUCTURE_PATTERN = RETURN_NODE_REGEX;
	private final static NodeTypes NODE_TYPE = NodeTypes.METHOD_RETURN_NODE;

	private static final int RETURN_ITEM_INDEX = 1;
	private static final String RETURN_WITHOUT_VALUE = "^" + RETURN
			+ POSSIBLE_SPACE + SEMICOLON_END_OF_LINE;

	/**
	 * The Return node constructor
	 * @param lineData - the deceleration line
	 * @param lineNum - the file line number
	 * @param decelerationType - the checked deceleration type
	 * @throws BadMemberNameDiscovered in case the node identifier is illegal
	 */
	public ReturnNode(
			String lineData, int lineNum, DecelerationType decelerationType)
			throws BadMemberNameDiscovered {
		super(lineData, lineNum, STRUCTURE_PATTERN, decelerationType,
				NODE_TYPE);
		this.setNodeName();
	}

	/**
	 * set the node name - the parameter returned in case empty will set
	 * default
	 * name
	 * @throws BadMemberNameDiscovered in case the node identifier is illegal
	 */
	public void setNodeName() throws BadMemberNameDiscovered {
		String returnValue;
		if (! this.getNodeDeceleration().matches(RETURN_WITHOUT_VALUE)) {
			returnValue = matcher.group(RETURN_ITEM_INDEX);
		} else {
			returnValue = EMPTY_RETURN_STATEMENT;
		}
		super.setNodeName(returnValue);
	}

	@Override
	public void isValid() throws MasterTypeOneException {
	}

	/**
	 * @return the node pattern
	 */
	public static String getStructurePattern() {
		return STRUCTURE_PATTERN;
	}
}
