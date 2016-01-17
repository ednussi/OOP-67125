package oop.ex7.semanticAnalyzer;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.NodeTypes;
import oop.ex7.nodes.generalTypes.AdvancedNode;
import oop.ex7.nodes.generalTypes.SimpleNode;
import oop.ex7.nodes.mediator.NodeMediator;
import oop.ex7.nodes.nodeFactories.DecelerationType;
import oop.ex7.nodes.oneLineNodes.ReturnNode;
import oop.ex7.nodes.scopeNodes.EnvironmentNode;

/**
 * the semantic analyzer which checks the deeper side of the declerations,
 * checks the assignments, methods call, variabals and etc. Created by fimak on
 * 18/06/14.
 */
public class SemanticAnalyzer {
	private static final String EMPTY_ARRAY_RETURN_INDICATOR = "\\{\\}";

	/**
	 * this runs the code verification process
	 * @param treeRoot the environment node which holds the methods and the
	 * member variables
	 * @throws MasterTypeOneException
	 */
	public static void VerifyCode(EnvironmentNode treeRoot)
			throws MasterTypeOneException {
		verifyMethodMembers(treeRoot);
		verifyMethods(treeRoot);
		testScopeNodesParameter(treeRoot);
	}

	/**
	 * Initializes the methods
	 * @param targetNode - the tree root
	 * @throws MasterTypeOneException
	 */
	private static void verifyMethods(AdvancedNode targetNode)
			throws MasterTypeOneException {
		for (SimpleNode currentNode : targetNode.getNodeList()) {
			if (isAdvancedNode(currentNode)) {
				AdvancedNode tempNodeHolder = (AdvancedNode) currentNode;
				verifyMethodMembers(tempNodeHolder);
				verifyMethods(tempNodeHolder);
				if (currentNode.getNodeType().equals(NodeTypes.METHOD_NODE)) {
					checkMethodsReturn(tempNodeHolder);
				}
			}
		}
	}

	private static boolean isAdvancedNode(SimpleNode currentNode) {
		return currentNode.getNodeType().equals(NodeTypes.SCOPE_NODE)
				|| currentNode.getNodeType()
				.equals(NodeTypes.METHOD_NODE);
	}

	/**
	 * Verifies the variables deceleration, checks the assigned values or
	 * variabls or methods, and finally if all goes well stors in the apporiate
	 * scope node or method node variable table as local variable so it can be
	 * later refranced. the method calls the isValid method in each node.
	 * @param scopeNode - the tree root
	 * @throws MasterTypeOneException in case of bad syntax
	 */
	private static void verifyMethodMembers(AdvancedNode scopeNode)
			throws MasterTypeOneException {
		for (SimpleNode node : scopeNode.getNodeList()) {
			if (! (isAdvancedNode(node))) {
				node.isValid();
			}
		}
	}

	/**
	 * Checks the Methods Return statements
	 * @param scopeNode - the tree root
	 */
	private static void checkMethodsReturn(AdvancedNode scopeNode)
			throws MasterTypeOneException {
		int itemCount = scopeNode.getNodeList().size();
		SimpleNode returnNodeLink = scopeNode.getNodeList().get(itemCount - 1);
		isReturnOnlyOnceInMethod(scopeNode);

		if (returnNodeLink.getNodeType().equals(NodeTypes
				.METHOD_RETURN_NODE)) {
			String returnNodeValue = returnNodeLink.getNodeName();
			DecelerationType requiredReturnType = scopeNode
					.getNodeDecelerationType();
			Boolean isArrayReturned = scopeNode.isLeftSideParameterArray();
			if (scopeNode.getNodeDecelerationType()
					.equals(DecelerationType.VOID) && isEmptyReturnStatement(
					returnNodeValue)) {
				return;
			} else {
				verifyVoidMethodReturn(scopeNode, returnNodeLink);
			}
			if (! (returnNodeValue.matches(EMPTY_ARRAY_RETURN_INDICATOR)
					&& isArrayReturned)) {
				NodeMediator.compareTypes(returnNodeLink, requiredReturnType,
						isArrayReturned, returnNodeValue);
			}
		} else {
			if (! scopeNode.getNodeDecelerationType()
					.equals(DecelerationType.VOID)) {
				throw new MethodReturnSyntexException(
						returnNodeLink.getNodeLineNumber());
			}
		}
	}

	/**
	 * Checks if either the method is not declered void and the return is empty
	 * or either it's aint void and the return empty;
	 * @param scopeNode the method node in which the return resides
	 * @param returnNodeLink the return node
	 * @throws MethodReturnSyntexException
	 */
	private static void verifyVoidMethodReturn(
			AdvancedNode scopeNode, SimpleNode returnNodeLink)
			throws MethodReturnSyntexException {


		String returnNodeValue = returnNodeLink.getNodeName();
		if ((! scopeNode.getNodeDecelerationType().equals(DecelerationType
				.VOID)
				&& isEmptyReturnStatement(returnNodeValue)) || (
				scopeNode.getNodeDecelerationType()
						.equals(DecelerationType.VOID)
						&& ! isEmptyReturnStatement(returnNodeValue))) {
			throw new MethodReturnSyntexException(
					returnNodeLink.getNodeLineNumber());
		}
	}

	/**
	 * verifies there's only one return statmnet per method
	 * @param scopeNode
	 * @throws MoreThenOneReturnStatement
	 */
	private static void isReturnOnlyOnceInMethod(AdvancedNode scopeNode)
			throws MoreThenOneReturnStatement {
		int returnCount = 0;
		for (SimpleNode node : scopeNode.getNodeList()) {
			if (node.getNodeType().equals(NodeTypes.METHOD_RETURN_NODE)) {
				returnCount++;
			}
		}
		if (returnCount > 1) {
			throw new MoreThenOneReturnStatement(scopeNode.getNodeLineNumber
					());
		}
	}

	/**
	 * Check the empty (void) return statements
	 * @param returnNodeValue - the returned node value
	 * @return
	 */
	private static boolean isEmptyReturnStatement(String returnNodeValue) {
		return returnNodeValue.equals(ReturnNode.EMPTY_RETURN_STATEMENT);
	}

	/**
	 * Checks the parameter in scope nodes such as if condtinal and whiles in
	 * each method and scope node (if nested)
	 * @param treeRoot the tree root
	 * @throws MasterTypeOneException
	 */
	private static void testScopeNodesParameter(AdvancedNode treeRoot)
			throws MasterTypeOneException {
		for (SimpleNode node : treeRoot.getNodeList()) {
			if (isAdvancedNode(node)) {
				if (node.getNodeType().equals(NodeTypes.SCOPE_NODE)) {
					parameterCheck((AdvancedNode) node);
				}
				testScopeNodesParameter((AdvancedNode) node);
			}
		}
	}

	/**
	 * The Parameter Check Method
	 * @param targetNode - the tree root
	 * @throws MasterTypeOneException
	 */
	private static void parameterCheck(AdvancedNode targetNode)
			throws MasterTypeOneException {
		targetNode.checkScopeParameters();
	}
}
