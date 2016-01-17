import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A subclass for implementations of hash-sets implementing the SimpleSet
 * interface. Represents a chained Hash set using linear probing
 * @author ednussi
 */
public class ChainedHashSet extends SimpleHashSet {
	
	// Initializing parameters
	private ArrayList<LinkedList<String>> chainedHashSet;
	private ArrayList<LinkedList<String>> newChainedHashSet;
	
	/**
	 * A default constructor. Constructs a new, empty table with default 
	 * initial capacity (16), upper load factor (0.75) 
	 * and lower load factor (0.25).
	 */
	public ChainedHashSet(){
		super();
		chainedHashSet = new ArrayList<LinkedList<String>>(
				this._initialHashSetCapicity);
		for(int i=0; i <_initialHashSetCapicity; i++){
			LinkedList<String> list = new LinkedList<String>();
			chainedHashSet.add(list);
		}
	}
	
	/**
	 * Constructs a new, empty table with the specified load factors, 
	 * and the default initial capacity (16).
	 * @param upperLoadFactor  - The upper load factor of the hash table.
	 * @param lowerLoadFactor - The lower load factor of the hash table.
	 */
	public ChainedHashSet(float upperLoadFactor, float lowerLoadFactor){
		super(upperLoadFactor, lowerLoadFactor);
		chainedHashSet = new ArrayList<LinkedList<String>>(
				this._initialHashSetCapicity);
		for(int i=0; i <_initialHashSetCapicity; i++){
			LinkedList<String> list = new LinkedList<String>();
			chainedHashSet.add(list);
		}
	}
	
	/**
	 * Data constructor - builds the hash set by adding the elements one by 
	 * one. Duplicate values should be ignored. The new table has the default 
	 * values of initial capacity (16), upper load factor (0.75), and lower 
	 * load factor (0.25).
	 * @param data  - Values to add to the set.
	 */
	public ChainedHashSet(java.lang.String[] data){
		this();
		for (String element : data){
			this.add(element);
		}
	}

	@Override
	public boolean add(String newValue) {
		if (contains(newValue)){
			return false;
		} else {
			if (checkUpperLoadFactor()){
				chainedHashSet = reHash(CHANGED_CAPACITY_FACTOR_INCREASE);
			}
			chainedHashSet.get(hash(newValue)).add(newValue);
			this._numOfElements++;
			return true;
		}
	}
	
	@Override
	public int capacity() {
		return this._currentHashSetCapacity;
	}

	@Override
	public boolean contains(String searchVal) {
		if (chainedHashSet.get(hash(searchVal)).contains(searchVal)){
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String toDelete) {
		if (chainedHashSet.get(hash(toDelete)).contains(toDelete)){
			chainedHashSet.get(hash(toDelete)).remove(toDelete);
			this._numOfElements--;
			if (checkLowerLoadFactor()){
				chainedHashSet = reHash(CHANGED_CAPACITY_FACTOR_DECREASE);
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
		
	private int hash(String newValue) {
		return basicHash(newValue) % this._currentHashSetCapacity;
	}
	
	/**
	 * reHashes elements into new sized Hash set table according to 
	 * the changed capacity factor.
	 * @param changeFactor - the factor of increasement / decreasement
	 * @return the new proper chained hash set.
	 */
	private ArrayList<LinkedList<String>> reHash(double changeFactor){
		this._currentHashSetCapacity *= changeFactor;
		newChainedHashSet = new ArrayList<LinkedList<String>>(
				(int) (this._currentHashSetCapacity));
		for(int i=0; i <this._currentHashSetCapacity; i++){
			LinkedList<String> list = new LinkedList<String>();
			newChainedHashSet.add(list);
		}
		for (LinkedList<String> bucket : chainedHashSet){
			for(String element : bucket){
				newChainedHashSet.get(hash(element)).add(element);
			}
		}
		return newChainedHashSet;
	}
}
