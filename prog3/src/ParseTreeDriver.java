import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParseTreeDriver {
    public static void main(String[] args) throws FileNotFoundException,
						  ParseException{
	Scanner scan = new Scanner(new File (args[0]));
	boolean debug = (args.length > 1);

	// loop through lines in input file
	while (scan.hasNextLine()) {
	    String line = scan.nextLine();
	    System.out.println("next line: [" + line +"]");
	    TokenIter tokIt = new TokenIter(line);
	    ParseTreeExpr buildTree = new ParseTreeExpr(tokIt, debug);
	    Tree pTree = buildTree.line();
	    
	    System.out.println("expression tree:"); 
	    pTree.preorderTraverse();
	    
	    System.out.println("result: " + pTree.postorderEval());
	}
    }
}
