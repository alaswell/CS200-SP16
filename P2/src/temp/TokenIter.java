import java.util.Iterator;
import java.lang.*;

public class TokenIter implements Iterator<String> {

    //input line to be tokenized
    private String line;

    // the next Token, null if no next Token
    private String nextToken;

    // counter for iterating through the string
    private int count;

    // implement
    public TokenIter(String line) {
	this.line = line;
	this.nextToken = "";
	this.count = 0;
    }

    @Override
    // implement
    public String next() {
	return this.nextToken;
    }
    

    @Override
    // implement
    public boolean hasNext() {
	// Don't stop until we have a valid token
	boolean found = false;
	
	while(!found && count < line.length()) { 		
		// If the next token is a valid token
		// return and "eat" it
		if(isToken(line.charAt(count))) {			
			this.nextToken = Character.toString(line.charAt(count));
			count++;
			found = true;
		}
		
		// is it "true"
		else if(line.charAt(count) == 't') {
			count++;
			if(line.charAt(count) == 'r') {
				count++;
				if(line.charAt(count) == 'u') {
					count++;
					if(line.charAt(count) == 'e') {
						count++;
						if(!(Character.isLetter(line.charAt(count)))) {
							this.nextToken = "true";
							found = true;
						}
					}
				}
			}	
		}
	
		// is is "false"
		else if(line.charAt(count) == 'f') {
			count++;
			if(line.charAt(count) == 'a') {
				count++;
				if(line.charAt(count) == 'l') {
					count++;
					if(line.charAt(count) == 's') {
						count++;
						if(line.charAt(count) == 'e') {
							count++;
							if(!(Character.isLetter(line.charAt(count)))) {
								this.nextToken = "false";
								found = true;
							}
						}
					}
				}
			}	
		}
		// it is not valid so "eat" and keep looking 
		if(!found) count++;
		}
	return found;
	}

    public boolean isToken(char c) {
	boolean foo = false;
	String s = Character.toString(c);

	switch(s) {
		case "^":
		case "&":
		case "!":
		case "(":
		case ")":
			foo = true;
			break;
		default:
	}
	return foo;
    }

    @Override
    // provided, do not change
    public void remove() {
	// TODO Auto-generated method stub
	throw new UnsupportedOperationException();
    }

    // provided
    public static void main(String[] args) {
	String line;
	// you can play with other inputs on the command line
	if(args.length>0)
	    line = args[0];
	// or do the standard test
	else
	    line = " ! BAD (true ^ false) % truelybad";
	System.out.println("line: [" + line + "]");
	TokenIter tokIt = new TokenIter(line);
	while (tokIt.hasNext())
	    System.out.println("next token: [" + tokIt.next() + "]");
    }
}
