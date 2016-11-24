import java.awt.*;
import java.util.*;

/**
 * Created by ken on 11/24/2016 AD.
 */
public class BeadFinder {

    private static ArrayList<Blob> blobList = new ArrayList<Blob>();

    //  finds all blobs in the specified picture using luminance threshold tau
    public BeadFinder(Picture picture, double tau) {
        int height = picture.height();
        int width = picture.width();
        boolean[][] separated = new boolean[width][height];

        for (int col = 0; col < width; col ++) {
            for (int row = 0; row < height; row ++) {
                Color color = picture.get(col, row);
                double lum = Luminance.lum(color);
                if (Double.compare(lum, tau) >= 0) {
                    separated[col][row] = true;
                } else {
                    separated[col][row] = false;
                }
            }
        }
        findBlobs(separated);
    }

    private void dfs(Blob bolb, boolean[][] separated, boolean[][] visited, int i, int j) {

        int width = separated.length;
        int height = separated[0].length;

        if (i < 0 || i >= width) {
            return;
        }
        if (j < 0 || j >= height) {
            return;
        }
        if (visited[i][j]) return;
        if (!separated[i][j]) {
            visited[i][j] = true;
            return;
        }

        bolb.add(i, j);
        visited[i][j] = true;

        dfs(bolb, separated, visited, i, j - 1);
        dfs(bolb, separated, visited, i, j + 1);
        dfs(bolb, separated, visited, i - 1, j);
        dfs(bolb, separated, visited, i + 1, j);
    }

    // call dfs from each unmarked pixel(x,y)
    private void findBlobs(boolean[][] separated) {
        int height = separated[0].length;
        int width = separated.length;

        boolean[][] visited = new boolean[width][height];

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                if (!visited[col][row]) {
                    if (separated[col][row]) {
                        Blob b = new Blob();
                        dfs(b, separated, visited, col, row);
                        blobList.add(b);
                    }
                    else {
                        visited[col][row] = true;
                    }
                }
            }
        }
    }

    private int countBeads(int minPixels) {
        int num = 0;
        for (Blob b : blobList) {
            if (b.mass() >= minPixels) {
                num += 1;
            }
        }
        return num;
    }

    //  returns all beads (blobs with >= min pixels)
    public Blob[] getBeads(int min) {
        int num = countBeads(min);
        Blob[] blobs = new Blob[num];

        int i = 0;
        for (Blob b : blobList) {
            if (b.mass() >= min) {
                blobs[i] = b;
                i += 1;
            }
        }

        return blobs;
    }

    //  unit tests the BeadFinder data type, as described below
    public static void main(String[] args) {
        Picture pic = new Picture("frame00001.jpg");
        BeadFinder blobfinder = new BeadFinder(pic, 180.0);

        int min = 25;
        Blob[] blobs = blobfinder.getBeads(min);
        System.out.println(blobfinder.countBeads(min) + "Beads");
        for (Blob blob : blobs) {
            System.out.println(blob.toString());
        }

        Blob[] beads = blobfinder.getBeads(0);
        System.out.println(blobfinder.countBeads(0) + "Beads");
        for (Blob blob : beads) {
            System.out.println(blob.toString());
        }
    }
}