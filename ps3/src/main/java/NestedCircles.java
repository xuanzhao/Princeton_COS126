
/******************************************************************************
 * Draws an N-level set of nested circles in random colors.
 * Usage:        java NestedCircles N
 * Dependencies: StdDraw.java
 ****************************************************************************/
public class NestedCircles {

    // draw a circle with some embellishments
    // the center is (x, y) and the radius is r
    public static void fancyCircle(double x, double y, double r) {
        // use one of two color pairs at random
        double randomBit = (int)(Math.random()*2); // equally likely 0 or 1
        // this is the color that will fill the circle
        if (randomBit == 0)
            StdDraw.setPenColor(StdDraw.CYAN);
        else
            StdDraw.setPenColor(StdDraw.ORANGE);
        StdDraw.filledCircle(x, y, r);
        // this is the color for the circle's border
        if (randomBit == 0)
            StdDraw.setPenColor(StdDraw.BLUE);
        else
            StdDraw.setPenColor(StdDraw.RED);
        StdDraw.circle(x, y, r);
    }

    // draw an order-n nested circle, centred on (x, y) with radius r
    public static void draw(int n, double x, double y, double r) {
        if (n==0) return;
        fancyCircle(x, y, r);

        double halfRadius = r/2;
        // recursively draw two nested circles of order n-1
        draw(n-1, x - halfRadius, y, halfRadius);
        draw(n-1, x + halfRadius, y, halfRadius);
    }

    // read in a command-line argument N and plot an order-N circle
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double x = 0.5, y = 0.5;      // biggest circle centred at (0.5, 0.5)
        draw(N, x, y, 0.5);           // radius fills up [0,1] x [0,1] view
    }
}