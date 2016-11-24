/******************************************************************************
 *  Name:    
 *  NetID:   
 *  Precept: 
 *
 *  Partner Name:    
 *  Partner NetID:   
 *  Partner Precept: 
 * 
 *  Description:  
 *
 * This is a template file for RingBuffer.java. It lists the constructors and
 * methods you need, along with descriptions of what they're supposed to do.
 *  
 * Note: it won't compile until you fill in the constructors and methods
 *       (or at least commment out the ones whose return type is non-void).
 *
 ******************************************************************************/

public class RingBuffer {
    // YOUR INSTANCE VARIABLES HERE
    private double[] rb;          // items in the buffer
    private int first;            // index for the next dequeue or peek
    private int last;             // index for the next enqueue
    private int size;             // number of items in the buffer


    // creates an empty ring buffer with the specified capacity
    public RingBuffer(int capacity) {
        rb = new double[capacity];
        this.size = 0;
    }

    // return the capacity of this ring buffer
    public int capacity() {
        return rb.length;
    }

    // return number of items currently in this ring buffer
    public int size() {
        return size;
    }

    // is this ring buffer empty (size equals zero)?
    public boolean isEmpty() {
        return (size == 0);
    }

    // is this ring buffer full (size equals capacity)?
    public boolean isFull() {
        return (size == rb.length);
    }

    // adds item x to the end of this ring buffer
    public void enqueue(double x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        if (isEmpty()) {
            first = 0;
            last = 0;
        }
        rb[last] = x;
        last += 1;
        last %= rb.length;
        size += 1;
    }

    // deletes and returns the item at the front of this ring buffer
    public double dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        double firstV = rb[first];
        first += 1;
        first %= rb.length;
        size -= 1;
        if (isEmpty()) {
            last = 0;
        }

        return firstV;
    }

    // returns the item at the front of this ring buffer
    public double peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    // unit tests this class

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(n);
        for (int i = 1; i <= n; i++) {
            buffer.enqueue(i);
        }
        double t = buffer.dequeue();
        buffer.enqueue(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        System.out.println(buffer.peek());
    }

}
