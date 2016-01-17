package oop.ex7.nodes.generalTypes;

import oop.ex7.main.MasterTypeOneException;
import oop.ex7.nodes.BadMemberNameDiscovered;
import oop.ex7.nodes.NodeTypes;
import oop.ex7.nodes.mediator.NodeMediator;
import oop.ex7.nodes.nodeFactories.DecelerationType;
import oop.ex7.nodes.oneLineNodes.CallMethodNode;
import oop.ex7.nodes.oneLineNodes.VariableNode;
import oop.ex7.nodes.oneLineNodes.variabals.CharNode;
import oop.ex7.nodes.oneLineNodes.variabals.DoubleNode;
import oop.ex7.nodes.oneLineNodes.variabals.IntNode;
import oop.ex7.nodes.oneLineNodes.variabals.StringNode;
import oop.ex7.nodes.scopeNodes.ScopeNode;
import oop.ex7.parser.LineParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * an abstract Class represent a node - The father of all nodes
 * @author owner
 */
public abstract class SimpleNode {
	protected static final String RIGHT_PATERNESES = "\\)";
	protected static final String OR = "|";
	protected static final String POSSIBLE_BASIC_TYPES_REGEX = "("
			+ VariableNode.INT + OR + VariableNode.DOUBLE + OR
			+ VariableNode.STRING + OR + VariableNode.CHAR + OR
			+ VariableNode.BOOLEAN + ")";
	protected static final String COMMA = ",";
	protected static final String LEFT_BRACKET = "\\[";
	protected static final String RIGHT_BRACKET = "\\]";
	protected static final String POSSIBLE_SPACE = "\\s*";
	protected static final String EMPTY_BRACKETS = LEFT_BRACKET +
			POSSIBLE_SPACE
			+
			RIGHT_BRACKET;
	protected static final String POSSIBLE_INITIALIZE_TYPES_REGEX =
			POSSIBLE_BASIC_TYPES_REGEX + POSSIBLE_SPACE +
					"(" + EMPTY_BRACKETS + ")?";
	public static final String OPERATORS = "(\\+|\\-|\\*|/)";
	private static final String MATH_OPERATION = "(" + IntNode.INT_REGEX + OR +
			DoubleNode.DOUBLE_REGEX + ")" + POSSIBLE_SPACE + OPERATORS
			+ POSSIBLE_SPACE + "(" +
			IntNode.INT_REGEX +
			OR + DoubleNode.DOUBLE_REGEX + ")";
	protected static final String ALL_VALUES = ".*";
	public static final String SJAVAC_MATH_OPERATION = "(" +
			ALL_VALUES + ")" +
			POSSIBLE_SPACE + OPERATORS + POSSIBLE_SPACE + "(([^;])*)";

	private static final String RIGHT_BRACES = "\\}";
	protected static final String LEFT_BRACES = "\\{";
	protected static final String RETURNING_ARRAY = LEFT_BRACES + ALL_VALUES
			+ RIGHT_BRACES;
	public static final String VARIABLE_NAME_REGEX
			= "([A-Za-z]\\w*|_[A-Za-z0-9]+)";
	protected static final String CALLING_FROM_ARRAY_REGEX = "\\w+\\[(" + OR
			+ IntNode.INT_REGEX + OR + VARIABLE_NAME_REGEX + OR +
			CallMethodNode.CALL_METHOD_REGEX + ")" + RIGHT_BRACKET
			+ POSSIBLE_SPACE;
	private static final String IN_ARRAY_BRACKETS = POSSIBLE_SPACE +
			"(\\w+" + POSSIBLE_SPACE + "(\\(" + POSSIBLE_SPACE
			+ RIGHT_PATERNESES + POSSIBLE_SPACE + ")*|" + POSSIBLE_SPACE + "-?"
			+ MATH_OPERATION + POSSIBLE_SPACE + ")" + POSSIBLE_SPACE;
	protected static final String ARRAY_ASSIGNING = VARIABLE_NAME_REGEX + "" +
			"(?:("
			+ POSSIBLE_SPACE + LEFT_BRACKET +
			"(" +
			IN_ARRAY_BRACKETS +
			")" + RIGHT_BRACKET + "))";
	public final static String ASSIGNED_VALUES_REGEX_BASIC = "(" +
			VARIABLE_NAME_REGEX + OR + IntNode.INT_REGEX + OR
			+ DoubleNode.DOUBLE_REGEX + OR +
			CallMethodNode.CALL_METHOD_REGEX + OR + StringNode.STRING_REGEX
			+ OR
			+ CharNode.CHAR_REGEX + ")";
	public static final String POSSIBLE_ASSIGNED_VALUES_REGEX = "("
			+ ASSIGNED_VALUES_REGEX_BASIC + OR +
			ARRAY_ASSIGNING +
			OR + SJAVAC_MATH_OPERATION + OR + RETURNING_ARRAY + OR +
			CALLING_FROM_ARRAY_REGEX + ")";
	protected static final String ASSIGNMENT_OPERATOR = "\\=";
	protected static final String LEFT_PANTERNESES = "\\(";
	protected static final String RETURN = "return";
	private static final String ILLEGAL_NAME_PATTERNS =
			"private|public|static|final|" + RETURN + OR + ScopeNode.WHILE + OR
					+ ScopeNode.IF + "|(^_$)" +
					"|void|" + POSSIBLE_BASIC_TYPES_REGEX;


