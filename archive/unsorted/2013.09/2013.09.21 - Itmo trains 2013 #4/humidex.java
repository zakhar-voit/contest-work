package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;

public class humidex {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String inp = in.nextToken();

        while (!inp.equals("E")) {
            char t1 = inp.charAt(0);
            double a1 = in.nextDouble();
            char t2 = in.nextToken().charAt(0);
            double a2 = in.nextDouble();

            if (t1 > t2) {
                char bufC = t1;
                t1 = t2;
                t2 = bufC;

                double bufD  = a1;
                a1 = a2;
                a2 = bufD;
            }

            if (t1 == 'D' && t2 == 'T') {
                out.print("T " + a2);
                out.print(" D " + a1);
                out.println(" H " + calcHumidex(a2, a1));
            } else if (t1 == 'D' && t2 == 'H') {
                out.print("T " + calcTemperature(a2, a1));
                out.print(" D " + a1);
                out.println(" H " + a2);
            } else if (t1 == 'H' && t2 == 'T') {
                out.print("T " + a2);
                out.print(" D " + calcDewPoint(a2, a1));
                out.println(" H " + a1);
            } else
                throw new RuntimeException();

            inp = in.nextToken();
        }
    }

    double calcDewPoint(double temperature, double humindex) {
        double h = humindex - temperature;
        double e = h / (0.5555) + 10.0;
        double exp = e / 6.11;
        double shit = Math.log(exp);
        double C1 = 5417.7530;
        double C3 = 273.16;
        double C2 = 1 / C3;

        double y = shit / C1;

        return roundToTenth((C3 * C2 - 1 - y * C3) / (y - C2));
    }

    double roundToTenth(double x) {
        return Math.round(x * 10) / 10.0;
    }

    double getE(double d) {
        return 6.11 * Math.exp(5417.7530 * ((1 / 273.16) - (1 / (d + 273.16))));
    }

    double getH(double d) {
        return 0.5555 * (getE(d) - 10.0);
    }

    double calcHumidex(double t, double d) {
        return roundToTenth(t + getH(d));
    }

    double calcTemperature(double h, double d) {
        return roundToTenth(h - getH(d));
    }
}
