package oop.ex7.nodes.scopeNodes;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.generalTypes.SimpleNode;
import oop.ex7.nodes.mediator.NodeMediator;

import java.util.ArrayList;

/**
 * A father nodes that holds all the member variabals and methods in the file to
 * ease run the code test Created by fimak on 20/06/14.
 */
public class EnvironmentNode extends MethodNode {

	/**
	 * simple constructor
	 * @param nodeTree the nodes list the has the uppermost level nods in the
	 * code, member variable and methods
	 * @throws MasterTypeOneException in case there's syntex exception in the
	 * code
	 */
	public EnvironmentNode(ArrayList<SimpleNode> nodeTree)
			throws MasterTypeOneException {
		for (SimpleNode node : nodeTree) {
			NodeMediator.connectChildToParent(this, node);
		}
	}
}
