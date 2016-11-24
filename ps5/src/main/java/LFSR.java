public class LFSR {

    private int n;
    private int[] reg;
    private int tapPosition;

    // creates an LFSR with the specified seed and tap
    public LFSR(String seed, int tap) {
        n = seed.length();
        reg = new int[n];
        for (int i = 0; i< n; i++) {
            reg[i] = seed.charAt(n - i -1) - 48;
        }
        tapPosition = tap;
    }

    // simulates one step and return the new bit (as 0 or 1)
    public int step() {
        int bit = reg[n-1] ^ reg[tapPosition];
        for (int i = n -1; i > 0; i--) {
            reg[i] = reg[i-1];
        }
        reg[0] = bit;

        return bit;
    }

    // simulates k steps and return the k bits as a k-bit integer
    public int generate(int k) {
        int v = 0;

        for (int i = 0; i < k; i++) {
            v *= 2;
            v += step();
        }

        return v;

    }

    // return a string representation of this LFSR
    public String toString()  {
        String seed = "";
        for (int i = n -1 ; i >= 0; i--) {
            seed += reg[i];
        }
        return seed;
    }

    // unit tests this class
    public static void main(String[] args) {


        LFSR lfsr1 = new LFSR("01101000010", 8);
        StdOut.println(lfsr1);
        for (int i = 0; i < 10; i++) {
            int bit = lfsr1.step();
            StdOut.println(lfsr1 + " " + bit);
        }

        LFSR lfsr2 = new LFSR("01101000010", 8);
        StdOut.println(lfsr2);
        for (int i = 0; i < 10; i++) {
            int r = lfsr2.generate(5);
            StdOut.println(lfsr2 + " " + r);
        }
    }
}