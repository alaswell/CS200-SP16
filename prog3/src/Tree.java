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
    public Double postorderEval(){
	Double res = null;
	if (!isEmpty())
	    res = postorderEval(root);
	return res;
    }


    // IMPLEMENT

	public Double postorderEval(TreeNode node){
		Double leftVal = 0.0;
		Double rightVal = 0.0;
		if(node!=null) {
			// evaluate left tree
			leftVal = postorderEval(node.getLeft());
			// evaluate right tree (if not null)
			if(node.getRight()!=null)
				rightVal = postorderEval(node.getRight());
			
			// evaluate operator in node and return Double result
			String operator = node.getItem();
			if(operator.compareTo("+") == 0) return leftVal + rightVal; // ADD
			else if(operator.compareTo("-") == 0) return leftVal - rightVal; // SUBTRACT
			else if(operator.compareTo("/\\") == 0) {
				// MAX
				if(leftVal >= rightVal) 
					return leftVal;
				else 
					return rightVal;
			}
			else if(operator.compareTo("\\/") == 0) {
				// MIN
				if(leftVal >= rightVal) 
					return rightVal;
				else 
					return leftVal;
			}
			else if(operator.compareTo("abs") == 0) return (leftVal <= 0.0) ? 0.0 - leftVal : leftVal;
			else return Double.parseDouble(node.getItem());
		}
		else
			return 0.0;
    }	


    // EXERCISE
    public static void main(String[] args){
	System.out.println("Stepwise build infix expression: 5.6 + 7.8");
	
	Tree t = new Tree();
	System.out.println("tree: "); t.preorderTraverse();	
	System.out.println("result:\n" + t.postorderEval()+"\n");
	
	TreeNode a = new TreeNode("5.6");
	t = new Tree(a);
	System.out.println("tree: "); t.preorderTraverse();	
	System.out.println("result:\n" + t.postorderEval()+"\n");
			
	TreeNode b = new TreeNode("7.8");
	TreeNode plus = new TreeNode("+", a, b);
	t = new Tree(plus);
	System.out.println("tree: "); t.preorderTraverse();	
	System.out.println("result:\n" + t.postorderEval()+"\n");
	
	TreeNode minus = new TreeNode("-", a, b);
	t = new Tree(minus);
	System.out.println("tree: "); t.preorderTraverse();	
	System.out.println("result:\n" + t.postorderEval()+"\n");
	
	TreeNode max = new TreeNode("/\\", a, b);
	t = new Tree(max);
	System.out.println("tree: "); t.preorderTraverse();	
	System.out.println("result:\n" + t.postorderEval()+"\n");
	
	TreeNode min = new TreeNode("\\/", a, b);
	t = new Tree(min);
	System.out.println("tree: "); t.preorderTraverse();	
	System.out.println("result:\n" + t.postorderEval()+"\n");
	
	TreeNode c = new TreeNode("-7.8");
	TreeNode abs = new TreeNode("abs", c);
	t = new Tree(abs);
	System.out.println("tree: "); t.preorderTraverse();	
	System.out.println("result:\n" + t.postorderEval()+"\n");
    }
}
