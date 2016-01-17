package oop.ex5.data_structures;

/**
 * An abstract class of a basic data tree. 
 * all tree data structure originate from this class.
 * Keeps all basic information of a tree such as the root, height, total
 * amount of nodes and last changed node
 * @author owner
 */
public abstract class BasicDataTree implements SimpleTree {

	protected int _height, _totalNodes;
	protected Node _root;
	protected Node lastActionEffectedNode;
	
	/**
	* A default constructor.
	*/
	public BasicDataTree() {
	}

	/**
	* A data constructor
	* a constructor that builds the tree by adding the elements in the 
	* input array one-by-one.
	* If the same value appears twice (or more) in the list, 
	* it is ignored.
	@param data values to add to tree
	*/
	public BasicDataTree(int[] data) {
	}

	/**
	* A copy constructor
	* a constructor that builds the tree a copy of an existing tree.
	@param tree an AvlTree
	*/
	public BasicDataTree(AvlTree tree) {
	}
	
	@Override
	public abstract boolean add(int newValue);
	
	@Override
	public abstract int contains(int searchVal);
	
	@Override
	public abstract boolean delete(int toDelete);
	
	@Override
	public abstract int size();
}
