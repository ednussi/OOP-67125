package oop.ex5.data_structures;

/**
 * A class which represent a tree node, a data structure similar to double
 * linked list containning information (integers) about its "father" and
 * two children "right child" and "left child". Used by the trees 
 * data sturcture as basic
 * data holders.
 * @author owner
 */
public class Node{
	
	private int _value;
	private Node _father, _leftChild, _rightChild;
	private int _height;
	private final int DEFUALT_LEAF_HEIGHT = 0;
	
	/**
	 * default constructor
	 * @param value - the value of the node
	 */
	public Node(int value){
		this._value = value;
		this._father = null;
		this._leftChild = null;
		this._rightChild = null;
		this._height = DEFUALT_LEAF_HEIGHT;
	}
	
	/**
	 * Given a "father" create a new node with the given father
	 * @param value - the value of the node
	 * @param father - the node's father
	 */
	public Node(int value, Node father){
		this._value = value;
		this._father = father;
		this._leftChild = null;
		this._rightChild = null;
		this._height = DEFUALT_LEAF_HEIGHT;
	}
	
	/**
	 * Specific constructor which set all nodes parameters
	 * @param value - the value of the node
	 * @param father - node's father
	 * @param leftChild - node's left child
	 * @param rightChild - node's right child
	 * @param height - node height in the tree
	 */
	public Node(int value, Node father,
			Node leftChild, Node rightChild
			, int height){
		this._value = value;
		this._father = father;
		this._leftChild = leftChild;
		this._rightChild = rightChild;
		this._height = height;
	}
	
	/**
	 * Father setter
	 * @param newFather - the new father to be set into
	 */
	public void setFather(Node newFather){
		this._father = newFather;
	}
	
	/**
	 * Left child setter
	 * @param newLeftChild - the new left child to be set into
	 */
	public void setLeftChild(Node newLeftChild){
		this._leftChild = newLeftChild;
	}
	
	/**
	 * Right child setter
	 * @param newRightChild - the new right child to be set into
	 */
	public void setRightChild(Node newRightChild){	
		this._rightChild = newRightChild;
	}
	
	/**
	 * Height setter
	 * @param newHeight - the new node's height to be set into
	 */
	public void setHeight(int newHeight){
		this._height = newHeight;
	}
	
	/**
	 * Value setter
	 * @param newValue - the new node's value to be set into
	 */
	public void setValue(int newValue){
		this._value = newValue;
	}
	
	/**
	 * Value getter
	 * @return the node's value
	 */
	public int getValue(){
		return this._value;
	}
	
	/**
	 * Height getter
	 * @return the node's height
	 */
	public int getHeight(){
		return this._height;
	}
	
	/**
	 * Right child getter
	 * @return - the node's right child
	 */
	public Node getRightChild(){
		return this._rightChild;
	}
	
	/**
	 * Left child getter
	 * @return - the node's left child
	 */
	public Node getLeftChild(){
		return this._leftChild;
	}
	
	/**
	 * Father getter
	 * @return - the node's father
	 */
	public Node getFather(){
		return this._father;
	}
}
