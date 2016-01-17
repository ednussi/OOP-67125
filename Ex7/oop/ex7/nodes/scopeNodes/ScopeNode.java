package oop.ex7.nodes.scopeNodes;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.BadMemberNameDiscovered;
import oop.ex7.nodes.NodeTypes;
import oop.ex7.nodes.generalTypes.AdvancedNode;
import oop.ex7.nodes.mediator.MissingParameterDeceleration;
import oop.ex7.nodes.mediator.NodeMediator;
import oop.ex7.nodes.nodeFactories.DecelerationType;

/**
 * A class representing a node with a scope in the code
 * @author owner
 */
public abstract class ScopeNode extends AdvancedNode {

	public static final String WHILE = "while";
	public static final String IF = "if";
	private final static NodeTypes NODE_TYPE = NodeTypes.SCOPE_NODE;
	public static final int PARAMETERS = 2;


	/**
	 * The method node constructor
	 * @param decelerationStatement - the deceleration line
	 * @param lineNumber - the file line number
	 * @param decelerationType - the checked deceleration type
	 * @throws BadMemberNameDiscovered in case the node identifier is illegal
	 */
	ScopeNode(
			String decelerationStatement, int lineNumber,
			DecelerationType decelerationType, String nodeStructurePattern) {
		super(decelerationStatement, lineNumber, nodeStructurePattern,
				decelerationType, NODE_TYPE);
	}

	/**
	 * the method checks the parameters of the scope node and stores their
	 * types
	 * to later be checked if needed in case ofa call and stored to be used in
	 * the method
	 * @throws MasterTypeOneException
	 */
	public void checkScopeParameters() throws MasterTypeOneException {

		if (matcher.matches()) {
			this.setScopeNodeParameters(matcher.group(PARAMETERS));
			if (this.getScopeNodeParameters().matches(
					this.getNodeDecelerationType().getVariableTypeRegex())) {
				return;
			} else if (NodeMediator
					.compareTypes(this, DecelerationType.BOOLEAN, false,
							this.getScopeNodeParameters())) {
				return;
			}

		}

		throw new MissingParameterDeceleration(this.getNodeLineNumber());
	}


}