	protected static final String SEMICOLON_END_OF_LINE = ";$";
	protected static final int NO_ASSIGNED_VALUE_FLAG = - 1;
	protected Matcher matcher;
	private int usedVariableName;
	private String nodeDecleration;
	private int nodeLineNumber;
	private AdvancedNode parentNode;
	private String nodeName;
	private NodeTypes nodeType;
	private DecelerationType nodeDeclaredType;
	private boolean assigned = false;
	private String nodeStructureRegExpression;
	private String assignedValue;
	private int comparedVariableName;
	private boolean isLeftSideParameterArray = false;

	/**
	 * constructor
	 * @param lineNumber - the line number in the text
	 * @param decelerationStatement - the line data
	 * @param structurePattern - its regex structure pattern
	 * @param decelerationType - its deceleration type
	 * @param nodeType the node type
	 */
	SimpleNode(
			int lineNumber, String decelerationStatement,
			String structurePattern, DecelerationType decelerationType,
			NodeTypes nodeType) {
		this.nodeLineNumber = lineNumber;
		this.nodeDecleration = decelerationStatement;
		this.nodeDeclaredType = decelerationType;
		this.nodeStructureRegExpression = structurePattern;
		this.nodeType = nodeType;
		this.initializeMatcher();
	}

	/**
	 * intillize the pattern matcher in order to set the appriate variablas
	 * from
	 * the dffrent groups
	 */
	void initializeMatcher() {
		Pattern compiledStructurePattern = Pattern
				.compile(this.nodeStructureRegExpression);
		this.matcher = compiledStructurePattern
				.matcher(this.getNodeDeceleration());
		this.matcher.matches();
	}

	/**
	 * @return node decleration e.g. the line in the code
	 */
	public String getNodeDeceleration() {
		return this.nodeDecleration;
	}

	/**
	 * constructor
	 */
	SimpleNode() {
	}

	/**
	 * get the string inside the braces
	 * @param string
	 * @return
	 */
	public static String getInsideString(String string) {
		if (string.startsWith("{") && string.endsWith("}")) {
			return string.substring(1, string.length() - 1);
		}
		return string;
	}

	/**
	 * checks if the string matched a pattern of a math operation
	 * @param string
	 * @return true iff matches
	 */
	public static boolean isMathOperation(String string) {
		return string.matches(SJAVAC_MATH_OPERATION);
	}

	/**
	 * @return node line number
	 */
	public int getNodeLineNumber() {
		return this.nodeLineNumber;
	}

	/**
	 * returnes the aprent node of the node
	 * @return
	 */
	public AdvancedNode getParentNode() {
		return this.parentNode;
	}

	/**
	 * @return the node name
	 */
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * sets the node name after checking it for illeagal start and words
	 * @param nodeName
	 * @throws BadMemberNameDiscovered in case of illegal name found or if it
	 * has illeagal symbols.
	 */
	protected void setNodeName(String nodeName) throws
			BadMemberNameDiscovered {
		checkForIllegalName(nodeName);
		this.nodeName = nodeName.trim();
	}

	/**
	 * set the node name
	 */
	protected void setNodeName() throws MasterTypeOneException {
		setNodeName(matcher.group(this.getUsedVariableName()));
	}

	/**
	 * Check if string is an illegal name
	 * @param nodeName - the string of the name
	 * @throws BadMemberNameDiscovered
	 */
	private void checkForIllegalName(String nodeName)
			throws BadMemberNameDiscovered {
		if (nodeName.matches(ILLEGAL_NAME_PATTERNS)) {
			throw new BadMemberNameDiscovered(this.getNodeLineNumber());
		}
	}

