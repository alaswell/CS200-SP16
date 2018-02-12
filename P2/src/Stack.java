import java.util.ArrayList;

public class Stack implements StackIF {

    // ArrayList opStack implements Stack, initialized in constructor
    private ArrayList<String> opStack;
    //
    // debug flag, set in constructor
    private boolean debug;

    public Stack(boolean debug) {
	opStack = new ArrayList<String>();
	this.debug = debug;
    }


    /* Implement
     * push String op on opStack
     * In debug mode print "push: " + pushed value
     i*/
    public void push(String op) {
	opStack.add(op);
	if(debug)System.out.printf("push: %s\n", op);
    }


    /* Implement
     * pop and return String from opStack
     * If opStack empty, throw StackException("popping from empty stack!")
     * In debug mode print "pop: " + popped value
     */
    public String pop() throws StackException {
	String s = null;
	if(opStack.isEmpty()) throw new StackException("popping from empty stack!");
	else {
		s = opStack.remove(opStack.size() - 1);
	} 
	if(debug)System.out.printf("pop: %s\n", s);
	return s;	
    }


    /* Implement
     * return, but do not pop, top of opStack
     * If opStack empty, throw StackException("peeking empty stack!")
     * In debug mode print "peek: " + peeked value
     */
    public String peek() throws StackException {
	String s = null;
	if(opStack.isEmpty()) throw new StackException("peeking while stack empty!");
	else {
		s = opStack.get(opStack.size() - 1);
	}
	if(debug)System.out.printf("peek: %s\n", s);
	return s;
    }

    /* Implement
     * return true if opStack empty, false otherwise
     */
    public boolean isEmpty() {
	return opStack.isEmpty();
    }

    // provided, do not change
    public String toString() {
	return opStack.toString();
    }

    public static void main(String[] args) throws StackException {
	Stack s = new Stack(true);
	s.push("alpha");
	s.push("beta");
	System.out.println(s);
	s.peek();
	System.out.println(s);	
	System.out.println(s.pop());
	System.out.println(s);
	System.out.println(s.pop());
    }
}
