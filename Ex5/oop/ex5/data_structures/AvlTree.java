package oop.ex5.data_structures;
import java.util.Iterator;

/**
 * A specific kind of Binary Search Tree which all sub-trees in it,
 * the height difference in all nodes of the left child and the right child is
 * less than or equal to 1 (or -1).
 * @author owner
 */
public class AvlTree extends BinarySearchTree{
	
	private final int INITIAL_HEIGHT_DIFFRENCE = 1, INITIAL_LEFT_HEIGHT = -1,
			INTIAL_RIGHT_HEIGHT = -1, LEFT_UNBALANCEY = 2, 
			RIGHT_UNBALANCEY =-2;
	private final int IS_BALANCED = 0, LL_UNBALANCED = 1, LR_UNBALANCED = 2,
			RL_UNBALANCED = 3, RR_UNBALANCED = 4;
	private int BALANCE_STATE; 


	
	/**
	* A default constructor.
	*/
	public AvlTree() {
		super();
	}

	/**
	* A data constructor
	* a constructor that builds the tree by adding the elements in the input
	* array one-by-one. If the same value appears twice (or more) in the list,
	*  it is ignored.
	@param data values to add to tree
	*/
	public AvlTree(int[] data) {
		this();
		for (int newValue:data){
			this.add(newValue);
		}
	}

	/**
	* A copy constructor
	* a constructor that builds the tree a copy of an existing tree.
	@param tree an AvlTree
	*/
	public AvlTree(AvlTree tree) {
		this();
		Iterator<Integer> copiedTreeIterator = tree.iterator();
		int nodeValue;
		
		while(copiedTreeIterator.hasNext()){
			nodeValue = copiedTreeIterator.next();
			this.add(nodeValue);
		}
	}

	/**
	* Add a new node with key
	newValue
	into the tree.
	@param newValue new value to add to the tree.
	@return false iff newValue already exist in the tree
	*/
	public boolean add(int newValue) {
		if (super.add(newValue)){
			reBalance(lastActionEffectedNode);
			return true;
		}
		return false;
	}
	
	/**
	* Does tree contain a given input value.
	@param val value to search for.
	@return if val is found in the tree, return the depth of its node 
	(where 0 is the root). Otherwise return -1.
	*/
	public int contains(int searchVal) {
		return super.contains(searchVal);
	}
	
	/**
	* Remove a node from the tree, if it exists.
	@param toDelete value to delete
	@return true iff toDelete is found and deleted
	*/
	public boolean delete(int toDelete) {
		if (super.delete(toDelete)){
			reBalance(lastActionEffectedNode);
			return true;
		}
		return false;
	}
	
	/**
	@return number of nodes in the tree
	*/
	public int size() {
		return _totalNodes;
	}
	
	/**
	*
	@return iterator to the Avl Tree. The returned iterator can 
	pass over the tree nodes in ascending order.
	*/
	public Iterator<Integer> iterator() {
		return super.iterator();
	}
	
	/**
	* This method calculates the minimum number of nodes in 
	an AVL tree of height
	@param h height of the tree (a non-negative number).
	* @return minimum number of nodes in the tree
	*/
	public static int findMinNodes(int h) {
		if (h == 0){
			return 1;
		} else if (h == 1){
			return 2;
		} else {
			return (1 + findMinNodes(h -1) + findMinNodes(h - 2));
		}
	}
	
	/**
	 * Rotate right the suc tree of the given node 
	 * @param node - the node to perform the rotation on
	 */
	private void rotateRight(Node node){
		Node leftChild = node.getLeftChild();
		if (node == _root){
			_root = leftChild;
		}
		leftChild.setFather(node.getFather());
		node.setLeftChild(leftChild.getRightChild());
		if (node.getLeftChild() != null){
			node.getLeftChild().setFather(node);
			
		}
		leftChild.setRightChild(node);
		if (node.getFather() != null){
			if (node.getValue() < node.getFather().getValue()){
				node.getFather().setLeftChild(leftChild);
			} else {
				node.getFather().setRightChild(leftChild);
			}
		}
		node.setFather(leftChild);
		updateNodesHeight(node);
	}
	
	/**
	 * Rotate left the sub tree of the given node
	 * @param node - the node to perform the rotation on
	 */
	private void rotateLeft(Node node){
		Node rightChild = node.getRightChild();
		if (node == _root){
			_root = rightChild;
		}
		rightChild.setFather(node.getFather());
		node.setRightChild(rightChild.getLeftChild());
		if (node.getRightChild() != null){
			node.getRightChild().setFather(node);
			
		}
		rightChild.setLeftChild(node);
		if (node.getFather() != null){
			if (node.getValue() > node.getFather().getValue()){
				node.getFather().setRightChild(rightChild);
			} else {
				node.getFather().setLeftChild(rightChild);	
			}
		}
		node.setFather(rightChild);
		updateNodesHeight(node);
	}
	
	/**
	 * Finds if tree is unbalanced and balances it
	 * @param node - the node to start checking from if the tree is unbalanced
	 */
	private void reBalance(Node node){
		node = isBalanced(node);
		if (BALANCE_STATE == LL_UNBALANCED){
			rotateRight(node);
			lastActionEffectedNode = node.getLeftChild();
		} else if (BALANCE_STATE == LR_UNBALANCED){
			rotateLeft(node.getLeftChild());
			rotateRight(node);
		} else if (BALANCE_STATE == RL_UNBALANCED){
			rotateRight(node.getRightChild());
			rotateLeft(node);
		} else if (BALANCE_STATE == RR_UNBALANCED){
			rotateLeft(node);
		}
	}
	
	/**
	 * Finds if tree is unbalanced and what kind of unbalanced is it
	 * @param node - the node to start checking from if unbalanced
	 * @return the node which the tree is unbalanced from it on
	 */
	private Node isBalanced(Node node){
		int leftHeight = INITIAL_LEFT_HEIGHT;
		int rightHeight = INTIAL_RIGHT_HEIGHT;
		int heightDifference = INITIAL_HEIGHT_DIFFRENCE;
		Node compareNode = node;
		while (compareNode != null){
			leftHeight = leftChildHeight(compareNode);
			rightHeight = rightChildHeight(compareNode);
			heightDifference = leftHeight - rightHeight;
			//after calculates height check unbalancy kind or advance next node
			if (heightDifference >= LEFT_UNBALANCEY || heightDifference 
					<= RIGHT_UNBALANCEY){
				if (heightDifference >= LEFT_UNBALANCEY){
					if (leftChildHeight(compareNode.getLeftChild()) >=
							rightChildHeight(compareNode.getLeftChild()) ){
						BALANCE_STATE = LL_UNBALANCED;
					} else {
						BALANCE_STATE = LR_UNBALANCED;
					}
				} else if (heightDifference <= RIGHT_UNBALANCEY){
					if (leftChildHeight(compareNode.getRightChild()) <=
							rightChildHeight(compareNode.getRightChild()) ){
						BALANCE_STATE = RR_UNBALANCED;
					} else {
						BALANCE_STATE = RL_UNBALANCED;
					}
				}
			return compareNode;
			}
			compareNode = compareNode.getFather();
		}
		BALANCE_STATE = IS_BALANCED;
		return null;
	}
}