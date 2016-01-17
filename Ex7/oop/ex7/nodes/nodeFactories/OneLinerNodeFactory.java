package oop.ex7.nodes.nodeFactories;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.generalTypes.OneLinerNode;
import oop.ex7.nodes.generalTypes.SimpleNode;
import oop.ex7.nodes.oneLineNodes.*;
import oop.ex7.fileReader.LineToken;

/**
 * A factory for all one liner nodes types - sort the information given into to
 * send into the right constructor and return the built node.
 * @author owner
 */
public class OneLinerNodeFactory {

	private static final DecelerationType DEFAULT_DECELERATION_TYPE
			= DecelerationType.NULL;

	/**
	 * Given a lineData returns the proper node that line represent
	 * @param currentToken the token which hold the line containg the one liner
	 * decleration and source line number
	 * @return the proper node that line represent
	 * @throws MasterTypeOneException
	 */
	public static SimpleNode createOneLineNode(LineToken currentToken)
			throws MasterTypeOneException {

		String lineData = currentToken.getTokenLine();
		int lineNumber = currentToken.getTokenLineNumber();
		OneLinerNode newNode;

		if (lineData.matches(VariableNode.getStructurePattern())) {
			newNode = VariableNodeFactory
					.createVariableNode(lineData, lineNumber);
			if (lineData.matches(ArrayNodeDecorator.getStructurePattern())) {
				newNode = new ArrayNodeDecorator(newNode);
			}
		} else if (lineData.matches(ReturnNode.getStructurePattern())) {
			newNode = new ReturnNode(lineData, lineNumber,
					DEFAULT_DECELERATION_TYPE);
		} else if (lineData.matches(CallMethodNode.getStructurePattern())) {
			newNode = new CallMethodNode(lineData, lineNumber,
					DEFAULT_DECELERATION_TYPE);
		} else if (lineData.matches(AssignmentNode.getStructurePattern())) {
			newNode = new AssignmentNode(lineData, lineNumber,
					DEFAULT_DECELERATION_TYPE);
			if (lineData.matches(
					ArrayAssignmentNodeDecorator.getStructurePattern())) {
				newNode = new ArrayAssignmentNodeDecorator(newNode);
			}
		} else {
			throw new UnknowenDeclerationInThisLine(lineNumber);
		}


		return newNode;
	}


}
