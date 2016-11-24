/**
 * Created by ken on 11/24/2016 AD.
 */
public class Avogadro {

    public static void main(String[] args) {

        double coeff = 0.175E-6;
        double sum = 0;
        int n = 0;

        String file = args[0];
        In in = new In(file);
        while (!in.isEmpty()) {
            double dr = in.readDouble();
            sum += dr * dr;
            n += 1;
        }

        //compute the self-diffusion constant D
        double D = sum * Math.pow(coeff, 2) / (2.0 * n);
        double T = 297;
        double etta = 9.132E-4;
        double radius = 0.5E-6;
        double R = 8.31457;       // universal gas constant, J * K-1 * mol-1

        // compute the Boltzmann constant, k
        double k = D * 6 * Math.PI * etta * radius / T;
        // compute the Avogadro's number, Na
        double Na = R / k;

        System.out.printf("Boltzmann = %.4e\n", k);
        System.out.printf("Avogadro = %.4e\n", Na);
    }
}
