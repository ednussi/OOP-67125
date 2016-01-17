package oop.ex7.nodes.generalTypes;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.NodeTypes;
import oop.ex7.nodes.nodeFactories.DecelerationType;
import oop.ex7.semanticAnalyzer.DuplicateVariableDeclerationException;

import java.util.ArrayList;

/**
 * An abstract calss represnting an advance node - a node which contains a scope
 * and is not limited to one line.
 * @author owner
 */
public abstract class AdvancedNode extends SimpleNode {

	private String scopeNodeParameters;
	private ArrayList<DecelerationType> scopeParameters = new ArrayList<>();
	private VariableTable veriableTable = new VariableTable();
	private VariableTable methodsTable = new VariableTable();
	private ArrayList<SimpleNode> nodeList = new ArrayList<>();

	protected AdvancedNode() {
		this.setNodeType(NodeTypes.SCOPE_NODE);
	}

	/**
	 * constructor
	 * @param declerationStatment - the declaration statement
	 * @param lineNumber - the line number in the text
	 * @param structurePattern - the pattern it need to match
	 * @param decelerationType - its type
	 */
	protected AdvancedNode(
			String declerationStatment, int lineNumber,
			String structurePattern,
			DecelerationType decelerationType, NodeTypes nodeType) {
		super(lineNumber, declerationStatment, structurePattern,
				decelerationType, nodeType);
	}

	/**
	 * Stores a node memebr within
	 * @param tableLink - the variableTable
	 * @param nodeId - the node's identifier
	 * @param nodeDeclaredType - it's type
	 * @param nodeLink - the node iteslf as a link
	 * @throws DuplicateVariableDeclerationException
	 */
	public static void storeNodeMember(
			VariableTable tableLink, String nodeId,
			DecelerationType nodeDeclaredType, SimpleNode nodeLink)
			throws DuplicateVariableDeclerationException {
		tableLink.insertEntry(nodeId, nodeDeclaredType, nodeLink);
	}


	/**
	 * @return Scope node parameter getter
	 */
	protected String getScopeNodeParameters() {
		return scopeNodeParameters;
	}

	/**
	 * Scope node parameter setter
	 * @param scopeNodeParameters
	 */
	protected void setScopeNodeParameters(String scopeNodeParameters) {
		this.scopeNodeParameters = scopeNodeParameters;
	}

	/**
	 * MethodsTable Getter
	 * @return MethodsTable
	 */
	public VariableTable getMethodsTable() {
		return this.methodsTable;
	}

	/**
	 * connects a child a node to the father
	 * @param newChild - the node
	 */
	public void connectNode(SimpleNode newChild) {
		nodeList.add(newChild);
	}

	/**
	 * @return the veriableTable
	 */
	public VariableTable getVeriableTable() {
		return this.veriableTable;
	}

	/**
	 * @return the nodeList
	 */
	public ArrayList<SimpleNode> getNodeList() {
		return this.nodeList;
	}

	/**
	 * An abstract class which check the given scope parameters within
	 * @throws MasterTypeOneException
	 */
	public abstract void checkScopeParameters() throws MasterTypeOneException;

	/**
	 * @return - Scope parameters declartion types list getter
	 */
	public ArrayList<DecelerationType> getScopeParametersDeclerationTypesList
			() {
		return scopeParameters;
	}
}
