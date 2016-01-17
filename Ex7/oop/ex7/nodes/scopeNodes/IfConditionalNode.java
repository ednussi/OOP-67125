package oop.ex7.nodes.scopeNodes;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.BadMemberNameDiscovered;
import oop.ex7.nodes.nodeFactories.DecelerationType;

/**
 * An if condtinal node Created by fimak on 20/06/14.
 */
public class IfConditionalNode extends ScopeNode {

	private static final String IF_NODE_REGEX = "(" + IF + ")" + POSSIBLE_SPACE
			+ "\\((.*)\\)" + POSSIBLE_SPACE + LEFT_BRACES;
	private final static String structurePattern = IF_NODE_REGEX;


	/**
	 * The if conditional node constructor
	 * @param decelerationStatement - the deceleration line
	 * @param lineNumber - the file line number
	 * @param decelerationType - the checked deceleration type
	 * @throws BadMemberNameDiscovered in case the node identifier is illegal
	 */
	public IfConditionalNode(
			String decelerationStatement, int lineNumber,
			DecelerationType decelerationType) {
		super(decelerationStatement, lineNumber, decelerationType,
				structurePattern);
	}

	/**
	 * @return the node pattern
	 */
	public static String getStructurePattern() {
		return structurePattern;
	}

	/**
	 * not needed therefore not implemented
	 * @throws MasterTypeOneException
	 */
	@Override
	public void isValid() throws MasterTypeOneException {
	}
}
