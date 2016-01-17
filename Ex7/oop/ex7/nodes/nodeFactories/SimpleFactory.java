package oop.ex7.nodes.nodeFactories;

import oop.ex7.nodes.DecelerationTypeTable;
import oop.ex7.nodes.generalTypes.SimpleNode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple factory - which interprets what is the line Declaration type
 * @author owner
 */
abstract class SimpleFactory {
	
	private final static String NAME_REGEX = SimpleNode.VARIABLE_NAME_REGEX;
	private final static int NAME = 0;
	/**
	 * Given a line Data string out and returns the correct Declaration type of
	 * the line
	 * @param lineData
	 * @return the Declaration type
	 * @throws IllegalArgumentException
	 */
	static DecelerationType checkDecelerationType(String lineData)
			throws IllegalArgumentException {

		String variableDeceleration;
		Matcher matcher = Pattern.compile(NAME_REGEX).matcher(lineData);
		matcher.find();
		variableDeceleration = matcher.group(NAME);
		DecelerationType tempDecelerationType = DecelerationTypeTable
				.checkVariableType(variableDeceleration);
		return tempDecelerationType;
	}
}
