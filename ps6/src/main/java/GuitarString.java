/******************************************************************************
 *  Name:    
 *  NetID:   
 *  Precept: 
 *
 *  Partner Name:    
 *  Partner NetID:   
 *  Partner Precept: 
 * 
 * Description: 
 *  
 * This is a template file for GuitarString.java. It lists the constructors
 * and methods you need, along with descriptions of what they're supposed
 * to do.
 *  
 * Note: it won't compile until you fill in the constructors and methods
 *       (or at least commment out the ones whose return type is non-void).
 *
 ******************************************************************************/

public class GuitarString {

    private int nTimes;
    private RingBuffer rb;

    // creates a guitar string of the specified frequency,
    // using sampling rate of 44,100
    public GuitarString(double frequency) {
        int n = (int) Math.ceil(StdAudio.SAMPLE_RATE / frequency);
        rb = new RingBuffer(n);
        for (int i = 0; i < rb.capacity(); i++) {
            rb.enqueue(0);
        }
    }

    // creates a guitar string whose size and initial values are given by
    // the specified array
    public GuitarString(double[] init) {
        rb = new RingBuffer(init.length);
        for (int i = 0; i < rb.capacity(); i++) {
            rb.enqueue(init[i]);
        }
    }

    // returns the number of samples in the ring buffer
    public int length() {
        return rb.size();
    }

    // plucks the guitar string (by replacing the buffer with white noise)
    public void pluck() {
        int size = rb.size();
        for (int i = 0; i< size; i++) {
            rb.dequeue();
        }
        for (int i = 0; i < size; i++) {
            rb.enqueue(Math.random() - 0.5);
        }
    }

    // advances the Karplus-String simulation one time step
    public void tic() {
        double firstV = rb.dequeue();
        double secV = rb.peek();
        double newV = 0.996 * 0.5 * (firstV + secV);
        rb.enqueue(newV);
        nTimes += 1;
    }


    // returns the current sample
    public double sample() {
        return rb.peek();
    }

    // return number of times tic was called
    public int time() {
        // YOUR CODE HERE
        // return the total number of times tic() was called on this instance
        return nTimes;
    }

    // unit tests this class
    public static void main(String[] args) {
        // Test Counstructor
        GuitarString gs1 = new GuitarString(100);
        System.out.println(gs1.sample());
        System.out.println(gs1.length());

        // Test Constructor
        int N = 10;
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < N; i++) {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
        testString.pluck();
        System.out.println(testString.rb);
    }

}
