/**
 * A superclass for implementations of hash-sets implementing the SimpleSet
 * interface.
 * @author ednussi
 */
public abstract class SimpleHashSet implements SimpleSet{
	
	// Initializing parameters
	private final int DEFAULT_INITIAL_CAPACITY = 16;
	private final float DEFAULT_UPPER_LOAD_FACTOR = (float) 0.75;
	private final float DEFAULT_LOWER_LOAD_FACTOR = (float) 0.25;
	protected float _upperLoadFactor;
	protected float _lowerLoadFactor;
	protected int _initialHashSetCapicity;
	protected int _currentHashSetCapacity;
	protected int _numOfElements;
	protected final double CHANGED_CAPACITY_FACTOR_INCREASE = 2;
	protected final double CHANGED_CAPACITY_FACTOR_DECREASE = 1 / 
			CHANGED_CAPACITY_FACTOR_INCREASE;
	protected final int EMPTY_TABLE = 0;
	private final int ADDITIONAL_ELEMENT = 1;
	
	/**
	 * A Default basic constructor for a simpleHashSet
	 */
	public SimpleHashSet(){
		this._numOfElements = EMPTY_TABLE;
		this._lowerLoadFactor = DEFAULT_LOWER_LOAD_FACTOR;
		this._upperLoadFactor = DEFAULT_UPPER_LOAD_FACTOR;
		this._initialHashSetCapicity = DEFAULT_INITIAL_CAPACITY;
		this._currentHashSetCapacity = this._initialHashSetCapicity;
	}

	/**
	 * Constructs a new, empty table with the specified load factors,
	 * @param upperLoadFactor  - The upper load factor of the hash table.
	 * @param lowerLoadFactor - The lower load factor of the hash table.
	 */
	public SimpleHashSet(float upperLoadFactor,
            float lowerLoadFactor){
		this._upperLoadFactor = upperLoadFactor;
		this._lowerLoadFactor = lowerLoadFactor;
		this._numOfElements = EMPTY_TABLE;
		this._initialHashSetCapicity = DEFAULT_INITIAL_CAPACITY;
		this._currentHashSetCapacity = this._initialHashSetCapicity;
	}
	
	@Override
	public abstract boolean add(String newValue);
	
	@Override
	public abstract boolean contains(String searchVal);

	@Override
	public abstract boolean delete(String toDelete);

	@Override
	public abstract int size();
	
	/**
	 * Check for the capacity (number of cells) of the table.
	 * @return The current capacity (number of cells) of the table.
	 */
	public abstract int capacity();
	
	/**
	 * Calculates the absolute basic hashed value of the given string
	 * @param newValue - the given String to hash
	 * @return absolute basic hashed value of the given string
	 */
	protected int basicHash(String newValue){
		return Math.abs(newValue.hashCode());
	}
	
	/**
	 * Checks if adding a new element would go above upper load Factor
	 * @return true if does
	 */
	protected boolean checkUpperLoadFactor(){
		if ((double)(this._numOfElements + ADDITIONAL_ELEMENT) / 
				this._currentHashSetCapacity > this._upperLoadFactor){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if removing the element would go below lower load Factor
	 * @return true if does
	 */
	protected boolean checkLowerLoadFactor(){
		if ((double)this._numOfElements / 
				this._currentHashSetCapacity < this._lowerLoadFactor){
			return true;
		} else {
			return false;
		}
	}
}
