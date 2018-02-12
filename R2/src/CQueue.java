public class CQueue implements QueueIF {
    boolean debug;	    
    Object[] queueC;   
    int count = 0;
    int front = 0;
    int back = 0;	      
    static int SIZE;
	      
    public CQueue(int size, boolean debug) {
	this.debug = debug;
	queueC = new Object[size];
	back = size - 1;
	SIZE = size;
    }
    
    public void enqueue(Object token) {
	back = (back + 1) % SIZE;
	queueC[back] = token;
	if(count < SIZE)
		count++;
	else
		front++;
    }

    public Object dequeue() throws QueueException {
	if(count == 0)
		throw new QueueException();
	else {
		Object temp;
	
		temp = queueC[front];
		front = (front + 1) % SIZE;
		--count;
		return temp;
	}
	

    }

    public int size() {
	return SIZE;
    }

    public static void main(String[] args) throws QueueException {
	CQueue intQueue = new CQueue(4, true);
	System.out.println("create Queue of Integers 12 34 56 78 90");
	intQueue.enqueue(12);	
	intQueue.enqueue(34);
	intQueue.enqueue(56);
	intQueue.enqueue(78);		    // Fills the circular queue
	intQueue.enqueue(90);		    // Replaces 12
			
	Object o34 = intQueue.dequeue();
	if (intQueue.debug)
	    System.out.println("o34: " + o34);		
	Object o56 = intQueue.dequeue();
	if (intQueue.debug) {
	    System.out.println("o56: " + o56);		
	    System.out.println("size: " + intQueue.size());
	}
	intQueue.dequeue();		    // Should work
	intQueue.dequeue();		    // Should work
	intQueue.dequeue();		    // Should throw an exception
    }
}
