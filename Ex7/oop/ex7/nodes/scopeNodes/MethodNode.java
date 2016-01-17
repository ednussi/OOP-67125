package oop.ex7.nodes.scopeNodes;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.BadMemberNameDiscovered;
import oop.ex7.nodes.NodeTypes;
import oop.ex7.nodes.generalTypes.AdvancedNode;
import oop.ex7.nodes.generalTypes.SimpleNode;
import oop.ex7.nodes.nodeFactories.DecelerationType;
import oop.ex7.nodes.nodeFactories.OneLinerNodeFactory;
import oop.ex7.fileReader.LineToken;

/**
 * A class representing a method OneLinerNode in the code
 * @author owner
 */
public class MethodNode extends AdvancedNode {

	private static final String GENERAL_ARRAY_METHOD_REGEX = "\\w+\\[" +
			POSSIBLE_SPACE +
			RIGHT_BRACKET + ALL_VALUES;
	private final static String structurePattern = "((?:" + VARIABLE_NAME_REGEX
			+ ")(\\[*\\]*))\\s+([A-Za-z]\\w*)\\s*(\\(((.)*)\\))\\s*\\{";
	private static final int METHOD_PARAMETERS_GROUP = 6;
	private static final String NO_PARAMETERS_FLAG = "NO_PARAMETERS";
	private static final int METHOD_NAME = 4;
	private final static NodeTypes NODE_TYPE = NodeTypes.METHOD_NODE;
	private static final String SEPERATOR_SIGN = ",";
	private static final String SPACE = "\\s*";


	/**
	 * The method node constructor
	 * @param declerationStatment - the deceleration line
	 * @param lineNumber - the file line number
	 * @param decelerationType - the checked deceleration type
	 * @throws BadMemberNameDiscovered in case the node identifier is illegal
	 */
	public MethodNode(
			String declerationStatment, int lineNumber,
			DecelerationType decelerationType) throws MasterTypeOneException {
		super(declerationStatment, lineNumber, structurePattern,
				decelerationType, NODE_TYPE);

		this.setUsedVariableName(METHOD_NAME);
		this.setNodeName();

		if (this.getNodeDeceleration().matches(GENERAL_ARRAY_METHOD_REGEX)) {
			this.setLeftSideParameterArray(true);
		}
		this.setScopeNodeParameters(matcher.group(METHOD_PARAMETERS_GROUP));
		checkScopeParameters();
	}

	/**
	 * the method checks the parameters of the scope node and stores their
	 * types
	 * to later be checked if needed in case ofa call and stored to be used in
	 * the method
	 * @throws MasterTypeOneException
	 */
	@Override
	public void checkScopeParameters() throws MasterTypeOneException {
		if (! this.getScopeNodeParameters().matches(SPACE)) {
			String[] methodParametersSplit = this.getScopeNodeParameters()
					.split(SEPERATOR_SIGN);
			for (String parameter : methodParametersSplit) {
				LineToken tempToken = new LineToken(parameter.trim(),
						this.getNodeLineNumber());
				SimpleNode newNode = OneLinerNodeFactory
						.createOneLineNode(tempToken);
				this.getNodeList().add(newNode);
				newNode.setAssigned(true);
				this.getScopeParametersDeclerationTypesList()
						.add(newNode.getNodeDecelerationType());
				newNode.setNodeParent(this);
			}
		} else {
			this.setScopeNodeParameters(NO_PARAMETERS_FLAG);
		}
	}

	/**
	 * constructor
	 */
	MethodNode() {
		super();
	}

	/**
	 * @return the node pattern
	 */
	public static String getStructurePattern() {
		return structurePattern;
	}

	/**
	 * not needed therfore not implemented
	 * @throws MasterTypeOneException
	 */
	@Override
	public void isValid() throws MasterTypeOneException {

	}
}
