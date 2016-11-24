public class Sierpinski {

    //  Height of an equilateral triangle whose sides are of the specified length.
    public static double height(double length) {
        return (length * Math.sqrt(3)) / 2;
    }

    //  Draws a filled equilateral triangle whose bottom vertex is (x, y)
    //  of the specified side length.
    public static void filledTriangle(double x, double y, double length) {
        double[] xs = new double[3];
        double[] ys = new double[3];

        double height = height(length);
        double topY = y + height;

        xs[0] = x - 0.5 * length;
        xs[1] = x + 0.5 * length;
        xs[2] = x;
        ys[0] = topY;
        ys[1] = topY;
        ys[2] = y;


        StdDraw.filledPolygon(xs, ys);
    }

    //  Draws a Sierpinski triangle of order n, such that the largest filled
    //  triangle has bottom vertex (x, y) and sides of the specified length.
    public static void sierpinski(int n, double x, double y, double length) {
        if (n == 0) return;
        filledTriangle(x, y, length);

        double newLength = length * 0.5;

        sierpinski(n-1, x - newLength, y, newLength);
        sierpinski(n-1, x + newLength, y, newLength);
        sierpinski(n-1, x, y + height(length), newLength);
    }

    //  Takes an integer command-line argument n;
    //  draws the outline of an equilateral triangle (pointed upwards) of length 1;
    //  whose bottom-left vertex is (0, 0) and bottom-right vertex is (1, 0); and
    //  draws a Sierpinski triangle of order n that fits snugly inside the outline.
    public static void main(String[] args) {
        //filledTriangle(0.5, 0.86, 1.0);
        int n = Integer.parseInt(args[0]);

        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        double s = 1.0;
        StdDraw.setXscale(-0.1 * s, 0.1 * s + s);
        StdDraw.setYscale(-0.1 * s, 0.45 * s + Math.cos(Math.PI / 3) * s);
        double xc[] = {0 , Math.cos(Math.PI / 3) * s, Math.cos(0) * s};
        double yc[] = {0 , Math.sin(Math.PI / 3) * s, Math.sin(0) * s};
        StdDraw.filledPolygon(xc, yc);

        // Set the pen color for drawing Sierpinski triangles
        StdDraw.setPenColor(StdDraw.BLACK);
        sierpinski(n, s * 0.5, 0, s * 0.5);
    }
}