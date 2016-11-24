/**
 * Created by ken on 11/24/2016 AD.
 */
public class BeadTracker {

    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);

        int numPic = args.length;
        for (int p = 3; p < numPic-1; p++) {

            Picture curPic = new Picture(args[p]);
            BeadFinder curBF = new BeadFinder(curPic, tau);
            Blob[] beadsThis = curBF.getBeads(min);

            Picture nextPic = new Picture(args[p + 1]);
            BeadFinder nextBF = new BeadFinder(nextPic, tau);
            Blob[] beadsNext = nextBF.getBeads(min);

            for (int i = 0; i < beadsNext.length; i++) {
                double displacement = Double.POSITIVE_INFINITY;
                for (int j = 0; j < beadsThis.length; j++) {
                    double distance = beadsNext[i].distanceTo(beadsThis[j]);
                    if (distance <= displacement) {
                        displacement = distance;
                    }
                }
                if (displacement <= delta) {
                    System.out.printf("%.4f\n", displacement);
                }
            }
            System.out.println();
        }
        System.exit(0);
    }
}
