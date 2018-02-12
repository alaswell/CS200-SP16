import java.util.ArrayList;


public class Queue implements QueueIF {

    // maintains expression, initialze in constructor
    private ArrayList<String> expQueue;

    // debug flag, set in constructor
    private boolean debug;
    
    // implement constructor
    public Queue(boolean debug) {
	expQueue = new ArrayList<String>();
	this.debug = debug;
    }
    
    /* implement:
     * enqueue token at the end of expQueue
     * In debug mode print "enqueue: " + enqueued value
     */
    public void enqueue(String token) {
	expQueue.add(token);
	if(debug)System.out.printf("enqueue: %s\n", token);
    }
    
    /* implement
     * dequeue token from the front of the expQueue
     * If expQueue empty, throw QueueException("dequeueing empty queue!")
     * In debug mode print "dequeue: " + dequeued value
     */ 
    public String dequeue() throws QueueException {
	if(expQueue.isEmpty())throw new QueueException("dequeueing empty queue!");
	return expQueue.remove(0);
    }

    // implement, return size (#elements) of the queue
    public int size() {
	return expQueue.size();
    }

    // provided, do not change
    public String toString() {
	return expQueue.toString();
    }
    
    public static void main(String[] args) throws QueueException {
	Queue q = new Queue(false);
	q.enqueue("gamma");
	q.enqueue("delta");
	System.out.println(q);
	System.out.println(q.dequeue());
	System.out.println(q.dequeue());
    }
}
