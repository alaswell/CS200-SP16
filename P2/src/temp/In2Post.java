import java.util.ArrayList;
import java.util.Scanner;

public class In2Post {
    // Use expQueue to create the postfix expression
    private Queue expQueue;

    // opStack maintains operators while building expQueue
    private Stack opStack;
    
    // evalStack is used for evaluation of the postfix expression in expQueue
    private Stack evalStack;
    
    // when scanning a next Token, put it in nextToken
    private String nextToken;

    // Iterator tokIt gets the input line in the constructor and produces tokens 
    private TokenIter tokIt;

    // provided, used in TestServer codes, do not change
    public Queue getExpQueue(){
	return expQueue;
    }

    // implement constructor
    public In2Post(String line, boolean debug){
	// show line with white space
	System.out.println("input line: [" + line + "]");
	// initialize tokIt, opStack, expQueue and evalStack 
		this.tokIt = new TokenIter(line);
		this.opStack = new Stack(debug);
		this.expQueue = new Queue(debug);
		this.evalStack = new Stack(debug);		
    }

    // convert infix to postfix using the algorithm discussed in 
    // lecture L3: Queues
    public void convertIn2Post() throws StackException, QueueException{
		while (tokIt.hasNext()) {
			nextToken = tokIt.next();
			
			if(Character.isDigit(nextToken.charAt(0))) {
				expQueue.enqueue(nextToken);
			}
			else if(nextToken.equals("(")) {
				opStack.push(nextToken);
			}
			else if(nextToken.equals(")")) {
				String toStack = opStack.pop();
				
				while(!(toStack.equals("("))) {
					expQueue.enqueue(toStack);
					toStack = opStack.pop();
				}
			}
			else {
				/** If stack empty or top is open push, else
				/*  pop and enqueue operators with greater or equal
				/*  precedence, until operator with lower precedence on
				/*  stack, or open on stack, or stack empty
				*/
				if(opStack.isEmpty() || expQueue.size() > 0) {
					opStack.push(nextToken);
				}
				else {
					String fromStack;
					do { 
						if(!opStack.isEmpty() || expQueue.size() > 0) break;
						fromStack = opStack.pop();						
						expQueue.enqueue(fromStack);
					} while(this.getPrecedence(nextToken) < this.getPrecedence(fromStack));
				}
			}								
		}
		while(!(opStack.isEmpty())) {
			String fromStack = opStack.pop();
			expQueue.enqueue(fromStack);
		}
    }
	
	public int getPrecedence(String s) {
		int x = 0;
		switch(s) {
			case "!":
				x = 3;
				break;
				
			case "&":
				x = 2;
				break;
				
			case "^":
				x = 1;
				break;
				
			case "true":
			case "false":
				x = 0;
				break;
			default:
				x = -1;
		}
		return x;
	}
	
    /* evaluate the postfix expression in expQueue
     * using evalStack and returning the final result value
     * when encountering an operand, push it on evalStack
     * when encountering an n-ary operator, 
     *    pop n values off the evalStack (n=2: right, then left)
     *    perform the operation, and push the result on the evalStack
     * at the end of the expression,
     *    pop the result off the stack and return it 
     *    
     * expQueue is either empty, or contains a valid postfix expression
     * if expQueue empty return ""     
     */
    public String evalPost() throws QueueException, StackException{
	return "";
    }

    public static void main(String[] args) throws StackException, QueueException{
	// exercise expressions
	boolean db = true;
	System.out.println("Testing In2Post, debug: " + db);
	
	String line = "";
	In2Post ex0 = new In2Post(line, db);
	ex0.convertIn2Post();
	System.out.println("postfix: " + ex0.expQueue);
	System.out.println("result: " + ex0.evalPost());

	line = " true ^  false  ";
	In2Post ex2 = new In2Post(line, db);
	ex2.convertIn2Post();
	System.out.println("postfix: " + ex2.expQueue);
	System.out.println("result: " + ex2.evalPost());

	line = "! true";
	In2Post ex4 = new In2Post(line, db);
	ex4.convertIn2Post();
	System.out.println("postfix: " + ex4.expQueue);
	System.out.println("result: " + ex4.evalPost());

	db = false;
	System.out.println("debug: " + db);
	
	line = "true ^ true & false";
	In2Post ex5 = new In2Post(line, db);
	ex5.convertIn2Post();
	System.out.println("postfix: " + ex5.expQueue);
	System.out.println("result: " + ex5.evalPost());
 
	line = "true & (true ^ false)";	
	In2Post ex6 = new In2Post(line, db);
	ex6.convertIn2Post();
	System.out.println("postfix: " + ex6.expQueue);
	System.out.println("result: " + ex6.evalPost());
	
	line = "! true";
	In2Post ex7 = new In2Post(line, db);
	ex7.convertIn2Post();
	System.out.println("postfix: " + ex7.expQueue);
	System.out.println("result: " + ex7.evalPost());

	line = "! true ^ true";
	In2Post ex8 = new In2Post(line, db);
	ex8.convertIn2Post();
	System.out.println("postfix: " + ex8.expQueue);
	System.out.println("result: " + ex8.evalPost());

	line = "! (! true ^ false)";
	In2Post ex9 = new In2Post(line, db);
	ex9.convertIn2Post();
	System.out.println("postfix: " + ex9.expQueue);
	System.out.println("result: " + ex9.evalPost());
    }
}
