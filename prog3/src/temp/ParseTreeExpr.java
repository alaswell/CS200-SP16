public class ParseTreeExpr {    private boolean debug;    private String nextToken;    private TokenIter itTokens;    public ParseTreeExpr(TokenIter tokenIter, boolean bl) {        this.itTokens = tokenIter;        this.debug = bl;        if (this.itTokens.hasNext()) {            this.nextToken = this.itTokens.next();        }    }    private void error(String string) throws ParseException {        throw new ParseException(string);    }    public Tree line() throws ParseException {        Tree tree = new Tree();        if (this.nextToken != null) {            TreeNode treeNode = this.expr("");            tree = new Tree(treeNode);        }        if (this.nextToken != null) {            this.error("end of line expected");        }        return tree;    }    private TreeNode expr(String string) throws ParseException {        TreeNode treeNode = this.term(string + " ");        while (this.itTokens.hasNext()) {            String string2 = this.nextToken;            if (this.nextToken != null) {                if (this.nextToken.compareTo("+") == 0 || this.nextToken.compareTo("-") == 0) {                    if (!this.itTokens.hasNext()) continue;                    this.nextToken = this.itTokens.next();                    treeNode = new TreeNode(string2, treeNode, this.term(string + " "));                    continue;                }                treeNode = this.term(string + " ");                continue;            }            throw new ParseException("Invalid: Term must be followed by a +/-");        }        return treeNode;    }    private TreeNode term(String string) throws ParseException {        TreeNode treeNode = this.factor(string + " ");        while (this.itTokens.hasNext()) {            String string2 = null;            this.nextToken = this.itTokens.next();            if (this.nextToken.compareTo("/\\") != 0 && this.nextToken.compareTo("\\/") != 0) break;            if (this.nextToken != null) {                string2 = this.nextToken;            }            if (this.itTokens.hasNext()) {                this.nextToken = this.itTokens.next();                treeNode = new TreeNode(string2, treeNode, this.factor(string + " "));                continue;            }            throw new ParseException("Invalid: +/- must be followed by a term");        }        return treeNode;    }    /*     * Enabled force condition propagation     * Lifted jumps to return sites     */    private TreeNode factor(String string) throws ParseException {        TreeNode treeNode;        if (this.nextToken.compareTo("abs") == 0) {            treeNode = new TreeNode(this.nextToken);            if (!this.itTokens.hasNext()) throw new ParseException("Invalid: abs must be followed by a factor");            this.nextToken = this.itTokens.next();            treeNode = this.nextToken.compareTo("(") == 0 ? new TreeNode(treeNode.getItem(), this.factor(string + " ")) : new TreeNode(this.nextToken, treeNode, this.factor(string + " "));        } else if (this.nextToken.compareTo("(") == 0) {            if (!this.itTokens.hasNext()) throw new ParseException("Invalid: No nextToken found");            this.nextToken = this.itTokens.next();            treeNode = this.expr(string + " ");            if (this.nextToken.compareTo(")") != 0) throw new ParseException("Invalid: No ending paren found");            if (this.itTokens.hasNext()) {                this.nextToken = this.itTokens.next();            }        } else {            treeNode = this.flopon(string + " ");        }        this.nextToken = null;        return treeNode;    }    private TreeNode flopon(String string) throws ParseException {        if (Character.isDigit(this.nextToken.charAt(0))) {            return new TreeNode(this.nextToken);        }        throw new ParseException("Invalid Flopon");    }    public static void main(String[] arrstring) throws ParseException {        String string = arrstring.length > 0 ? arrstring[0] : "3 /\\ 2 \\/ 1";        System.out.println("line: [" + string + "]");        TokenIter tokenIter = new TokenIter(string);        ParseTreeExpr parseTreeExpr = new ParseTreeExpr(tokenIter, true);        Tree tree = parseTreeExpr.line();        System.out.println("expression tree:");        tree.preorderTraverse();    }}