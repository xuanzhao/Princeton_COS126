import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by ken on 11/20/2016 AD.
 */
public class Decoder {
    // return an integer corresponding to the 4-digit hex string
    public static int fromHex(String s) {
        return Integer.parseInt(s, 16) & 0xFFFF;
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(new File(args[0]));

        while (file.hasNext()) {
            String line = file.nextLine();
            String[] parts = line.split("\\s+");
            int m1 = fromHex(parts[0]);
            if (m1 == 0xFFFF) {
                break;
            }
            int m2 = fromHex(parts[1]);
            int m3 = fromHex(parts[2]);
            int m4 = fromHex(parts[3]);
            int p1 = fromHex(parts[4]);
            int p2 = fromHex(parts[5]);
            int p3 = fromHex(parts[6]);


            // check parity bits
            int c1 = p1 ^ m1 ^ m2 ^ m4;
            int c2 = p2 ^ m1 ^ m3 ^ m4;
            int c3 = p3 ^ m2 ^ m3 ^ m4;

            // flip bits if necessary
            if (c1 + c2 + c3 == 3) m4 = 1 ^ m4;
            else if (c1 + c2 == 2) m1 = 1 ^ m1;
            else if (c1 + c3 == 2) m2 = 1 ^ m2;
            else if (c2 + c3 == 2) m3 = 1 ^ m3;


            System.out.printf("%04d\n%04d\n%04d\n%04d\n", m1, m2 , m3 ,m4);
        }
    }

}
