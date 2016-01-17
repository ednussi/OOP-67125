package oop.ex7.nodes.generalTypes;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.NodeTypes;
import oop.ex7.nodes.mediator.NodeMediator;
import oop.ex7.nodes.nodeFactories.DecelerationType;

/**
 * An abstract class which represent a single one liner command node - extends
 * SimpleNode
 * @author owner
 */
public abstract class OneLinerNode extends SimpleNode {

	/**
	 * constructor
	 * @param lineData - the string of the line
	 * @param lineNum - the number of the line in the text
	 * @param wantedStructure - the wanted structure
	 * @param decelerationType - its declaration type
	 * @param nodeType it's node type
	 */
	protected OneLinerNode(
			String lineData, int lineNum, String wantedStructure,
			DecelerationType decelerationType, NodeTypes nodeType) {
		super(lineNum, lineData, wantedStructure, decelerationType, nodeType);
	}


	/**
	 * Checks the assigned variable if exists in is of the correct type
	 * @param node - the node to check
	 * @throws MasterTypeOneException
	 */
	protected static void checkAssignedVariable(OneLinerNode node)
			throws MasterTypeOneException {
		DecelerationType leftSideParameterType = NodeMediator
				.checkIfVerExists(node, node.getNodeName())
				.getNodeDecelerationType();
		NodeMediator.compareTypes(node, leftSideParameterType,
				node.isLeftSideParameterArray(),
				node.getAssignedVariableName());
	}


}
