package oop.ex7.nodes;

import oop.ex7.nodes.nodeFactories.DecelerationType;

import java.util.HashMap;

/**
 * A Class representing the Declaration Type Table
 * @author owner
 */
public class DecelerationTypeTable {
	static private HashMap<String, DecelerationType> table = new HashMap<>();

	private DecelerationTypeTable() {
	}

	/**
	 * Check and returns what is the declaration type of the given val
	 * @param val - the string to cehck its declarartion type
	 * @return
	 */
	static public DecelerationType checkVariableType(String val) {
		DecelerationType item = table.get(val);
		if (item == null) {
			return null;
		} else {
			return item;
		}
	}

	/**
	 * Table Initializer
	 */
	static public void tableInitializer() {
		table.clear();
		insertNewSymbol("int", DecelerationType.INT);
		insertNewSymbol("double", DecelerationType.DOUBLE);
		insertNewSymbol("String", DecelerationType.STRING);
		insertNewSymbol("boolean", DecelerationType.BOOLEAN);
		insertNewSymbol("char", DecelerationType.CHAR);
		insertNewSymbol("void", DecelerationType.VOID);
	}

	/**
	 * Insert new symbol into table
	 * @param key - the key
	 * @param symbol - the correct symbol it fits
	 */
	private static void insertNewSymbol(String key, DecelerationType symbol) {
		table.put(key, symbol);
	}
}
