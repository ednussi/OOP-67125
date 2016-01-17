package oop.ex5.data_structures;
import java.util.Iterator;

/**
 * A specific kind of tree which uses a binary logic of implemention,
 * each node in the binary tree has a father and two children - the right
 * contains a bigger value, and the left containing a lower value
 * @author owner
 */
public class BinarySearchTree extends BasicDataTree{

	private final int IS_NOT_IN_TREE = -1;
	private final int YES = 1, NO = -1;
	private final int IS_LEAF = 0, HAS_ONLY_RIGHT_CHILD = 1, 
			HAS_ONLY_LEFT_CHILD = 2, HAS_BOTH_CHILDS = 3;
	protected final int DEFUALT_NULL_HEIGHT = -1;
	
	/**
	* A default constructor.
	*/
	public BinarySearchTree(){
		_root = null;
		_totalNodes = 0;
	}
	
	/**
	* A data constructor
	* a constructor that builds the tree by adding the elements in the
	* input array one-by-one.
	* If the same value appears twice (or more) in the list, it is ignored.
	@param data values to add to tree
	*/
	public BinarySearchTree(int[] data) {
		this();
		for (int newValue:data){
			this.add(newValue);
		}
	}
	
	/**
	* A copy constructor
	* a constructor that builds the tree a copy of an existing tree.
	@param tree a BinarySearchTree
	*/
	public BinarySearchTree(BinarySearchTree tree) {
		this();
		Iterator<Integer> copiedTreeIterator = tree.iterator();
		int nodeValue;
		
		while(copiedTreeIterator.hasNext()){
			nodeValue = copiedTreeIterator.next();
			this.add(nodeValue);
		}
	}
	
	
	@Override
	public boolean add(int newValue) {
		// in case tree is empty
		if (_root == null){
			_root = new Node(newValue);
			_totalNodes++;
			return true;
		}
		// initialize node as root and start looking for right place to add
		Node checkNode = _root;
		Node fatherOfCheckNode = null;
		while (checkNode != null){
			// in case tree already contains this value
			if (checkNode.getValue() == newValue){
				return false;
			// progressing logic - binary search
			} else {
				fatherOfCheckNode = checkNode;
				if (checkNode.getValue() > newValue){
				checkNode = checkNode.getLeftChild();
				} else if (checkNode.getValue() < newValue){
				checkNode = checkNode.getRightChild();
				}
			}
		}
		// decide final placing of node as right or left child
		if (fatherOfCheckNode.getValue() > newValue){
			fatherOfCheckNode.setLeftChild(
					new Node(newValue, fatherOfCheckNode));
			lastActionEffectedNode = fatherOfCheckNode.getLeftChild();
		} else {
			fatherOfCheckNode.setRightChild(
					new Node(newValue, fatherOfCheckNode));
			lastActionEffectedNode = fatherOfCheckNode.getRightChild();
		}
		updateNodesHeight(lastActionEffectedNode);
		_totalNodes++;
		return true;
	}

	@Override
	public int contains(int searchVal) {
		// in case tree is empty
		if (_root == null){
			return  IS_NOT_IN_TREE;
		}
		// run from root down using binary search logic and increasing counter
		Node checkNode = _root;
		int depth = 0;
		while (checkNode.getValue() != searchVal){
			if (checkNode.getValue() > searchVal){
				if (checkNode.getLeftChild() == null){
					return  IS_NOT_IN_TREE;
				} else {
					checkNode = checkNode.getLeftChild();
				}
			} else if (checkNode.getValue() < searchVal){
				if (checkNode.getRightChild() == null){
					return  IS_NOT_IN_TREE;
				} else {
					checkNode = checkNode.getRightChild();
				}
			}	
			depth++;
		}
		return depth;
	}

	@Override
	public boolean delete(int toDelete) {
		// in case tree is empty
		if (_root == null){
			return false;
		}
		Node checkNode = _root;
		int fatherIsBigger = NO;
		while (checkNode != null){
			// found node to delete
			if (checkNode.getValue() == toDelete){
				lastActionEffectedNode = checkNode.getFather();
				int kindOfNode = kindOfNode(checkNode);
				//has no children
				if (kindOfNode == IS_LEAF){
					// in case tree is only root
					if (checkNode == _root){
						_root = null;
					} else {
						if (fatherIsBigger == YES){
							checkNode.getFather().setLeftChild(null);
						} else if (fatherIsBigger == NO){
						checkNode.getFather().setRightChild(null);
						}	
					}
				// has one child
				} else if (kindOfNode == HAS_ONLY_RIGHT_CHILD){
					checkNode.getRightChild().setFather(checkNode.getFather());
					if (fatherIsBigger == YES){
						checkNode.getFather().setLeftChild(
								checkNode.getRightChild());
					} else if (fatherIsBigger == NO){
						checkNode.getFather().setRightChild(
								checkNode.getRightChild());
					}
				} else if (kindOfNode == HAS_ONLY_LEFT_CHILD){
					checkNode.getLeftChild().setFather(checkNode.getFather());
					if (fatherIsBigger == YES){
						checkNode.getFather().setLeftChild(
								checkNode.getLeftChild());
					} else if (fatherIsBigger == NO){
						checkNode.getFather().setRightChild(
								checkNode.getLeftChild());
					}
				// has two children
				} else if (kindOfNode == HAS_BOTH_CHILDS){
					int successorValue = successor(checkNode).getValue();
					int curNodeCount = _totalNodes;
					delete(successorValue);
					_totalNodes = curNodeCount;
					checkNode.setValue(successorValue);
				}
				updateNodesHeight(lastActionEffectedNode);
				_totalNodes--;
				return true;
			// progressing logic - binary search
			} else {
				if (checkNode.getValue() > toDelete){
				checkNode = checkNode.getLeftChild();
				fatherIsBigger = YES;
				} else if (checkNode.getValue() < toDelete){
				checkNode = checkNode.getRightChild();
				fatherIsBigger = NO;
				}
			}
		}
		return false;
	}

