/**
 * Created by ken on 11/12/2016 AD.
 */
public class Checkboard {

    public static void main(String[] args) {
        int row = Integer.parseInt(args[0]);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (i % 2 == 0) {
                    System.out.printf("* ");
                } else {
                    System.out.printf(" *");
                }
            }
            System.out.println();
        }
    }
}
