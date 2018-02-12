import java.util.ArrayList;
import java.util.Scanner;

public class Hanoi implements StackIF{
	private int count = 0;
	private boolean debug;
	private ArrayList<Object> rts;

	// provided: do not change
	// constructor sets debug: 
	// if debug is true, rtsHanoi prints the RTS
	public Hanoi(boolean debug){
		this.debug = debug;
	}

	// provided: do not change
	private void resetCount(){
	// reset count to 0 to count another Hanoi implementation
		count = 0; 
	}
	
	// implement
	private int getCount(){
		return this.count;  
	}

	// implement
	private void initRTS(){
	// assign a new empty array list, implementing the run time stack, to rts
	// null is not an empty array list! Do a proper new
		rts = new ArrayList<Object>();
	}

	
	// implement
	@Override
	/*
	 * push Frame op on Run Time Stack (at end of array list)
	 */
	public void push(Object op) {
		rts.add(op);	
	}

	// implement
	@Override
	/*
	 * pop and return frame Object from Run Time Stack
	 * If Stack empty, throw StackException
	 */
	public Object pop() throws StackException {
		// TODO Auto-generated method stub
		Object popped = null;
		if(!rts.isEmpty())
			// remove last frame Object from the run time stack
			popped = rts.remove(rts.size() -1);
		else throw new StackException("popping from empty stack!");
		return popped;
	}

	// implement
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		// the stack is empty
		//  when the array list rts is empty
		if(rts.size() == 0)
			return true;
		else
			return false;
	}

	//Implement Hanoi using an explicit Run Time Stack rts
	//Initially, there is one Frame [state,n,from,to] on rts
        //  (put there by itHanoi)
	//When popping a frame:
	// if n == 0 do nothing (discard frame)
	// else if frame in state 0:
	//       Go into state 1 by pushing [1,n,from,to]
	//       and call hanoi(n-1,from,via) by pushing [0,n-1,from,via]
	// else if in state 1
	//    print disk n move (see recHanoi)
	//    go into state 2 by pushing [2,n,from,to] 
	//    and c all hanoi(n-1,via,to) by pushing [0,n-1,via,to]
	// else (in state 2) do nothing	(stack frame has already been popped)
	private void rtsHanoi() throws StackException{
	    	// while rts not empty
		while(!isEmpty()){
			//  use ArrayList toString method to print rts
			if(debug)System.out.println("RTS: " + rts);
			// put your code here:
			//  pop a frame
			//  check its state 
			//  and perform operation described above   
			Frame f = (Frame) pop();
			int n = f.getN();
			int from = f.getFrom();
			int to = f.getTo();
			int via = 6 - f.getFrom() - f.getTo();
			int state = f.getState();
	
			if(n > 0) {
				if(state == 0) {
					// Go into state 1 by pushing [1,n,from,to]
					f.setState(1);
					push(f);
				
					// Call hanoi(n-1,from,via) by pushing [0,n-1,from,via]
					Frame g = new Frame(n-1, from, via);
					push(g);				
				}
				else if(state == 1) {
					System.out.println("move disk " + n + " from " + from + " to " + to);
					count++;
					f.setState(2);
					push(f);
	
					// Call hanoi(n-1,from,via) by pushing [0,n-1,from,via]
                	                Frame g = new Frame(n-1, via, to);
                        	        push(g);
				}
			}			
		}
	}

        // provided: do not change
	// iterative Hanoi using stack of frames rts
	public void itHanoi(int n, int from, int to) throws StackException{
		Frame start = new Frame(n,from,to);
		initRTS();
		push(start);
		rtsHanoi();
	}


        // provided: do not change
        // pegs are numbers, via is computed
	// number of moves are counted
	// n==0: empty base case
        // state corresponds to return address
	public void recHanoi(int n, int from, int to){
		//state 0
		if (n>0) {
			  int via = 6 - from - to;
			  recHanoi(n-1,from, via);
 			  //state 1
			  System.out.println("move disk " + n + 
					" from " + from + " to " + to);
			  count++;
			  recHanoi(n-1,via,to);
			//state 2

		}
	}

	/**
	 * @param args
	 * @throws StackException 
	 */
	public static void main(String[] args) throws StackException {
		// Recursive and iterative Hanoi
		// In both cases count moves
		// In debug mode, print the run time stack in the iterative method
		boolean debug = false;
		int n = Integer.parseInt(args[0]);
		// set debug flag, if it is set in the command arguments
		if(args.length > 1)
			debug = true;
		System.out.println("Recursive and Iterative Hanoi: n = " +n+ ", debug = " +debug);
		Hanoi h = new Hanoi(debug);
		if(n>0){
			h.resetCount();
			System.out.println("\nrecHanoi " +n+ " from 1 to 3");
			h.recHanoi(n,1,3);
			System.out.println("Number of moves: " + h.getCount());


			h.resetCount();
			System.out.println("\nitHanoi " +n+ " from 1 to 3");				
			h.itHanoi(n,1,3);
			System.out.println("Number of moves: " + h.getCount());				
		}

	}
}
