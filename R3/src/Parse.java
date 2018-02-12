import java.util.Scanner;

/*
 * Class for parsing (recognizing) simple prefix expressions
 * Recursive descent parser for grammar:
 * <expression> = <ID> | <operator> <expression> <expression>
 * <operator> = * | + | - | /
 * <ID> = a | b | c
 *
 * Code written by Adele Howe for CS200
 */
public class Parse {
    private String nextIn;
    private Scanner scexp;
    // debug is used to check how the parser is proceeding
    private boolean debug = false;

    public Parse(String sentence) {
	// set up iteration over symbols in the sentence
	scexp = new Scanner(sentence);
	nextIn = scexp.next();
	if (debug) System.out.println("Token: " + nextIn);
    }

    private boolean start() {
	// kicks off parsing and checks that all of string is used
	if (this.expression(" ")) {	// string appears to parse
	    if (nextIn.equals("")) {	// and all of string has been consumed
		return true;
	    }
	    else {
		return false;
	    }				// more string is left
	}
	else {
	    return false;
	}
    }

    /*
     * Each method returns true or false for whether current part is legal
     * <expression> = <ID> | <operator> <expression> <expression>
     */
    private boolean expression(String indent) {
	// try the two different parts of left hand side of rule
	if (debug) System.out.println(indent + "in expression");
	if (ID(indent + " ")) {
	    return true;
	}
	else {
	    // rule <expression> = <operator> <expression> <expression>
	    if (operator(indent + " ")) {
		if (expression(indent + " ")) {
		    if (expression(indent + " ")) {
			return true;
		    }
		    else {
			if (debug) System.out.println(indent + " not an expression");
			return false;
		    }
		}
		else  {
		    if (debug) System.out.println(indent + " not an expression");
		    return false;
		    }
	    }
	    else  {
		if (debug) System.out.println(indent + " not an expression");
		return false;
		}
	    }
    }

    /*
     * rule is
     * <ID> = integer
     * PRE-CONDITION(s): string must not be null
     * POST-CONDITION(s): returns if is a valid int or not
     */
    private boolean ID(String indent) {
	if (debug) System.out.println(indent + "in ID");
	
	try {
		Integer.parseInt(nextIn);
	    if (debug) System.out.println(indent + " found ID");
	    if (scexp.hasNext()) {
		nextIn = scexp.next();
		if (debug) System.out.println("Token: " + nextIn);
	    }
	    else {
		nextIn = "";
	    }
	    //System.out.println("Token: " + nextIn);
	    return true;
	} catch(NumberFormatException e) {
		if (debug) System.out.println(indent + " not an ID");
		return false;
	}
    }

    /*
     * rule is
     * <operator> = + | - | * | /
     */
    private boolean operator(String indent) {
	if (debug) System.out.println(indent + "in operator: " + nextIn + "  " + nextIn.equals("-"));
	if (nextIn.equals("*") || nextIn.equals("-")
	    || nextIn.equals("+")|| nextIn.equals("/")) {
	    if (debug) System.out.println(indent + " found operator");
	    if (scexp.hasNext()) {
		nextIn = scexp.next();
		if (debug) System.out.println("Token: " + nextIn);
	    }
	    else {
		nextIn = "";
	    }
	    //System.out.println("Token: " + nextIn);
	    return true;
	}
	//System.out.println(indent + " not an operator");
	return false;
    }


    /*
     * Main allows us to test the class
     */
    public static void main(String[] args) {
	// legal example
	String string1 = "* 10 3";
	String string2 = "* 10 3 4";
	String string3 = "* a b";

	// illegal example
	//String string1 = "* a b c";

	// what about these...
	//String string1 = "a * b";
	//String string1 = "* + a b - c / a b";
	//String string1 = "a ";
	Parse newInput = new Parse(string1);
	System.out.println("String " + string1 + " is legal? " + newInput.start());
	System.out.println("String " + string2 + " is legal? " + newInput.start());
	System.out.println("String " + string3 + " is legal? " + newInput.start());
	}
}
