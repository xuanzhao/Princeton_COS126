/*************************************************************************
 *  YOU DO NOT NEED TO MODIFY THIS FILE
 *
 *  Compilation:  javac TSPTimer.java
 *  Execution:    java TSPTimer n
 *  Dependencies: Tour.java Point.java Stopwatch.java StdOut.java
 *
 *  Time the two heuristics by generated random instances of size n.
 *
 *  % java TSPTimer n
 *
 *************************************************************************/

public class TSPTimer {

    public static void main(String[] args) {
        double lo = 0.0, hi = 600.0;
        int n = Integer.parseInt(args[0]);

        // generate data and run nearest insertion heuristic
        Stopwatch timer1 = new Stopwatch();
        Tour tour1 = new Tour();
        for (int i = 0; i < n; i++) {
            double x = StdRandom.uniform(lo, hi);
            double y = StdRandom.uniform(lo, hi);
            Point p = new Point(x, y);
            tour1.insertNearest(p);
        }
        double elapsed1 = timer1.elapsedTime();
        StdOut.println("Tour length = " + tour1.length());
        StdOut.println("Nearest insertion:  " + elapsed1 + " seconds");
        StdOut.println();


        // generate data and run smallest insertion heuristic
        Stopwatch timer2 = new Stopwatch();
        Tour tour2 = new Tour();
        for (int i = 0; i < n; i++) {
            double x = StdRandom.uniform(lo, hi);
            double y = StdRandom.uniform(lo, hi);
            Point p = new Point(x, y);
            tour2.insertSmallest(p);
        }
        double elapsed2 = timer2.elapsedTime();
        StdOut.println("Tour length = " + tour2.length());
        StdOut.println("Smallest insertion:  " + elapsed2 + " seconds");
    }

}
