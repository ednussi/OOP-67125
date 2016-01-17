package oop.ex7.nodes.generalTypes;

import oop.ex7.nodes.nodeFactories.DecelerationType;
import oop.ex7.semanticAnalyzer.DuplicateVariableDeclerationException;

import java.util.HashMap;

/**
 * A public Class representing the VariableTable
 * @author owner
 */
public class VariableTable {
	private HashMap<String, TableEntry> table;

	public VariableTable() {
		this.table = new HashMap<>();
	}

	/**
	 * Inserts a new key into the table
	 * @param key - its key
	 * @param decelerationType - it's type
	 * @param nodeLink - it's node
	 * @throws DuplicateVariableDeclerationException
	 */
	public void insertEntry(
			String key, DecelerationType decelerationType, SimpleNode nodeLink)
			throws DuplicateVariableDeclerationException {
		if (! table.containsKey(key)) {
			TableEntry newTableEntry = new TableEntry(decelerationType,
					nodeLink);
			table.put(key, newTableEntry);
		} else {
			throw new DuplicateVariableDeclerationException(
					nodeLink.getNodeLineNumber());
		}
	}

	/**
	 * Returns a Table Entry according to a key
	 * @param key - the key it is stored in
	 * @return a Table Entry according to a key
	 */
	public TableEntry lookupEntry(String key) {
		return table.get(key);
	}

	/**
	 * Inner Class to construct the table
	 * @author owner
	 */
	public class TableEntry {
		DecelerationType entryType;
		SimpleNode nodeReferance;

		/**
		 * Enters a node to the variable table
		 * @param decelerationType - its type
		 * @param nodeLink - the node to save
		 */
		TableEntry(DecelerationType decelerationType, SimpleNode nodeLink) {
			this.entryType = decelerationType;
			this.nodeReferance = nodeLink;
		}

		/**
		 * @return the veriableType
		 */
		public DecelerationType getType() {
			return entryType;
		}

		/**
		 * @return the node reference - getter
		 */
		public SimpleNode getLinkedNode() {
			return nodeReferance;
		}
	}
}
