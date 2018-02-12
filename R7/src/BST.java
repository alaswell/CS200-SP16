
public class BST{
	// this Binary Search Tree is used for the implementation of the 
	// Symbol Table containing Symbols: (id,value) pairs
	// A Symbol is a Comparable object containing a String Identifier  
	// and a Boolean value 
	private BSTNode<IdVal> root;

	//empty tree
	public BST(){
		this.root = null;
	}

	public boolean isEmpty(){
		return root==null;
	}

	public void insertItem(IdVal item) throws BSTException{
		root = insertItem(root, item);
	}

	private BSTNode<IdVal> insertItem(BSTNode<IdVal> node, IdVal item) throws BSTException{
		BSTNode<IdVal> newSubTree;
		if(node==null){
			newSubTree = new BSTNode<IdVal>(item);
			return newSubTree;
		}
		
		IdVal tok = node.getItem();
		if(item.getKey().compareTo(tok.getKey())<0){
			newSubTree = insertItem(node.getLeft(), item);
			node.setLeft(newSubTree);
			return node;
		}
		if(item.getKey().compareTo(tok.getKey())>0){
			newSubTree = insertItem(node.getRight(), item);
			node.setRight(newSubTree);
			return node;
		}
		// ERROR: inserting existing item
		else 
			throw new BSTException("Inserting item with existing key!");
	}

	public IdVal retrieveItem(String key){
		return retrieveItem(root,key);
	}
	
	private IdVal retrieveItem(BSTNode<IdVal> node, String key){
		IdVal treeItem;
		
		if(node==null)
			treeItem = null;
		else{
			IdVal nodeItem = node.getItem();
			if(key.compareTo(nodeItem.getKey()) == 0)
				//found
				treeItem = nodeItem;
			else if(key.compareTo(nodeItem.getKey()) < 0)
				//search left
				treeItem = retrieveItem(node.getLeft(), key);
			else
				// search right
				treeItem = retrieveItem(node.getRight(), key);
		}
		return treeItem;

	}
	

	public void preorderTraverse(){
		if (!isEmpty())
			preorderTraverse(root,"");
		else
			System.out.println("root is null");
	}

	public void preorderTraverse(BSTNode<IdVal> node, String indent){
		System.out.println(indent+node.getItem());		
		if(node.getLeft()!=null) {
			System.out.println(indent+"left");
			preorderTraverse(node.getLeft(),indent+" ");
		}

		if(node.getRight()!=null) {
			System.out.println(indent+"right");
			preorderTraverse(node.getRight(),indent+" ");
		}
	}

	public void deleteItem(String key) throws BSTException {
		root = deleteItem(root, retrieveItem(key));
	}

	private BSTNode<IdVal> deleteItem(BSTNode<IdVal> node, IdVal item) throws BSTException{
		BSTNode<IdVal> newNode;

		if(node==null)
			throw new BSTException("Inserting item with existing key!");
		
		IdVal tok = node.getItem();

		if(item.getKey().compareTo(tok.getKey())<0){
			newNode = deleteNode(node);
			return newNode;
		}
		else if(item.getKey().compareTo(tok.getKey())<0){
			newNode = deleteItem(node.getLeft(), item);
			node.setLeft(newNode);
			return node;
		}
		else {
			newNode = deleteItem(node.getRight(), item);
			node.setRight(newNode);
			return node;
		}
	}

	public BSTNode<IdVal> deleteNode(BSTNode<IdVal> node) {
		BSTNode<IdVal> returnNode;

		if(node.getLeft()==null && node.getRight()==null) {
			return null;	
		}
		else if(node.getLeft()!=null || node.getRight()!=null) {
			if(node.getLeft()!=null) {
				// c is left child
				return node.getLeft();
			}	
			else
				return node.getRight();
		}
		else {
			node.setLeft(findLeftMostItem(node.getRight()));
			node.setRight(deleteLeftMostNode(node.getRight()));
			return node;
		}
		
			
				
	}
	
	public BSTNode<IdVal> findLeftMostItem(BSTNode<IdVal> node) {
		BSTNode<IdVal> prevNode = node;

		while(node != null) {
			prevNode = node;
			node = node.getLeft();
		}

		return prevNode;
	}
	
	public BSTNode<IdVal> deleteLeftMostNode(BSTNode<IdVal> node) {
		if(node.getLeft()==null)
			return node.getRight();
		else {
			node.setLeft(deleteLeftMostNode(node.getLeft()));
			return node;
		}
	}
}