	/**
	 * get the node name group number
	 * @return
	 */
	int getUsedVariableName() {
		return usedVariableName;
	}

	/**
	 * set the node name group number
	 * @param usedVariableName
	 */
	protected void setUsedVariableName(int usedVariableName) {
		this.usedVariableName = usedVariableName;
	}

	/**
	 * get the assigned vbariable/method name group number
	 * @param comparedVariableName
	 */
	void setComparedVariableName(int comparedVariableName) {
		this.comparedVariableName = comparedVariableName;
	}

	/**
	 * get the node type
	 * @return
	 */
	public NodeTypes getNodeType() {
		return nodeType;
	}

	/**
	 * set the node type
	 * @param nodeType
	 */
	protected void setNodeType(NodeTypes nodeType) {
		this.nodeType = nodeType;
	}

	/**
	 * set the node parent
	 * @param fatherNode
	 */
	public void setNodeParent(SimpleNode fatherNode) {
		this.parentNode = (AdvancedNode) fatherNode;

	}

	/**
	 * check the assigned variable if it's fits the requested
	 * @return
	 * @throws MasterTypeOneException
	 */
	protected boolean checkAssignedVariableValidation()
			throws MasterTypeOneException {
		if (this.getNodeDeceleration().contains("=")) {
			if (NodeMediator.compareTypes(this, this.getNodeDecelerationType(),
					this.isLeftSideParameterArray(),
					this.getAssignedVariableName())) {
				this.setAssigned(true);
				return true;
			} else {
				return false;
			}
		}
		return true;

	}

	/**
	 * get the assigned variable name
	 * @return
	 */
	public String getAssignedVariableName() {
		return this.assignedValue;
	}

	/**
	 * set assigned variable name
	 */
	void setAssignedVariableName(String assignedVariableName)
			throws MasterTypeOneException {
		this.assignedValue = assignedVariableName;
		if (this.assignedValue != null) {
			checkForIllegalName(this.assignedValue);
			this.assignedValue = LineParser
					.consumeUnneededSpaced(this.assignedValue);
		}
	}

	/**
	 * is left side parameter is array
	 * @return
	 */
	public boolean isLeftSideParameterArray() {
		return isLeftSideParameterArray;
	}

	/**
	 * set is left side parameter is array
	 * @return
	 */
	protected void setLeftSideParameterArray(boolean
			isLeftSideParameterArray) {
		this.isLeftSideParameterArray = isLeftSideParameterArray;
	}

	/**
	 * get the node decleration type
	 * @return
	 */
	public DecelerationType getNodeDecelerationType() {
		return nodeDeclaredType;
	}

	/**
	 * check if is assigned
	 * @return true iff it does
	 */
	public boolean isAssigned() {
		return assigned;
	}

	/**
	 * Assigned Setter
	 * @param assigned - true or false accordingly
	 */
	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	/**
	 * set assigned variable name
	 */
	void setAssignedVariableName() throws MasterTypeOneException {
		setAssignedVariableName(matcher.group(this.comparedVariableName));
	}

	/**
	 * the main semantic analyzer method - which sets the criteria and tests
	 * each node has to pass
	 * @throws MasterTypeOneException
	 */
	public abstract void isValid() throws MasterTypeOneException;

	/**
	 * sets the constants of groups that relate to variable name and assigned
	 * variable names (if existant) so they can be used afterwords.
	 * @param variableNameIndex the group index of the variable name
	 * @param comparedVariableNameIndex the group index of the assigned name
	 * @throws MasterTypeOneException rises up due to syntax errros
	 */
	protected void setNodeProperties(
			int variableNameIndex, int comparedVariableNameIndex)
			throws MasterTypeOneException {
		this.setUsedVariableName(variableNameIndex);
		this.setNodeName();
		if (comparedVariableNameIndex != NO_ASSIGNED_VALUE_FLAG) {
			this.setComparedVariableName(comparedVariableNameIndex);
			this.setAssignedVariableName();
		}
	}

	/**
	 * sets the constants of groups that relate to variable name and assigned
	 * variable names (if existant) so they can be used afterwords.
	 * @param variableNameIndex the group index of the variable name
	 * @param comparedVariableName the assigned variable name
	 * @throws MasterTypeOneException rises up due to syntax errros
	 */
	protected void setNodeProperties(
			int variableNameIndex, String comparedVariableName)
			throws MasterTypeOneException {
		this.setUsedVariableName(variableNameIndex);
		this.setNodeName();
		this.setAssignedVariableName(comparedVariableName);
	}
}