	@Override
	public int size() {
		return _totalNodes;
	}
	
	/**
	 * Finds for a non empty tree the node with the minimum value
	 * @return the node with the minimum value
	 */
	protected Node minimum(Node treeRoot){
		while (treeRoot.getLeftChild() != null){
			treeRoot = treeRoot.getLeftChild();
		}
		return treeRoot;
	}
	
	/**
	 * Finds for a non empty tree the node with the maximum value
	 * @return the node with the maximum value
	 */
	protected Node maximum(Node treeRoot){
		while (treeRoot.getRightChild() != null){
			treeRoot = treeRoot.getRightChild();
		}
		return treeRoot;
	}
	
	/**
	 * Finds the given node's successor
	 * @param node - the node which we want to fins it's successor
	 * @return - the given node's successor
	 */
	protected Node successor(Node node){
		// in case node is maximum
		if (node == this.maximum(_root)){
			return null;
		// in case has right child successor is miniumum of that sub-tree
		} else if (node.getRightChild() != null){
			return minimum(node.getRightChild());
		// in case has no right child go up until you find a father which 
		// you are his left child
		} else {
			Node fatherNode = node.getFather();
			while (fatherNode != null 
					&& fatherNode.getRightChild() == node){
				node = node.getFather();
				fatherNode = node.getFather();
			}
			return fatherNode;
		}
	}
	
	/**
	 * Updates nodes height from a given node
	 * @param node - the node to update heights from
	 */
	protected void updateNodesHeight(Node node){
		while (node != null){
			int leftChildHeight = leftChildHeight(node);
			int rightChildHeight = rightChildHeight(node);
			node.setHeight(1 + Math.max(leftChildHeight, rightChildHeight));
			node = node.getFather();
		}
	}
	
	/**
	 * Checks what kind of Node it is
	 * @param node - the node to check
	 * @return the kind of node it is between defualt options
	 */
	protected int kindOfNode(Node node){
		if (node.getLeftChild() == null){
			if (node.getRightChild() == null){
				return IS_LEAF;
			} else {
				return HAS_ONLY_RIGHT_CHILD;
			}
		} else {
			if (node.getRightChild() == null){
				return HAS_ONLY_LEFT_CHILD;
			} else {
				return HAS_BOTH_CHILDS;
			}
		}
	}
	
	/**
	 * Checks what is left child's height
	 * @param node - node to check for his left child's height
	 * @return - left child's height
	 */
	protected int leftChildHeight(Node node){
		if (node.getLeftChild() == null){
			return DEFUALT_NULL_HEIGHT;
		} else {
			return node.getLeftChild().getHeight();
		}
	}
	
	/**
	 * Checks what is right child's height
	 * @param node - node to check for his right child's height
	 * @return - right child's height
	 */
	protected int rightChildHeight(Node node){
		if (node.getRightChild() == null){
			return DEFUALT_NULL_HEIGHT;
		} else {
			return node.getRightChild().getHeight();
		}
	}
	
	
	/**
	*
	@return iterator to the Binary Tree. The returned iterator can 
	pass over the tree nodes in ascending order.
	*/
	public Iterator<Integer> iterator() {
		return this.new BinaryTreeIterator();
	}
	
	/**
	 * BinaryTreeIterator is an inner class which creates a tree iterator
	 * which can pass over the tree's nodes in ascending values.
	 * @author owner
	 */
	private class BinaryTreeIterator implements Iterator<Integer>{
		
		private Node _currentNode;
		private int _nodeCounter;
		
		/**
		 * Iterator Constructor
		 */
		private BinaryTreeIterator(){
			this._currentNode = minimum(_root);
			this._nodeCounter = size();
		}
		
		/**
		 * Checks if the iterator has still nodes to pass over
		 * @return true if still has nodes to pass over
		 */
		@Override
		public boolean hasNext() {
			if(_nodeCounter > 0){
				return true;
			} else {
				return false;
			}
		}

		/**
		 * Iterates on all values of this tree from the minimum 
		 * in ascending values 
		 * @return value of the current node
		 */
		@Override
		public Integer next() {
			if (_currentNode != null){
				int currentNodeValue = _currentNode.getValue();
				_currentNode = successor(_currentNode);
				_nodeCounter--;
				return currentNodeValue;
			}
			return null;
		}

		/**
		 * Overidden yet not implemented since was not required in exercise 
		 */
		@Override
		public void remove() {	
		}	
	}
}
