/*****************************************************************************
 *  Compilation:  javac AutoGuitar.java
 *  Execution:    java  AutoGuitar
 *  Dependencies: StdAudio.java GuitarString.java RingBuffer.java
 *  Description  : program automatically plays a song with GuitarStrings
 *
 *  % java-introcs AutoGuitar
 *
 ****************************************************************************/

public class AutoGuitar {

    public static void main(String[] args) {

        // Set duration of the song in seconds
        double duration = 10.0;

        // Create two guitar strings, for concert A and C
        double CONCERT_A = 440.0;

        // use the following 37 keys to represent the keyboard, from lowest
        // note to highest note:
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        int keyboardSize = keyboard.length();

        // create an array of size = 37 GuitarString objects
        GuitarString guitarStrings[] = new GuitarString[keyboardSize];

        for (int i = 0; i < keyboardSize; i++) {
            double frequency = CONCERT_A * Math.pow(2, (i - 24) / 12.0);
            guitarStrings[i] = new GuitarString(frequency);
            // System.out.println(frequency + ": " + i);
        }

        //String song = "nn//  /0..,,mmn0//..,,m0//..,,m0nn//  /0..,,mmn";
        String song = "i p z v b z p b n z p n d [ i d z p i p z p i u i i";

        double[] audio;

        int songSize = song.length();

        // determine number of samples for each note
        int N = (int) (StdAudio.SAMPLE_RATE * duration / songSize);

        // use for-loop to iterate through notes of the song
        for (int i = 0; i < songSize; i++) {

            // determine current character in the song
            char key = song.charAt(i);

			/*
			 * use try-and-catch to make sure that program does not crash if a
			 * key is pressed that does not correspond to one of notes of the
			 * song
			 */

            try {
                // use keyboard.indexOf(key) to figure out which key was typed
                int index = keyboard.indexOf(key);
                System.out.println(key + ": " + keyboard.indexOf(key));
                // pluck the corresponding string
                guitarStrings[index].pluck();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(key + ": is not in keyborad");
            }

            // play samples for a given note for the given duration
            int n = 0;
            while (n < N) {

                double sample = 0;
                for (int j = 0; j < keyboardSize; j++) {
                    sample += guitarStrings[j].sample();
                }

                // play the current sample for a given node
                StdAudio.play(sample);

                for (int j = 0; j < keyboardSize; j++) {
                    guitarStrings[j].tic();
                }

                n++;
            }


        }

        // needed to terminate program - known Java bug
        System.exit(0);

    }

}