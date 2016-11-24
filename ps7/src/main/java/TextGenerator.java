import java.io.*;

/*****************************************************************************
 *  Compilation:   javac TextGenerator.java
 *  Execution:     java  TextGenerator k T < file.txt
 *  Dependencies:  MarkovModel.java; StdIn.java; StdOut.java
 *  Description  : program represent a Markov model of order k from a given
 *                 text string
 *
 *  % java-introcs TextGenerator 2 3 < input17.txt
 *
 *  // ask the Java Virtual Machine for more main memory beyond the default
 *  % java-introcs -Xmx500m TextGenerator 7 1000 < input.txt
 *
 ****************************************************************************/

public class TextGenerator {

    public static void main(String[] args) throws IOException {
        int k = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

//        String text = "gagggagaggcgagaaa";
        String file = args[2];
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String data;
        while ((data = reader.readLine()) != null) {
            sb.append(data);
        }
        reader.close();

        String text = sb.toString();
        if (text.length() < k) {
            throw new RuntimeException("text length is less than kgram");
        }
        if (k > T) {
            throw new RuntimeException("k > T");
        }

        String kgram = text.substring(0, k);
        StringBuilder textBuilder = new StringBuilder(kgram);
        MarkovModel model = new MarkovModel(text, k);

        for (int i = 0; i < T; i++) {
            char newChar = model.random(kgram);
            textBuilder.append(newChar);
            kgram = kgram.substring(1) + newChar;
        }
        System.out.println(textBuilder.toString());
    }
}
