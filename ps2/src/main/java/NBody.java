import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ken on 11/12/2016 AD.
 */
public class NBody {

    private static final double G = 6.67e-11;
    private static double[] Fx;
    private static double[] Fy;
    private static double[] ax;
    private static double[] ay;
    private static double deltaTime;
    private static double stoppingTime;
    private static double time;
    private static double[] px;
    private static double[] py;
    private static double[] vx;
    private static double[] vy;
    private static double[] mass;
    private static String[] image;
    private static int N;

    private static void drawParticle() {
        StdDraw.clear();
        StdDraw.picture(0, 0, "starfield.jpg");

        for (int i = 0; i < N; i++) {
            StdDraw.picture(px[i], py[i], image[i]);
        }

        StdDraw.show();
        StdDraw.pause(20);

    }

    private static void calcuPos() {
        for (int i = 0; i < N; i++) {

            ax[i] = Fx[i] / mass[i];
            ay[i] = Fy[i] / mass[i];

            vx[i] = vx[i] + ax[i] * deltaTime;
            vy[i] = vy[i] + ay[i] * deltaTime;

            px[i] += vx[i] * deltaTime;
            py[i] += vy[i] * deltaTime;
        }
    }

    private static void calcuForce() {
        Fx = new double[N];
        Fy = new double[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    double dx = px[j] - px[i];
                    double dy = py[j] - py[i];
                    double r = Math.sqrt(dx * dx + dy * dy);
                    Fx[i] += G * mass[i] * mass[j] * (dx) / Math.pow(r, 3);
                    Fy[i] += G * mass[i] * mass[j] * (dy) / Math.pow(r, 3);

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        // read file
        stoppingTime = Double.parseDouble(args[0]);
        deltaTime = Double.parseDouble(args[1]);

        BufferedReader reader = new BufferedReader(new FileReader(args[2]));
        N = Integer.parseInt(reader.readLine());
        double R = Double.parseDouble(reader.readLine());

        for (int i = 0; i< N ; i++) {
            px = new double[N];
            py = new double[N];
            vx = new double[N];
            vy = new double[N];
            mass = new double[N];
            image = new String[N];
            Fx = new double[N];
            Fy = new double[N];
            ax = new double[N];
            ay = new double[N];
        }

        for (int i = 0; i< N; i++) {
            String line;
            line = reader.readLine();
            String[] parts = line.trim().split("\\s++");
            px[i] = Double.parseDouble(parts[0]);
            py[i] = Double.parseDouble(parts[1]);
            vx[i] = Double.parseDouble(parts[2]);
            vy[i] = Double.parseDouble(parts[3]);
            mass[i] = Double.parseDouble(parts[4]);
            image[i] = parts[5];
        }

        StdDraw.setXscale(-R, +R);
        StdDraw.setYscale(-R, +R);
        StdDraw.enableDoubleBuffering();

        while (time < stoppingTime) {

            /*
            Step 1: for each particle: Calculate the net force(Fx, Fy) at the current time t acting on
            that particle using Newton's law of gravitation and the principle of superposition.
             */

            calcuForce();

            /*
            Step 2. For each particle: Calculate the net force(Fx, Fy) at the current time acting on
            that particle using Newton's law of gravitation and the principle of superposition.
             */
            calcuPos();
            /*
            Step 3. For each particle, draw it using the position computed in step 2.
             */
            drawParticle();
            time += deltaTime;
        }

        System.out.printf("%d\n", N);
        System.out.printf("%.2e\n", R);

        for (int i = 0; i < N; i++) {
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                                px[i],py[i],vx[i], vy[i], mass[i], image[i]);
        }
        System.exit(0);


    }

}
