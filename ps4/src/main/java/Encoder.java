/**
 * Created by ken on 11/20/2016 AD.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Encoder {

    // return an integer corresponding to the 4-digit hex string
    private static int fromHex(String s) {
        return Integer.parseInt(s, 16) & 0xFFFF;
    }

    public static void main(String[] args) throws FileNotFoundException {

        // load a file
        Scanner file = new Scanner(new File(args[0]));

        while (file.hasNext()) {
            String line = file.nextLine();
            String[] ms = line.split("\\s+");
            int m1 = fromHex(ms[0]);
            if (m1 == 0xFFFF) break;
            int m2 = fromHex(ms[1]);
            int m3 = fromHex(ms[2]);
            int m4 = fromHex(ms[3]);

            // compute partity bits
            int p1 = m1 ^ m2 ^ m4;
            int p2 = m1 ^ m3 ^ m4;
            int p3 = m2 ^ m3 ^ m4;

            System.out.printf("%04d\n%04d\n%04d\n%04d\n", m1, m2, m3, m4);
            System.out.printf("%04d\n%04d\n%04d\n", p1, p2, p3);
        }
        file.close();

    }
}
