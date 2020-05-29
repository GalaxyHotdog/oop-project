import java.util.Scanner;

public class Equation {
    public static void Quadratic(String[] args) {
        Scanner scn = new Scanner(System.in);
        double a, b, c, x1, xx1, x2, xx2, delta;
        try {
            System.out.println("ax^2+bx+c. Enter the value of a, b, c.");
            a = scn.nextDouble();
            b = scn.nextDouble();
            c = scn.nextDouble();

            delta = b * b - 4 * a * c;
            if (delta >= 0) {
                x1 = (-b + Math.sqrt(delta)) / (2 * a);
                x2 = (-b - Math.sqrt(delta)) / (2 * a);
                System.out.println(x1 + "\n" + x2);
            } else if (delta < 0) {
                delta = Math.abs(delta);
                x1 = -b / (2 * a);
                x2 = -b / (2 * a);
                xx1 = Math.sqrt(delta) / (2 * a);
                xx2 = -Math.sqrt(delta) / (2 * a);
                System.out.println("(" + x1 + ")" + " + (" + xx1 + ")i");
                System.out.println("(" + x2 + ")" + " + (" + xx2 + ")i");
            }
        } catch (Exception e) {
            System.out.println("Input Invalid.");
        }
    }

    public static void cubic(String[] args) {
        double a, b, c, d, delta, p, q, x1, xx1, x2, xx2, x3, xx3, v, v1, v2;
        Scanner scn = new Scanner(System.in);
        a = scn.nextDouble();
        b = scn.nextDouble();
        c = scn.nextDouble();
        d = scn.nextDouble();
        p = (3 * a * c - b * b) / (3 * a * a);
        q = (2 * b * b * b - 9 * a * b * c + 27 * a * a * d) / (27 * a * a* a);
        delta = (27 * p * p + 4 * q * q * q) / 27;
        if (a == 0) {
            System.out.println("Invalid equation.");
        } else {
            if (delta > 0) {
                v = 0.5*(Math.sqrt(delta)-q);
            }
        }
    }
}
