import java.util.Scanner;

public class CubicEq {
    public static void main(String[] args) {
        double a, b, c, d, delta, p, q, x1, xx1, x2, xx2, x3, xx3, v, v1, v2;
        Scanner scn = new Scanner(System.in);
        a = scn.nextDouble();
        b = scn.nextDouble();
        c = scn.nextDouble();
        d = scn.nextDouble();
        p = (3 * a * c - b * b) / (3 * a * a);
        q = (2 * b * b * b - 9 * a * b * c + 27 * a * a * d) / (27 * a * a * a);
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
