/**
 * Created by ken on 11/21/2016 AD.
 */
public class GuitarHero {

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString GuitarString[];

        GuitarString = new GuitarString[keyboard.length()];
        for (int i = 0; i < GuitarString.length; i++) {
            GuitarString[i] = new GuitarString(440.0 * Math.pow(2, (i - 24) / 12.0));
        }

        // the main input loop
        while (true) {
            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();

                try {
                    // pluck the corresponding string
                    int keyIdx = keyboard.indexOf(key);
                    GuitarString[keyIdx].pluck();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(key + ": is not in keyboard");
                }
            }

            // compute the superposition of the samples
            double sample = 0;
            for (int i = 0; i < keyboard.length(); i++) {
                sample += GuitarString[i].sample();
            }
            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for (int i = 0; i < keyboard.length(); i++) {
                GuitarString[i].tic();
            }
        }
    }
}
