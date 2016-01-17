/**
 * A subclass for implementations of hash-sets implementing the SimpleSet
 * interface. Represents an open Hash set using quadratic probing
 * @author ednussi
 */
public class OpenHashSet extends SimpleHashSet {
	
	// Initializing parameters
	private final double LINEAR_CONSTANT_PROBING = 0.5;
	private final double QUADRIC_CONSTANT_PROBING = 0.5;
	private final boolean FLAG = true;
	private Object[] openHashSet;
	private Object[] transferOpenHashSet;
	private int hashingModuloNum = this._currentHashSetCapacity - 1;
	private int INTIAL_TIMES_RUN = 0;

	/**
	 * A default constructor. Constructs a new, empty table with default
	 *  initial capacity (16), upper load factor (0.75) and lower 
	 *  load factor (0.25).
	 */
	public OpenHashSet(){
		super();
		openHashSet = new Object[this._initialHashSetCapicity];
	}
	
	/**
	 * Constructs a new, empty table with the specified load factors,
	 *  and the default initial capacity (16)
	 * @param upperLoadFactor  +- The upper load factor of the hash table.
	 * @param lowerLoadFactor - The lower load factor of the hash table.
	 */
	public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
		super(upperLoadFactor, lowerLoadFactor);
		openHashSet = new Object[this._initialHashSetCapicity];
	}
	
	/**
	 * Data constructor - builds the hash set by adding the elements 
	 * one by one. Duplicate values should be ignored. 
	 * The new table has the default values of initial capacity (16)
	 * the upper load factor (0.75), and lower load factor (0.25).
	 * @param data - Values to add to the set.
	 */
	public OpenHashSet(String[] data){
		super();
		openHashSet = new Object[this._initialHashSetCapicity];
		for (String element : data){
			add(element);
		}
	}

	@Override
	public boolean add(String newValue) {
		int containsWhere = containsWhere(newValue);
		if (!newValue.equals(openHashSet[containsWhere])){
			if(checkUpperLoadFactor()){
					openHashSet = reHash(CHANGED_CAPACITY_FACTOR_INCREASE);
					containsWhere = containsWhere(newValue);
				}
			openHashSet[containsWhere] = newValue;
			this._numOfElements++;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int capacity() {
		return this._currentHashSetCapacity;
	}

	@Override
	public boolean contains(String searchVal) {
		int searchResult= containsWhere(searchVal);
		if (openHashSet[searchResult]!=null && 
				openHashSet[searchResult].equals(searchVal)){
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String toDelete) {
		int containsWhere = containsWhere(toDelete);
		if (toDelete.equals(openHashSet[containsWhere])){
			openHashSet[this.containsWhere(toDelete)] = FLAG;
			this._numOfElements--;
			if (checkLowerLoadFactor()){
				openHashSet = reHash(CHANGED_CAPACITY_FACTOR_DECREASE);
				}
			return true;
			} else {
				return false;
			}
	}

	@Override
	public int size() {
		return this._numOfElements;
	}
	
	/**
	 * Gives the hashed value for the given string
	 * @param newValue - the String to hash
	 * @param probeNum - the number of times the hash used on the term
	 * @return the hashed value for the given string
	 */
	
	private int hash(String newValue, int probeNum, int basicHashedValue){
		return (basicHashedValue +
				(int) ((probeNum * LINEAR_CONSTANT_PROBING) +
						(probeNum * probeNum *
								QUADRIC_CONSTANT_PROBING))) &
								(hashingModuloNum);
	}
	
	/**
	 * Change the openHashSet table into bigger/smaller table according to the 
	 * change factor entered
	 * @param changeFactor - factor of change
	 * @return the new sized hashed table
	 */
	private Object[] reHash (double changeFactor){
		// copying the table - creating one with fitted size 
		// and using its element to insert simply without
		// checking contains all the time to the new table
		transferOpenHashSet = openHashSet;
		this._numOfElements = EMPTY_TABLE;
		this._currentHashSetCapacity *= changeFactor;
		this.hashingModuloNum = 
				this._currentHashSetCapacity - 1;
		openHashSet = new Object[this._currentHashSetCapacity];
		for (Object element : transferOpenHashSet){
			if (element != null && !element.equals(FLAG)){
				this.addInReHash((String)element);
			}
		}
		return openHashSet;
	}
	
	/**
	 * Re-adding an existing element from one rehash table to another
	 * @param newValue - the new element to add
	 */
	private void addInReHash(String newValue) {
			openHashSet[containsWhere(newValue)] = newValue;
			this._numOfElements++;
	}
	
	/**
	 * Returns the index of the first null - meaning was not found
	 * or index of the exact item looking for.
	 * @param searchVal - the String to search for
	 * @return - Returns the index of the first null - meaning was not found
	 * or index of the exact item looking for.
	 */
	private int containsWhere(String searchVal){
		int hashCounter = INTIAL_TIMES_RUN;
		int hashedValue;
		int basicHashedValue = searchVal.hashCode();
		while (true){
			//calculate the hashed value and checks if null or the item
			hashedValue = hash(searchVal, hashCounter, basicHashedValue);
			if (openHashSet[hashedValue] == null){
				return hashedValue;
			}
			else {
				if (searchVal.equals(openHashSet[hashedValue])){
					return hashedValue;
				}
			}
			hashCounter++;
		}
	}
}