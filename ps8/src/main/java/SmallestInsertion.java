/*************************************************************************
 *  YOU DO NOT NEED TO MODIFY THIS FILE
 *
 *  Compilation:  javac SmallestInsertion.java
 *  Execution:    java SmallestInsertion < file.txt
 *  Dependencies: Tour.java Point.java StdIn.java StdDraw.java
 *
 *  Run smallest insertion heuristic for traveling salesperson problem
 *  and plot results.
 *
 *  % java SmallestInsertion < tsp1000.txt
 *
 *************************************************************************/

public class SmallestInsertion {

    public static void main(String[] args) {

        String file = args[0];
        In in = new In(file);
        // get dimensions
        int width = in.readInt();
        int height = in.readInt();
	    StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        // turn on double buffering
        StdDraw.enableDoubleBuffering();

        // run smallest insertion heuristic
        Tour tour = new Tour();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point p = new Point(x, y);
            tour.insertSmallest(p);

            // uncomment the 4 lines below to animate
            // StdDraw.clear();
            // tour.draw();
            // StdDraw.text(100, 0, "" + tour.length());
            // StdDraw.show();
            // StdDraw.pause(50);
        }

        // draw to standard draw 
        tour.draw();
        StdDraw.show();
        
        // print tour to standard output
        tour.show();
        StdOut.printf("Tour length = %.4f\n", tour.length());
        StdOut.printf("Number of points = %d\n", tour.size());
    }

}
