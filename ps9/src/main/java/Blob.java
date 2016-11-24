/**
 * Created by ken on 11/24/2016 AD.
 */
public class Blob {

    private int numPix;
    private double xcord;
    private double ycord;

    //  creates an empty blob
    public Blob() {
        numPix = 0;
        xcord = 0;
        ycord = 0;
    }

    //  adds pixel (x, y) to this blob
    public void add(int x, int y) {
        xcord = (xcord * numPix + x) / (numPix + 1);
        ycord = (ycord * numPix + y) / (numPix + 1);
        numPix += 1;
    }

    //  returns the number of pixels added to this blob
    public int mass() {
        return numPix;
    }

    //  returns the Euclidean distance between the center of masses of the two blobs
    public double distanceTo(Blob that) {
        return Math.sqrt(Math.pow((this.xcord - that.xcord), 2) + Math.pow((this.ycord - that.ycord), 2));
    }

    //  returns a string representation of this blob (see below)
    public String toString() {
        return String.format("%2d (%8.4f, %8.4f)", numPix, xcord, ycord);
    }

    //  unit tests all methods in the Blob data type
    public static void main(String[] args) {

    }
}
