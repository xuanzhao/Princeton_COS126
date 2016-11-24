public class MarkovModel {

    private ST<String, ST<Character, Integer>> st;
    private ST<Character, Integer> stValue;

    private int kOrder;
    private String circleText;

    // creates a Markov model of order k for the specified text
    public MarkovModel(String text, int k) {
        circleText = text + text.substring(0, k);
        kOrder = k;
        String kgram;
        st = new ST<String, ST<Character, Integer>>();

        for (int i = 0; i < text.length(); i++) {
            kgram = circleText.substring(i, i + k);
            char nextChar = circleText.charAt(i + k);

            if (st.contains(kgram)) {
                int newValue = st.get(kgram).get(nextChar) + 1;
                st.get(kgram).put(nextChar, newValue);

            } else {
                stValue = new ST<Character, Integer>();
                for (int j = 0; j < 128; j++) {
                    if (j == (int) nextChar) {
                        stValue.put((char) j, 1);
                    } else {
                        stValue.put((char) j, 0);
                    }
                }
                st.put(kgram, stValue);
            }
        }
    }

    // returns the order k of this Markov model
    public int order() {
        return kOrder;
    }

    // returns the number of times the specified kgram appears in the text
    public int freq(String kgram) {
        if (kgram.length() != kOrder) {
            throw new IllegalArgumentException("kgram is not consist with order k");
        }

        if (!st.contains(kgram)) {
            return 0;
        }
        stValue = st.get(kgram);
        int sum = 0;
        for (Character c : stValue.keys()) {
            sum += stValue.get(c);
        }

        return sum;
    }

    // returns the number of times the character c follows the specified
    // kgram in the text
    public int freq(String kgram, char c) {
        if (kgram.length() != kOrder) {
            throw new IllegalArgumentException("kgram is not consist with order k");
        }
        if (!st.contains(kgram)) {
            return 0;
        }
        return st.get(kgram).get(c);
    }

    // returns a random character that follows the specified kgram in the text,
    // chosen with weight proportional to the number of times that character
    // follows the specified kgram in the text
    public char random(String kgram) {

        if (kgram.length() != kOrder) {
            throw new IllegalArgumentException("kgram is not consist with order k");
        }
        if (!st.contains(kgram)) {
            throw new RuntimeException("kgram is not exist");
        }
        stValue = st.get(kgram);

        int[] freqs = new int[128];
        for (Character c : stValue.keys()) {
            freqs[(int) c] = stValue.get(c);
        }

        return (char) StdRandom.discrete(freqs);
    }

    // unit tests this class
    public static void main(String[] args) {

        // test freq
        String text1 = "banana";
        MarkovModel model1 = new MarkovModel(text1, 2);
        StdOut.println("freq(\"an\", 'a')    = " + model1.freq("an", 'a'));
        StdOut.println("freq(\"na\", 'b')    = " + model1.freq("na", 'b'));
        StdOut.println("freq(\"na\", 'a')    = " + model1.freq("na", 'a'));
        StdOut.println("freq(\"na\")         = " + model1.freq("na"));
        StdOut.println();

        String text2 = "one fish two fish red fish blue fish";
        MarkovModel model2 = new MarkovModel(text2, 4);
        StdOut.println("freq(\"ish \", 'r') = " + model2.freq("ish ", 'r'));
        StdOut.println("freq(\"ish \", 'x') = " + model2.freq("ish ", 'x'));
        StdOut.println("freq(\"ish \")      = " + model2.freq("ish "));
        StdOut.println("freq(\"tuna\")      = " + model2.freq("tuna"));

        /* test rand() method
         * write a loop to call rand() many times and count how many times
         * a particular character is returned. (e.g., With mod1 above,
         * rand("na") should generate 'b' half of the time. */
        int count = 0;
        for (int i = 0; i < 20; i++) {
            if ('b' == model1.random("na")) {
                count +=1;
            }
        }
        StdOut.println("na has 'b' : " + count / 20.0);

        count = 0;
        for (int i = 0; i < 20; i++) {
            if ('o' == model2.random("fish")) {
                count +=1;
            }
        }
        StdOut.println("fish has 'o' : " + count/ 20.0);
    }

}