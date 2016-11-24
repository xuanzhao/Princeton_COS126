/**
 * Created by ken on 11/12/2016 AD.
 */
public class RandomWalker {

    private static double calcSquaredDis(int x, int y) {
        return Math.pow(x, 2) + Math.pow(y, 2);
    }

    private static double oneTrial(int move, int x, int y) {
        for (int i = 0; i < move; i++) {
            double p = Math.random();
            if (p < 0.25) {
                x += 1;
            } else if (p < 0.5) {
                x -= 1;
            } else if (p < 0.75) {
                y -= 1;
            } else {
                y += 1;
            }
        }
        return calcSquaredDis(x, y);
    }

    public static void main(String[] args) {
        int move = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        int x = 0, y = 0;
        double result = 0;
        for (int i = 0; i < trials; i++) {
            result += oneTrial(move, x, y);
        }
        System.out.println("mean squared distance = " + result / trials);

    }
}
