public class Tree {

    // Provided, DON'T CHANGE
    // the root of the tree
    private TreeNode root;
	public int i = 0;

    //empty tree
    public Tree(){
	this.root = null;
    }

    // rootItem, empty children
    public Tree(TreeNode root){
	this.root = root;
    }

    public boolean isEmpty(){
	return root==null;
    }

    public void preorderTraverse(){
	if (!isEmpty())
	    preorderTraverse(root,"");
	else
	    System.out.println("null");
    }

    // print root item
    // print left tree
    // print right tree
    public void preorderTraverse(TreeNode node, String indent){
	System.out.println(indent+node.getItem());
	if (node.getLeft()!=null) preorderTraverse(node.getLeft(),indent+" ");
	if (node.getRight()!=null) preorderTraverse(node.getRight(),indent+" ");

    }

    // if tree empty return null
    // else evaluate the tree by postorder traversal 
    // and return its value
    public Boolean postorderEval() throws BSTException {
	Boolean res = null;
	if (!isEmpty())
	    res = postorderEval(root, null);
	return res;
    }


    // IMPLEMENT

	public Boolean postorderEval(TreeNode node, BST symTab) throws BSTException {
		Boolean leftVal = null;
		Boolean rightVal = null;
		if(node != null) {
			String operator = node.getItem();
			Boolean zero = operator.compareTo("0") == 0;
			Boolean one = operator.compareTo("1") == 0;

			if(!(zero || one)) {				
				// if it's not a 1, 0
				// is it an identifier
				IdVal treeItem = symTab.retrieveItem(operator);
				if(treeItem != null) // is an identifier, so return that value			
					return treeItem.getVal();				
				
				// evaluate left tree
				leftVal = postorderEval(node.getLeft(), symTab);
				// evaluate right tree (if not null)
				if(node.getRight()!=null)
					rightVal = postorderEval(node.getRight(), symTab);
				
				// evaluate operator in node and return Boolean result
				if(operator.compareTo("|") == 0) return leftVal || rightVal; // OR
				else if(operator.compareTo("&") == 0) return leftVal && rightVal; // AND
				else if(operator.compareTo("!") == 0) return !leftVal; // NOT
				else throw new BSTException();
			}
			return zero ? false : true;
		}
		return null;
    }	


    // EXERCISE
    public static void main(String[] args){
	System.out.println("Stepwise build infix expression: 5.6 + 7.8");
	
	Tree t = new Tree();
	System.out.println("tree: "); t.preorderTraverse();	
	}
}
