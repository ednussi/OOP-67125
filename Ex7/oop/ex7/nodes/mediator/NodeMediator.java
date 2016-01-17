package oop.ex7.nodes.mediator;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.CastingRulesTable;
import oop.ex7.nodes.IllegalCastingOfDifferentDataTypes;
import oop.ex7.nodes.NodeTypes;
import oop.ex7.nodes.generalTypes.AdvancedNode;
import oop.ex7.nodes.generalTypes.SimpleNode;
import oop.ex7.nodes.generalTypes.VariableTable;
import oop.ex7.nodes.nodeFactories.DecelerationType;
import oop.ex7.nodes.oneLineNodes.ArrayAssignmentNodeDecorator;
import oop.ex7.nodes.oneLineNodes.CallMethodNode;
import oop.ex7.nodes.scopeNodes.MethodNode;
import oop.ex7.semanticAnalyzer.DuplicateVariableDeclerationException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * the node mediator supposed to support operationes between the method and
 * scope nodes and their children during the code verification test mainly in
 * the samantical analysys process
 */
public class NodeMediator {

	private final static String SPECIFIC_ASSIGNMENT_OF_ARRAY = 
	"([A-Za-z_]\\w*)\\[((\\w+))" + "\\]";
	private final static String SPECIFIC_METHOD_CALL = 
			"(\\w+)\\s*\\(((.)*)\\);?";
	private final static String SPECIFIC_ARRAY =  "\\{\\s*\\}";
	private final static String WITHIN_ARRAY_SEPERATOR = ",";
	private final static int NAME_OF_ARRAY = 1;
	private final static int NAME_OF_METHOD_CALL = 1;
	private final static int VARIABLE_TO_BE_ASSIGNED = 1;
	private final static int WHAT_IS_ASSIGNED_TO_VARIABLE = 3;
	private final static int METHOD_INNER_PARAMETERS = 2;
	private final static int EMPTY = 0;
	private final static int FIRST_PARAMETER = 0;
	
	
	private NodeMediator() {
	}

	/**
	 * ensures the values in the array braces("{}")
	 * @param currentArrayNode the related array
	 * @param values hte values inside the Braces
	 * @throws WrongSyntaxVariabalsInPretzels = thrown inc ase the values
	 * doesn't fit the approved values or they're not in the correct format.
	 */
	private static void correctValuesInBraces(
			SimpleNode currentArrayNode, String values)
			throws WrongSyntaxVariabalsInPretzels {
		if (! values.matches(SimpleNode.POSSIBLE_ASSIGNED_VALUES_REGEX +
				"(\\s*,\\s*" + SimpleNode.POSSIBLE_ASSIGNED_VALUES_REGEX +
				")*")) {
			throw new WrongSyntaxVariabalsInPretzels(
					currentArrayNode.getNodeLineNumber());
		}
	}

	/**
	 * stores a variable in the parent node
	 * @param nodeId node name
	 * @param nodeDeclaredType node declered type
	 * @param nodeLink the node to store
	 * @throws DuplicateVariableDeclerationException
	 */
	public static void storeVariableInParent(
			String nodeId, DecelerationType nodeDeclaredType,
			SimpleNode nodeLink) throws DuplicateVariableDeclerationException {
		AdvancedNode
				.storeNodeMember(nodeLink.getParentNode().getVeriableTable(),
						nodeId, nodeDeclaredType, nodeLink);
	}

	/**
	 * Checks if a method exists and send back its node if it does
	 * @param nodeLink - the node which contained the method call
	 * @param targetNode - the string of the method which need to be found if
	 * initializied
	 * @return the node which the method was initialized in
	 * @throws NoSuchMethodORVariable
	 */
	private static AdvancedNode checkIfMethodExists(
			SimpleNode nodeLink, String targetNode)
			throws NoSuchMethodORVariable {

		AdvancedNode parentNodeLink = nodeLink.getParentNode();
		VariableTable.TableEntry searchResult ;
			while (parentNodeLink != null) {
				searchResult = parentNodeLink.getMethodsTable()
						.lookupEntry(targetNode);
				parentNodeLink = parentNodeLink.getParentNode();
				if (searchResult != null) {
					return (AdvancedNode) searchResult.getLinkedNode();
				}
			}
			throw new NoSuchMethodORVariable(nodeLink.getNodeLineNumber());
	}

