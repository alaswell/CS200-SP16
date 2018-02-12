public class Equation {
    private boolean debug;
    private String nextToken;
    private TokenIter itTokens;

    public Equation(TokenIter tokenIter, boolean bl) {
        this.itTokens = tokenIter;
        this.debug = bl;
        if (this.itTokens.hasNext()) {
            this.nextToken = this.itTokens.next();
        }
    }

    private void error(String string) throws ParseException {
        throw new ParseException(string);
    }

    public IdVal line(BST symTab) throws BSTException, ParseException {
		IdVal identVal = null;
        if (this.nextToken != null) {
			// equation must begin with an identifier
			if(Character.isLetter(this.nextToken.charAt(0))) {
				String identifier = this.nextToken;
				this.nextToken = this.itTokens.next(); // get next
				// identifier must be followed by an '='
				if(this.nextToken.charAt(0) == '=') {
					this.nextToken = this.itTokens.next(); // get next
					TreeNode treeNode = this.expr(""); // build expression tree
					Tree tree = new Tree(treeNode);
					
					/** Evaluate the tree **/
					// tree.preorderTraverse(); // debug
					Boolean value = tree.postorderEval(treeNode, symTab);
					identVal = new IdVal(identifier, value);
					symTab.insertItem(identVal);

					this.nextToken = null; 	
				}
				else
					this.error("Invalid: Not a valid equation, missing '='");				
			}
			else
				this.error("Invalid: Must begin equation with an identifier");
        }
        if (this.nextToken != null) {
            this.error("end of line expected");
        }
        return identVal;
}    

    private TreeNode expr(String string) throws ParseException {
    	TreeNode treeNode = this.term(string + " ");
        while (this.itTokens.hasNext()) {
            String string2 = this.nextToken;
            if (this.nextToken != null) {
                if (this.nextToken.compareTo("|") == 0) {
                    if (!this.itTokens.hasNext()) continue;
                    this.nextToken = this.itTokens.next();
                    treeNode = new TreeNode(string2, treeNode, this.term(string + " "));
                    continue;
                }
                treeNode = this.term(string + " ");
                continue;
            }
            throw new ParseException("Invalid: Term must be followed by a '|'");
        }
        return treeNode;
    }

    private TreeNode term(String string) throws ParseException {
        TreeNode treeNode = this.factor(string + " ");
        while (this.itTokens.hasNext()) {
            String string2 = null;
            this.nextToken = this.itTokens.next();
			if (this.nextToken.compareTo("&") != 0) break; // must be followed by an '&'
            if (this.nextToken != null) {
                string2 = this.nextToken;
            }
            if (this.itTokens.hasNext()) {
                this.nextToken = this.itTokens.next();
                treeNode = new TreeNode(string2, treeNode, this.factor(string + " "));
                continue;
            }
            throw new ParseException("Invalid: | must be followed by a term");
        }
        return treeNode;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private TreeNode factor(String string) throws ParseException {
        TreeNode treeNode;
        if (this.nextToken.charAt(0) == '!') {
            treeNode = new TreeNode(this.nextToken);
            if (!this.itTokens.hasNext()) throw new ParseException("Invalid: ! must be followed by a factor");
            this.nextToken = this.itTokens.next();
			treeNode = new TreeNode("!", this.factor(string + " "));
        } else if (this.nextToken.charAt(0) == '(') {
            if (!this.itTokens.hasNext()) throw new ParseException("Invalid: '(' must be followed by an expr");
            this.nextToken = this.itTokens.next();
            treeNode = this.expr(string + " ");
            if (this.nextToken.charAt(0) != ')') throw new ParseException("Invalid: No ending paren found");
            /** /if (this.itTokens.hasNext()) {
             *   this.nextToken = this.itTokens.next();
             	}*/
	System.out.println("nextTok:" + this.nextToken); // debug
        } else if (this.nextToken.charAt(0) == '0' || this.nextToken.charAt(0) == '1' || this.nextToken.charAt(0) == ')' || this.nextToken.charAt(0) == '|' || this.nextToken.charAt(0) == '&' || Character.isLetter(this.nextToken.charAt(0))) {
			treeNode = new TreeNode(this.nextToken);
			return treeNode;
		} else {
			throw new ParseException("Invalid Factor");
        }
        //this.nextToken = null;
        return treeNode;
    }

    public static void main(String[] arrstring) throws BSTException, ParseException {
        String string = arrstring.length > 0 ? arrstring[0] : "b = (1 | 0) & 0";
        System.out.println("line: [" + string + "]");
        TokenIter tokenIter = new TokenIter(string);
        Equation eq = new Equation(tokenIter, true);
		BST symbolTable = new BST();
        IdVal tree = eq.line(symbolTable);
		System.out.println("result: " + tree);
    }	
}
