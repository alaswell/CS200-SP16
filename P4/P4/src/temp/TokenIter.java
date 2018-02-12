import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TokenIter implements Iterator<String> {

    //input line to be tokenized
    private String line;

    // the next Token, null if no next Token
    private String nextToken;

    // counter for iterating through the string
    private int count;
    
    // Array for holding the tokens
    private ArrayList<String> tokens;

    // implement
    public TokenIter(String line) {
	this.line = line;
	this.nextToken = "";
	this.count = 0;
	this.tokens = new ArrayList<String>();
	parseIt();
    }


    
    // implement
    public String next() throws NoSuchElementException{
    	if(hasNext()) {
    		return tokens.remove(0);
    	}
		else throw new NoSuchElementException("Empty");
    }
    
    
    //implement
    public boolean hasNext() {
    	return !tokens.isEmpty();
    }
    
    public void parseIt() {
    	if(line.length() == 0) tokens.add(null);
    	else {
	    	while(count < line.length()) {
		    	// Don't stop until we have a valid token
		    	boolean found = false;
		    	nextToken = "";
		
		    	while(!found && count < line.length()) {
	
		    		char c = line.charAt(count);
			
		    		if(Character.isLetter(c)) {
						// If it starts with a letter it's an identifier
						found = true;
						nextToken = Character.toString(c);
						if(++count < line.length())
							c = line.charAt(count);
		    			// eat while there is not a space 
		    			while(c != ' ' && c != ')' && count < line.length()) {
		    				nextToken += Character.toString(c);
		    				if(++count < line.length())	
		    					c = line.charAt(count);
		    			}
		    			tokens.add(nextToken);
		    		}
		    		else if(c == '(' || c == ')' || c == '0' || c == '1' || c == '|' || c == '&' || c == '!' || c == '=') {
		    			found = true;
		
		    			// Eat
		    			nextToken = Character.toString(c);
		    			count++;
		    			tokens.add(nextToken);
		    		}
		    		else count++;	// Is a space
		    	}
	    	}
    	}
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
	    line = "";
	System.out.println("line: [" + line + "]");
	TokenIter tokIt = new TokenIter(line);
	while (tokIt.hasNext())
	    System.out.println("next token: [" + tokIt.next() + "]");
    }
}
