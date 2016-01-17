package oop.ex7.nodes;

import oop.ex7.nodes.nodeFactories.DecelerationType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class representing the allowed and proper Casting Rules Table
 * @author owner
 */
public class CastingRulesTable {
	static private HashMap<DecelerationType, ArrayList<DecelerationType>> table
			= new HashMap<>();

	/**
	 * constructor
	 */
	private CastingRulesTable() {
	}

	/**
	 * Check the legallity of casting between two symbols
	 * @param firstSymbol - a symbol represting a type
	 * @param secondSymbol - the symbol of to check if can put into it
	 * @return true iff casting is possible
	 * @throws IllegalCastingOfDifferentDataTypes
	 */
	static public boolean isLegalCasting(
			DecelerationType firstSymbol, DecelerationType secondSymbol)
			throws IllegalCastingOfDifferentDataTypes {
		ArrayList<DecelerationType> item = table.get(firstSymbol);
		if (item == null) {
			return false;
		} else {
			if (item.contains(secondSymbol)) {
				return true;
			} else {
				throw new IllegalCastingOfDifferentDataTypes(firstSymbol,
						secondSymbol);
			}
		}
	}

	/**
	 * table initializer that has the available casting rules - easy to add or
	 * to change
	 */
	static public void tableInitializer() {
		ArrayList<DecelerationType> intCastingPossibilities = new 
				ArrayList<>();
		intCastingPossibilities.add(DecelerationType.INT);

		ArrayList<DecelerationType> doubleCastingPossibilities
				= new ArrayList<>();
		doubleCastingPossibilities.add(DecelerationType.DOUBLE);
		doubleCastingPossibilities.add(DecelerationType.INT);

		ArrayList<DecelerationType> booleanCastingPossibilities
				= new ArrayList<>();
		booleanCastingPossibilities.add(DecelerationType.BOOLEAN);

		ArrayList<DecelerationType> stringCastingPossibilities
				= new ArrayList<>();
		stringCastingPossibilities.add(DecelerationType.STRING);
		stringCastingPossibilities.add(DecelerationType.CHAR);

		ArrayList<DecelerationType> charCastingPossibilities
				= new ArrayList<>();
		charCastingPossibilities.add(DecelerationType.CHAR);

		insertNewSymbol(DecelerationType.INT, intCastingPossibilities);
		insertNewSymbol(DecelerationType.DOUBLE, doubleCastingPossibilities);
		insertNewSymbol(DecelerationType.STRING, stringCastingPossibilities);
		insertNewSymbol(DecelerationType.BOOLEAN, booleanCastingPossibilities);
		insertNewSymbol(DecelerationType.CHAR, charCastingPossibilities);
	}

	/**
	 * A method to insert the table a new symbol casting property
	 * @param firstSymbol - a symbol
	 * @param secondSymbol - the symbol of what can be put into it
	 */
	static private void insertNewSymbol(
			DecelerationType firstSymbol,
			ArrayList<DecelerationType> secondSymbol) {
		table.put(firstSymbol, secondSymbol);
	}
}
