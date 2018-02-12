public class ptExpr {

    // you can choose to use debug or not
    // we will not test debug mode
    private boolean debug;

    // contains next Token or null at end of line
    private String nextToken;

    // Token Iterator, set in constructor
    private TokenIter itTokens;

    // IMPLEMENT

    public ptExpr(TokenIter iter, boolean debug) {
		this.itTokens = iter;
		this.debug = debug;

		if(itTokens.hasNext())
			this.nextToken = itTokens.next();
    }

    // provided
    private void error(String errMess) throws ParseException {
		throw new ParseException(errMess);
    }


    // provided
    public Tree line() throws ParseException {
		TreeNode root;
		Tree tree = new Tree();
		if (nextToken!=null) {
			root = expr("");
			tree = new Tree(root);
		}
		if (nextToken!=null)
			error("end of line expected");
		return tree;
    }


    // IMPLEMENT

    // expr = term ( ("+"|"-") term )*
    private TreeNode expr(String indent) throws ParseException {
		TreeNode root = term(indent + " ");

		while(itTokens.hasNext()) {
			String lastToken = nextToken;
			if(nextToken!=null) {
				if(nextToken.compareTo("+") == 0 || nextToken.compareTo("-") == 0) {
				
					// Next token better be a term
					if(itTokens.hasNext()) {
						nextToken = itTokens.next();					
						root = new TreeNode(lastToken, root, term(indent + " "));
					}
				}
				else
					root = term(indent + " ");
			}
			else
				throw new ParseException("Invalid: Term must be followed by a +/-");
		}
		return root;
    }

    // term = factor ( ("/\" | "\/") factor )*
    private TreeNode term(String indent) throws ParseException {
		TreeNode root = factor(indent + " ");

		while(itTokens.hasNext()) {
			nextToken = itTokens.next();
			if(nextToken.compareTo("/\\") == 0 || nextToken.compareTo("\\/") == 0) {
			
				root = new TreeNode(nextToken, root);
				
				// Next token better be a factor
				if(itTokens.hasNext()) {
					nextToken = itTokens.next();
					root = new TreeNode(nextToken, root, factor(indent + " "));
				}
				else
					throw new ParseException("Invalid: +/- must be followed by a term");
			}
			else break;
		}
		return root;
    }

    // factor = "abs" factor | "(" expr ")" | flopon
    private TreeNode factor(String indent) throws ParseException {
		TreeNode root = new TreeNode("");
		
		if(nextToken.compareTo("abs") == 0) {
			root = new TreeNode(nextToken);
			
			// Next token better be a factor
			if(itTokens.hasNext()) {
				nextToken = itTokens.next();
				root = new TreeNode(nextToken, root, factor(indent + " "));
			}
			else
				throw new ParseException("Invalid: abs must be followed by a factor");
		}
		else if(nextToken.compareTo("(") == 0) {
			System.out.println("blah");
			if(itTokens.hasNext()) {
				nextToken = itTokens.next();
				System.out.println(nextToken); //DEBUG
				root = expr(indent + " ");
				
				System.out.println(nextToken); //DEBUG
				if(nextToken.compareTo(")") == 0) {
					if(itTokens.hasNext() && nextToken!=null)	nextToken = itTokens.next();
				}
			}
			else 
				throw new ParseException("Invalid: No nextToken found");
		}
		else {
			root = flopon(indent + " ");
		}
		nextToken = null;
		return root;
    }

    // flopon = digits ("." digits)? ("e" ("+"|"-") digs)?
    private TreeNode flopon(String indent) throws ParseException {
		if(Character.isDigit(nextToken.charAt(0))) {
			return new TreeNode(nextToken);
		}
		else
			throw new ParseException("Invalid Flopon");
    }
	
	public static void main(String args[]) throws ParseException{
		String line;
		// input on the command line
		if(args.length>0)
			line = args[0];
		// or do a standard test
		else
			line = "(4.5 - 6.700) - 2";
		System.out.println("line: [" + line + "]");
		TokenIter tokIt = new TokenIter(line);
		ParseTreeExpr buildTree = new ParseTreeExpr(tokIt, true);
		Tree pTree = buildTree.line();
		
		System.out.println("expression tree:"); 
	    pTree.preorderTraverse();
	}
}