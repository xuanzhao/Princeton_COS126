import java.awt.Color;

public class PhotoMagic {

    // Returns a transformed copy of the specified picture, using the specified LFSR.
    public static Picture transform(Picture picture, LFSR lfsr) {
        Picture newPic = picture;

        int col = newPic.width();
        int row = newPic.height();

        for (int c = 0; c < col; c++) {
            for (int r = 0; r < row; r++) {
                Color color = newPic.get(c, r);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                red = (red ^ lfsr.generate(8));
                green = (green ^ lfsr.generate(8));
                blue = (blue ^ lfsr.generate(8));

                newPic.set(c, r, new Color(red, green, blue));
            }
        }
        return newPic;
    }

    // Takes the name of an image file and
    // a description of an LFSR as command-line arguments;
    // displays a copy of the image that is "encrypted" using the LFSR.
    public static void main(String[] args) throws InterruptedException {

        Picture pic = new Picture(args[0]);
        String seed = args[1];
        int tapPos = Integer.parseInt(args[2]);

        LFSR lfsr = new LFSR(seed.trim(), tapPos);
        pic.show();
        pic = transform(pic, lfsr);
        pic.show();

//        LFSR lfsr = new LFSR("01101000010100010000", 16);
//        Picture pic = new Picture("baboon-blue.png");
//
//        pic.show();
//        pic = transform(pic, lfsr);
//        pic.show();

    }
}