	/**
	 * Tries to find the requested item to check if it's can be assigned
	 * (either
	 * a varaible or method)  to the requested type.
	 * @param callingNode the calling node the searches the variable
	 * @param requiredType the wanted type
	 * @param searchTarget the search target
	 * @return iff found the node connected to the searched item.
	 * @throws NoSuchMethodORVariable in case didnt find any variable or emthod
	 * by this name
	 * @throws IllegalCastingOfDifferentDataTypes in case found the tiem but
	 * its
	 * not in the proper types and cant be casted to the wanted type.
	 * @throws AssignedVariableExistsButNotInitialized the item isnt iniizlized
	 * and therfore cannot be assigned
	 */
	public static SimpleNode deepValidationItemToDecleredItem(
			SimpleNode callingNode, DecelerationType requiredType,
			String searchTarget)
			throws NoSuchMethodORVariable, IllegalCastingOfDifferentDataTypes,
			AssignedVariableExistsButNotInitialized {

		SimpleNode searchResult;
		try{
			searchResult = checkIfVerExists(callingNode, searchTarget);
		} catch (NoSuchMethodORVariable noSuchMethodORVariable){
			searchResult = checkIfMethodExists(callingNode, searchTarget);
		}

		if (searchResult == null) {
			throw new NoSuchMethodORVariable(callingNode.getNodeLineNumber());
		}

		if (! CastingRulesTable.isLegalCasting(requiredType,
				searchResult.getNodeDecelerationType())) {
			throw new NoSuchMethodORVariable(callingNode.getNodeLineNumber());
		}

		if (! searchResult.isAssigned()) {
			throw new AssignedVariableExistsButNotInitialized(
					callingNode.getNodeLineNumber());
		}

		return searchResult;
	}

	/**
	 * Tries to verify an assigned value of diffrent types and to check if it's
	 * matched the rquiment of the assigned node
	 * @param node the assigned node
	 * @param wantedType the wanted type of value to be assigned
	 * @param isLeftSideIsArrayRequirement true iff an array type variable is
	 * needed
	 * @param parameter the id of the assigned item
	 * @return true iff a match was found.
	 * @throws MasterTypeOneException in case there's a mismatch.
	 */
	public static boolean compareTypes(
			SimpleNode node, DecelerationType wantedType,
			boolean isLeftSideIsArrayRequirement, String parameter)
			throws MasterTypeOneException {
		try{
			parameter = parameter.trim();
			if (isMethodCall(parameter)) {
				verifyMethodCall(node, wantedType, parameter);
			} else if (
					parameter.matches(DecelerationType.getIsArrayCheckRegex())
							&& isLeftSideIsArrayRequirement) {
				verifyArray(node, wantedType, parameter);
				return true;
			} else if (parameter.matches(
					"-?\\s*" + SimpleNode.ASSIGNED_VALUES_REGEX_BASIC)) {

				return verifyAssignedLiteralValue(parameter, wantedType, node,
						isLeftSideIsArrayRequirement);

			} else if (parameter.matches(SPECIFIC_ASSIGNMENT_OF_ARRAY)) {
				Matcher matcher = 
						Pattern.compile(SPECIFIC_ASSIGNMENT_OF_ARRAY)
						.matcher(parameter);
				matcher.matches();

				deepValidationItemToDecleredItem(node, wantedType,
						matcher.group(NAME_OF_ARRAY));


				if (parameter
						.matches(ArrayAssignmentNodeDecorator.ARRAY_ASSIGNMENT)
						&& node.isLeftSideParameterArray()) {
					throw new IllegalAssignmentFromArrayToInteger(
							node.getNodeLineNumber());
				}


			} else if (SimpleNode.isMathOperation(parameter) && (
					wantedType.equals(DecelerationType.DOUBLE) || wantedType
							.equals(DecelerationType.INT))) {
				verifyMathOperation(node, wantedType, parameter);

			} else {
				throw new ReturnTypeNotFitMethodDeclarations(
						node.getNodeLineNumber());
			}
			return true;
		} catch (IllegalCastingOfDifferentDataTypes e){
			throw new ReturnTypeNotFitMethodDeclarations(
					node.getNodeLineNumber());
		}
	}

