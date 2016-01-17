package oop.ex7.nodes.scopeNodes;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.BadMemberNameDiscovered;
import oop.ex7.nodes.nodeFactories.DecelerationType;

/**
 * * A while loop node Created by fimak on 20/06/14.
 */
public class WhileLoopNode extends ScopeNode {

	private static final String WHILE_NODE_REGEX = "(" + WHILE + ")"
			+ POSSIBLE_SPACE + LEFT_PANTERNESES +
			"(" + ALL_VALUES + ")" + RIGHT_PATERNESES + POSSIBLE_SPACE
			+ LEFT_BRACES;
	private final static String structurePattern = WHILE_NODE_REGEX;


	/**
	 * The while loop node constructor
	 * @param decelerationStatement - the deceleration line
	 * @param lineNumber - the file line number
	 * @param decelerationType - the checked deceleration type
	 * @throws BadMemberNameDiscovered in case the node identifier is illegal
	 */
	public WhileLoopNode(
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
