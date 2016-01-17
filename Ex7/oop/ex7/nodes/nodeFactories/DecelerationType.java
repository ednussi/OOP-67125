package oop.ex7.nodes.nodeFactories;

/**
 * An enum representing the declartion types possible
 * @author owner
 */
public enum DecelerationType {
	INT("\\-*\\s*\\d+"),
	BOOLEAN("true|false"),
	STRING("\"\\s*\\w*\\s*\"|\'\\s*.\\s*\'"),
	CHAR("\'\\s*.\\s*\'"),
	DOUBLE("\\s*\\-*\\s*(\\s*\\d+|\\d+\\.\\d+)"),
	VOID,
	NULL;

	private String veriableTypeRegex;

	/**
	 * constructor
	 */
	private DecelerationType() {
	}

	/**
	 * constructor
	 * @param type - the variable type regex
	 */
	private DecelerationType(String type) {
		this.veriableTypeRegex = type;
	}

	/**
	 * @return getter of the checker if the string is array regex
	 */
	public static String getIsArrayCheckRegex() {
		return ("\\{.*\\}");
	}


	/**
	 * @return the veriableTypeRegex
	 */
	public String getVariableTypeRegex() {
		return this.veriableTypeRegex;
	}


}
