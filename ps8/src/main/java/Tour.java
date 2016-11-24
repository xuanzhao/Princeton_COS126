import java.util.LinkedList;

/**
 * Created by ken on 11/22/2016 AD.
 */
public class Tour {

    private Node firstNode;

    private class Node {
        private Point p;
        private Node next;

        public Node(Point p) {
            this.p = p;
        }
    }

    //  creates an empty tour
    public Tour() {
    }

    //  creates the 4-point tour a->b->c->d->a (for debugging)
    public Tour(Point a, Point b, Point c, Point d) {
        Node node1 = new Node(a);
        Node node2 = new Node(b);
        Node node3 = new Node(c);
        Node node4 = new Node(d);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node1;

        firstNode = node1;
    }

    //  returns the number of points in this tour
    public int size() {
        int count = 0;
        if (firstNode != null) {
            Node curNode = firstNode;
            do {
                count += 1;
                curNode = curNode.next;
            } while (curNode != firstNode);
        }
        return count;
    }

    //  returns the length of this tour
    public double length() {
        double sum = 0.0;
        if (firstNode != null) {
            Node curNode = firstNode;
            do {
                sum += curNode.p.distanceTo(curNode.next.p);
                curNode = curNode.next;
            } while (curNode != firstNode);
        }
        return sum;
    }

    //  draws this tour to standard drawing
    public void draw() {
        if (firstNode != null) {
            Node curNode = firstNode;
            do {
                curNode.p.drawTo(curNode.next.p);
                curNode = curNode.next;
            } while (curNode != firstNode);
        }
    }

    //  prints this tour to standard output
    public void show() {

        if (firstNode != null) {
            Node curNode = firstNode;
            do {
                StdOut.println(curNode.p.toString());
                curNode = curNode.next;
            } while (curNode != firstNode);
        }
    }

    //  inserts p using the nearest neighbor heuristic
    public void insertNearest(Point p) {
        double minDistance = Double.MAX_VALUE;
        if (firstNode == null) {
            firstNode = new Node(p);
            firstNode.next = firstNode;
        }

        Node curNode = firstNode;
        Node closedNode = firstNode;
        do {
            double newDis = curNode.p.distanceTo(p);
            if (newDis < minDistance) {
                closedNode = curNode;
                minDistance = newDis;
            }
            curNode = curNode.next;
        } while (curNode != firstNode);

//        Node nextNode = closedNode.next;
//        closedNode.next = new Node(p);
//        closedNode.next.next = nextNode;
        Node pNode = new Node(p);
        pNode.next = closedNode.next;
        closedNode.next = pNode;

    }

    //  inserts p using the smallest increase heuristic
    public void insertSmallest(Point p) {
        double minIncreaseDis = Double.MAX_VALUE;
        if (firstNode == null) {
            firstNode = new Node(p);
            firstNode.next = firstNode;
        }

        Node curNode = firstNode;
        Node preNode = firstNode;
        Node pNode = new Node(p);

        do {

            double deltaDistance = curNode.p.distanceTo(p) + p.distanceTo(curNode.next.p) -
                                        curNode.p.distanceTo(curNode.next.p);
            if (deltaDistance < minIncreaseDis) {
                minIncreaseDis = deltaDistance;
                preNode = curNode;
            }
            curNode = curNode.next;
        } while (curNode != firstNode);

        pNode.next = preNode.next;
        preNode.next = pNode;
    }

    // main method for testing
    public static void main(String[] args) {
        // define 4 points forming a square
        Point a = new Point(100.0, 100.0);
        Point b = new Point(500.0, 100.0);
        Point c = new Point(500.0, 500.0);
        Point d = new Point(100.0, 500.0);

        // Set up a Tour with those four points
        // The constructor should link a->b->c->d->a
        Tour squareTour = new Tour(a, b, c, d);

        // Output the Tour
        squareTour.show();
        System.out.println(squareTour.length());
        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 600);
        squareTour.draw();

        // test 2
        StdDraw.clear();
        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 600);

        double [] xc = {110.0, 161.0, 325.0, 490.0, 157.0, 283.0, 397.0, 306.0, 343.0, 552.0};
        double [] yc = {225, 280, 554.0, 285, 443.0, 379.0, 566.0, 360.0, 110.0, 199.0};

        Tour tenCityProblem = new Tour();
        System.out.println("Tour distance = " + tenCityProblem.length());

        for (int i = 0; i < xc.length; i++) {
            Point p = new Point(xc[i], yc[i]);
//            tenCityProblem.insertNearest(p);
            tenCityProblem.insertSmallest(p);
            System.out.println("Tour distance = " + tenCityProblem.length());
        }
        tenCityProblem.show();
        tenCityProblem.draw();


    }
}