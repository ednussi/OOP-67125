package oop.ex5.data_structures;

/**
 * Interface which all tree data types implements which basic functions,
 * add, delete, contains and size
 * @author owner
 */
public interface SimpleTree {

	/**
	* Add a new node with key
	newValue
	into the tree.
	@param newValue new value to add to the tree.
	@return false iff newValue already exist in the tree
	*/
	public boolean add(int newValue);
	
	/**
	* Does tree contain a given input value.
	@param val value to search for.
	@return if val is found in the tree, return the depth of its node 
	(where 0 is the root). Otherwise return -1.
	*/
	public int contains(int searchVal);
	
	/**
	* Remove a node from the tree, if it exists.
	@param toDelete value to delete
	@return true iff toDelete is found and deleted
	*/
	public boolean delete(int toDelete);
	
	/**
	@return number of nodes in the tree
	*/
	public int size();
}