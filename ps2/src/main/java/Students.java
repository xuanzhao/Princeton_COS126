import java.io.*;
import java.util.Scanner;

/******************************************************************************
 *  Compilation:  javac Students.java
 *  Execution:    java Students < students.txt
 *  Dependencies: StdIn.java
 *  Sample data:  http://introcs.cs.princeton.edu/15inout/students.txt
 *
 *  Reads in the integer n from standard input, then a list
 *  of n student records, where each record consists of four
 *  fields, separated by whitespace:
 *      - first name
 *      - last name
 *      - email address
 *      - which section they're in
 *
 *  Then, print out a list of email address of students in section 4 and 5.
 *
 *  % java Students 130 < students.txt
 *  Sarah Wang twang 7
 *  Austin Taylor actaylor 1
 *  David Rosner drosner 4
 *  Rebecca Allen rebeccaa 7
 *  Rajiv Ayyangar ayyangar 7
 *  Daniel Barrett drbarret 8
 *  Nic Byrd nbyrd 7
 *  Emily Capra ecapra 8
 *  Johnny Clore jclore 7
 *  ...
 *
 * % Section 4
 *  ---------
 *  drosner
 *  jcharles
 *  jph
 *  mlampert
 *  ...
 *
 *  Section 5
 *  ---------
 *  giwank
 *  agrozdan
 *  ajh
 *  akornell
 *  ...
 *
 ******************************************************************************/


public class Students {

    public static void main(String[] args) throws IOException {


        //String file = StdIn.readString();
        File file = new File("src/main/resources/students.txt");
        System.out.println(file.isFile());
        System.out.println(file.exists());
        file.getClass();
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        FileReader rfile = new FileReader(file);

        BufferedReader reader = new BufferedReader(rfile);
        //String file = StdIn.readString();
        //

        // read the number of students
        int n = Integer.parseInt(reader.readLine());
        // initialize four parallel arrays
        String[] first = new String[n];
        String[] last  = new String[n];
        String[] email = new String[n];
        int[] section  = new int[n];


        // read in the data
        for (int i = 0; i < n; i++) {
            String line;
            line = reader.readLine();
            String[] parts = line.split("\\s");
            first[i] = parts[0];
            last[i] = parts[1];
            email[i] = parts[2];
            section[i] = Integer.parseInt(parts[3]);

        }

        // print email addresses of all students in section 4
        StdOut.println("Section 4");
        StdOut.println("---------");
        for (int i = 0; i < n; i++) {
            if (section[i] == 4) {
                StdOut.println(email[i]);
            }
        }
        StdOut.println();

        // print email addresses of all students in section 5
        StdOut.println("Section 5");
        StdOut.println("---------");
        for (int i = 0; i < n; i++) {
            if (section[i] == 5) {
                StdOut.println(email[i]);
            }
        }
    }

}