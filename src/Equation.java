import java.util.Scanner;

public class Equation {
    public static void Quadratic(double i,double j,double k) {
        Scanner scn = new Scanner(System.in);
        double a, b, c, x1, xx1, x2, xx2, delta;
        try {
            System.out.println("ax^2+bx+c. Enter the value of a, b, c.");
            a = i;
            b = j;
            c = k;

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

    public static void cubic(double i,double j,double k,double l) {
        double a, b, c, d, delta, p, q, x1, xx1, x2, xx2, x3, xx3, v, v1, v2, v3, v4;
        Scanner scn = new Scanner(System.in);
        a = i;
        b = j;
        c = k;
        d = l;
        p = (3 * a * c - b * b) / (3 * a * a);
        q = (2 * b * b * b - 9 * a * b * c + 27 * a * a * d) / (27 * a * a* a);
        delta = (27 * q * q + 4 * p * p * p) / 27;
        if (a == 0) {
            System.out.println("Invalid equation.");
        } else {
            if (delta > 0) {
                v = 0.5*(Math.sqrt(delta)-q);
                v1 = (v >= 0) ? (Math.exp(Math.log(Math.abs(v)) / 3)) : (-Math.exp(Math.log(Math.abs(v)) / 3));
                x1 = (3 * a * v1 * v1 - a * p - b * v1) / (3 * a * v1);
                x2 = -(b + a * x1) / (2 * a);
                xx2 = (Math.sqrt(4 * a * (c + b * x1 + a * x1 * x1) - (b + a * x1) * (b + a * x1))) / (2 * Math.abs(a));
                System.out.print("x1 = ("+x1+")\nx2 = ("+x2+") - ("+xx2+")i\nx3 = ("+x2+") + ("+xx2+")i\n");
            }
            else if(delta<0){
                double val = 0.5 * Math.sqrt(Math.abs(delta));
                double a1 = (-q / 2) * (-q / 2) + val * val;
                double a2 = ((val >= 0) ? (Math.acos((-q / 2) / Math.sqrt(a1))) : (-Math.acos((-q / 2) / Math.sqrt(a1)))) / 3;
                v = Math.exp(Math.log(Math.sqrt(a1)) / 3) * Math.cos(a2);
                v1 = Math.exp(Math.log(Math.sqrt(a1)) / 3) * Math.sin(a2);
                x1 = ((3 * v * v * v + 3 * v * v1 * v1 - p * v) / (3 * v * v + 3 * v1 * v1)) - (b / (3 * a));
                x2 = (-b - a * x1 + Math.sqrt((b + a * x1) * (b + a * x1) - 4 * a *(c + b * x1 + a * x1 * x1))) / (2 * a);
                x3 = (-b - a * x1 - Math.sqrt((b + a * x1) * (b + a * x1) - 4 * a *(c + b * x1 + a * x1 * x1))) / (2 * a);
                System.out.print("x1 = (" + x1 + ") \nx2 = (" + x2 + ") \nx3 = (" + x3 + ") \n");
            }
            else if(delta==0){
                v=-b;
                v1=3*a;
                x1=v/v1;
                x2=x1;
                x3=x2;
                System.out.print("x1 = (" + x1 + ") \nx2 = (" + x2 + ") \nx3 = (" + x3 + ") \n");
            }
        }
    }
    public static void Ternary_Linear(){
        Scanner scn = new Scanner(System.in);
        int i=1;
        double x,y,z;
        double a1,b1,c1,d1,a2,b2,c2,d2,a3,b3,c3,d3;
        a1=scn.nextDouble();
        b1=scn.nextDouble();
        c1=scn.nextDouble();
        d1=scn.nextDouble();
        a2=scn.nextDouble();
        b2=scn.nextDouble();
        c2=scn.nextDouble();
        d2=scn.nextDouble();
        a3=scn.nextDouble();
        b3=scn.nextDouble();
        c3=scn.nextDouble();
        d3=scn.nextDouble();
        x=((d1*c2-d2*c1)*(b2*c3-b3*c2)-(d2*c3-d3*c2)*(b1*c2-b2*c1))/((a1*c2-a2*c1)*(b2*c3-b3*c2)-(a2*c3-a3*c2)*(b1*c2-b2*c1));
        y=((d1*c2-d2*c1)*(a2*c3-a3*c2)-(d2*c3-d3*c2)*(a1*c2-a2*c1))/((a2*c3-a3*c2)*(b1*c2-b2*c1)-(a1*c2-a2*c1)*(b2*c3-b3*c2));
        z=(d1-a1*x-b1*y)/c1;
        System.out.println();

    }
    public static void main(String[] args){
        cubic(1,12,60,48);
    }
}
