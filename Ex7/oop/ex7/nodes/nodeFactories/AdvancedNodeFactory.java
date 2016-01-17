package oop.ex7.nodes.nodeFactories;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.generalTypes.AdvancedNode;
import oop.ex7.nodes.scopeNodes.IfConditionalNode;
import oop.ex7.nodes.scopeNodes.MethodNode;
import oop.ex7.nodes.scopeNodes.WhileLoopNode;
import oop.ex7.fileReader.LineToken;

/**
 * A factory for all advance nodes types - sort the information given into to
 * send into the right constructor and return the built node.
 * @author owner
 */
public class AdvancedNodeFactory {
	private static final DecelerationType DEFAULT_DECELERATION_TYPE
			= DecelerationType.BOOLEAN;

	/**
	 * Given a lineToken return the proper node the token represent
	 * @param currentToken - the line token element to be sorted
	 * @return The correct AdavancedNode
	 * @throws MasterTypeOneException
	 */
	public static AdvancedNode produceNode(LineToken currentToken)
			throws MasterTypeOneException {
		String lineData = currentToken.getTokenLine();
		int lineNumber = currentToken.getTokenLineNumber();
		DecelerationType decelerationType = SimpleFactory
				.checkDecelerationType(lineData);
		if (decelerationType != null && lineData
				.matches(MethodNode.getStructurePattern())) {
			return new MethodNode(lineData, lineNumber, decelerationType);
		} else if (lineData.matches(WhileLoopNode.getStructurePattern())) {
			return new WhileLoopNode(lineData, lineNumber,
					DEFAULT_DECELERATION_TYPE);
		} else if (lineData.matches(IfConditionalNode.getStructurePattern())) {
			return new IfConditionalNode(lineData, lineNumber,
					DEFAULT_DECELERATION_TYPE);
		} else {
			throw new UnknowenDeclerationInThisLine(
					currentToken.getTokenLineNumber());
		}
	}
}
