import java.util.Scanner;

public class QuadraticEq {
    public static void main(String[] args) {
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
}