	/**
	 * verifies that an assigned litral value is either a lnowen variable or
	 * method which can return the rwuired type wither a value that fits the
	 * wanted pattern by the variablas type
	 * @param node the assigned node
	 * @param wantedType the wanted type of value to be assigned
	 * @param isArrayRequired true iff an array type variable is needed
	 * @param parameter the id of the assigned item
	 * @return true iff the paramter fits the wanted type
	 */
	private static boolean verifyAssignedLiteralValue(
			String parameter, DecelerationType wantedType, SimpleNode node,
			boolean isArrayRequired) throws MasterTypeOneException {

		boolean isValueParameter = parameter
				.matches(wantedType.getVariableTypeRegex());
		if (isValueParameter && ! isArrayRequired) {
			return true;
		}

		SimpleNode tempFoundNode = deepValidationItemToDecleredItem(node,
				wantedType, prepareVariableForCompering(parameter));
		if (tempFoundNode.isLeftSideParameterArray() == isArrayRequired) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * verifies the method call decleration type - checks the returned type vs
	 * the wanted
	 * @param node the assigned node
	 * @param wantedType the wanted type of value to be assigned
	 * @param parameter the id of the assigned item
	 * @throws MasterTypeOneException
	 */
	private static void verifyMethodCall(
			SimpleNode node, DecelerationType wantedType, String parameter)
			throws MasterTypeOneException {
		Matcher matcher = Pattern
				.compile(CallMethodNode.getStructurePattern())
				.matcher(parameter);
		matcher.matches();
		AdvancedNode methodCallParameter = checkIfMethodExists(node,
				matcher.group(NAME_OF_METHOD_CALL));
		if (! CastingRulesTable.isLegalCasting(wantedType,
				methodCallParameter.getNodeDecelerationType())) {
			throw new ReturnTypeNotFitMethodDeclarations(
					node.getNodeLineNumber());
		}
		calledMethodCorrectly(node, parameter);
	}

	/**
	 * tries to verify if the arrays itmes fit it's decleration type
	 * @param node the assigned node
	 * @param wantedType the wanted type of value to be assigned
	 * @param parameter the id of the assigned item
	 * @throws MasterTypeOneException
	 */
	private static void verifyArray(
			SimpleNode node, DecelerationType wantedType, String parameter)
			throws MasterTypeOneException {
		if (parameter.matches(SPECIFIC_ARRAY)) {
			return;
		}
		String returnString = SimpleNode.getInsideString(parameter);
		correctValuesInBraces(node, returnString);
		String[] splitArrayItems = returnString.split(WITHIN_ARRAY_SEPERATOR);

		for (String item : splitArrayItems) {
			if (! compareTypes(node, wantedType, false, item)) {
				throw new ReturnTypeNotFitMethodDeclarations(
						node.getNodeLineNumber());
			}
		}
	}

	/**
	 * in case of a math operation - tries toverifyy it's counterparts their
	 * type and fitness to be with an operator
	 * @param node the assigned node
	 * @param wantedType the wanted type of value to be assigned
	 * @param parameter the id of the assigned item
	 * @throws MasterTypeOneException
	 */
	private static void verifyMathOperation(
			SimpleNode node, DecelerationType wantedType, String parameter)
			throws MasterTypeOneException {
		Matcher matcher = Pattern.compile(SimpleNode.SJAVAC_MATH_OPERATION)
				.matcher(parameter);
		matcher.matches();
		String leftSideOfOperation = prepareVariableForCompering(
				matcher.group(VARIABLE_TO_BE_ASSIGNED));
		String rightSideOfOperation = prepareVariableForCompering(
				matcher.group(WHAT_IS_ASSIGNED_TO_VARIABLE));
		if (isMathOperation(leftSideOfOperation) || isMathOperation(
				rightSideOfOperation)) {
			throw new IllegalMathOperation(node.getNodeLineNumber());
		}
		compareTypes(node, wantedType, false, leftSideOfOperation);
		compareTypes(node, wantedType, false, rightSideOfOperation);
	}

	private static boolean isMathOperation(String string) {
		return string.matches(SimpleNode.SJAVAC_MATH_OPERATION);
	}

	/**
	 * tries to find the wanted variable in the available scopes
	 * @param nodeLink the calling node so it can iterate the fathers
	 * @param targetNodeName the wanted variable name
	 * @return the wanted node
	 * @throws NoSuchMethodORVariable iff not found will throw this
	 */
	public static SimpleNode checkIfVerExists(
			SimpleNode nodeLink, String targetNodeName)
			throws NoSuchMethodORVariable {
		AdvancedNode parentNodeLink = nodeLink.getParentNode();
		VariableTable.TableEntry searchResult;
			while (parentNodeLink != null) {
				searchResult = parentNodeLink.getVeriableTable()
						.lookupEntry(targetNodeName);
				parentNodeLink = parentNodeLink.getParentNode();
				if (searchResult != null) {
					return searchResult.getLinkedNode();
				}
			}
			throw new NoSuchMethodORVariable(nodeLink.getNodeLineNumber());
	}
	/**
	 * checks if the the given line matched a amthod call
	 * @param parameterCall the line to check
	 * @return true if the line amtches the pattern fo a method call
	 */
	private static boolean isMethodCall(String parameterCall) {
		return parameterCall
				.matches(CallMethodNode.getStructurePattern() + "$");
	}

	/**
	 * prpers the given variable for compering trimming unwanted spaced and
	 * other possiable uneeded signes
	 * @param targetString
	 * @return
	 */
	private static String prepareVariableForCompering(String targetString) {
		String result;
		result = targetString
				.replaceAll(SimpleNode.OPERATORS + "$|^" + SimpleNode
						.OPERATORS,
						"");
		result = result.trim();
		return result;
	}

	/**
	 * Checks if a method was called correctly
	 * @param node - the node that contained the method call
	 * @param methodCallString - the string of the method call
	 * @return - true iff the method was called correctly
	 * @throws NoSuchMethodORVariable
	 * @throws AssignedVariableExistsButNotInitialized
	 */
	public static void calledMethodCorrectly(
			SimpleNode node, String methodCallString)
			throws MasterTypeOneException {
		Matcher matcher = Pattern.compile(SPECIFIC_METHOD_CALL)
				.matcher(methodCallString);
		matcher.matches();
		String[] insertedParameters = matcher.group(METHOD_INNER_PARAMETERS)
				.split(WITHIN_ARRAY_SEPERATOR);

		MethodNode calledMethodNode = (MethodNode) checkIfMethodExists(node,
				matcher.group(NAME_OF_METHOD_CALL));
		ArrayList<DecelerationType> calledMethodParametersTypes
				= calledMethodNode.getScopeParametersDeclerationTypesList();
		if (calledMethodParametersTypes.size() != insertedParameters.length
				&& ! (insertedParameters[FIRST_PARAMETER].equals("")
				&& calledMethodParametersTypes.size() == EMPTY)) {
			throw new RequiredMethodCallParametersNotFitContract(
					node.getNodeLineNumber());
		}
		if (! calledMethodParametersTypes.isEmpty()) {
			Iterator<DecelerationType> parametersTypeIterator
					= calledMethodParametersTypes.iterator();
			for (String insertedParameter : insertedParameters) {
				compareTypes(node, parametersTypeIterator.next(),
						node.isLeftSideParameterArray(), insertedParameter);
			}
		}

	}

	/**
	 * connect a child node to it's parent
	 * @param fatherNode
	 * @param childNode
	 * @throws DuplicateVariableDeclerationException in case there already a
	 * child node with the same name aka its a duplicate decleration in a
	 * amethod it will be thrown
	 */
	public static void connectChildToParent(
			AdvancedNode fatherNode, SimpleNode childNode)
			throws DuplicateVariableDeclerationException {
		childNode.setNodeParent(fatherNode);
		fatherNode.connectNode(childNode);
		if (childNode.getNodeType().equals(NodeTypes.METHOD_NODE)) {
			storeMethodInParent(childNode.getNodeName(),
					childNode.getNodeDecelerationType(), childNode);
		}
	}

	/**
	 * store a method in a a parent method table - declare it.
	 * @param nodeId the ndoe name
	 * @param nodeDeclaredType the node type
	 * @param nodeLink the node to be linked
	 * @throws DuplicateVariableDeclerationException in case a similier a
	 * method
	 * was already declered
	 */
	private static void storeMethodInParent(
			String nodeId, DecelerationType nodeDeclaredType,
			SimpleNode nodeLink) throws DuplicateVariableDeclerationException {
		AdvancedNode.storeNodeMember(nodeLink.getParentNode()
				.getMethodsTable(),
				nodeId, nodeDeclaredType, nodeLink);
	}
}