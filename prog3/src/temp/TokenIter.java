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


    @Override
    // implement
    public String next() throws NoSuchElementException{
    	if(hasNext()) {
    		return tokens.remove(0);
    	}
		else throw new NoSuchElementException("Empty");
    }
    
    @Override
    //implement
    public boolean hasNext() {
    	return !tokens.isEmpty();
    }
    
    public void parseIt() {
    	while(count < line.length()) {
	    	// Don't stop until we have a valid token
	    	boolean found = false;
	    	nextToken = "";
	
	    	while(!found && count < line.length()) {
	    		char c = line.charAt(count);
	    		char lastc = '0';
		
	    		if(Character.isDigit(c)) {
	    			found = true;
	    			
	    			// It's a number so "eat" until
	    			//  we find a space or an open/close paren or endOfString
	    			while(c != ' ' && c != '(' && c != ')' && count < line.length()) {
	    				// If just a digit keep going
	    				if(Character.isDigit(c)) {
		    				nextToken += Character.toString(c);
		    				count++;
		    				if(count < line.length()) {
		    					lastc = c;
		    					c = line.charAt(count);
		    				}
	    				}
	    				else if(c == '.') {
		    				// If it's a decimal point, is it followed by another digit
	    					if(!(Character.isDigit(line.charAt(count+1)))) {
	    						break; // exit if not
	    					}
	    					nextToken += Character.toString(c);
	    					count++;
	    					lastc = c;
	    					c = line.charAt(count);
	    				}
	    				else if(c == 'e') {
	    					// If it's an 'e' is it followed by a +/-
	    					char next = line.charAt(count+1);
	    					if((next != '+') && (next != '-')) {
	    						break; // exit if not
	    					}
	    					else {
		    					nextToken += Character.toString(c);
		    					count++;
		    					lastc = c;
		    					c = line.charAt(count);
	    					}
	    				}
	    				else if(c == '-' || c == '+') {
	    					// If +/- lastc better be an 'e'
	    					if(lastc != 'e')
	    						break; // exit if not
	    					nextToken += Character.toString(c);
	    					count++;
	    					lastc = c;
	    					c = line.charAt(count);
	    				}
	    				else 
	    					break; //Otherwise, it's something else so finish this token
	    			}
	    			tokens.add(nextToken);
	    		}
	    		else if(c == '/') {
	    			found = true;
	
	    			// Eat the max operator "/\"
	    			nextToken = Character.toString(c);
	    			count++;
	    			nextToken += Character.toString(line.charAt(count));
	    			count++;
	    			tokens.add(nextToken);
	    		}
	    		else if(c == '\\') {
	    			found = true;
	
	    			// Eat the min operator "\/"
	    			nextToken = Character.toString(c);
	    			count++;
	    			nextToken += Character.toString(line.charAt(count));
	    			count++;
	    			tokens.add(nextToken);
	    		}
	    		else if(c == 'a') {
	    			found = true;
	    			
	    			// Eat "abs"
	    			nextToken = Character.toString(c);
	    			count++;
	    			nextToken += Character.toString(line.charAt(count));
	    			count++;
	    			nextToken += Character.toString(line.charAt(count));
	    			count++;
	    			tokens.add(nextToken);
	    		}
	    		else if(c == '(' || c == ')' || c == '+' || c == '-') {
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
	    line = "3.3e-45 \\/ 3.4e+45";
	System.out.println("line: [" + line + "]");
	TokenIter tokIt = new TokenIter(line);
	while (tokIt.hasNext())
	    System.out.println("next token: [" + tokIt.next() + "]");
    }
}
